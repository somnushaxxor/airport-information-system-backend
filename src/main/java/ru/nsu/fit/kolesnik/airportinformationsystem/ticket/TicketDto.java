package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

import java.time.LocalDate;

public record TicketDto(Long flightId, String firstName, String lastName, Long genderId,
                        LocalDate dateOfBirth, String localPassportNumber, String internationalPassportNumber,
                        Integer seat, Boolean baggage) {

}
