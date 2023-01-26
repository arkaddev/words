package com.example.wordsApp.exception;

public class UserDeleteException extends RuntimeException {

    public UserDeleteException(String username){
        super("Problem removing user: " + username);
    }
}
