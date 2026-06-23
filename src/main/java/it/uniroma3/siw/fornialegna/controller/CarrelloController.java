package it.uniroma3.siw.fornialegna.controller;

import it.uniroma3.siw.fornialegna.model.Carrello;
import it.uniroma3.siw.fornialegna.service.CarrelloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/carrello")
public class CarrelloController {

    private final CarrelloService carrelloService;

    public CarrelloController(CarrelloService carrelloService) {
        this.carrelloService = carrelloService;
    }

    @GetMapping
    public String mostraCarrello(Model model, Principal principal) {
        Carrello carrello = carrelloService.getOrCreaCarrello(principal.getName());
        model.addAttribute("carrello", carrello);
        model.addAttribute("totale", carrelloService.calcolaTotale(carrello));
        return "carrello";
    }

    @PostMapping("/aggiungi/pizza/{id}")
    public String aggiungiPizza(@PathVariable Long id, Principal principal) {
        carrelloService.aggiungiPizza(principal.getName(), id);
        return "redirect:/menu";
    }

    @PostMapping("/aggiungi/bibita/{id}")
    public String aggiungiBibita(@PathVariable Long id, Principal principal) {
        carrelloService.aggiungiBibita(principal.getName(), id);
        return "redirect:/menu";
    }

    @PostMapping("/aggiungi/fritto/{id}")
    public String aggiungiFritto(@PathVariable Long id, Principal principal) {
        carrelloService.aggiungiFritto(principal.getName(), id);
        return "redirect:/menu";
    }

    @PostMapping("/rimuovi/{id}")
    public String rimuoviElemento(@PathVariable Long id) {
        carrelloService.rimuoviElemento(id);
        return "redirect:/carrello";
    }

    @PostMapping("/svuota")
    public String svuotaCarrello(Principal principal) {
        carrelloService.svuotaCarrello(principal.getName());
        return "redirect:/carrello";
    }
}
