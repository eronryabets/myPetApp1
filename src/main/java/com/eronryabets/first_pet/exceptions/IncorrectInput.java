package com.eronryabets.first_pet.exceptions;

public class IncorrectInput extends RuntimeException{
    public IncorrectInput(String message){
        super(message);
    }

    public IncorrectInput() {
        super();
    }
}
