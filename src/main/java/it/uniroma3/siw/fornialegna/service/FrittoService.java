package it.uniroma3.siw.fornialegna.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.fornialegna.repository.FrittoRepository;
import it.uniroma3.siw.fornialegna.model.Fritto;
import java.util.List;

@Service
public class FrittoService {

    private final FrittoRepository frittoRepository;

    public FrittoService(FrittoRepository frittoRepository) {
        this.frittoRepository = frittoRepository;
    }

    @Transactional
    public Fritto save(Fritto fritto) {
        return frittoRepository.save(fritto);
    }

    @Transactional(readOnly = true)
    public Fritto findById(Long id) {
        return frittoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        frittoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Fritto> findAll() {
        return frittoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Fritto> findTop10ByOrderByIdAsc() {
        return frittoRepository.findTop10ByOrderByIdAsc();
    }

    @Transactional(readOnly = true)
    public long count() {
        return frittoRepository.count();
    }

    /*@Transactional(readOnly = true)
    public List<Fritto> findAllSortedByNome() {
        return frittoRepository.findAllByOrderByNomeAsc();
    }

    @Transactional(readOnly = true)
    public List<Fritto> findAllSortedByPrezzo() {
        return frittoRepository.findAllByOrderByPrezzoAsc();
    }*/

}
