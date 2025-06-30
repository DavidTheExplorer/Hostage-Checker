package dte.hostagechecker.hostage.exceptions;

import dte.hostagechecker.hostage.listprovider.HostageListProvider;

import java.io.Serial;

public class HostageFetchingException extends RuntimeException
{
    private final HostageListProvider listProvider;

    @Serial
    private static final long serialVersionUID = -3761393991921381009L;

    public HostageFetchingException(Throwable cause, HostageListProvider listProvider)
    {
        super(cause);

        this.listProvider = listProvider;
    }

    public HostageListProvider getListProvider()
    {
        return this.listProvider;
    }
}
