package com.eronryabets.first_pet.exceptions;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(String message){
        super(message);
    }

    public WalletNotFoundException() {
        super();
    }
}
