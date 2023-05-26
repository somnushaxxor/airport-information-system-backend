package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import java.util.List;

public interface AirplaneMaintenanceOperationService {

    List<AirplaneMaintenanceOperation> getAllAirplaneMaintenanceOperationsBy();

    AirplaneMaintenanceOperation getAirplaneMaintenanceOperationById(Long id);

    void createAirplaneMaintenanceOperation(AirplaneMaintenanceOperationCreationRequest creationRequest);

    void updateAirplaneMaintenanceOperation(AirplaneMaintenanceOperationUpdateRequest updateRequest);

    void deleteAirplaneMaintenanceOperationById(Long id);

}
