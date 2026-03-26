package io.ulysse.ulysseIndepBack.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.ulysse.ulysseIndepBack.entities.DTO.UserDTO;
import io.ulysse.ulysseIndepBack.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping(name = "User administration")
public class UserController {

    private UserService userservice;
    static final String FRONT = "*";

    @Autowired
    public UserController(final UserService userservice) {
        this.userservice = userservice;
    }


    @GetMapping("/user")
    @SecurityRequirement(name = "BearerAuthentication")
    @ResponseBody
    public UserDTO getUserByID(@AuthenticationPrincipal Jwt jwt) {
        String userUuid = jwt.getClaimAsString("sub"); // généralement le "sub" contient l'ID unique Auth0
        return this.userservice.getUserByID(userUuid);
    }

    @SecurityRequirement(name = "BearerAuthentication")
    @PostMapping(value = "/user/create")
    public void createUser(@RequestBody UserDTO createDTO, @AuthenticationPrincipal Jwt jwt) {
        String userUuid = jwt.getClaimAsString("sub"); // généralement le "sub" contient l'ID unique Auth0
        createDTO.setUserUUID(userUuid);
        this.userservice.createUser(createDTO);
    }

    @SecurityRequirement(name = "BearerAuthentication")
    @PostMapping(value = "/user/update")
    public void updateUser(@RequestBody UserDTO userDTO, @AuthenticationPrincipal Jwt jwt) {
        String userUuid = jwt.getClaimAsString("sub"); // généralement le "sub" contient l'ID unique Auth0
        userDTO.setUserUUID(userUuid);
        if(!this.userservice.updateCurrentUser(userDTO)){;
            throw new RuntimeException("Unable to update user with UUID: " + userUuid);
        }

    }

}
