package it.uniroma3.siw.fornialegna.repository;

import it.uniroma3.siw.fornialegna.model.Fritto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FrittoRepository extends JpaRepository<Fritto, Long> {
    //List<Fritto> findAllByOrderByNomeAsc();
    //List<Fritto> findAllByOrderByPrezzoAsc();
    List<Fritto> findTop10ByOrderByIdAsc();
    List<Fritto> findByNomeStartingWithIgnoreCase(String nome);
}
