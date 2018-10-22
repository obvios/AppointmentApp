package com.example.ericpalma.calendarapp;

import android.arch.persistence.room.*;
import java.util.List;

@Dao
public interface AppointmentsDao {
    @Insert
    void insertAppointment(Appointments appmt);

    @Insert
    void insertAllAppointments(Appointments ... appointments);

    @Delete
    void deleteAppointment(Appointments appointment);

    @Query("DELETE FROM Appointments")
    void deleteAllAppointments();
}
