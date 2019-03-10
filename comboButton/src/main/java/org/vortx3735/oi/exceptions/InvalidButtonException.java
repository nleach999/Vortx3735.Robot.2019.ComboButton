package org.vortx3735.oi.exceptions;


public class InvalidButtonException extends Exception
{
    private static final long serialVersionUID = -6497688926367966496L;

    public InvalidButtonException(Integer buttonNumber)
    {
        super (String.format ("Button %d is not valid.", buttonNumber) );
    }
}