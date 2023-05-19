package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model.AirplaneModel;
import ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model.AirplaneModelService;
import ru.nsu.fit.kolesnik.airportinformationsystem.airport.Airport;
import ru.nsu.fit.kolesnik.airportinformationsystem.airport.AirportService;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.Brigade;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.BrigadeService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final AirplaneModelService airplaneModelService;
    private final BrigadeService brigadeService;
    private final AirportService airportService;
    @Value("${specializations.core.pilot}")
    private String pilotSpecializationName;
    @Value("${specializations.core.technician}")
    private String technicianSpecializationName;
    @Value("${specializations.core.service}")
    private String serviceSpecializationName;

    @Override
    public List<Airplane> getAllAirplanesBy(Long homeAirportId, LocalDate joinedAt, Integer flightsNumber) {
        return airplaneRepository.findAllIgnoringNullBy(homeAirportId, joinedAt, flightsNumber);
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airplane not found: " + id));
    }

    @Override
    public void createAirplane(AirplaneCreationRequest creationRequest) {
        AirplaneModel model = airplaneModelService.getAirplaneModelById(creationRequest.modelId());
        LocalDate joinedAt = LocalDate.now();
        if (isJoinedBeforeCreated(creationRequest.createdAt(), joinedAt)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Airplane can not be created after joining the airport");
        }
        Brigade pilotsBrigade = brigadeService.getBrigadeById(creationRequest.pilotsBrigadeId());
        if (!pilotsBrigade.getSpecialization().getName().equals(pilotSpecializationName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as pilots brigade is not specialized on pilots");
        }
        if (pilotsBrigade.getEmployees().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as pilots brigade is empty");
        }
        Brigade techniciansBrigade = brigadeService.getBrigadeById(creationRequest.techniciansBrigadeId());
        if (!techniciansBrigade.getSpecialization().getName().equals(technicianSpecializationName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as technicians brigade is not specialized on technicians");
        }
        if (techniciansBrigade.getEmployees().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as technicians brigade is empty");
        }
        Brigade serviceBrigade = brigadeService.getBrigadeById(creationRequest.serviceBrigadeId());
        if (!serviceBrigade.getSpecialization().getName().equals(serviceSpecializationName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as service brigade is not specialized on service");
        }
        if (serviceBrigade.getEmployees().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as service brigade is empty");
        }
        Airport homeAirport = airportService.getAirportById(creationRequest.homeAirportId());
        Airplane airplane = new Airplane();
        airplane.setModel(model);
        airplane.setCreatedAt(creationRequest.createdAt());
        airplane.setJoinedAt(joinedAt);
        airplane.setPilotsBrigade(pilotsBrigade);
        airplane.setTechniciansBrigade(techniciansBrigade);
        airplane.setServiceBrigade(serviceBrigade);
        airplane.setHomeAirport(homeAirport);
        airplaneRepository.save(airplane);
    }

    private boolean isJoinedBeforeCreated(LocalDate createdAt, LocalDate joinedAt) {
        return createdAt.isAfter(joinedAt);
    }

    @Override
    public void updateAirplane(AirplaneUpdateRequest updateRequest) {
        Airplane airplane = getAirplaneById(updateRequest.id());
        AirplaneModel model = airplaneModelService.getAirplaneModelById(updateRequest.modelId());
        if (isJoinedBeforeCreated(updateRequest.createdAt(), updateRequest.joinedAt())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Airplane can not be created after joining the airport");
        }
        Brigade pilotsBrigade = brigadeService.getBrigadeById(updateRequest.pilotsBrigadeId());
        if (!pilotsBrigade.getSpecialization().getName().equals(pilotSpecializationName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as pilots brigade is not specialized on pilots");
        }
        if (pilotsBrigade.getEmployees().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as pilots brigade is empty");
        }
        Brigade techniciansBrigade = brigadeService.getBrigadeById(updateRequest.techniciansBrigadeId());
        if (!techniciansBrigade.getSpecialization().getName().equals(technicianSpecializationName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as technicians brigade is not specialized on technicians");
        }
        if (techniciansBrigade.getEmployees().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as technicians brigade is empty");
        }
        Brigade serviceBrigade = brigadeService.getBrigadeById(updateRequest.serviceBrigadeId());
        if (!serviceBrigade.getSpecialization().getName().equals(serviceSpecializationName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as service brigade is not specialized on service");
        }
        if (serviceBrigade.getEmployees().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Brigade given as service brigade is empty");
        }
        Airport homeAirport = airportService.getAirportById(updateRequest.homeAirportId());
        airplane.setModel(model);
        airplane.setCreatedAt(updateRequest.createdAt());
        airplane.setJoinedAt(updateRequest.joinedAt());
        airplane.setPilotsBrigade(pilotsBrigade);
        airplane.setTechniciansBrigade(techniciansBrigade);
        airplane.setServiceBrigade(serviceBrigade);
        airplane.setHomeAirport(homeAirport);
        airplaneRepository.save(airplane);
    }

    @Override
    @Transactional
    public void deleteAirplaneById(Long id) {
        // TODO
        throw new UnsupportedOperationException();
    }

}
