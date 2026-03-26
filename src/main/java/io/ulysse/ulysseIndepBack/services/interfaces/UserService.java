package io.ulysse.ulysseIndepBack.services.interfaces;

import io.ulysse.ulysseIndepBack.entities.DTO.UserDTO;



public interface UserService {

	/*
	 * Récupération de la technologie avec l'ID correspondant
	 */
	 UserDTO getUserByID(String uuid);

     boolean createUser(UserDTO createDTO);


     boolean updateCurrentUser(UserDTO createDTO);


	//EntitiesUser createUser(userDTO user);
}
