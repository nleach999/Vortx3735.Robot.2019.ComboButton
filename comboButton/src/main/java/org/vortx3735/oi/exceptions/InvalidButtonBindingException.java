package org.vortx3735.oi.exceptions;


public class InvalidButtonBindingException extends Exception
{
    private static final long serialVersionUID = -2413306487966020834L;

    public InvalidButtonBindingException(String reason)
    {
        super (reason);
    }
}