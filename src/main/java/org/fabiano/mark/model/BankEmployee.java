package org.fabiano.mark.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String rol;

    @Column(name = "fecha_ingreso")
    private String fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "bank_entity_id")
    private BankEntity bankEntity;
}
