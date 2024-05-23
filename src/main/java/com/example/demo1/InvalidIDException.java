package com.example.demo1;

import javafx.scene.control.Alert;

public class InvalidIDException extends RuntimeException {
    public InvalidIDException(String message) {///هرسال alert به جای متن
        super(message);
    }

    public InvalidIDException() {
        super();
    }
}
