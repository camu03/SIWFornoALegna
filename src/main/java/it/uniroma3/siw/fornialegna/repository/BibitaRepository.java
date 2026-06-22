package it.uniroma3.siw.fornialegna.repository;

import it.uniroma3.siw.fornialegna.model.Bibita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BibitaRepository extends JpaRepository<Bibita, Long> {
    //List<Bibita> findAllByOrderByNomeAsc();
    //List<Bibita> findAllByOrderByPrezzoAsc();
    List<Bibita> findTop10ByOrderByIdAsc();
    List<Bibita> findByNomeStartingWithIgnoreCase(String nome);
}
