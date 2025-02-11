package org.fabiano.mark.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.fabiano.mark.dto.BankEmployeeDTO;
import org.fabiano.mark.service.BankEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Bank Employee", description = "Operaciones CRUD para empleados bancarios")
public class BankEmployeeController {

    @Autowired
    private BankEmployeeService service;

    @GetMapping
    @Operation(summary = "Obtener todos los empleados", description = "Devuelve una lista de todos los empleados registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida exitosamente")
    })
    public ResponseEntity<List<BankEmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un empleado por ID", description = "Devuelve los detalles de un empleado específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<BankEmployeeDTO> getEmployeeById(@Parameter(description = "ID del empleado a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo empleado", description = "Permite registrar un nuevo empleado en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    public ResponseEntity<BankEmployeeDTO> createEmployee(@Parameter(description = "Detalles del nuevo empleado") @Valid @RequestBody BankEmployeeDTO employeeDTO) {
        return ResponseEntity.status(201).body(service.createEmployee(employeeDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un empleado existente", description = "Actualiza la información de un empleado específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<BankEmployeeDTO> updateEmployee(@Parameter(description = "ID del empleado a actualizar", example = "1") @PathVariable Long id, @Parameter(description = "Detalles actualizados del empleado") @Valid @RequestBody BankEmployeeDTO employeeDTO) {
        return ResponseEntity.ok(service.updateEmployee(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado por ID", description = "Elimina un empleado específico del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empleado eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<Void> deleteEmployee(@Parameter(description = "ID del empleado a eliminar", example = "1") @PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
