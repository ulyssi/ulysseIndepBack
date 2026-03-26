package io.ulysse.ulysseIndepBack.entities.mapper;

import io.ulysse.ulysseIndepBack.entities.DTO.UserDTO;
import io.ulysse.ulysseIndepBack.entities.Entities.EntitiesUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /* =========================
     * CREATE
     * ========================= */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    EntitiesUser toEntity(UserDTO dto);

    /* =========================
     * UPDATE
     * ========================= */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    void updateEntityFromDTO(UserDTO dto, @MappingTarget EntitiesUser entity);


    @Mapping(target = "email", ignore = true)

    UserDTO toDTO(EntitiesUser entity);
}