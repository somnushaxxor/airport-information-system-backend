package ru.nsu.fit.kolesnik.airportinformationsystem.route;

public final class RouteMapper {

    private RouteMapper() {
    }

    public static RoutePreviewDto toPreviewDto(Route route) {
        String transferAirportName = null;
        String transferAirportCityName = null;
        if (route.getTransferAirport() != null) {
            transferAirportName = route.getTransferAirport().getName();
            transferAirportCityName = route.getTransferAirport().getCity().getName();
        }
        return new RoutePreviewDto(route.getId(), route.getDepartureAirport().getName(),
                route.getDepartureAirport().getCity().getName(), transferAirportName, transferAirportCityName,
                route.getArrivalAirport().getName(), route.getArrivalAirport().getCity().getName());
    }

    public static RouteDto toDto(Route route) {
        Long transferAirportId = null;
        if (route.getTransferAirport() != null) {
            transferAirportId = route.getTransferAirport().getId();
        }
        return new RouteDto(route.getDepartureAirport().getId(), transferAirportId, route.getArrivalAirport().getId());
    }

}
