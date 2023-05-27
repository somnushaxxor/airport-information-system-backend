package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

import java.time.LocalDate;

public record TicketPreviewDto(Long id, Long flightNumber, String firstName, String lastName, String genderName,
                               LocalDate dateOfBirth, String localPassportNumber, String internationalPassportNumber,
                               Integer seat, Boolean baggage) {

}
