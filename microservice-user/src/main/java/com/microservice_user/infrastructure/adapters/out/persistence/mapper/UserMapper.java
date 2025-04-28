package com.microservice_user.infrastructure.adapters.out.persistence.mapper;

import com.microservice_user.application.DTOs.CreateClientDTO;
import com.microservice_user.application.DTOs.CreateEmployeeDTO;
import com.microservice_user.application.DTOs.CreateOwnerDTO;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.adapters.out.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    // Método para convertir un objeto de tipo User (de dominio) a un objeto de tipo UserEntity
    UserEntity toUserEntity(User domainUser);

    // Método para convertir UserEntity a un objeto User (de dominio)
    User toUser(UserEntity userEntity);


    // Método para mapear de CreateClientDTO a User del Dominio
    // MapStruct mapeará campos con el mismo nombre.
    // El 'id' no viene en el DTO, se genera al guardar, por eso no lo mapeamos source="id"
    // El 'role' no viene en el DTO, se asigna en el servicio de aplicación, por eso lo ignoramos o no lo mapeamos aquí.
    @Mappings({
            @Mapping(target = "id", ignore = true), // El ID se genera en persistencia
            @Mapping(target = "role", ignore = true), // El rol se asigna en el servicio de aplicación
            @Mapping(target = "birthday", ignore = true) // No viene en CreateClientDTO
            // Si tuvieras un campo en User que no está en el DTO y no debe ser nulo, podrías necesitar un defaultValue o expresión
    })
    User toUser(CreateClientDTO clientDTO);

    // Método para mapear de CreateEmployeeDTO a User del Dominio
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "role", ignore = true), // El rol se asigna en el servicio de aplicación
            //birthday sí viene en CreateEmployeeDTO ahora, MapStruct lo mapeará si se llama igual
    })
    User toUser(CreateEmployeeDTO employeeDTO);


    // Método para mapear de CreateOwnerDTO a User del Dominio
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "role", ignore = true) // El rol se asigna en el servicio de aplicación
            //birthday sí viene en CreateOwnerDTO, MapStruct lo mapeará si se llama igual
    })
    User toUser(CreateOwnerDTO ownerDTO);



}
