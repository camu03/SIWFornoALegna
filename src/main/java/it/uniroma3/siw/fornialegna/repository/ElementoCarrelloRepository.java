package it.uniroma3.siw.fornialegna.repository;
import it.uniroma3.siw.fornialegna.model.Bibita;
import it.uniroma3.siw.fornialegna.model.Carrello;
import it.uniroma3.siw.fornialegna.model.ElementoCarrello;
import it.uniroma3.siw.fornialegna.model.Fritto;
import it.uniroma3.siw.fornialegna.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementoCarrelloRepository extends JpaRepository<ElementoCarrello, Long> {

    ElementoCarrello findFirstByCarrelloAndPizza(Carrello carrello, Pizza pizza);
    ElementoCarrello findFirstByCarrelloAndBibita(Carrello carrello, Bibita bibita);
    ElementoCarrello findFirstByCarrelloAndFritto(Carrello carrello, Fritto fritto);
}
