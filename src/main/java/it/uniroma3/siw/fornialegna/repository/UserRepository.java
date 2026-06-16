package it.uniroma3.siw.fornialegna.repository;
import it.uniroma3.siw.fornialegna.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
