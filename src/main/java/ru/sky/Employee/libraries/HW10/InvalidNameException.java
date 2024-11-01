package ru.sky.Employee.libraries.HW10;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Name is incorrect")
public class InvalidNameException extends IllegalArgumentException {
    public InvalidNameException(String name) {
        super("Invalid name" + name);
    }
}