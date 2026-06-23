package it.uniroma3.siw.fornialegna.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrello {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElementoCarrello> elementiCarrello = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<ElementoCarrello> getElementiCarrello() { return elementiCarrello; }
    public void setElementiCarrello(List<ElementoCarrello> elementiCarrello) { this.elementiCarrello = elementiCarrello; }
}
