package dte.hostagechecker.exceptions.handler;

import dte.hostagechecker.exceptions.HostageFetchingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(HostageFetchingException exception)
    {
        return ErrorResponse.asResponseEntity(INTERNAL_SERVER_ERROR, "Could not fetch the hostage list.");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(Exception exception)
    {
        return ErrorResponse.asResponseEntity(INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}
