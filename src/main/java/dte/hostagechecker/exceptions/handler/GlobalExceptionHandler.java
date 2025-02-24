package dte.hostagechecker.exceptions.handler;

import dte.hostagechecker.exceptions.HostageFetchingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(HostageFetchingException exception)
    {
        LOGGER.error("Exception fetching the hostage list from \"{}\"", exception.getListProvider().getClass().getSimpleName(), exception.getCause());

        return ErrorResponse.asResponseEntity(INTERNAL_SERVER_ERROR, "Could not fetch the hostage list.");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(Exception exception)
    {
        LOGGER.error("Unexpected exception occurred", exception);

        return ErrorResponse.asResponseEntity(INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    //prevent non-existent endpoints from spamming the console
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(NoResourceFoundException exception)
    {
        return ErrorResponse.asResponseEntity(NOT_FOUND, "No resource found.");
    }
}
