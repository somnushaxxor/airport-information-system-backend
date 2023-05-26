package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.Airplane;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.AirplaneService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirplaneMaintenanceOperationServiceImpl implements AirplaneMaintenanceOperationService {

    private final AirplaneMaintenanceOperationRepository airplaneMaintenanceOperationRepository;
    private final AirplaneService airplaneService;

    @Override
    public List<AirplaneMaintenanceOperation> getAllAirplaneMaintenanceOperationsBy() {
        return airplaneMaintenanceOperationRepository.findAll();
    }

    @Override
    public AirplaneMaintenanceOperation getAirplaneMaintenanceOperationById(Long id) {
        return airplaneMaintenanceOperationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airplane maintenance operation not found: " + id));
    }

    @Override
    public void createAirplaneMaintenanceOperation(AirplaneMaintenanceOperationCreationRequest creationRequest) {
        Airplane airplane = airplaneService.getAirplaneById(creationRequest.airplaneId());
        if (airplaneMaintenanceOperationRepository.existsByDoneAt(creationRequest.doneAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Airplane maintenance operation for this date is already registered");
        }
        AirplaneMaintenanceOperation airplaneMaintenanceOperation = new AirplaneMaintenanceOperation();
        airplaneMaintenanceOperation.setDoneAt(creationRequest.doneAt());
        airplaneMaintenanceOperation.setRepairRequired(creationRequest.repairRequired());
        airplaneMaintenanceOperation.setAirplane(airplane);
        airplaneMaintenanceOperationRepository.save(airplaneMaintenanceOperation);
    }

    @Override
    public void updateAirplaneMaintenanceOperation(AirplaneMaintenanceOperationUpdateRequest updateRequest) {
        AirplaneMaintenanceOperation airplaneMaintenanceOperation = getAirplaneMaintenanceOperationById(updateRequest.id());
        Airplane airplane = airplaneService.getAirplaneById(updateRequest.airplaneId());
        if (airplaneMaintenanceOperationRepository.existsByDoneAt(updateRequest.doneAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Airplane maintenance operation for this date is already registered");
        }
        airplaneMaintenanceOperation.setDoneAt(updateRequest.doneAt());
        airplaneMaintenanceOperation.setRepairRequired(updateRequest.repairRequired());
        airplaneMaintenanceOperation.setAirplane(airplane);
        airplaneMaintenanceOperationRepository.save(airplaneMaintenanceOperation);
    }

    @Override
    public void deleteAirplaneMaintenanceOperationById(Long id) {
        AirplaneMaintenanceOperation airplaneMaintenanceOperation = getAirplaneMaintenanceOperationById(id);
        airplaneMaintenanceOperationRepository.delete(airplaneMaintenanceOperation);
    }

}
