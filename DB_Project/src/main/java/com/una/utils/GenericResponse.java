package com.una.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    public T data;
    public boolean success;
    public String message;

    public ResponseEntity<GenericResponse<T>>
    buildResponse(T data, boolean success, String message, HttpStatus status) {
        GenericResponse<T> response = new GenericResponse<>();
        response.setData(data);
        response.setSuccess(success);
        response.setMessage(message);
        return ResponseEntity.status(status).body(response);
    }
}
