package org.fabiano.mark.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @Schema(description = "Identificador único del cliente", example = "1")
    private Long id;

    @NotBlank(message = "El nombre completo es obligatorio.")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.")
    @Schema(description = "Nombre completo del cliente", example = "Juan Pérez")
    private String nombreCompleto;

    @NotBlank(message = "La identificación es obligatoria.")
    @Size(min = 6, max = 20, message = "La identificación debe tener entre 6 y 20 caracteres.")
    @Schema(description = "Número de identificación del cliente", example = "123456789")
    private String identificacion;

    @Schema(description = "Dirección del cliente", example = "Calle 123, Ciudad, País")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 dígitos.")
    @Schema(description = "Número de teléfono del cliente", example = "5551234567")
    private String telefono;

    @Email(message = "El correo electrónico no es válido.")
    @Schema(description = "Correo electrónico del cliente", example = "juan.perez@example.com")
    private String correo;
}