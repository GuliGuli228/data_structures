package org.example.Exceptions;

public  class EmptyLinkedListException extends RuntimeException {
    public EmptyLinkedListException() {
        super("List is empty");
    }
}