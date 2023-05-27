package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TicketUpdateRequest(@NotNull Long id, @NotNull Long flightId, @NotBlank String firstName,
                                  @NotBlank String lastName,
                                  @NotNull Long genderId, @NotNull LocalDate dateOfBirth,
                                  @NotBlank String localPassportNumber, @Nullable String internationalPassportNumber,
                                  @Min(0) @NotNull Integer seat, @NotNull Boolean baggage) {

}
