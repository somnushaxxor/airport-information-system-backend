package ru.nsu.fit.kolesnik.airportinformationsystem.flight;

import java.time.LocalDate;

public final class FlightMapper {

    private FlightMapper() {
    }

    public static FlightPreviewDto toPreviewDto(Flight flight) {
        String transferAirportName = null;
        String transferAirportCityName = null;
        if (flight.getRoute().getTransferAirport() != null) {
            transferAirportName = flight.getRoute().getTransferAirport().getName();
            transferAirportCityName = flight.getRoute().getTransferAirport().getCity().getName();
        }
        return new FlightPreviewDto(flight.getId(), flight.getAirplane().getId(),
                flight.getRoute().getDepartureAirport().getName(),
                flight.getRoute().getDepartureAirport().getCity().getName(),
                transferAirportName, transferAirportCityName, flight.getRoute().getArrivalAirport().getName(),
                flight.getRoute().getArrivalAirport().getCity().getName(), flight.getCategory().getName(),
                flight.getScheduledDepartureAt(), flight.getScheduledArrivalAt(), flight.getActualDepartureAt(),
                flight.getTicketPrice(), flight.getMinTicketsNumber());
    }

    public static FlightDto toDto(Flight flight) {
        LocalDate actualDepartureAt = null;
        if (flight.getActualDepartureAt() != null) {
            actualDepartureAt = flight.getActualDepartureAt().toLocalDate();
        }
        return new FlightDto(flight.getAirplane().getId(), flight.getRoute().getId(), flight.getCategory().getId(),
                flight.getScheduledDepartureAt().toLocalDate(), flight.getScheduledArrivalAt().toLocalDate(),
                actualDepartureAt, flight.getTicketPrice(), flight.getMinTicketsNumber());
    }

}
