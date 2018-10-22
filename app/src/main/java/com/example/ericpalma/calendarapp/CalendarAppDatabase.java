package com.example.ericpalma.calendarapp;
import android.arch.persistence.room.*;
import android.content.Context;

@Database(entities = {Accounts.class, Appointments.class}, version = 1)
public abstract class CalendarAppDatabase extends RoomDatabase {
    private static volatile CalendarAppDatabase INSTANCE;

    static CalendarAppDatabase getINSTANCE(final Context context){
        if(INSTANCE == null){
            synchronized (CalendarAppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CalendarAppDatabase.class, "calendar_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
