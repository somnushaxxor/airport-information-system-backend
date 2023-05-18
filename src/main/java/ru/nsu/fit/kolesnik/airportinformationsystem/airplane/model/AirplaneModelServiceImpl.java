package ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirplaneModelServiceImpl implements AirplaneModelService {

    private final AirplaneModelRepository airplaneModelRepository;

    @Override
    public List<AirplaneModel> getAllAirplaneModels() {
        return airplaneModelRepository.findAll();
    }

    @Override
    public AirplaneModel getAirplaneModelById(Long id) {
        return airplaneModelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Airplane model not found: " + id));
    }

    @Override
    public void createAirplaneModel(AirplaneModelCreationRequest creationRequest) {
        if (airplaneModelRepository.existsByName(creationRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Airplane model name must be unique: " + creationRequest.name());
        }
        AirplaneModel airplaneModel = new AirplaneModel();
        airplaneModel.setName(creationRequest.name());
        airplaneModel.setPassengersCapacity(creationRequest.passengersCapacity());
        airplaneModelRepository.save(airplaneModel);
    }

    @Override
    public void updateAirplaneModel(AirplaneModelUpdateRequest updateRequest) {
        AirplaneModel airplaneModel = getAirplaneModelById(updateRequest.id());
        if (airplaneModelRepository.existsByName(updateRequest.name())
                && !airplaneModel.getName().equals(updateRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Airplane model name must be unique: " + updateRequest.name());
        }
        airplaneModel.setName(updateRequest.name());
        airplaneModel.setPassengersCapacity(updateRequest.passengersCapacity());
        airplaneModelRepository.save(airplaneModel);
    }

    @Override
    public void deleteAirplaneModelById(Long id) {
        AirplaneModel airplaneModel = getAirplaneModelById(id);
        airplaneModelRepository.delete(airplaneModel);
    }

}
