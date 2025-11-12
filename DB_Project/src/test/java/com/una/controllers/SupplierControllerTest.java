
package com.una.controllers;

import com.una.dto.SupplierDTO;
import com.una.dto.SupplierPhonesDTO;
import com.una.services.SupplierService;
import com.una.utils.GenericResponse;
import com.una.utils.requests.suppliers.AddSupplierRequest;
import com.una.utils.requests.suppliers.UpdateSupplierRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    private SupplierDTO supplierDTO;
    private AddSupplierRequest addRequest;
    private UpdateSupplierRequest updateRequest;

    @BeforeEach
    void setUp() {
        // Setup SupplierDTO
        supplierDTO = new SupplierDTO();
        supplierDTO.setId(1);
        supplierDTO.setName("Proveedor Test");
        supplierDTO.setDescription("Descripción de prueba");
        supplierDTO.setEmail("proveedor@test.com");

        // Setup SupplierPhonesDTO
        SupplierPhonesDTO phoneDTO = new SupplierPhonesDTO();
        phoneDTO.setId(1);
        phoneDTO.setPhone("1234567890");
        supplierDTO.setPhones(List.of(phoneDTO));

        // Setup AddSupplierRequest
        addRequest = new AddSupplierRequest();
        addRequest.setName("Nuevo Proveedor");
        addRequest.setDescription("Nueva descripción");
        addRequest.setEmail("nuevo@test.com");
        addRequest.setPhone("0987654321");

        // Setup UpdateSupplierRequest
        updateRequest = new UpdateSupplierRequest();
        updateRequest.setName("Proveedor Actualizado");
        updateRequest.setDescription("Descripción actualizada");
        updateRequest.setEmail("actualizado@test.com");
        updateRequest.setPhone("1111111111");
    }

    @Test
    void testGetAllSuppliers_Success() {
        // Arrange
        List<SupplierDTO> suppliers = new ArrayList<>();
        suppliers.add(supplierDTO);
        when(supplierService.getAllSuppliers()).thenReturn(suppliers);

        // Act
        ResponseEntity<GenericResponse<List<SupplierDTO>>> response = supplierController.getAllSuppliers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Se desplegó el listado correctamente", response.getBody().getMessage());
        assertEquals(1, response.getBody().getData().size());
        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void testGetAllSuppliers_EmptyList() {
        // Arrange
        when(supplierService.getAllSuppliers()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<GenericResponse<List<SupplierDTO>>> response = supplierController.getAllSuppliers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("El listado de proveedores está vacío", response.getBody().getMessage());
        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void testGetAllSuppliers_Exception() {
        // Arrange
        when(supplierService.getAllSuppliers()).thenThrow(new RuntimeException("Error de base de datos"));

        // Act
        ResponseEntity<GenericResponse<List<SupplierDTO>>> response = supplierController.getAllSuppliers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertTrue(response.getBody().getMessage().contains("Hubo un error en el proceso"));
        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void testGetSupplierById_Success() {
        // Arrange
        when(supplierService.findSupplierById(1)).thenReturn(Optional.of(supplierDTO));

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.getSupplierById(1);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Se encontró el proveedor con el ID seleccionado", response.getBody().getMessage());
        assertEquals("Proveedor Test", response.getBody().getData().getName());
        verify(supplierService, times(1)).findSupplierById(1);
    }

    @Test
    void testGetSupplierById_NotFound() {
        // Arrange
        when(supplierService.findSupplierById(999)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.getSupplierById(999);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("No se ha encontrado el proveedor con el ID seleccionado", response.getBody().getMessage());
        verify(supplierService, times(1)).findSupplierById(999);
    }

    @Test
    void testGetSupplierByName_Success() {
        // Arrange
        when(supplierService.findSupplierByName("Proveedor Test")).thenReturn(Optional.of(supplierDTO));

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.getSupplierByName("Proveedor Test");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Se encontró al proveedor en los registros", response.getBody().getMessage());
        verify(supplierService, times(1)).findSupplierByName("Proveedor Test");
    }

    @Test
    void testGetSupplierByName_NotFound() {
        // Arrange
        when(supplierService.findSupplierByName("No Existe")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.getSupplierByName("No Existe");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("El proveedor no existe", response.getBody().getMessage());
        verify(supplierService, times(1)).findSupplierByName("No Existe");
    }

    @Test
    void testCreateSupplier_Success() {
        // Arrange
        when(supplierService.findSupplierByName("Nuevo Proveedor")).thenReturn(Optional.empty());
        doNothing().when(supplierService).insertSupplier(anyString(), anyString(), anyString(), anyString());

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.createSupplier(addRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Creación del proveedor exitosa", response.getBody().getMessage());
        verify(supplierService, times(1)).findSupplierByName("Nuevo Proveedor");
        verify(supplierService, times(1)).insertSupplier(
                "Nuevo Proveedor", 
                "Nueva descripción", 
                "nuevo@test.com", 
                "0987654321"
        );
    }

    @Test
    void testCreateSupplier_AlreadyExists() {
        // Arrange
        when(supplierService.findSupplierByName("Nuevo Proveedor")).thenReturn(Optional.of(supplierDTO));

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.createSupplier(addRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertTrue(response.getBody().getMessage().contains("La creación del proveedor no fue exitosa"));
        verify(supplierService, times(1)).findSupplierByName("Nuevo Proveedor");
        verify(supplierService, never()).insertSupplier(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testUpdateSupplier_Success() {
        // Arrange
        when(supplierService.findSupplierById(1)).thenReturn(Optional.of(supplierDTO));
        doNothing().when(supplierService).updateSupplier(anyInt(), anyString(), anyString(), anyString(), anyString());

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.updateSupplier(1, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("La actualización del registro ha sido exitosa", response.getBody().getMessage());
        verify(supplierService, times(1)).findSupplierById(1);
        verify(supplierService, times(1)).updateSupplier(
                eq(1),
                eq("Proveedor Actualizado"),
                eq("Descripción actualizada"),
                eq("actualizado@test.com"),
                eq("1111111111")
        );
    }

    @Test
    void testUpdateSupplier_NotFound() {
        // Arrange
        when(supplierService.findSupplierById(999)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.updateSupplier(999, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("No se pudo actualizar el registro", response.getBody().getMessage());
        verify(supplierService, times(1)).findSupplierById(999);
        verify(supplierService, never()).updateSupplier(anyInt(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testDeleteSupplierById_Success() {
        // Arrange
        when(supplierService.findSupplierById(1)).thenReturn(Optional.of(supplierDTO));
        doNothing().when(supplierService).deleteSupplierById(1);

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.deleteSupplierById(1);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals("Se eliminó el registro exitosamente", response.getBody().getMessage());
        assertEquals("Proveedor Test", response.getBody().getData().getName());
        verify(supplierService, times(1)).findSupplierById(1);
        verify(supplierService, times(1)).deleteSupplierById(1);
    }

    @Test
    void testDeleteSupplierById_NotFound() {
        // Arrange
        when(supplierService.findSupplierById(999)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.deleteSupplierById(999);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("El proveedor seleccionado no existe", response.getBody().getMessage());
        verify(supplierService, times(1)).findSupplierById(999);
        verify(supplierService, never()).deleteSupplierById(anyInt());
    }

    @Test
    void testDeleteSupplierById_Exception() {
        // Arrange
        when(supplierService.findSupplierById(1)).thenReturn(Optional.of(supplierDTO));
        doThrow(new RuntimeException("Error al eliminar")).when(supplierService).deleteSupplierById(1);

        // Act
        ResponseEntity<GenericResponse<SupplierDTO>> response = supplierController.deleteSupplierById(1);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertTrue(response.getBody().getMessage().contains("Hubo un error en el proceso"));
        verify(supplierService, times(1)).findSupplierById(1);
        verify(supplierService, times(1)).deleteSupplierById(1);
    }
}
