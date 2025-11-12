
package com.una.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.una.dto.SupplierDTO;
import com.una.security.token.JwtAuthFilter;
import com.una.security.token.JwtService;
import com.una.services.SupplierService;
import com.una.utils.requests.suppliers.AddSupplierRequest;
import com.una.utils.requests.suppliers.UpdateSupplierRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
    controllers = SupplierController.class,
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = JwtAuthFilter.class
    )
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SupplierService supplierService;

    @MockBean
    private JwtService jwtService;

    private SupplierDTO supplierDTO;
    private AddSupplierRequest addRequest;
    private UpdateSupplierRequest updateRequest;

    @BeforeAll
    void setUpAll() {
        System.out.println("\n========================================");
        System.out.println("INICIANDO TESTS DE SUPPLIER CONTROLLER");
        System.out.println("========================================\n");
    }

    @AfterAll
    void tearDownAll() {
        System.out.println("\n========================================");
        System.out.println("TESTS DE SUPPLIER CONTROLLER COMPLETADOS");
        System.out.println("========================================\n");
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("▶ Ejecutando: " + testInfo.getDisplayName());
        
        supplierDTO = new SupplierDTO();
        supplierDTO.setId(1);
        supplierDTO.setName("Proveedor Test");
        supplierDTO.setEmail("test@test.com");
        supplierDTO.setDescription("Descripción Test");

        addRequest = new AddSupplierRequest();
        addRequest.setName("Nuevo Proveedor");
        addRequest.setEmail("nuevo@test.com");
        addRequest.setDescription("Nueva Descripción");
        addRequest.setPhone("12345678"); // Agregar teléfono

        updateRequest = new UpdateSupplierRequest();
        updateRequest.setName("Proveedor Actualizado");
        updateRequest.setEmail("actualizado@test.com");
        updateRequest.setDescription("Descripción Actualizada");
        updateRequest.setPhone("87654321"); // Agregar teléfono
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("✓ Completado: " + testInfo.getDisplayName() + "\n");
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Obtener todos los proveedores - Lista vacía")
    @WithMockUser
    void testGetAllSuppliers_EmptyList() throws Exception {
        when(supplierService.getAllSuppliers()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/coco_tours/api/v2/supplier/all"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("El listado de proveedores está vacío"));

        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Obtener todos los proveedores - Éxito")
    @WithMockUser
    void testGetAllSuppliers_Success() throws Exception {
        List<SupplierDTO> suppliers = List.of(supplierDTO);
        when(supplierService.getAllSuppliers()).thenReturn(suppliers);

        mockMvc.perform(get("/coco_tours/api/v2/supplier/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Se desplegó el listado correctamente"))
                .andExpect(jsonPath("$.data[0].name").value("Proveedor Test"));

        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Obtener proveedor por ID - Éxito")
    @WithMockUser
    void testGetSupplierById_Success() throws Exception {
        when(supplierService.findSupplierById(1)).thenReturn(Optional.of(supplierDTO));

        mockMvc.perform(get("/coco_tours/api/v2/supplier/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Proveedor Test"));

        verify(supplierService, times(1)).findSupplierById(1);
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Obtener proveedor por ID - No encontrado")
    @WithMockUser
    void testGetSupplierById_NotFound() throws Exception {
        when(supplierService.findSupplierById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/coco_tours/api/v2/supplier/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));

        verify(supplierService, times(1)).findSupplierById(999);
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Obtener proveedor por nombre - Éxito")
    @WithMockUser
    void testGetSupplierByName_Success() throws Exception {
        when(supplierService.findSupplierByName("Proveedor Test")).thenReturn(Optional.of(supplierDTO));

        mockMvc.perform(get("/coco_tours/api/v2/supplier/name/Proveedor Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Proveedor Test"));

        verify(supplierService, times(1)).findSupplierByName("Proveedor Test");
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Crear proveedor - Éxito")
    @WithMockUser
    void testCreateSupplier_Success() throws Exception {
        when(supplierService.findSupplierByName("Nuevo Proveedor")).thenReturn(Optional.empty());
        doNothing().when(supplierService).insertSupplier(anyString(), anyString(), anyString(), anyString());

        mockMvc.perform(post("/coco_tours/api/v2/supplier")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Creación del proveedor exitosa"));

        verify(supplierService, times(1)).findSupplierByName("Nuevo Proveedor");
        verify(supplierService, times(1)).insertSupplier(
                eq("Nuevo Proveedor"),
                eq("Nueva Descripción"),
                eq("nuevo@test.com"),
                eq("12345678") // Verificar con el teléfono correcto
        );
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Crear proveedor - Ya existe")
    @WithMockUser
    void testCreateSupplier_AlreadyExists() throws Exception {
        when(supplierService.findSupplierByName("Nuevo Proveedor")).thenReturn(Optional.of(supplierDTO));

        mockMvc.perform(post("/coco_tours/api/v2/supplier")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));

        verify(supplierService, times(1)).findSupplierByName("Nuevo Proveedor");
        verify(supplierService, never()).insertSupplier(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Actualizar proveedor - Éxito")
    @WithMockUser
    void testUpdateSupplier_Success() throws Exception {
        when(supplierService.findSupplierById(1)).thenReturn(Optional.of(supplierDTO));
        doNothing().when(supplierService).updateSupplier(anyInt(), anyString(), anyString(), anyString(), anyString());

        mockMvc.perform(put("/coco_tours/api/v2/supplier/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("La actualización del registro ha sido exitosa"));

        verify(supplierService, times(1)).findSupplierById(1);
        verify(supplierService, times(1)).updateSupplier(
                eq(1),
                eq("Proveedor Actualizado"),
                eq("Descripción Actualizada"),
                eq("actualizado@test.com"),
                eq("87654321") // Verificar con el teléfono correcto
        );
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Actualizar proveedor - No encontrado")
    @WithMockUser
    void testUpdateSupplier_NotFound() throws Exception {
        when(supplierService.findSupplierById(999)).thenReturn(Optional.empty());

        mockMvc.perform(put("/coco_tours/api/v2/supplier/999")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));

        verify(supplierService, times(1)).findSupplierById(999);
        verify(supplierService, never()).updateSupplier(anyInt(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Eliminar proveedor por ID - Éxito")
    @WithMockUser
    void testdeleteSupplierByIdById_Success() throws Exception {
        when(supplierService.findSupplierById(1)).thenReturn(Optional.of(supplierDTO));
        doNothing().when(supplierService).deleteSupplierById(1);

        mockMvc.perform(delete("/coco_tours/api/v2/supplier/1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Se eliminó el registro exitosamente")) // Mensaje correcto del controlador
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Proveedor Test"));

        verify(supplierService, times(1)).findSupplierById(1);
        verify(supplierService, times(1)).deleteSupplierById(1);
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Eliminar proveedor por ID - No encontrado")
    @WithMockUser
    void testdeleteSupplierByIdById_NotFound() throws Exception {
        when(supplierService.findSupplierById(999)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/coco_tours/api/v2/supplier/999")
                        .with(csrf()))
                .andExpect(status().isBadRequest()) // El controlador devuelve BAD_REQUEST, no NOT_FOUND
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("El proveedor seleccionado no existe"));

        verify(supplierService, times(1)).findSupplierById(999);
        verify(supplierService, never()).deleteSupplierById(anyInt());
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Obtener todos los proveedores - Excepción")
    @WithMockUser
    void testGetAllSuppliers_Exception() throws Exception {
        when(supplierService.getAllSuppliers()).thenThrow(new RuntimeException("Error de base de datos"));

        mockMvc.perform(get("/coco_tours/api/v2/supplier/all"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("Error de base de datos")));

        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Eliminar proveedor - Excepción durante eliminación")
    @WithMockUser
    void testdeleteSupplierById_Exception() throws Exception {
        when(supplierService.findSupplierById(1)).thenReturn(Optional.of(supplierDTO));
        doThrow(new RuntimeException("Error al eliminar")).when(supplierService).deleteSupplierById(1);

        mockMvc.perform(delete("/coco_tours/api/v2/supplier/1")
                .with(csrf()))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("Error al eliminar")));

        verify(supplierService, times(1)).findSupplierById(1);
        verify(supplierService, times(1)).deleteSupplierById(1);
    }
}
