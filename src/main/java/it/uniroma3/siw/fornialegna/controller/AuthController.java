package it.uniroma3.siw.fornialegna.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.fornialegna.model.User;
import it.uniroma3.siw.fornialegna.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Controller
public class AuthController {
       
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        // 1. Cripta la password
        String passwordCriptata = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordCriptata);
        
        // 2. Salva l'utente nel database
        userRepository.save(user);
        
        // 3. Reindirizza al login
        return "redirect:/login";
    }
}
