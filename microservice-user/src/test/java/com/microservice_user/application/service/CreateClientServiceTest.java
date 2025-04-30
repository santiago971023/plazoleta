package com.microservice_user.application.service;

import com.microservice_user.application.DTOs.CreateClientDTO;
import com.microservice_user.application.exception.DocumentAlreadyExistsException;
import com.microservice_user.application.exception.EmailAlreadyExistsException;
import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.application.services.CreateClientService;
import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.adapters.out.persistence.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

//----- Esta clase tendrá paso a paso porque llevo buen tiempo sin hacer tests

// Indica a JUnit 5 que ejecute este test con la extensión de mockito
// eso habilita el uso de @Mock y @InjectMocks
@ExtendWith(MockitoExtension.class)
class CreateClientServiceTest {

    // Paso 1a. declaro mis dependencias como mocks, es decir que mockito creará objetos simulados para estas interfaces o clase
    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    // Paso 1b. Declaro la clase que quiero probar con @InjectMocks para que mockito inyecte los mocks declarados arriba en el constructor o campos de esta instancia
    @InjectMocks
    private CreateClientService createClientService;

    // Y declara campos a nivel de clase para objetos comunes
    private CreateClientDTO clientDTO;
    private User userAfterMapping; // Objeto User simulado después del mapeo
    private User userAfterSave;    // Objeto User simulado después de guardar


    // PASO 1c. Un método que se ejecuta antes de cada @Test.
    // Aquí se puede poner la lógica de configuración que se repite en varios tests
     @BeforeEach
     void setUp() {
         // Instancia estándar el DTO antes de cada test
         clientDTO = new CreateClientDTO();
         clientDTO.setName("Juan");
         clientDTO.setLastname("Test");
         clientDTO.setDocumentNumber("1323456789");
         clientDTO.setPhoneNumber("+573014655454");
         clientDTO.setEmail("juantest@test.com");
         clientDTO.setPassword("test_passw");


         // También puedes inicializar aquí objetos User simulados si su estructura base es común
         userAfterMapping = new User();
         userAfterMapping.setName(clientDTO.getName()); // Reutiliza datos del DTO
         userAfterMapping.setLastname(clientDTO.getLastname());
         userAfterMapping.setDocumentNumber(clientDTO.getDocumentNumber());
         userAfterMapping.setPhoneNumber(clientDTO.getPhoneNumber());
         userAfterMapping.setEmail(clientDTO.getEmail());
         userAfterMapping.setPassword(clientDTO.getPassword()); // Clave sin encriptar

         userAfterSave = new User();
         userAfterSave.setId(1L);
         userAfterSave.setName(clientDTO.getName());
         userAfterSave.setLastname(clientDTO.getLastname());
         userAfterSave.setDocumentNumber(clientDTO.getDocumentNumber());
         userAfterSave.setPhoneNumber(clientDTO.getPhoneNumber());
         userAfterSave.setEmail(clientDTO.getEmail());
         userAfterSave.setPassword("encodedPasswordXYZ");  // clave encriptada simulada
         userAfterSave.setRole(RoleEnum.CLIENTE);



     }
    // Aquí arriba podrías poner lógica de configuración que se repita en varios tests,
    // pero a menudo es más claro configurar los mocks directamente en cada test.


