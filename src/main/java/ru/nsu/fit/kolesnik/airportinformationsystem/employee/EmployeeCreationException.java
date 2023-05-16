package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeCreationException extends RuntimeException {

    public EmployeeCreationException(String message) {
        super(message);
    }

}
