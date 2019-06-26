/*
 * Filename:	Accounts.java
 * Purpose:		Account class that holds all information pertaining to a user account
 * Author:      Eric Palma
 */
package com.example.ericpalma.calendarapp;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

@Entity
public class Accounts {
    /*Define primary key for RoomDatabase*/
    @PrimaryKey
    @NonNull
    private String username;

    private String password;
    private String calendarType;
    private String calendarColor;
    private String userFirstName;
    private String userLastName;

    /*Setters*/
    public Accounts(String userFirstName, String userLastName, String username, String password, String calendarType, String calendarColor){
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.username = username;
        this.password = password;
        this.calendarType = calendarType;
        this.calendarColor = calendarColor;
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
