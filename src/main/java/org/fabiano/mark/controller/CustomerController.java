package org.fabiano.mark.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.fabiano.mark.dto.CustomerDTO;
import org.fabiano.mark.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Operaciones CRUD para clientes bancarios")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
    })
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente por ID", description = "Devuelve los detalles de un cliente específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<CustomerDTO> getCustomerById(@Parameter(description = "ID del cliente a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.getCustomerById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Permite registrar un nuevo cliente en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    public ResponseEntity<CustomerDTO> createCustomer(@Parameter(description = "Detalles del nuevo cliente") @Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(201).body(service.createCustomer(customerDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente existente", description = "Actualiza la información de un cliente específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<CustomerDTO> updateCustomer(@Parameter(description = "ID del cliente a actualizar", example = "1") @PathVariable Long id, @Parameter(description = "Detalles actualizados del cliente") @Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(service.updateCustomer(id, customerDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente por ID", description = "Elimina un cliente específico del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Void> deleteCustomer(@Parameter(description = "ID del cliente a eliminar", example = "1") @PathVariable Long id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}