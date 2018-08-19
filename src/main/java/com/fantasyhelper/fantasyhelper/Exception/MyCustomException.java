package com.fantasyhelper.fantasyhelper.Exception;

import org.springframework.stereotype.Service;


public class MyCustomException extends Exception{

    public MyCustomException(String message) {
        super(message);
    }

}
