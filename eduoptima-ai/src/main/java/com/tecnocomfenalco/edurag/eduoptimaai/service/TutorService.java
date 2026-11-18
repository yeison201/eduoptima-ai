package com.tecnocomfenalco.edurag.eduoptimaai.service;

import com.tecnocomfenalco.edurag.eduoptimaai.model.Tutor;
import com.tecnocomfenalco.edurag.eduoptimaai.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    public Tutor findById(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));
    }

    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public void deleteById(Long id) {
        tutorRepository.deleteById(id);
    }
}