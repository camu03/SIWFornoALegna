package it.uniroma3.siw.fornialegna.repository;

import it.uniroma3.siw.fornialegna.model.Bibita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BibitaRepository extends JpaRepository<Bibita, Long> {
    List<Bibita> findAllByOrderByNomeAsc();
    List<Bibita> findAllByOrderByPrezzoAsc();
}
