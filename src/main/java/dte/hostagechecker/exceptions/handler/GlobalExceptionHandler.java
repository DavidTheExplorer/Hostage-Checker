package dte.hostagechecker.exceptions.handler;

import dte.hostagechecker.exceptions.HostageFetchingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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
        exception.printStackTrace();

        return ErrorResponse.asResponseEntity(INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    //prevent non-existent endpoints from spamming the console
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(NoResourceFoundException exception)
    {
        return ErrorResponse.asResponseEntity(NOT_FOUND, "No resource found.");
    }
}
