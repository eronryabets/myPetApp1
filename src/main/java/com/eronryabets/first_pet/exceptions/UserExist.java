package com.eronryabets.first_pet.exceptions;

public class UserExist extends RuntimeException{
    public UserExist(String message){
        super(message);
    }

    public UserExist() {
        super();
    }
}
