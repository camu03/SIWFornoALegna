package it.uniroma3.siw.fornialegna.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.fornialegna.model.User;
import it.uniroma3.siw.fornialegna.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("DEBUG: Tentativo login con username: " + username);
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            System.out.println("DEBUG: Utente NON trovato!");
            throw new UsernameNotFoundException("Utente non trovato: " + username);
        }

        System.out.println("DEBUG: Utente trovato! Role: " + user.getRole());
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + user.getRole())
                ))
                .build();
    }
}
