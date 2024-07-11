package com.example.demo1;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class EX {
    private final ObjectProperty<LocalDate> date;
    private final ObjectProperty<LocalTime> time;
    private final ObjectProperty<Object> sellbuy;
    private final ObjectProperty<Double> amount;
    private final ObjectProperty<String> state;
    private final ObjectProperty<Integer> price;

    public EX(LocalDate date, LocalTime time, Integer sellbuy, Double amount, Integer price, String state) {
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleObjectProperty<>(time);
        this.sellbuy = new SimpleObjectProperty<>(sellbuy);
        this.amount = new SimpleObjectProperty<>(amount);
        this.price = new SimpleObjectProperty<>(price);
        this.state = new SimpleObjectProperty<>(state);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public LocalTime getTime() {
        return time.get();
    }

    public void setTime(LocalTime time) {
        this.time.set(time);
    }

    public Object getSellbuy() {
        return sellbuy.get();
    }

    public void setSellbuy(Object sellbuy) {
        this.sellbuy.set(sellbuy);
    }

    public Double getAmount() {
        return amount.get();
    }

    public void setAmount(Double amount) {
        this.amount.set(amount);
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public Integer getPrice() {
        return price.get();
    }

    public void setPrice(Integer price) {
        this.price.set(price);
    }
}
