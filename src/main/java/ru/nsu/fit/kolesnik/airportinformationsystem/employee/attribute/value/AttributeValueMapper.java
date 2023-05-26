package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value;

public final class AttributeValueMapper {

    private AttributeValueMapper() {
    }

    public static AttributeValuePreviewDto toPreviewDto(AttributeValue attributeValue) {
        return new AttributeValuePreviewDto(attributeValue.getId(), attributeValue.getAttribute().getName(),
                attributeValue.getEmployee().getFirstName(), attributeValue.getEmployee().getLastName(),
                attributeValue.getValue());
    }

    public static AttributeValueDto toDto(AttributeValue attributeValue) {
        return new AttributeValueDto(attributeValue.getAttribute().getId(), attributeValue.getEmployee().getId(),
                attributeValue.getValue());
    }

}
