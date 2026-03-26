package io.ulysse.ulysseIndepBack.services.impl;

import io.ulysse.ulysseIndepBack.dao.rest.AuthUserDao;
import io.ulysse.ulysseIndepBack.entities.DTO.UserDTO;
import io.ulysse.ulysseIndepBack.entities.Entities.EntitiesUser;
import io.ulysse.ulysseIndepBack.entities.Entities.EntitiesUserHistory;
import io.ulysse.ulysseIndepBack.entities.mapper.UserMapper;
import io.ulysse.ulysseIndepBack.repositories.UserHistoryRepository;
import io.ulysse.ulysseIndepBack.repositories.UserRepository;
import io.ulysse.ulysseIndepBack.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private AuthUserDao auth0UserDao;

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public UserDTO getUserByID(String uuid) {
        Map<String, Object> user = auth0UserDao.getUserById(uuid);

        UserDTO userDTO = this.userRepository.findByuserUUID(uuid)
                .map(userMapper::toDTO)
                .orElse(null);
        if (userDTO != null && user != null)
            userDTO.setEmail((String) user.get("email"));
        return userDTO;
    }

    @Override
    public boolean createUser(UserDTO createDTO) {
        if (this.userRepository.existsByuserUUID(createDTO.getUserUUID())) {
            LOGGER.warning("User with UUID " + createDTO.getUserUUID() + " already exists.");
            return false;
        }

        EntitiesUser user = userMapper.toEntity(createDTO);

        // --- Initialisation des dates et user ---
        Timestamp now = new Timestamp(System.currentTimeMillis());

        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        Integer systemUserId = 0; // ou un user admin dédié
        user.setCreatedBy(systemUserId);
        user.setUpdatedBy(systemUserId);

        // Sauvegarde
        userRepository.save(user);
        LOGGER.info("User created with UUID: " + createDTO.getUserUUID());
        return true;
    }

    @Override
    public boolean updateCurrentUser(UserDTO updateDTO) {
        Optional<EntitiesUser> existingUserOpt = userRepository.findByuserUUID(updateDTO.getUserUUID());
        if (existingUserOpt.isEmpty()) {
            LOGGER.warning("User with UUID " + updateDTO.getUserUUID() + " doesn't exist.");
            createUser(updateDTO);
            return true;
        }

        EntitiesUser existingUser = existingUserOpt.get();

        // --- Archiver l'état actuel ---
        EntitiesUserHistory history = EntitiesUserHistory.builder()
                .userId(existingUser.getId())
                .userUUID(existingUser.getUserUUID())
                .userName(existingUser.getUserName())
                .firstName(existingUser.getFirstName())
                .lastName(existingUser.getLastName())
                .birthDate(existingUser.getBirthDate())
                .phoneNumber(existingUser.getPhoneNumber())
                .city(existingUser.getCity())
                .zipCode(existingUser.getZipCode())
                .address(existingUser.getAddress())
                .archivedAt(new Timestamp(System.currentTimeMillis()))
                .archivedBy(existingUser.getId())
                .build();
        userHistoryRepository.save(history);

        // --- Mettre à jour l'utilisateur ---
        userMapper.updateEntityFromDTO(updateDTO, existingUser);
        existingUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        existingUser.setUpdatedBy(existingUser.getId());

        userRepository.save(existingUser);

        LOGGER.info("User updated successfully: " + updateDTO.getUserUUID());
        return true;
    }
    public Integer getCurrentUserId(String uuid) {
        return userRepository.findByuserUUID(uuid)
                .map(user -> user.getId())
                .orElse(null); // ou lancer une exception si non trouvé
    }

}