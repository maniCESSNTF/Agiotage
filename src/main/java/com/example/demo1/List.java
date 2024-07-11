package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class List {
    private final SimpleStringProperty persone;
    private final SimpleIntegerProperty number;

    public List(String persone, Integer number) {
        this.persone = new SimpleStringProperty(persone);
        this.number = new SimpleIntegerProperty(number);
    }

    public String getPersone() {
        return persone.get();
    }

    public void setPersone(String persone) {
        this.persone.set(persone);
    }

    public Integer getNumber() {
        return number.get();
    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }
}
