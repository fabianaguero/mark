package org.fabiano.mark.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BankEntityDTO {

    @Schema(description = "Identificador único del banco", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del banco es obligatorio.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    @Schema(description = "Nombre del banco", example = "Banco Internacional")
    private String nombre;

    @NotBlank(message = "El código SWIFT es obligatorio.")
    @Size(min = 8, max = 11, message = "El código SWIFT debe tener entre 8 y 11 caracteres.")
    @Schema(description = "Código SWIFT del banco", example = "BIC12345678")
    private String codigoSwift;

    @NotBlank(message = "El país es obligatorio.")
    @Schema(description = "País de operación del banco", example = "España")
    private String pais;

    @Schema(description = "Fecha de creación del banco en formato ISO 8601", example = "2023-01-01T00:00:00Z")
    private String fechaCreacion;

    @NotBlank(message = "El estado es obligatorio.")
    @Schema(description = "Estado actual del banco", example = "Activo")
    private String estado;
}
