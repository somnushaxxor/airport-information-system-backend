package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.flight.Flight;
import ru.nsu.fit.kolesnik.airportinformationsystem.flight.FlightService;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.Gender;
import ru.nsu.fit.kolesnik.airportinformationsystem.gender.GenderService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FlightService flightService;
    private final GenderService genderService;

    @Override
    public List<Ticket> getAllTicketsBy(Long flightId, Long genderId, Integer ageInYears) {
        return ticketRepository.findAllIgnoringNullBy(flightId, genderId, ageInYears);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + id));
    }

    @Override
    public void createTicket(TicketCreationRequest creationRequest) {
        Flight flight = flightService.getFlightById(creationRequest.flightId());
        Gender gender = genderService.getGenderById(creationRequest.genderId());
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setFirstName(creationRequest.firstName());
        ticket.setLastName(creationRequest.lastName());
        ticket.setGender(gender);
        ticket.setDateOfBirth(creationRequest.dateOfBirth());
        ticket.setLocalPassportNumber(creationRequest.localPassportNumber());
        String internationalPassportNumber = null;
        if (!creationRequest.internationalPassportNumber().isBlank()) {
            internationalPassportNumber = creationRequest.internationalPassportNumber();
        }
        ticket.setInternationalPassportNumber(internationalPassportNumber);
        ticket.setSeat(creationRequest.seat());
        ticket.setBaggage(creationRequest.baggage());
        ticketRepository.save(ticket);
    }

    @Override
    public void updateTicket(TicketUpdateRequest updateRequest) {
        Ticket ticket = getTicketById(updateRequest.id());
        Flight flight = flightService.getFlightById(updateRequest.flightId());
        Gender gender = genderService.getGenderById(updateRequest.genderId());
        ticket.setFlight(flight);
        ticket.setFirstName(updateRequest.firstName());
        ticket.setLastName(updateRequest.lastName());
        ticket.setGender(gender);
        ticket.setDateOfBirth(updateRequest.dateOfBirth());
        ticket.setLocalPassportNumber(updateRequest.localPassportNumber());
        String internationalPassportNumber = null;
        if (!updateRequest.internationalPassportNumber().isBlank()) {
            internationalPassportNumber = updateRequest.internationalPassportNumber();
        }
        ticket.setInternationalPassportNumber(internationalPassportNumber);
        ticket.setSeat(updateRequest.seat());
        ticket.setBaggage(updateRequest.baggage());
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicketById(Long id) {
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }

}
