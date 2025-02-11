package org.fabiano.mark.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String correo;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<BankAccount> cuentas;
}

