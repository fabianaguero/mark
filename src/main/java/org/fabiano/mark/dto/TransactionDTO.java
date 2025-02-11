package org.fabiano.mark.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {

    @Schema(description = "Identificador único de la transacción", example = "1")
    private Long id;

    @NotNull(message = "El monto es obligatorio.")
    @Positive(message = "El monto debe ser un valor positivo.")
    @Schema(description = "Monto de la transacción", example = "1500.50")
    private Double monto;

    @NotNull(message = "El tipo de transacción es obligatorio.")
    @Schema(description = "Tipo de transacción", example = "Depósito")
    private String tipo;  // Ej: "Depósito", "Retiro", "Transferencia"

    @Schema(description = "Fecha y hora de la transacción", example = "2024-10-12T14:30:00")
    private LocalDateTime fechaTransaccion;

    @NotNull(message = "El estado de la transacción es obligatorio.")
    @Schema(description = "Estado actual de la transacción", example = "Completada")
    private String estado;

    @NotNull(message = "Debe asociar una cuenta de origen.")
    @Schema(description = "Identificador de la cuenta de origen", example = "1234567890")
    private Long cuentaOrigenId;

    @Schema(description = "Identificador de la cuenta de destino (opcional para transferencias)", example = "0987654321")
    private Long cuentaDestinoId;  // Opcional para transferencias
}
