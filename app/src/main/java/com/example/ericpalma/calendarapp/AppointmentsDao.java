/*
 * Filename:	AppointmentsDao.java
 * Purpose:		Data Access Object to define interactions between database and Appointments entity.
 * Author:      Eric Palma
 */
package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;
import java.util.List;

@Dao
public interface AppointmentsDao {
    //insert appointment to database
    @Insert
    void insertAppointment(Appointments appmt);

    //insert multiple appointments to database at once
    @Insert
    void insertAllAppointments(Appointments ... appointments);

    //returns a list of all appointments in database
    @Query("SELECT * FROM Appointments")
    List<Appointments> getAllAppointments();

    //updates an existing appointment's date and time
    @Query("UPDATE Appointments SET date = :newDate , time = :newTime " +
            "WHERE date = :oldDate AND time = :oldTime")
    void changeAppointmentDayTime(String oldDate, String oldTime ,String newDate, String newTime);

    //delete an appointment
    @Delete
    void deleteAppointment(Appointments appointment);

    //delete all appointments
    @Query("DELETE FROM Appointments")
    void deleteAllAppointments();
}
