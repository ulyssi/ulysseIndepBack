package io.ulysse.ulysseIndepBack.entities.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    private String userUUID;
    private String userName;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
    private String city;
    private String zipCode;
    private String address;
    private String email;

    public UserDTO() {}
}

