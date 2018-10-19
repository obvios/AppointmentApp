package com.example.ericpalma.calendarapp;

import android.arch.persistence.room.*;

public class Accounts {
    /*Properties*/
    @PrimaryKey
    private String username;
    @ColumnInfo
    private String password;
    @ColumnInfo
    private String calendarType;
    @ColumnInfo
    private String calendarColor;

    /*Setters*/
    public Accounts(String usrName, String pass){
        this.username = usrName;
        this.password = pass;
        this.calendarType = "Month";
        this.calendarColor = "silver";
    }

    public Accounts(String usrName, String pass, String calType, String calColor){
        this.username = usrName;
        this.password = pass;
        this.calendarType = calType;
        this.calendarColor = calColor;
    }

    public void setCalendarType(String type){
        this.calendarType = type;
    }

    public void setCalendarColor(String color){
        this.calendarColor = color;
    }

    /*Getters*/
    public String getUsername() {
        return this.username;
    }

    public String getPassword(){
        return  this.password;
    }

    public String getCalendarType() {
        return this.calendarType;
    }

    public String getCalendarColor() {
        return this.calendarColor;
    }
}
