package it.uniroma3.siw.fornialegna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.model.Bibita;
import it.uniroma3.siw.fornialegna.model.Fritto;
import it.uniroma3.siw.fornialegna.model.Ingrediente;
import it.uniroma3.siw.fornialegna.service.PizzaService;
import it.uniroma3.siw.fornialegna.service.BibitaService;
import it.uniroma3.siw.fornialegna.service.FrittoService;
import it.uniroma3.siw.fornialegna.service.IngredienteService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PizzaService pizzaService;
    private final BibitaService bibitaService;
    private final FrittoService frittoService;
    private final IngredienteService ingredienteService;

    public AdminController(PizzaService pizzaService, BibitaService bibitaService,
                           FrittoService frittoService, IngredienteService ingredienteService) {
        this.pizzaService = pizzaService;
        this.bibitaService = bibitaService;
        this.frittoService = frittoService;
        this.ingredienteService = ingredienteService;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("totalePizze", pizzaService.count());
        model.addAttribute("totaleBibite", bibitaService.count());
        model.addAttribute("totaleFritti", frittoService.count());
        model.addAttribute("totaleIngredienti", ingredienteService.count());
        return "admin/dashboard";
    }

    // ── GESTIONE PIZZE ────────────────────────────────────────────────

    @GetMapping("/pizze")
    public String gestisciPizze(Model model) {
        model.addAttribute("pizze", pizzaService.findAll());
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("tuttiGliIngredienti", ingredienteService.findAll());
        return "admin/pizze";
    }

    @PostMapping("/pizze/add")
    public String aggiungiPizza(@ModelAttribute Pizza pizza,
                                @RequestParam(value = "file", required = false) MultipartFile file,
                                @RequestParam(value = "ingredientiIds", required = false) List<Long> ingredientiIds) {
        try {
            if (file != null && !file.isEmpty()) {
                pizza.setImmagine(file.getBytes());
            }
            if (ingredientiIds != null) {
                pizza.setIngredienti(ingredienteService.findAllByIds(ingredientiIds));
            }
            pizzaService.save(pizza);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/pizze?error=upload";
        }
        return "redirect:/admin/pizze";
    }

    @PostMapping("/pizze/delete/{id}")
    public String eliminaPizza(@PathVariable Long id) {
        pizzaService.deleteById(id);
        return "redirect:/admin/pizze";
    }

    @PostMapping("/pizze/update")
    public String aggiornaPizza(@RequestParam Long id,
                                @RequestParam String nome,
                                @RequestParam String descrizione,
                                @RequestParam Double prezzo,
                                @RequestParam(value = "file", required = false) MultipartFile file,
                                @RequestParam(value = "ingredientiIds", required = false) List<Long> ingredientiIds) {
        try {
            Pizza pizza = pizzaService.findById(id);
            if (pizza == null) return "redirect:/admin/pizze?error=notfound";

            pizza.setNome(nome);
            pizza.setDescrizione(descrizione);
            pizza.setPrezzo(prezzo);

            if (file != null && !file.isEmpty()) {
                pizza.setImmagine(file.getBytes());
            }

            List<Ingrediente> ingredienti = (ingredientiIds != null)
                    ? ingredienteService.findAllByIds(ingredientiIds)
                    : new ArrayList<>();
            pizza.setIngredienti(ingredienti);

            pizzaService.save(pizza);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/pizze?error=upload";
        }
        return "redirect:/admin/pizze";
    }

    // ── GESTIONE BIBITE ───────────────────────────────────────────────

    @GetMapping("/bibite")
    public String gestisciBibite(Model model) {
        model.addAttribute("bibite", bibitaService.findAll());
        model.addAttribute("bibita", new Bibita());
        return "admin/bibite";
    }

    @PostMapping("/bibite/add")
    public String aggiungiBibita(@ModelAttribute Bibita bibita,
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                bibita.setImmagine(file.getBytes());
            }
            bibitaService.save(bibita);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/bibite?error=upload";
        }
        return "redirect:/admin/bibite";
    }

    @PostMapping("/bibite/delete/{id}")
    public String eliminaBibita(@PathVariable Long id) {
        bibitaService.deleteById(id);
        return "redirect:/admin/bibite";
    }

    @PostMapping("/bibite/update")
    public String aggiornaBibita(@RequestParam Long id,
                                 @RequestParam String nome,
                                 @RequestParam String descrizione,
                                 @RequestParam Double prezzo,
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Bibita bibita = bibitaService.findById(id);
            if (bibita == null) return "redirect:/admin/bibite?error=notfound";

            bibita.setNome(nome);
            bibita.setDescrizione(descrizione);
            bibita.setPrezzo(prezzo);

            if (file != null && !file.isEmpty()) {
                bibita.setImmagine(file.getBytes());
            }

            bibitaService.save(bibita);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/bibite?error=upload";
        }
        return "redirect:/admin/bibite";
    }

    // ── GESTIONE FRITTI ───────────────────────────────────────────────

    @GetMapping("/fritti")
    public String gestisciFritti(Model model) {
        model.addAttribute("fritti", frittoService.findAll());
        model.addAttribute("fritto", new Fritto());
        return "admin/fritti";
    }

    @PostMapping("/fritti/add")
    public String aggiungiFritto(@ModelAttribute Fritto fritto,
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                fritto.setImmagine(file.getBytes());
            }
            frittoService.save(fritto);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/fritti?error=upload";
        }
        return "redirect:/admin/fritti";
    }

    @PostMapping("/fritti/delete/{id}")
    public String eliminaFritto(@PathVariable Long id) {
        frittoService.deleteById(id);
        return "redirect:/admin/fritti";
    }

    @PostMapping("/fritti/update")
    public String aggiornaFritto(@RequestParam Long id,
                                 @RequestParam String nome,
                                 @RequestParam String descrizione,
                                 @RequestParam Double prezzo,
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Fritto fritto = frittoService.findById(id);
            if (fritto == null) return "redirect:/admin/fritti?error=notfound";

            fritto.setNome(nome);
            fritto.setDescrizione(descrizione);
            fritto.setPrezzo(prezzo);

            if (file != null && !file.isEmpty()) {
                fritto.setImmagine(file.getBytes());
            }

            frittoService.save(fritto);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/fritti?error=upload";
        }
        return "redirect:/admin/fritti";
    }

    // ── GESTIONE INGREDIENTI ──────────────────────────────────────────

    @GetMapping("/ingredienti")
    public String gestisciIngredienti(Model model) {
        model.addAttribute("ingredienti", ingredienteService.findAll());
        model.addAttribute("ingrediente", new Ingrediente());
        return "admin/ingredienti";
    }

    @PostMapping("/ingredienti/add")
    public String aggiungiIngrediente(@ModelAttribute Ingrediente ingrediente) {
        ingredienteService.save(ingrediente);
        return "redirect:/admin/ingredienti";
    }

    @PostMapping("/ingredienti/delete/{id}")
    public String eliminaIngrediente(@PathVariable Long id) {
        ingredienteService.deleteById(id);
        return "redirect:/admin/ingredienti";
    }
}
