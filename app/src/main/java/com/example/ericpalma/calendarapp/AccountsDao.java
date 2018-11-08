package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;
import java.util.List;

@Dao
public interface AccountsDao {
    @Insert
    void insertUserAccount(Accounts userAccount1);

    @Insert
    void insertAllAccounts(Accounts ... userAccounts);

    @Query("SELECT * FROM Accounts")
    LiveData<List<Accounts>> getAllAccounts();

    @Query("SELECT * FROM Appointments WHERE apptUserName LIKE :usrName")
    List<Appointments> getAccountAppointments(String usrName);

    @Query("UPDATE Accounts SET username = :newUsrName WHERE username = :usrName AND password = :password")
    void changeUsername(String usrName, String newUsrName, String password);

    @Query("UPDATE Accounts SET password = :newPassword WHERE username = :usrName AND password = :currentPassword")
    void changePassword(String usrName,String currentPassword, String newPassword);

    @Query("UPDATE Accounts SET calendarColor = :newColor WHERE username = :usrName")
    void changeCalColorPreference(String usrName ,String newColor);

    @Query("UPDATE Accounts SET calendarType = :newType WHERE username = :usrName AND password = :password")
    void changeCalTypePreference(String usrName,String password , String newType);

    @Query("UPDATE Accounts SET userFirstName = :newFirst, userLastName = :newLast WHERE username = :usrName AND password = :password")
    void modifyAccountData(String usrName, String password, String newFirst, String newLast);

    @Delete
    void deleteAccount(Accounts userAccount1);

    @Query("DELETE FROM Accounts")
    void deleteAllAccounts();
}
