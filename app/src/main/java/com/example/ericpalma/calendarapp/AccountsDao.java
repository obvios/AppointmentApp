package com.example.ericpalma.calendarapp;

import android.arch.persistence.room.*;
import java.util.List;

@Dao
public interface AccountsDao {
    @Insert
    void insertUserAccount(Accounts userAccount1);

    @Insert
    void insertAll(Accounts ... userAccounts);

    @Query("DELETE FROM Accounts")
    void deleteAll();

    @Query("SELECT * FROM Appointments WHERE apptUserName LIKE :usrName")
    List<Appointments> getAccountAppointments(String usrName);

    @Delete
    void deleteAccount(Accounts userAccount1);
}
