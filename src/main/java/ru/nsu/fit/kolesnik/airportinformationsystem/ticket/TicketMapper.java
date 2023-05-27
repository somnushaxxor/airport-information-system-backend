package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

public final class TicketMapper {

    private TicketMapper() {
    }

    public static TicketPreviewDto toPreviewDto(Ticket ticket) {
        return new TicketPreviewDto(ticket.getId(), ticket.getFlight().getId(), ticket.getFirstName(),
                ticket.getLastName(), ticket.getGender().getName(), ticket.getDateOfBirth(),
                ticket.getLocalPassportNumber(), ticket.getInternationalPassportNumber(), ticket.getSeat(),
                ticket.getBaggage());
    }

    public static TicketDto toDto(Ticket ticket) {
        return new TicketDto(ticket.getFlight().getId(), ticket.getFirstName(), ticket.getLastName(),
                ticket.getGender().getId(), ticket.getDateOfBirth(), ticket.getLocalPassportNumber(),
                ticket.getInternationalPassportNumber(), ticket.getSeat(), ticket.getBaggage());
    }

}
