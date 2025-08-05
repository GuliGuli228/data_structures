package org.example.Exceptions;

public class EmptyBinaryTreeException extends RuntimeException {
    public EmptyBinaryTreeException() {
        super("Tree is empty");
    }
}
