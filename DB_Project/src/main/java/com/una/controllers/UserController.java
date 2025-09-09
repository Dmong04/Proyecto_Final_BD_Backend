package com.una.controllers;

import com.una.utils.requests.adminUser.AddAdminUserRequest;
import com.una.dto.UserDTO;
import com.una.services.UserService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.clientUser.AddClientUserRequest;
import com.una.utils.requests.adminUser.UpdateAdminUserRequest;
import com.una.utils.requests.clientUser.UpdateClientUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("coco_tours/api/v2/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     *
     * @return Lista de usuarios envuelta en {@link GenericResponse}.
     * Retorna 204 si no hay usuarios.
     */
    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<UserDTO>>> getAllUsers() {
        try {
            List<UserDTO> users = service.getAllUsers();
            GenericResponse<List<UserDTO>> response = new GenericResponse<>();
            if (users.isEmpty()) {
                return response.buildResponse(null, false,
                        "El listado de usuarios está vacío",
                        HttpStatus.NO_CONTENT);
            }
            return response.buildResponse(users, true,
                    "Se desplegó el listado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<List<UserDTO>> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * Obtiene al usuario consultado por su nombre de usaurio
    * @return Información envuelta de su usuario y cliente/administrador en {@link GenericResponse}.
     * Retorna un 404 en caso de no existir el usuario.
    * */
    @GetMapping("/{username}")
    public ResponseEntity<GenericResponse<UserDTO>> getUserByUsername(@PathVariable String username) {
        try {
            Optional<UserDTO> found = service.findUserByUsername(username);
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El usuario no existe",
                        HttpStatus.NOT_FOUND);
            }
            return response.buildResponse(found.get(), true,
                    "Se encontró al usuario en los registros",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoints para gestión de usuarios administradores.
     * Crea un usuario administrador mediante un procedimiento almacenado en la base de datos
     * que emplea un proceso maestro de usuario y persona administradora al enviarse una {@link AddAdminUserRequest}.
     * @return Mensaje de confirmación en la creación del usuario envuelto en {@link GenericResponse}.
     * Retorna un 400 en caso de fallar la creación
     * */
    @PostMapping("/admin")
    public ResponseEntity<GenericResponse<UserDTO>> createAdminUser(@RequestBody AddAdminUserRequest request) {
        try {
            Optional<UserDTO> found = service.findUserByUsername(request.getUsername());
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(null, false,
                        "No se pudo ingresar al usuario administrador",
                        HttpStatus.BAD_REQUEST);
            }
            service.saveAdminUser(request.getName(), request.getEmail(), request.getUsername(), request.getPassword());
            return response.buildResponse(null, true,
                    "Usuario administrador ingresado exitosamente",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza a un usuario administrador y toda su información personal mediante un procedimiento almacenado
     * en la base de datos, enviados desde {@link UpdateAdminUserRequest}.
     * @return Mensaje de confirmación de la actualización de los datos envuelta en {@link GenericResponse}.
     * Retorna un 400 en caso de fallar la actualización.
     * */
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<GenericResponse<UserDTO>>
    updateAdminUser(@PathVariable Integer id, @RequestBody UpdateAdminUserRequest request) {
        try {
            Optional<UserDTO> found = service.findUserById(id);
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se encontró al usuario solicitado",
                        HttpStatus.BAD_REQUEST);
            }
            service.updateAdminUser(id, request.getName(), request.getEmail(), request.getUsername(), request.getPassword());
            return response.buildResponse(null, true,
                    "Datos actualizados correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina al usuario administrador seleccionado por identificador {@link Integer id}.
     * @return Mensaje de confirmación de la eliminación exitosa del usuario envuelto en {@link GenericResponse}.
     * Retorna un 400 en caso de fallar la eliminación.*/
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<GenericResponse<UserDTO>> deleteAdminUserById(@PathVariable Integer id) {
        try {
            Optional<UserDTO> found = service.findUserById(id);
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "No se encontró el usuario solicitado",
                        HttpStatus.BAD_REQUEST);
            }
            service.deleteAdminUser(found.get().getId());
            return response.buildResponse(found.get(), true,
                    "Se eliminó el usuario seleccionado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoints para gestión de usuarios clientes. Crea un usuario administrador mediante un procedimiento almacenado
     * en la base de datos que emplea un proceso maestro de usuario y persona cliente al enviarse una {@link AddClientUserRequest}.
     * @return Mensaje de confirmación en la creación del usuario envuelto en {@link GenericResponse}.
     * Retorna un 400 en caso de fallar la creación*/
    @PostMapping("/client")
    public ResponseEntity<GenericResponse<UserDTO>> createClientUser(@RequestBody AddClientUserRequest request) {
        try {
            Optional<UserDTO> found = service.findUserByUsername(request.getUsername());
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isPresent()) {
                return response.buildResponse(null, false,
                        "No se pudo ingresar al usuario cliente",
                        HttpStatus.BAD_REQUEST);
            }
            service.saveClientUser(request.getName(), request.getPhone(), request.getEmail(),
                    request.getUsername(), request.getPassword());
            return response.buildResponse(null, true,
                    "Usuario cliente ingresado exitosamente",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza a un usuario cliente y toda su información personal mediante un procedimiento almacenado
     * en la base de datos, enviados desde {@link UpdateClientUserRequest}.
     * @return Mensaje de confirmación de la actualización de los datos envuelta en {@link GenericResponse}.
     * Retorna un 400 en caso de fallar la actualización.
     * */
    @PutMapping("/client/update/{id}")
    public ResponseEntity<GenericResponse<UserDTO>> updateClientUser(@PathVariable Integer id, @RequestBody UpdateClientUserRequest request) {
        try {
            Optional<UserDTO> found = service.findUserById(id);
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El usuario no existe",
                        HttpStatus.BAD_REQUEST);
            }
            service.updateClientUser(id, request.getName(), request.getPhone(),
                    request.getEmail(), request.getUsername(), request.getPassword());

            return response.buildResponse(null, true,
                    "Los datos del cliente han sido actualizados",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina al usuario cliente seleccionado por identificador {@link Integer id}.
     * @return Mensaje de confirmación de la eliminación exitosa del usuario envuelto en {@link GenericResponse}.
     * Retorna un 400 en caso de fallar la eliminación.*/
    @DeleteMapping("/client/delete/{id}")
    public ResponseEntity<GenericResponse<UserDTO>> deleteClientUserById(@PathVariable Integer id) {
        try {
            Optional<UserDTO> found = service.findUserById(id);
            GenericResponse<UserDTO> response = new GenericResponse<>();
            if (found.isEmpty()) {
                return response.buildResponse(null, false,
                        "El usuario seleccionado no existe",
                        HttpStatus.BAD_REQUEST);
            }
            service.deleteClientUser(id);
            return response.buildResponse(found.get(), true,
                    "Se eliminó al usuario seleccionado correctamente",
                    HttpStatus.OK);
        } catch (Exception e) {
            GenericResponse<UserDTO> response = new GenericResponse<>();
            return response.buildResponse(null, false,
                    "Hubo un error en el proceso, inténtelo nuevamente más tarde: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
