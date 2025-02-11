package org.fabiano.mark.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monto;
    private String tipo;

    @Column(name = "fecha_transaccion")
    private LocalDateTime fechaTransaccion;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id")
    private BankAccount cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id")
    private BankAccount cuentaDestino;
}
