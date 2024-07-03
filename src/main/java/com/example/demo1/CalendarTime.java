package com.example.demo1;

import javax.crypto.spec.PSource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTime {


    public String dateNow(long milli){
        Calendar customTime = Calendar.getInstance();
        customTime.setTimeInMillis(milli);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return("Current time: " + customTime);
    }


    public String formatDate(long milli) {
        Calendar customTime = Calendar.getInstance();
        customTime.setTimeInMillis(milli);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(customTime.getTime());
        return  formattedDate;
    }

    public String formatTime(long milli) {
        Calendar customTime = Calendar.getInstance();
        customTime.setTimeInMillis(milli);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = timeFormat.format(customTime.getTime());
        return formattedTime;
    }

    public String formatTime35(long milli) {
        Calendar customTime = Calendar.getInstance();
        customTime.setTimeInMillis(milli);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:35");
        String formattedTime = timeFormat.format(customTime.getTime());
        return formattedTime;
    }


    public long dif(int year,int month,int dey,int hour,int minute,int sec) {
        CalendarTime currentTime = new CalendarTime();
        // currentTime.calculateRemainingTime(Calendar.getInstance());
        Calendar customTime = Calendar.getInstance();
        customTime.set(year,month,dey,hour,minute,sec);
        Calendar current = Calendar.getInstance();
        long dif = customTime.getTimeInMillis() - current.getTimeInMillis();
        return dif;


    }

    public long now(){
        CalendarTime currentTime = new CalendarTime();
        Calendar customTime = Calendar.getInstance();
        return  customTime.getTimeInMillis();
    }

    public CalendarTime() {
    }

    public static void main(String[] args) {
        CalendarTime s=new CalendarTime();
        long t=s.now();
        long g=t-1576000000L;
        String datetime = s + " " + "ff";

        System.out.println("-----------------------"+s.formatTime(1718196335501L));

        String dateNow= s.dateNow(g);
        System.out.println(dateNow);
        String dateNow1= s.formatDate(g);
        System.out.println(dateNow1);

        String dateNow11= s.formatTime(g);
        System.out.println(dateNow11);
    }
}
