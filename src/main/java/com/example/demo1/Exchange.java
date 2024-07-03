//package com.example.demo1;
//
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleObjectProperty;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//public class Exchange {
//    private final ObjectProperty<LocalDate> date;
//    private final ObjectProperty<LocalTime> time;
//
//    public LocalTime getSellbuy() {
//        return sellbuy.get();
//    }
//
//    public ObjectProperty<LocalSellbuy> sellbuyProperty() {
//        return sellbuy;
//    }
//
//    public ObjectProperty<LocalTime> timeProperty() {
//        return time;
//    }
//
//    public ObjectProperty<LocalDate> dateProperty() {
//        return date;
//    }
//
//    public LocalTime getAmount() {
//        return amount.get();
//    }
//
//    public ObjectProperty<LocalTime> amountProperty() {
//        return amount;
//    }
//
//    public LocalTime getType() {
//        return type.get();
//    }
//
//    public ObjectProperty<LocalTime> typeProperty() {
//        return type;
//    }
//
//    public LocalTime getState() {
//        return state.get();
//    }
//
//    public ObjectProperty<LocalTime> stateProperty() {
//        return state;
//    }
//
//    public LocalTime getPrice() {
//        return price.get();
//    }
//
//    public ObjectProperty<LocalTime> priceProperty() {
//        return price;
//    }
//
//    private final ObjectProperty<LocalTime> sellbuy;
//
//    private final ObjectProperty<LocalSellbuy> type;
//    private final ObjectProperty<LocalTime> amount;
//    private final ObjectProperty<LocalTime> state;
//    private final ObjectProperty<LocalTime> price;
//
//
//    public Exchange(LocalDate date, LocalTime time,LocalSellbuy sellbuy,LocalTime type,LocalTime amount,LocalTime price,LocalTime state) {
//        this.date = new SimpleObjectProperty<>(date);
//        this.time = new SimpleObjectProperty<>(time);
//        this.sellbuy = new SimpleObjectProperty<>(sellbuy);
//        this.type = new SimpleObjectProperty<>(type);
//        this.amount = new SimpleObjectProperty<>(amount);
//        this.price = new SimpleObjectProperty<>(price);
//        this.state = new SimpleObjectProperty<>(state);
//    }
//
//    public LocalDate getDate() {
//        return date.get();
//    }
//
//    public void setDate(LocalDate date) {
//        this.date.set(date);
//    }
//
//    public LocalTime getTime() {
//        return time.get();
//    }
//
//    public LocalSellbuy getTime() {
//        return sellbuy.get();
//    }
//
//    public void setTime(LocalTime time) {
//        this.time.set(time);
//    }
//
//
//
//}


package com.example.demo1;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class Exchange {
    private final ObjectProperty<LocalDate> date;
    private final ObjectProperty<LocalTime> time;
    private final ObjectProperty<Object> sellbuy;
    private final ObjectProperty<String> type;
    private final ObjectProperty<Double> amount;
    private final ObjectProperty<String> state;
    private final ObjectProperty<Integer> price;

    public Exchange(LocalDate date, LocalTime time, Integer sellbuy, String type, Double amount, Integer price, String state) {
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleObjectProperty<>(time);
        this.sellbuy = new SimpleObjectProperty<>(sellbuy);
        this.type = new SimpleObjectProperty<>(type);
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

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
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
