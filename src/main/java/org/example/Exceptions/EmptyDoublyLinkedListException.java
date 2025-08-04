package org.example.Exceptions;

public  class EmptyDoublyLinkedListException extends RuntimeException {
    public EmptyDoublyLinkedListException() {
        super("List is empty");
    }
}