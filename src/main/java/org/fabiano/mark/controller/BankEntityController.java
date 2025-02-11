package org.fabiano.mark.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.fabiano.mark.dto.BankEntityDTO;
import org.fabiano.mark.service.BankEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@Tag(name = "Bank Entity", description = "Operaciones CRUD para entidades bancarias")
public class BankEntityController {

    @Autowired
    private BankEntityService service;

    @Operation(summary = "Obtener todas las entidades bancarias", description = "Devuelve una lista de todas las entidades bancarias registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de entidades obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<BankEntityDTO>> getAllBanks() {
        return ResponseEntity.ok(service.getAllBanks());
    }

    @Operation(summary = "Obtener una entidad bancaria por ID",
            description = "Devuelve los detalles de una entidad bancaria específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entidad encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BankEntityDTO> getBankById(
            @Parameter(description = "ID de la entidad bancaria a buscar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getBankById(id));
    }

    @Operation(summary = "Crear una nueva entidad bancaria",
            description = "Permite registrar una nueva entidad bancaria en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entidad creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    @PostMapping
    public ResponseEntity<BankEntityDTO> createBank(
            @Parameter(description = "Detalles de la nueva entidad bancaria")
            @Valid @RequestBody BankEntityDTO bankDTO) {
        return ResponseEntity.status(201).body(service.createBank(bankDTO));
    }

    @Operation(summary = "Actualizar una entidad bancaria existente",
            description = "Actualiza la información de una entidad bancaria específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entidad actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BankEntityDTO> updateBank(
            @Parameter(description = "ID de la entidad bancaria a actualizar", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Detalles actualizados de la entidad bancaria")
            @Valid @RequestBody BankEntityDTO bankDTO) {
        return ResponseEntity.ok(service.updateBank(id, bankDTO));
    }

    @Operation(summary = "Eliminar una entidad bancaria por ID",
            description = "Elimina una entidad bancaria específica del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entidad eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(
            @Parameter(description = "ID de la entidad bancaria a eliminar", example = "1")
            @PathVariable Long id) {
        service.deleteBank(id);
        return ResponseEntity.noContent().build();
    }
}
