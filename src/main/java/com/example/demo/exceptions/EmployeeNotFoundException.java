package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
    private String employeeName;
    private String fieldName;
    private Object fieldValue;

    public EmployeeNotFoundException(String employeeName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", employeeName, fieldName, fieldValue));
        this.employeeName = employeeName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
