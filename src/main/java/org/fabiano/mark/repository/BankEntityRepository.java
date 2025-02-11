package org.fabiano.mark.repository;


import org.fabiano.mark.model.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankEntityRepository extends JpaRepository<BankEntity, Long> {
}
