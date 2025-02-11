package org.fabiano.mark.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.fabiano.mark.dto.TransactionDTO;
import org.fabiano.mark.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction", description = "Operaciones CRUD para transacciones bancarias")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    @Operation(summary = "Obtener todas las transacciones", description = "Devuelve una lista de todas las transacciones registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transacciones obtenida exitosamente")
    })
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok(service.getAllTransactions());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una transacción por ID", description = "Devuelve los detalles de una transacción específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public ResponseEntity<TransactionDTO> getTransactionById(@Parameter(description = "ID de la transacción a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(service.getTransactionById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva transacción", description = "Permite registrar una nueva transacción en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transacción creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    public ResponseEntity<TransactionDTO> createTransaction(@Parameter(description = "Detalles de la nueva transacción") @Valid @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(201).body(service.createTransaction(transactionDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una transacción existente", description = "Actualiza la información de una transacción específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public ResponseEntity<TransactionDTO> updateTransaction(@Parameter(description = "ID de la transacción a actualizar", example = "1") @PathVariable Long id, @Parameter(description = "Detalles actualizados de la transacción") @Valid @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(service.updateTransaction(id, transactionDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una transacción por ID", description = "Elimina una transacción específica del sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transacción eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public ResponseEntity<Void> deleteTransaction(@Parameter(description = "ID de la transacción a eliminar", example = "1") @PathVariable Long id) {
        service.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}

