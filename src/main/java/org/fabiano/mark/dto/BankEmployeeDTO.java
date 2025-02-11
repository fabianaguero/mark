package org.fabiano.mark.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BankEmployeeDTO {

    @Schema(description = "Identificador único del empleado", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del empleado es obligatorio.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    @Schema(description = "Nombre del empleado", example = "Juan Pérez")
    private String nombre;

    @NotBlank(message = "El rol del empleado es obligatorio.")
    @Schema(description = "Rol del empleado en la entidad bancaria", example = "Cajero")
    private String rol;

    @Schema(description = "Fecha de ingreso del empleado a la entidad bancaria", example = "2023-01-15")
    private String fechaIngreso;

    @NotNull(message = "Debe asociar una entidad bancaria.")
    @Schema(description = "Identificador de la entidad bancaria asociada", example = "101")
    private Long bankEntityId;
}
