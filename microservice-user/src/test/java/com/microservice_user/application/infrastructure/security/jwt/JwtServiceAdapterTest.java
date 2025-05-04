package com.microservice_user.application.infrastructure.security.jwt;

import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.security.jwt.JwtServiceAdapter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class JwtServiceAdapterTest {

    @Autowired
    private JwtServiceAdapter jwtServiceAdapter;
    private String testSecretKey;

    @BeforeEach
    void setUp() {
        // Leer la clave secreta configurada para tests si la necesitas para parsar manualmente
        // Asegurate que jwt.secret.key este definido en src/test/resources/application.properties
        // Puedes leerlo usando @Value si quieres, o simplemente usar el valor fijo del archivo
        // this.testSecretKey = "tuClaveDePruebaDeapplication.properties"; // O leerlo de application.properties si es diferente
        this.testSecretKey = "estaEsUnaClaveSecretaDePruebaMuySeguraDeMasDe256BitsParaJJWT"; // Asegurate que coincida con src/test/resources/application.properties
    }


    @Test
    void testGenerateToken_Successful(){

        // PReparar datos de entrada (Un objeto User de dominio de prueba)
        User testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("test@example.com");
        testUser.setRole(RoleEnum.CLIENTE);

        // Ejecutar el método bajo prueba (Generar el token)
        String generatedToken = jwtServiceAdapter.generateToken(testUser);

        // Verificar resultado
        assertNotNull(generatedToken, "El token generado no debería ser null");
        assertFalse(generatedToken.isEmpty(), "El token generado no debería estar vacío.");

        // Verificar que el token es un JWT válido y parsearlo para verificar los claims
        try {
            // PAra parsear necesito la clave de firma, y esta clave de firma se crea a partir de la SecretKey
            // LA voy a recrear usando la testSecretKey
            Key validationSigningKey = Keys.hmacShaKeyFor(testSecretKey.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(validationSigningKey) // usar la clave de la firma
                    .build()
                    .parseClaimsJws(generatedToken)// parsear el token KWT
                    .getBody(); // obtener los claims

            // Verificar los claims
            assertNotNull(claims, "Los claims no deberían ser nulos después de parsear.");
            assertEquals(testUser.getEmail(), claims.getSubject(), "El claim 'subject' debería ser el email del testUser");
            assertEquals(testUser.getRole().name(), claims.get("roles", String.class), "El claims 'roles' debería ser el rol del suaurio");

            // verificar que iat y exp existen
            assertNotNull(claims.getIssuedAt(), "El claim 'iat' no deberia ser nulo");
            assertNotNull(claims.getExpiration(), "El claim 'exp' no deberia ser nulo");

        } catch (SignatureException e) {
            fail("La validacion de la firma del token falló, lo cual no deberia pasar: " + e.getMessage());
        } catch (Exception e) {
            fail("Ocurrio un error al parsar o verificar el token: " + e.getMessage());
        }

    }

}



