package com.mycompany.app.microservice_restaurant_catalog.application.ports.out;

import org.springframework.security.core.userdetails.UserDetails;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 10352e266cac05aa46b170167b17643ccc591305
public interface TokenServicePort {

    // Método para extraer el usuario desde el token
    String extractSubjectFromToken(String token);

<<<<<<< HEAD
    List<String> extractRolesFromToken(String token);
=======
>>>>>>> 10352e266cac05aa46b170167b17643ccc591305


}
