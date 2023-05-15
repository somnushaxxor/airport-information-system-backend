package ru.nsu.fit.kolesnik.airportinformationsystem.department;

public final class DepartmentMapper {

    private DepartmentMapper() {
    }

    public static DepartmentDto toDto(Department department) {
        return new DepartmentDto(department.getId(), department.getName(),
                department.getChief().getFirstName() + " " + department.getChief().getLastName());
    }

}
