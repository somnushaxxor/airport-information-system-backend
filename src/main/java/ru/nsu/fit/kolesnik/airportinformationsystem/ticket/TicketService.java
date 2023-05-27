package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTicketsBy(Long flightId, Long genderId, Integer ageInYears);

    Ticket getTicketById(Long id);

    void createTicket(TicketCreationRequest creationRequest);

    void updateTicket(TicketUpdateRequest updateRequest);

    void deleteTicketById(Long id);

}
