package dte.hostagechecker.hostage.exceptions;

import java.io.Serial;

public class HostageFetchingException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = -3761393991921381009L;

    public HostageFetchingException(Throwable cause)
    {
        super(cause);
    }
}
