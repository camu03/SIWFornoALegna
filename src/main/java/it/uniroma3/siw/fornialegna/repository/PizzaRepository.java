package it.uniroma3.siw.fornialegna.repository;

import it.uniroma3.siw.fornialegna.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
