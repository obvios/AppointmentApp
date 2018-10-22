package com.example.ericpalma.calendarapp;
import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"apptUserName","year","month","day","time"},
        foreignKeys = @ForeignKey(entity = Accounts.class,
                                    parentColumns = "username",
                                    childColumns = "apptUserName",
                                    onDelete = CASCADE))
public class Appointments {
    /*Columns*/
    @NonNull
    private String year;
    @NonNull
    private String month;
    @NonNull
    private String day;
    @NonNull
    private String time;
    @NonNull
    private String apptUserName;

    /*Setters*/
    public Appointments(String year, String month, String day, String time, String apptUserName){
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.apptUserName = apptUserName;
    }

    public void setApptYear(String yr){
        this.year = yr;
    }

    public void setApptMonth(String m){
        this.month = m;
    }

    public void setApptDay(String d){
        this.day = d;
    }

    public void setApptTime(String t) {
        this.time = t;
    }

    /*Getters*/
    public String getApptUserName() {
        return this.apptUserName;
    }

    public String getYear() {
        return this.year;
    }

    public String getMonth() {
        return this.month;
    }

    public String getDay() {
        return this.day;
    }

    public String getTime() {
        return this.time;
    }
}
