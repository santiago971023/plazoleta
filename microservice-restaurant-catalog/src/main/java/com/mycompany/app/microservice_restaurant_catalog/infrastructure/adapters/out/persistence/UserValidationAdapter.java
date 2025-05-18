package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.out.persistence;

import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.UserValidationPort;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.feign.client.UserFeignClient;
import com.mycompany.app.microservice_restaurant_catalog.infrastructure.feign.client.dto.OwnerValidationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger; // Para logging
import org.slf4j.LoggerFactory;

@Service
public class UserValidationAdapter implements UserValidationPort {

    private static final Logger log = LoggerFactory.getLogger(UserValidationAdapter.class); // Logger

    private final UserFeignClient userFeignClient;

    public UserValidationAdapter(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public boolean isValidOwner(Long id) {

        log.debug("Validando owner con el id: {} usando UserFeignClient", id);

        try {
            //Llamo al método definido en la interfaz Feign
            // OpenFeign maneja la llamada HTTP real a microservice-user
            OwnerValidationResponseDTO response = userFeignClient.validateOwner(id);
            if(response != null){
                log.debug("Recibido el OwnerValidationResponseDTO: {}", response.isValidOwner());
                return response.isValidOwner();
            }else{
                log.warn("Received null response from UserFeignClient for ownerId: {}", id);
                return false;
            }
        } catch (Exception e){
            // Manejo de errores si la llamada Feign falla (ej. microservice-user caido, error de red, etc.)
            log.error("Error callamdo UserFeignClient para validar User {}: {}", id, e.getMessage(), e);
            return false;
        }
    }
}
