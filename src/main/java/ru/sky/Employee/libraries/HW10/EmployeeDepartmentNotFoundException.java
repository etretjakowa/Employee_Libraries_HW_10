package ru.sky.Employee.libraries.HW10;

public class EmployeeDepartmentNotFoundException extends RuntimeException{
    public EmployeeDepartmentNotFoundException(String message) {
        super(message);
    }
}