package org.fabiano.mark.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bank_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String codigoSwift;
    private String pais;

    @Column(name = "fecha_creacion")
    private String fechaCreacion;

    private String estado;

    @OneToMany(mappedBy = "bankEntity", cascade = CascadeType.ALL)
    private List<BankAccount> cuentas;

    @OneToMany(mappedBy = "bankEntity", cascade = CascadeType.ALL)
    private List<BankEmployee> empleados;
}
