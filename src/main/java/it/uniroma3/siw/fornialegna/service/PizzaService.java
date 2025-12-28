package it.uniroma3.siw.fornialegna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.repository.PizzaRepository;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza findById(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        pizzaRepository.deleteById(id);
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    //metodo per ordinare le pizze per nome
    public List<Pizza> findAllSortedByNome() {
        return pizzaRepository.findAllByOrderByNomeAsc();
    }

    //metodo per ordinaere le pizze per prezzo
    public List<Pizza> findAllSortedByPrezzo() {
        return pizzaRepository.findAllByOrderByPrezzoAsc();
    }

}
