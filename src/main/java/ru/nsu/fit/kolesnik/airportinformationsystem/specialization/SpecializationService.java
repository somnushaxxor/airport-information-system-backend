package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

import java.util.List;

public interface SpecializationService {

    List<Specialization> getAllSpecializations();

    Specialization getSpecializationById(Long id);

}
