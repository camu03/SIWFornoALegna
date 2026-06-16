package it.uniroma3.siw.fornialegna.repository;

import it.uniroma3.siw.fornialegna.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
