package ru.nsu.fit.kolesnik.airportinformationsystem.flight.category;

import java.util.List;

public interface FlightCategoryService {

    List<FlightCategory> getAllFlightCategories();

    FlightCategory getFlightCategoryById(Long id);

    void createFlightCategory(FlightCategoryCreationRequest creationRequest);

    void updateFlightCategory(FlightCategoryUpdateRequest updateRequest);

    void deleteFlightCategoryById(Long id);

}
