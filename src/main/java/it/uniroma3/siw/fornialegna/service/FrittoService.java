package it.uniroma3.siw.fornialegna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.fornialegna.repository.FrittoRepository;
import it.uniroma3.siw.fornialegna.model.Fritto;
import java.util.List;

@Service
public class FrittoService {
    
    @Autowired
    private FrittoRepository frittoRepository;

    public Fritto save(Fritto fritto) {
        return frittoRepository.save(fritto);
    }

    public Fritto findById(Long id) {
        return frittoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        frittoRepository.deleteById(id);
    }

    public List<Fritto> findAll() {
        return frittoRepository.findAll();
    }

    public List<Fritto> findAllSortedByNome() {
        return frittoRepository.findAllByOrderByNomeAsc();
    }

    public List<Fritto> findAllSortedByPrezzo() {
        return frittoRepository.findAllByOrderByPrezzoAsc();
    }

}
