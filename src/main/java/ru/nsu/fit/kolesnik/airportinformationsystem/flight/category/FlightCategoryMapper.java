package ru.nsu.fit.kolesnik.airportinformationsystem.flight.category;

public final class FlightCategoryMapper {

    private FlightCategoryMapper() {
    }

    public static FlightCategoryPreviewDto toPreviewDto(FlightCategory flightCategory) {
        return new FlightCategoryPreviewDto(flightCategory.getId(), flightCategory.getName());
    }

    public static FlightCategoryDto toDto(FlightCategory flightCategory) {
        return new FlightCategoryDto(flightCategory.getName());
    }

}
