package ru.nsu.fit.kolesnik.airportinformationsystem.gender;

public final class GenderMapper {

    private GenderMapper() {
    }

    public static GenderDto toDto(Gender gender) {
        return new GenderDto(gender.getName());
    }

}
