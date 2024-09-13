package dte.hostagechecker.exceptions.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public record ErrorResponse(String error)
{
    public static ResponseEntity<ErrorResponse> asResponseEntity(HttpStatusCode statusCode, String errorMessage)
    {
        ErrorResponse response = new ErrorResponse(errorMessage);

        return new ResponseEntity<>(response, statusCode);
    }
}
