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
    private DataRepository dataRepository;

    private LiveData<List<Accounts>> allAccounts;

    public AppViewModel (Application application){
        super(application);
        dataRepository = new DataRepository(application);
        allAccounts = dataRepository.getAllAccounts();
    }

    LiveData<List<Accounts>> getAllAccounts(){
        return allAccounts;
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

    public void exportAppointments(Context context){
        Log.d(TAG ,"in view model");
        dataRepository.downloadAppointments(context);
    }
}
