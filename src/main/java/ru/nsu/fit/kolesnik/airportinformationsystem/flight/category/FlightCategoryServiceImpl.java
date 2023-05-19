package ru.nsu.fit.kolesnik.airportinformationsystem.flight.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightCategoryServiceImpl implements FlightCategoryService {

    private final FlightCategoryRepository flightCategoryRepository;

    @Override
    public List<FlightCategory> getAllFlightCategories() {
        return flightCategoryRepository.findAll();
    }

    @Override
    public FlightCategory getFlightCategoryById(Long id) {
        return flightCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight category not found: " + id));
    }

    @Override
    public void createFlightCategory(FlightCategoryCreationRequest creationRequest) {
        if (flightCategoryRepository.existsByName(creationRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Flight category name must be unique: " + creationRequest.name());
        }
        FlightCategory flightCategory = new FlightCategory();
        flightCategory.setName(creationRequest.name());
        flightCategoryRepository.save(flightCategory);
    }

    @Override
    public void updateFlightCategory(FlightCategoryUpdateRequest updateRequest) {
        FlightCategory flightCategory = getFlightCategoryById(updateRequest.id());
        if (flightCategoryRepository.existsByName(updateRequest.name())
                && !flightCategory.getName().equals(updateRequest.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Flight category name must be unique: " + updateRequest.name());
        }
        flightCategory.setName(updateRequest.name());
        flightCategoryRepository.save(flightCategory);
    }

    @Override
    public void deleteFlightCategoryById(Long id) {
        FlightCategory flightCategory = getFlightCategoryById(id);
        flightCategoryRepository.delete(flightCategory);
    }

}
