package it.uniroma3.siw.fornialegna.repository;

import it.uniroma3.siw.fornialegna.model.Fritto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FrittoRepository extends JpaRepository<Fritto, Long> {
    List<Fritto> findAllByOrderByNomeAsc();
    List<Fritto> findAllByOrderByPrezzoAsc();
}
