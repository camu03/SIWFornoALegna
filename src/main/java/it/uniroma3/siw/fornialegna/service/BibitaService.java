package it.uniroma3.siw.fornialegna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.fornialegna.model.Bibita;
import it.uniroma3.siw.fornialegna.repository.BibitaRepository; 
import java.util.List;

@Service
public class BibitaService {
    
    @Autowired
    private BibitaRepository bibitaRepository;

    public Bibita save(Bibita bibita) {
        return bibitaRepository.save(bibita);
    }

    public Bibita findById(Long id) {
        return bibitaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        bibitaRepository.deleteById(id);
    }

    public List<Bibita> findAll() {
        return bibitaRepository.findAll();
    }

    public List<Bibita> findAllSortedByNome() {
        return bibitaRepository.findAllByOrderByNomeAsc();
    }

    public List<Bibita> findAllSortedByPrezzo() {
        return bibitaRepository.findAllByOrderByPrezzoAsc();
    }
}
