package it.uniroma3.siw.fornialegna.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.repository.PizzaRepository;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Transactional
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Transactional(readOnly = true)
    public Pizza findById(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        pizzaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public long count() {
        return pizzaRepository.count();
    }

    //metodo ordina cresecente per nome
    @Transactional(readOnly = true)
    public List<Pizza> findAllByOrderByNomeAsc() {
        return pizzaRepository.findAllByOrderByNomeAsc();
    }

}
