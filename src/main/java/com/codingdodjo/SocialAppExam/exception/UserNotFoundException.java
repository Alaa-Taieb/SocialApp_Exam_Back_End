package com.codingdodjo.SocialAppExam.exception;

/**
* UserNotFoundException.java
* This class represents an exception that is thrown when a user is not found in the system
*/
public class UserNotFoundException extends RuntimeException{
    /**
    * Constructor for UserNotFoundException
    * @param message the error message to be displayed
    */
    public UserNotFoundException(String message){
        super(message);
    }
}
