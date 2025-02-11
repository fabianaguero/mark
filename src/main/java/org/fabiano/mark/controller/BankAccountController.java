package org.fabiano.mark.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.fabiano.mark.dto.BankAccountDTO;
import org.fabiano.mark.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Bank Account", description = "Operaciones CRUD para cuentas bancarias")
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    @GetMapping
    @Operation(summary = "Obtener todas las cuentas bancarias", description = "Devuelve una lista de todas las cuentas bancarias registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cuentas obtenida exitosamente")
    })
    public ResponseEntity<List<BankAccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cuenta bancaria por ID", description = "Devuelve los detalles de una cuenta bancaria específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    public ResponseEntity<BankAccountDTO> getAccountById(@Parameter(description = "ID de la cuenta bancaria a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cuenta bancaria", description = "Permite registrar una nueva cuenta bancaria en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    public ResponseEntity<BankAccountDTO> createAccount(@Parameter(description = "Detalles de la nueva cuenta bancaria") @Valid @RequestBody BankAccountDTO accountDTO) {
        return ResponseEntity.status(201).body(service.createAccount(accountDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cuenta bancaria existente", description = "Actualiza la información de una cuenta bancaria específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    public ResponseEntity<BankAccountDTO> updateAccount(@Parameter(description = "ID de la cuenta bancaria a actualizar", example = "1") @PathVariable Long id, @Parameter(description = "Detalles actualizados de la cuenta bancaria") @Valid @RequestBody BankAccountDTO accountDTO) {
        return ResponseEntity.ok(service.updateAccount(id, accountDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cuenta bancaria por ID", description = "Elimina una cuenta bancaria específica del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cuenta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    public ResponseEntity<Void> deleteAccount(@Parameter(description = "ID de la cuenta bancaria a eliminar", example = "1") @PathVariable Long id) {
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}