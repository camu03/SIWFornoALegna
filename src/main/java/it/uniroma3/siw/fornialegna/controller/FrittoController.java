package it.uniroma3.siw.fornialegna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import it.uniroma3.siw.fornialegna.service.FrittoService;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.fornialegna.model.Fritto;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FrittoController {
    
    @Autowired
    private FrittoService frittoService;

    @GetMapping("/menu/fritti")
    public List<Fritto> getAllFritti(@RequestParam(required = false, defaultValue = "false") boolean sortedByName,
                                    @RequestParam(required = false, defaultValue = "false") boolean sortedByPrice) {
                                    
        if (sortedByName) {
            return frittoService.findAllSortedByNome();
        } else if (sortedByPrice) {
            return frittoService.findAllSortedByPrezzo();
        } else {                                
            return frittoService.findAll();
        }
    }
    
    @GetMapping("/menu/fritti/{id}")
    public Fritto getFrittoById(@PathVariable Long id) {
        return frittoService.findById(id);
    }

}
