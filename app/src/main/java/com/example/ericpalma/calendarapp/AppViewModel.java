package com.example.ericpalma.calendarapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

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
}
