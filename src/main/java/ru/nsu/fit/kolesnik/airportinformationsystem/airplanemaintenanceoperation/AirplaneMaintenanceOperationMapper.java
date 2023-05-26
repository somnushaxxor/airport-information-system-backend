package ru.nsu.fit.kolesnik.airportinformationsystem.airplanemaintenanceoperation;

public final class AirplaneMaintenanceOperationMapper {

    private AirplaneMaintenanceOperationMapper() {
    }

    public static AirplaneMaintenanceOperationPreviewDto toPreviewDto(AirplaneMaintenanceOperation airplaneMaintenanceOperation) {
        return new AirplaneMaintenanceOperationPreviewDto(airplaneMaintenanceOperation.getId(),
                airplaneMaintenanceOperation.getDoneAt(), airplaneMaintenanceOperation.getRepairRequired(),
                airplaneMaintenanceOperation.getAirplane().getId());
    }

    public static AirplaneMaintenanceOperationDto toDto(AirplaneMaintenanceOperation airplaneMaintenanceOperation) {
        return new AirplaneMaintenanceOperationDto(airplaneMaintenanceOperation.getDoneAt(), airplaneMaintenanceOperation.getRepairRequired(),
                airplaneMaintenanceOperation.getAirplane().getId());
    }

}