    @Test
    @DisplayName("Debería crear un cliente exitosamente con datos válidos y únicos.")
    void createClient_ValidAndUniqueData_ShouldSucced(){

         // GIVEN - dado que...
         // Aquí necesitamos lo que ya creamos en BeforeEach, como el clientDTO, userAfterMapping y el userAfterSave

        //  Configurar mocks específicos para este test (unicidad y save)
        // Queremos simular escenario de exito, por eso debemos hacer que nuestros métodos internos en nuestro createClient retornen false
        when(userRepositoryPort.existsByDocumentNumber(anyString())).thenReturn(false);
        when(userRepositoryPort.existsByEmail(anyString())).thenReturn(false);

        // Esto puede ser opcional pero configurar comportamientos de mocks que son comunes a TODOS o la mayoría de los tests
        // Por ejemplo, si userMapper.toUser(any(CreateClientDTO.class)) siempre debe devolver userAfterMapping
        when(userMapper.toUser(any(CreateClientDTO.class))).thenReturn(userAfterMapping);

        // Otro opcional es si passwordEncoder.encode(anyString()) siempre debe devolver "encodedPasswordXYZ"
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPasswordXYZ");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepositoryPort.save(userCaptor.capture())).thenReturn(userAfterSave);


        //  WHEN - Cuando...(acción)
        User resultUser = createClientService.createClient(clientDTO);

        // THEN - Entonces... (verificamos)
        // Verifico resultado retornado
        assertNotNull(resultUser, "El resultado no debería ser nulo");
        assertEquals(userAfterSave, resultUser, "El usuario retornado debería ser el que simulé que save devuelve.");

        // Verifico intecacciones con los mocks
        verify(userRepositoryPort, times(1)).existsByDocumentNumber(clientDTO.getDocumentNumber());
        verify(userRepositoryPort, times(1)).existsByEmail(clientDTO.getEmail());
        verify(userMapper, times(1)).toUser(clientDTO);
        verify(passwordEncoder, times(1)).encode(clientDTO.getPassword());
        verify(userRepositoryPort, times(1)).save(userCaptor.getValue());

        // Verifico el contenido de User que fue pasado a save (usando el captor)
        User capturedUser = userCaptor.getValue();
        assertNotNull(capturedUser, "El objeto capturado no debería ser null");
        assertEquals(RoleEnum.CLIENTE, capturedUser.getRole(), "El rol del usuario guardado debería ser Cliente");
        assertEquals("encodedPasswordXYZ", capturedUser.getPassword(), "La clave del usuario guardado debería estar encriptada");
        assertEquals(clientDTO.getEmail(), capturedUser.getEmail(), "El email en el objeto guardado debería ser el del DTO");

        // Verifico que otros métodos no se hayan llamado
        verifyNoMoreInteractions(userRepositoryPort, userMapper, passwordEncoder);



    }

    // Tests para casos de error
    @Test
    @DisplayName("Debería lanzar DocumentAlreadyExistException cuando el número de documento ya existe.")
    void createClient_DocumentAlreadyExist_ShouldThrowException(){

         // GIVEN - Dado que... (contexto)
        // Configuro lo necesario (mocks) para este test (Documento ya existe)
        when(userRepositoryPort.existsByDocumentNumber(clientDTO.getDocumentNumber())).thenReturn(true);


        // Cuando documento existe, existsByEmail NO debería ser llamado (depende de la lógica del servicio)
        // No necesito definir el comportamiento de userMapper, passwordEncoder, save porque no deberían llamarse.

        // WHEN - Cuando... (acción)
        // Ejecutar el método y esperar una excepción
        DocumentAlreadyExistsException thrown = assertThrows(DocumentAlreadyExistsException.class,
                () -> createClientService.createClient(clientDTO),
                "Debería lanzar DocumentAlreadyExist cuando el documento ya existe."
        );

        // THEN - Entonces... (verficamos)
        // Verificamos si fue llamado 1 vez el método correcto
        verify(userRepositoryPort, times(1)).existsByDocumentNumber(clientDTO.getDocumentNumber());

        // El método que comprueba si el email existe no debería haber sido llamado
        verify(userRepositoryPort, never()).existsByEmail(anyString());

        // userMapper, passwordEncoder, save NO deberían haber sido llamados
        verifyNoMoreInteractions(userRepositoryPort, userMapper, passwordEncoder);

    }

    @Test
    @DisplayName("Debería lanzar EmailAlreadyExistException cuando el email ya existe.")
    void createClient_EmailAlreadyExist_ShouldThrowException(){

        // GIVEN - Dado que... (contexto)
        // Configuro lo necesario (mocks) para este test (Documento no existe y Email SI existe)
        when(userRepositoryPort.existsByDocumentNumber(clientDTO.getDocumentNumber())).thenReturn(false);
        when(userRepositoryPort.existsByEmail(clientDTO.getEmail())).thenReturn(true);


        // Cuando document no existe, existsByEmail debería ser llamado (depende de la lógica del servicio)
        // No necesito definir el comportamiento de userMapper, passwordEncoder, save porque no deberían llamarse.

        // WHEN - Cuando... (acción)
        // Ejecutar el método y esperar una excepción
        EmailAlreadyExistsException thrown = assertThrows(EmailAlreadyExistsException.class,
                () -> createClientService.createClient(clientDTO),
                "Debería lanzar EmailAlreadyExist cuando el email ya existe."
        );

        // THEN - Entonces... (verficamos)
        // Verificamos si fue llamado 1 vez el método correcto
        // El método que verifica si el documento existe debería haberse llamado una vez porque está antes del que verifica el email
        // Prueba con esta sintaxis:
        verify(userRepositoryPort, times(1)).existsByDocumentNumber(anyString());

        verify(userRepositoryPort, times(1)).existsByEmail(clientDTO.getEmail());



        // userMapper, passwordEncoder, save NO deberían haber sido llamados
        verifyNoMoreInteractions(userRepositoryPort, userMapper, passwordEncoder);

    }



}
