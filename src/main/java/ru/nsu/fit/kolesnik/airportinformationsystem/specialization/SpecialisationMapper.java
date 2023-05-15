package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

public final class SpecialisationMapper {

    private SpecialisationMapper() {
    }

    public static SpecializationDto toDto(Specialization specialization) {
        return new SpecializationDto(specialization.getId(), specialization.getName());
    }

}
