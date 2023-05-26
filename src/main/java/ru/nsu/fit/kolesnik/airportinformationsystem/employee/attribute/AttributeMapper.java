package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute;

public final class AttributeMapper {

    private AttributeMapper() {
    }

    public static AttributePreviewDto toPreviewDto(Attribute attribute) {
        return new AttributePreviewDto(attribute.getId(), attribute.getName(), attribute.getSpecialization().getName());
    }

    public static AttributeDto toDto(Attribute attribute) {
        return new AttributeDto(attribute.getName(), attribute.getSpecialization().getId());
    }

}
