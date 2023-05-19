package ru.nsu.fit.kolesnik.airportinformationsystem.airport;

public final class AirportMapper {

    private AirportMapper() {
    }

    public static AirportDto toDto(Airport airport) {
        return new AirportDto(airport.getId(), airport.getName(), airport.getCity().getName());
    }

}
