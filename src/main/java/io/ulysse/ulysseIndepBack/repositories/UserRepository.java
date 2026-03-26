package io.ulysse.ulysseIndepBack.repositories;

import io.ulysse.ulysseIndepBack.entities.Entities.EntitiesUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//auto-implemented
public interface UserRepository extends CrudRepository<EntitiesUser, Integer> {

    Optional<EntitiesUser> findByuserUUID(String userUUID);

    boolean existsByuserUUID(String userUUID);
}
