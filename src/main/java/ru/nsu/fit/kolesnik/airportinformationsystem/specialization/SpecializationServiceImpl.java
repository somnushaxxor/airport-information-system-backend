package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;

    @Override
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization getSpecializationById(Long id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Specialization not found: " + id));
    }

}
