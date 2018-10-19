/*User class*/
package com.example.ericpalma.calendarapp;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"firstName","lastName"})
public class User {
    /*Primary Keys*/
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    /*Setters*/
    public User(String first, String last){
        this.firstName = first;
        this.lastName = last;
    }

    /*getters*/
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}

}//end of User
