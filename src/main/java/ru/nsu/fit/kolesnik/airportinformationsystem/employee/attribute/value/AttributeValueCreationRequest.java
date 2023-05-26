package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttributeValueCreationRequest(@NotNull Long attributeId, @NotNull Long employeeId,
                                            @NotBlank String value) {

}
