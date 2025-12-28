package it.uniroma3.siw.fornialegna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import it.uniroma3.siw.fornialegna.service.BibitaService;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.fornialegna.model.Bibita;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class BibitaController {
    
    @Autowired
    private BibitaService bibitaService;

    @GetMapping("/menu/bibite")
    public List<Bibita> getAllBibite(@RequestParam(required = false, defaultValue = "false") boolean sortedByName,
                                    @RequestParam(required = false, defaultValue = "false") boolean sortedByPrice) {
        if (sortedByName) {
            return bibitaService.findAllSortedByNome();
        } else if (sortedByPrice) {
            return bibitaService.findAllSortedByPrezzo();
        } else {
            return bibitaService.findAll();
        }
    }

    @GetMapping("/menu/bibite/{id}")
    public Bibita getBibitaById(@PathVariable Long id) {
        return bibitaService.findById(id);
    }   
}
