package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;
import java.util.List;

@Dao
public interface AppointmentsDao {
    @Insert
    void insertAppointment(Appointments appmt);

    @Insert
    void insertAllAppointments(Appointments ... appointments);

    @Query("SELECT * FROM Appointments")
    List<Appointments> getAllAppointments();

    @Query("UPDATE Appointments SET date = :newDate , time = :newTime " +
            "WHERE date = :oldDate AND time = :oldTime")
    void changeAppointmentDayTime(String oldDate, String oldTime ,String newDate, String newTime);

    @Delete
    void deleteAppointment(Appointments appointment);

    @Query("DELETE FROM Appointments")
    void deleteAllAppointments();
}
