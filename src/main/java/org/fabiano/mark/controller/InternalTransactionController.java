package org.fabiano.mark.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.fabiano.mark.dto.TransactionDTO;
import org.fabiano.mark.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/internal")
@Tag(name = "Internal Transaction", description = "Endpoints internos para consumir transacciones dentro del mismo microservicio")
public class InternalTransactionController {

    @Autowired
    private TransactionService service;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Obtiene una transacción por su ID usando una llamada directa al servicio interno.
     *
     * @param id ID de la transacción a consultar.
     * @return Detalles de la transacción.
     */
    @GetMapping("/transaction/direct/{id}")
    @Operation(summary = "Obtener transacción por ID (llamada directa)",
            description = "Devuelve los detalles de una transacción específica utilizando una llamada directa al servicio interno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public ResponseEntity<TransactionDTO> getInternalTransactionDirectly(
            @Parameter(description = "ID de la transacción a buscar", example = "1")
            @PathVariable Long id) {
        TransactionDTO transaction = service.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Obtiene una transacción por su ID usando una llamada HTTP interna (RestTemplate).
     *
     * @param id ID de la transacción a consultar.
     * @return Detalles de la transacción.
     */
    @GetMapping("/transaction/http/{id}")
    @Operation(summary = "Obtener transacción por ID (llamada HTTP interna)",
            description = "Devuelve los detalles de una transacción específica realizando una llamada HTTP interna al mismo microservicio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public ResponseEntity<TransactionDTO> getInternalTransactionViaHttp(
            @Parameter(description = "ID de la transacción a buscar", example = "1")
            @PathVariable Long id) {
        String url = "http://localhost:8080/api/transactions/" + id;
        TransactionDTO transaction = restTemplate.getForObject(url, TransactionDTO.class);
        return ResponseEntity.ok(transaction);
    }
}
