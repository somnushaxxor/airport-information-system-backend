package ru.nsu.fit.kolesnik.airportinformationsystem.airplane.model;

public final class AirplaneModelMapper {

    private AirplaneModelMapper() {
    }

    public static AirplaneModelDto toDto(AirplaneModel airplaneModel) {
        return new AirplaneModelDto(airplaneModel.getName(), airplaneModel.getPassengersCapacity());
    }

    public static AirplaneModelPreviewDto toPreviewDto(AirplaneModel airplaneModel) {
        return new AirplaneModelPreviewDto(airplaneModel.getId(), airplaneModel.getName(),
                airplaneModel.getPassengersCapacity());
    }

}
