package it.uniroma3.siw.fornialegna.repository;

import it.uniroma3.siw.fornialegna.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {   

    List<Pizza> findTop10ByOrderByIdAsc();
    List<Pizza> findByNomeStartingWithIgnoreCase(String nome);
}