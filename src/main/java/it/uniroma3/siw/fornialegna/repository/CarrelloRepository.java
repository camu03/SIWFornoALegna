package it.uniroma3.siw.fornialegna.repository;
import it.uniroma3.siw.fornialegna.model.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.fornialegna.model.User;

public interface CarrelloRepository extends JpaRepository<Carrello, Long> {
    Carrello findByUser(User user);
}
