package org.fabiano.mark.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bank_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;

    private String tipoCuenta;
    private Double saldo;
    private String moneda;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "bank_entity_id")
    private BankEntity bankEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cuentaOrigen", cascade = CascadeType.ALL)
    private List<Transaction> transaccionesOrigen;

    @OneToMany(mappedBy = "cuentaDestino", cascade = CascadeType.ALL)
    private List<Transaction> transaccionesDestino;
}
