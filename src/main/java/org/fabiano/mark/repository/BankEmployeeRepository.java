package org.fabiano.mark.repository;


import org.fabiano.mark.model.BankEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankEmployeeRepository extends JpaRepository<BankEmployee, Long> {
}
