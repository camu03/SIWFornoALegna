package it.uniroma3.siw.fornialegna.controller;

import it.uniroma3.siw.fornialegna.model.Ingrediente;
import it.uniroma3.siw.fornialegna.repository.BibitaRepository;
import it.uniroma3.siw.fornialegna.repository.FrittoRepository;
import it.uniroma3.siw.fornialegna.repository.PizzaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MenuRestController {

    private final PizzaRepository pizzaRepository;
    private final BibitaRepository bibitaRepository;
    private final FrittoRepository frittoRepository;

    public MenuRestController(PizzaRepository pizzaRepository,
                              BibitaRepository bibitaRepository,
                              FrittoRepository frittoRepository) {
        this.pizzaRepository = pizzaRepository;
        this.bibitaRepository = bibitaRepository;
        this.frittoRepository = frittoRepository;
    }

    @GetMapping("/api/menu")
    public Map<String, Object> getMenu() {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("pizze", pizzaRepository.findTop10ByOrderByIdAsc().stream().map(p -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", p.getId());
            m.put("nome", p.getNome());
            m.put("descrizione", p.getDescrizione());
            m.put("prezzo", p.getPrezzo());
            m.put("hasImmagine", p.getImmagine() != null);
            m.put("ingredienti", p.getIngredienti().stream()
                    .map(Ingrediente::getNome).collect(Collectors.toList()));
            return m;
        }).collect(Collectors.toList()));

        result.put("bibite", bibitaRepository.findTop10ByOrderByIdAsc().stream().map(b -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", b.getId());
            m.put("nome", b.getNome());
            m.put("descrizione", b.getDescrizione());
            m.put("prezzo", b.getPrezzo());
            m.put("hasImmagine", b.getImmagine() != null);
            return m;
        }).collect(Collectors.toList()));

        result.put("fritti", frittoRepository.findTop10ByOrderByIdAsc().stream().map(f -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", f.getId());
            m.put("nome", f.getNome());
            m.put("descrizione", f.getDescrizione());
            m.put("prezzo", f.getPrezzo());
            m.put("hasImmagine", f.getImmagine() != null);
            return m;
        }).collect(Collectors.toList()));

        return result;
    }
}
