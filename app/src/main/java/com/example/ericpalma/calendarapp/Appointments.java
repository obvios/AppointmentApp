package com.example.ericpalma.calendarapp;
import android.arch.persistence.room.*;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"apptUserName","year","month","day","time"},
        foreignKeys = @ForeignKey(entity = Accounts.class,
                                    parentColumns = "username",
                                    childColumns = "apptUserName",
                                    onDelete = CASCADE))
public class Appointments {
    /*Columns*/
    private String year;
    private String month;
    private String day;
    private String time;
    private String apptUserName;

    /*Setters*/
    public Appointments(String apptYr, String apptMon, String apptDay, String apptTime, String theUsername){
        this.year = apptYr;
        this.month = apptMon;
        this.day = apptDay;
        this.time = apptTime;
        this.apptUserName = theUsername;
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

    public String getApptYear() {
        return this.year;
    }

    public String getApptMonth() {
        return this.month;
    }

    public String getApptDay() {
        return this.day;
    }

    public String getApptTime() {
        return this.time;
    }
}
