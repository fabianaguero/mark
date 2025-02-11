package org.fabiano.mark.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BankAccountDTO {

    private Long id;

    @NotBlank(message = "El número de cuenta es obligatorio.")
    @Schema(description = "Número de cuenta", example = "1234567890")
    @Size(min = 10, max = 20, message = "El número de cuenta debe tener entre 10 y 20 caracteres.")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio.")
    @Schema(description = "Tipo de Cuenta", example = "USD, PESOS")
    private String tipoCuenta;

    @NotNull(message = "El saldo es obligatorio.")
    @Schema(description = "Saldo de la cuenta", example = "1500.50")
    @PositiveOrZero(message = "El saldo no puede ser negativo.")
    private Double saldo;

    @NotBlank(message = "La moneda es obligatoria.")
    @Schema(description = "Tipo de moneda", example = "USD, PESOS")
    private String moneda;

    @NotBlank(message = "El estado de la cuenta es obligatorio.")
    @Schema(description = "Estado de la cuenta", example = "Activa")
    private String estado;

    @NotNull(message = "Debe asociar una entidad bancaria.")
    @Schema(description = "Entidad bancaria", example = "01")
    private Long bankEntityId;

    @NotNull(message = "Debe asociar un cliente.")
    @Schema(description = "CLiente", example = "20901454")
    private Long customerId;
}
