package com.lcwd.electronic.store.exception;

public class BadAPIRequest extends  RuntimeException{
    public BadAPIRequest(String message)
    {
        super(message);
    }
    public BadAPIRequest()
    {
        super("Bad Request !!");
    }
}
