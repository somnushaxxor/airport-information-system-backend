package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

public final class BrigadeMapper {

    private BrigadeMapper() {
    }

    public static BrigadeDto toDto(Brigade brigade) {
        return new BrigadeDto(brigade.getId(), brigade.getName(), brigade.getDepartment().getName(),
                brigade.getSpecialization().getName());
    }

}
