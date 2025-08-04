package org.example.Exceptions;

public class EmptyQueueException extends RuntimeException{
    public EmptyQueueException(){
        super("Queue is empty");
    }
}
