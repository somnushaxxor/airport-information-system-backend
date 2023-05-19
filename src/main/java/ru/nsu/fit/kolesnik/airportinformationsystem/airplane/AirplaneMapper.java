package ru.nsu.fit.kolesnik.airportinformationsystem.airplane;

public final class AirplaneMapper {

    private AirplaneMapper() {
    }

    public static AirplanePreviewDto toPreviewDto(Airplane airplane) {
        return new AirplanePreviewDto(airplane.getId(), airplane.getModel().getName(), airplane.getCreatedAt(),
                airplane.getJoinedAt(), airplane.getPilotsBrigade().getName(),
                airplane.getTechniciansBrigade().getName(), airplane.getServiceBrigade().getName(),
                airplane.getHomeAirport().getName(), airplane.getHomeAirport().getCity().getName());
    }

    public static AirplaneDto toDto(Airplane airplane) {
        return new AirplaneDto(airplane.getModel().getId(), airplane.getCreatedAt(),
                airplane.getJoinedAt(), airplane.getPilotsBrigade().getId(),
                airplane.getTechniciansBrigade().getId(), airplane.getServiceBrigade().getId(),
                airplane.getHomeAirport().getId());
    }

}
