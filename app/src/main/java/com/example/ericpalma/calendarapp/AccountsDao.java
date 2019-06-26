/*
 * Filename:	AccountsDao.java
 * Purpose:		Data Access Object interface for Accounts entity.
 *              Defines database interactions for Accounts entity.
 * Author:      Eric Palma
 */
package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;
import java.util.List;

@Dao
public interface AccountsDao {
    //insert Accounts object to RoomDatabase
    @Insert
    void insertUserAccount(Accounts userAccount1);

    //insert multiple accounts at once to RoomDatabase
    @Insert
    void insertAllAccounts(Accounts ... userAccounts);

    //get a list of all accounts in database
    @Query("SELECT * FROM Accounts")
    List<Accounts> getAllAccounts();

    //Get list of all appointments associated with user account
    @Query("SELECT * FROM Appointments WHERE apptUserName LIKE :usrName")
    List<Appointments> getAccountAppointments(String usrName);

    //update username
    @Query("UPDATE Accounts SET username = :newUsrName WHERE username = :usrName AND password = :password")
    void changeUsername(String usrName, String newUsrName, String password);

    //change user's password
    @Query("UPDATE Accounts SET password = :newPassword WHERE username = :usrName AND password = :currentPassword")
    void changePassword(String usrName,String currentPassword, String newPassword);

    //change calendar color preference for user account
    @Query("UPDATE Accounts SET calendarColor = :newColor WHERE username = :usrName")
    void changeCalColorPreference(String usrName ,String newColor);

    //change calendar type setting for user's account
    @Query("UPDATE Accounts SET calendarType = :newType WHERE username = :usrName AND password = :password")
    void changeCalTypePreference(String usrName,String password , String newType);

    //update personal info for user's account
    @Query("UPDATE Accounts SET userFirstName = :newFirst, userLastName = :newLast WHERE username = :usrName AND password = :password")
    void modifyAccountData(String usrName, String password, String newFirst, String newLast);

    //delete a user account
    @Delete
    void deleteAccount(Accounts userAccount1);

    //delete all accounts from database
    @Query("DELETE FROM Accounts")
    void deleteAllAccounts();
}
