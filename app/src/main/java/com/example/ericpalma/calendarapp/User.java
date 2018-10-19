/*User class*/
package com.example.ericpalma.calendarapp;

public class User {
    /*Properties*/
    private String firstName;
    private String lastName;
    private String password;

    /*Initialize instance*/
    public User(String f, String l, String pass){
        this.firstName = f;
        this.lastName = l;
        this.password = pass;
    }

    public void changePassword(String p){
        if(!p.equals(""))
            this.password = p;
    }
}//end of User
