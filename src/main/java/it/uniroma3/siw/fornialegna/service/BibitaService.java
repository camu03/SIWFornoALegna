package it.uniroma3.siw.fornialegna.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.fornialegna.model.Bibita;
import it.uniroma3.siw.fornialegna.repository.BibitaRepository;
import java.util.List;

@Service
public class BibitaService {

    private final BibitaRepository bibitaRepository;

    public BibitaService(BibitaRepository bibitaRepository) {
        this.bibitaRepository = bibitaRepository;
    }

    @Transactional
    public Bibita save(Bibita bibita) {
        return bibitaRepository.save(bibita);
    }

    @Transactional(readOnly = true)
    public Bibita findById(Long id) {
        return bibitaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        bibitaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Bibita> findAll() {
        return bibitaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public long count() {
        return bibitaRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Bibita> findAllSortedByNome() {
        return bibitaRepository.findAllByOrderByNomeAsc();
    }

    @Transactional(readOnly = true)
    public List<Bibita> findAllSortedByPrezzo() {
        return bibitaRepository.findAllByOrderByPrezzoAsc();
    }
}
