package ru.nsu.fit.kolesnik.airportinformationsystem.route;

import java.util.List;

public interface RouteService {

    List<Route> getAllRoutes();

    Route getRouteById(Long id);

    void createRoute(RouteCreationRequest creationRequest);

    void updateRoute(RouteUpdateRequest updateRequest);

    void deleteRouteById(Long id);

}
