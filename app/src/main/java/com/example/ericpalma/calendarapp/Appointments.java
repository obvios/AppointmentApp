/*
 * Filename:	Appointments.java
 * Purpose:		Class representing an appointment. Defines primary key and foreign keys.
 *              Notice the primary key includes a user's username from Accounts class.
 * Author:      Eric Palma
 */
package com.example.ericpalma.calendarapp;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"apptUserName","date","time"},
        foreignKeys = @ForeignKey(entity = Accounts.class,
                                    parentColumns = "username",
                                    childColumns = "apptUserName",
                                    onDelete = CASCADE))
public class Appointments {
    /*Columns*/
    @NonNull
    private String date;
    @NonNull
    private String time;
    @NonNull
    private String apptUserName;

    /*Setters*/
    public Appointments(String date, String time, String apptUserName){
        this.date = date;
        this.time = time;
        this.apptUserName = apptUserName;
    }

    public void setApptDate (String date) { this.date = date;}

    public void setApptTime(String t) { this.time = t; }

    /*Getters*/
    public String getApptUserName() { return this.apptUserName; }

    public String getDate() { return this.date;}

    public String getTime() { return this.time; }
}
