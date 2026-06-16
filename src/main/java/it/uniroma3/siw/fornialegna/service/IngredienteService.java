package it.uniroma3.siw.fornialegna.service;

import it.uniroma3.siw.fornialegna.model.Ingrediente;
import it.uniroma3.siw.fornialegna.repository.IngredienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Ingrediente> findAll() {
        return ingredienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Ingrediente> findAllByIds(List<Long> ids) {
        return ingredienteRepository.findAllById(ids);
    }

    @Transactional
    public void save(Ingrediente ingrediente) {
        ingredienteRepository.save(ingrediente);
    }

    @Transactional
    public void deleteById(Long id) {
        ingredienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long count() {
        return ingredienteRepository.count();
    }
}
