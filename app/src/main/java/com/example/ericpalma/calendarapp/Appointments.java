package com.example.ericpalma.calendarapp;

public class Appointments {
    /*Properties*/
    String appointmentName;
    int month;
    int day;
    int hour;

    /*Initialize instance*/
    public Appointments(int name, int mon, int d, int hr){
        this.appointmentName = name;
        this.month = mon;
        this.day = d;
        this.hour = hr;
    }
}
