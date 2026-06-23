package it.uniroma3.siw.fornialegna.service;

import it.uniroma3.siw.fornialegna.model.*;
import it.uniroma3.siw.fornialegna.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarrelloService {

    private final CarrelloRepository carrelloRepository;
    private final ElementoCarrelloRepository elementoCarrelloRepository;
    private final UserRepository userRepository;
    private final PizzaRepository pizzaRepository;
    private final BibitaRepository bibitaRepository;
    private final FrittoRepository frittoRepository;

    public CarrelloService(CarrelloRepository carrelloRepository,
                           ElementoCarrelloRepository elementoCarrelloRepository,
                           UserRepository userRepository,
                           PizzaRepository pizzaRepository,
                           BibitaRepository bibitaRepository,
                           FrittoRepository frittoRepository) {
        this.carrelloRepository = carrelloRepository;
        this.elementoCarrelloRepository = elementoCarrelloRepository;
        this.userRepository = userRepository;
        this.pizzaRepository = pizzaRepository;
        this.bibitaRepository = bibitaRepository;
        this.frittoRepository = frittoRepository;
    }

    @Transactional
    public Carrello getOrCreaCarrello(String username) {
        User user = userRepository.findByUsername(username);
        Carrello carrello = carrelloRepository.findByUser(user);
        if (carrello == null) {
            carrello = new Carrello();
            carrello.setUser(user);
            carrelloRepository.save(carrello);
        }
        return carrello;
    }

    @Transactional
    public void aggiungiPizza(String username, Long pizzaId) {
        Carrello carrello = getOrCreaCarrello(username);
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        if (pizza == null) return;
        ElementoCarrello esistente = elementoCarrelloRepository.findFirstByCarrelloAndPizza(carrello, pizza);
        if (esistente != null) {
            esistente.setQuantita(esistente.getQuantita() + 1);
            elementoCarrelloRepository.save(esistente);
        } else {
            ElementoCarrello elemento = new ElementoCarrello();
            elemento.setCarrello(carrello);
            elemento.setPizza(pizza);
            elemento.setQuantita(1);
            elementoCarrelloRepository.save(elemento);
        }
    }

    @Transactional
    public void aggiungiBibita(String username, Long bibitaId) {
        Carrello carrello = getOrCreaCarrello(username);
        Bibita bibita = bibitaRepository.findById(bibitaId).orElse(null);
        if (bibita == null) return;
        ElementoCarrello esistente = elementoCarrelloRepository.findFirstByCarrelloAndBibita(carrello, bibita);
        if (esistente != null) {
            esistente.setQuantita(esistente.getQuantita() + 1);
            elementoCarrelloRepository.save(esistente);
        } else {
            ElementoCarrello elemento = new ElementoCarrello();
            elemento.setCarrello(carrello);
            elemento.setBibita(bibita);
            elemento.setQuantita(1);
            elementoCarrelloRepository.save(elemento);
        }
    }

    @Transactional
    public void aggiungiFritto(String username, Long frittoId) {
        Carrello carrello = getOrCreaCarrello(username);
        Fritto fritto = frittoRepository.findById(frittoId).orElse(null);
        if (fritto == null) return;
        ElementoCarrello esistente = elementoCarrelloRepository.findFirstByCarrelloAndFritto(carrello, fritto);
        if (esistente != null) {
            esistente.setQuantita(esistente.getQuantita() + 1);
            elementoCarrelloRepository.save(esistente);
        } else {
            ElementoCarrello elemento = new ElementoCarrello();
            elemento.setCarrello(carrello);
            elemento.setFritto(fritto);
            elemento.setQuantita(1);
            elementoCarrelloRepository.save(elemento);
        }
    }

    @Transactional
    public void incrementaElemento(Long elementoId) {
        ElementoCarrello elemento = elementoCarrelloRepository.findById(elementoId).orElse(null);
        if (elemento == null) return;
        elemento.setQuantita(elemento.getQuantita() + 1);
        elementoCarrelloRepository.save(elemento);
    }

    @Transactional
    public void decrementaElemento(Long elementoId) {
        ElementoCarrello elemento = elementoCarrelloRepository.findById(elementoId).orElse(null);
        if (elemento == null) return;
        if (elemento.getQuantita() <= 1) {
            elementoCarrelloRepository.delete(elemento);
        } else {
            elemento.setQuantita(elemento.getQuantita() - 1);
            elementoCarrelloRepository.save(elemento);
        }
    }

    @Transactional
    public void rimuoviElemento(Long elementoId) {
        elementoCarrelloRepository.deleteById(elementoId);
    }

    @Transactional
    public void svuotaCarrello(String username) {
        Carrello carrello = getOrCreaCarrello(username);
        carrello.getElementiCarrello().clear();
        carrelloRepository.save(carrello);
    }

    @Transactional(readOnly = true)
    public double calcolaTotale(Carrello carrello) {
        double totale = 0;
        for (ElementoCarrello e : carrello.getElementiCarrello()) {
            double prezzo = 0;
            if (e.getPizza() != null)  prezzo = e.getPizza().getPrezzo();
            if (e.getBibita() != null) prezzo = e.getBibita().getPrezzo();
            if (e.getFritto() != null) prezzo = e.getFritto().getPrezzo();
            totale += prezzo * e.getQuantita();
        }
        return totale;
    }
}
