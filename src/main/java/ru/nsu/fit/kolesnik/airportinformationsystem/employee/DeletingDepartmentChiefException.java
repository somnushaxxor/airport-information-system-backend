package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeletingDepartmentChiefException extends RuntimeException {

    public DeletingDepartmentChiefException() {
        super("Unable to delete the chief of the department");
    }

}
