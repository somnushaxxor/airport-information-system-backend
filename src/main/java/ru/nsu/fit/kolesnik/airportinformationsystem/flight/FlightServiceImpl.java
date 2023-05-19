package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public List<Flight> getAllFlightsBy() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found: " + id));
    }

    @Override
    public void createFlight(FlightCreationRequest creationRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFlight(FlightUpdateRequest updateRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteFlightById(Long id) {
        throw new UnsupportedOperationException();
    }

}
