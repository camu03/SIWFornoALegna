package it.uniroma3.siw.fornialegna.repository;
import it.uniroma3.siw.fornialegna.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
