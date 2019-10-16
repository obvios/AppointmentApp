/*
 * Filename:	AppViewModel.java
 * Purpose:		The application's view model. Handles interactions between
 *              application view and data repository.
 * Author:      Eric Palma
 */
package com.example.ericpalma.calendarapp;

import android.app.Application;
import android.app.TaskStackBuilder;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;

public class AppViewModel extends AndroidViewModel {
    //the application's data repository
    private DataRepository dataRepository;

    //Constructor
    //registers the application with the view model.
    //Connects the application with the data repository
    public AppViewModel (Application application){
        super(application);
        dataRepository = new DataRepository(application);
    }

    public void insert(Accounts account){
        dataRepository.insertAccount(account);
    }

    public void changeUsername(String oldUsername, String newUsername, String password){
        dataRepository.changeUsername(oldUsername,newUsername, password);
    }

    public void changePassword(String username, String currentPassword, String newPassword){
        dataRepository.changePassword(username,currentPassword,newPassword);
    }

    public void changeCalendarViewType(String user, String password, String newType){
        dataRepository.changeCalendarTypePreference(user,password,newType);
    }

    public void modifyAccountData(String user, String password, String newFirst, String newLast){
        dataRepository.modifyAccountData(user,password,newFirst,newLast);
    }

    public Boolean checkAvailability(String date){
        return dataRepository.checkAvailabitity(date);
    }

    public void insertAppointment(Appointments appointment){
        dataRepository.insertAppointment(appointment);
    }

    public void getAccountAppointments(String username,AsyncResponse listener){
        dataRepository.getAccountAppointments(username,listener);
    }

    public void deleteAppointment(String appointmentDateTime){
        dataRepository.deleteAppointment(appointmentDateTime);
    }

    public void changeAppointment(String appointmentDateTime, String newDate, String newTime) {
        dataRepository.changeAppointment(appointmentDateTime, newDate, newTime);
    }

    public void exportAppointments(String username){
        dataRepository.downloadAppointments(username);
    }

    public boolean accountIsCreated(String username){
        return dataRepository.accountIsCreated(username);
    }
}
