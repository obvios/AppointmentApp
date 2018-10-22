package com.example.ericpalma.calendarapp;

import android.arch.persistence.room.*;
import java.util.List;

@Dao
public interface AccountsDao {
    @Insert
    void insertUserAccount(Accounts userAccount1);

    @Insert
    void insertAllAccounts(Accounts ... userAccounts);

    @Query("SELECT * FROM Accounts")
    List<Accounts> getAllAccounts();

    @Query("SELECT * FROM Appointments WHERE apptUserName LIKE :usrName")
    List<Appointments> getAccountAppointments(String usrName);

    @Query("UPDATE Accounts SET calendarColor = :newColor where username = :usrName")
    void changeCalColorPreference(String usrName ,String newColor);

    @Query("UPDATE Accounts SET calendarType = :newType WHERE username = :usrName")
    void changeCalTypePreference(String usrName , String newType);

    @Delete
    void deleteAccount(Accounts userAccount1);

    @Query("DELETE FROM Accounts")
    void deleteAllAccounts();
}
