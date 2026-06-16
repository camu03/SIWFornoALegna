package it.uniroma3.siw.fornialegna.controller;

import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.model.Bibita;
import it.uniroma3.siw.fornialegna.model.Fritto;
import it.uniroma3.siw.fornialegna.repository.PizzaRepository;
import it.uniroma3.siw.fornialegna.repository.BibitaRepository;
import it.uniroma3.siw.fornialegna.repository.FrittoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageController {

    private final PizzaRepository pizzaRepository;
    private final BibitaRepository bibitaRepository;
    private final FrittoRepository frittoRepository;

    public ImageController(PizzaRepository pizzaRepository, BibitaRepository bibitaRepository, FrittoRepository frittoRepository) {
        this.pizzaRepository = pizzaRepository;
        this.bibitaRepository = bibitaRepository;
        this.frittoRepository = frittoRepository;
    }

    @GetMapping("/pizze/immagine/{id}")
    public ResponseEntity<byte[]> getImmaginePizza(@PathVariable Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        
        if (pizza == null || pizza.getImmagine() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(pizza.getImmagine(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/bibite/immagine/{id}")
    public ResponseEntity<byte[]> getImmagineBibita(@PathVariable Long id) {
        Bibita bibita = bibitaRepository.findById(id).orElse(null);
        
        if (bibita == null || bibita.getImmagine() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(bibita.getImmagine(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/fritti/immagine/{id}")
    public ResponseEntity<byte[]> getImmagineFritto(@PathVariable Long id) {
        Fritto fritto = frittoRepository.findById(id).orElse(null);
        
        if (fritto == null || fritto.getImmagine() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(fritto.getImmagine(), headers, HttpStatus.OK);
    }
}
