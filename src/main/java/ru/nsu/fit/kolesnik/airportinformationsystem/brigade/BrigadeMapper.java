package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

public final class BrigadeMapper {

    private BrigadeMapper() {
    }

    public static BrigadePreviewDto toPreviewDto(Brigade brigade) {
        return new BrigadePreviewDto(brigade.getId(), brigade.getName(), brigade.getDepartment().getName(),
                brigade.getSpecialization().getName());
    }

    public static BrigadeDto toDto(Brigade brigade) {
        return new BrigadeDto(brigade.getName(), brigade.getDepartment().getId(), brigade.getSpecialization().getId());
    }

}
