package ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model;

import java.util.List;

public interface AirplaneModelService {

    List<AirplaneModel> getAllAirplaneModels();

    AirplaneModel getAirplaneModelById(Long id);

    void createAirplaneModel(AirplaneModelCreationRequest creationRequest);

    void updateAirplaneModel(AirplaneModelUpdateRequest updateRequest);

    void deleteAirplaneModelById(Long id);

}
