package com.example.ericpalma.calendarapp;

import android.arch.persistence.room.*;

@Entity
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
    @ColumnInfo
    private String userFirstName;
    @ColumnInfo
    private String userLastName;

    /*Setters*/
    public Accounts(String usrFirst, String usrLast, String usrName, String pass){
        this.userFirstName = usrFirst;
        this.userLastName = usrLast;
        this.username = usrName;
        this.password = pass;
        this.calendarType = "Month";
        this.calendarColor = "silver";
    }

    public Accounts(String usrFirst, String usrLast, String usrName, String pass, String calType, String calColor){
        this.userFirstName = usrFirst;
        this.userLastName = usrLast;
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

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public String getUserLastName() {
        return this.userLastName;
    }
}
