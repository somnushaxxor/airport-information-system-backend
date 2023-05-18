package ru.nsu.fit.kolesnik.airportinformationsystem.specialization;

public final class SpecializationMapper {

    private SpecializationMapper() {
    }

    public static SpecializationDto toDto(Specialization specialization) {
        return new SpecializationDto(specialization.getName());
    }

    public static SpecializationPreviewDto toPreviewDto(Specialization specialization) {
        return new SpecializationPreviewDto(specialization.getId(), specialization.getName());
    }

}
