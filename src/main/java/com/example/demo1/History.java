package com.example.demo1;

import java.util.Date;

public class History {
    public Date date;
    public int state;//0=buy 1=sell 2=pending
    ////نوع، مقدار و قیمت ارزو تاریخ
    public int typeToken;
    public int amount;
    public double price;
    public  History(int typeToken,int amount,double price,Date date,int state){
        this.amount=amount;
        this.date=date;
        this.typeToken=typeToken;
        this.price=price;
        this.state=state;
    }
}
