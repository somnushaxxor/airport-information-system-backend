package ru.nsu.fit.kolesnik.airportinformationsystem.department;

public final class DepartmentMapper {

    private DepartmentMapper() {
    }

    public static DepartmentPreviewDto toPreviewDto(Department department) {
        String chiefName = null;
        if (department.getChief() != null) {
            chiefName = department.getChief().getFirstName() + " " + department.getChief().getLastName();
        }
        return new DepartmentPreviewDto(department.getId(), department.getName(), chiefName);
    }

    public static DepartmentDto toDto(Department department) {
        return new DepartmentDto(department.getName());
    }

}
