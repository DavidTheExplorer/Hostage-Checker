package dte.hostagechecker.backend.exceptions;

import dte.hostagechecker.hostage.exceptions.HostageFetchingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ProblemDetail handle(HostageFetchingException exception)
    {
        LOGGER.error("Exception fetching the latest hostage list", exception.getCause());

        return ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Could not fetch the hostage list.");
    }

    @ExceptionHandler
    public ProblemDetail handle(Exception exception)
    {
        LOGGER.error("Unexpected exception occurred", exception);

        return ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    //prevent non-existent endpoints from spamming the console
    @ExceptionHandler
    public ProblemDetail handle(NoResourceFoundException exception)
    {
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, "No resource found.");
    }
}
