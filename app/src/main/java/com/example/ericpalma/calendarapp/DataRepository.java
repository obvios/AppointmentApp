package com.example.ericpalma.calendarapp;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class DataRepository {
    private AccountsDao accountsDao;
    private AppointmentsDao appointmentsDao;
    private List<Accounts> allAccounts;
    private List<Appointments> allAppointments;

    DataRepository(Application application){
        CalendarAppDatabase db = CalendarAppDatabase.getINSTANCE(application);
        accountsDao = db.accountsDao();
        appointmentsDao = db.appointmentsDao();
        allAccounts = accountsDao.getAllAccounts();
    }

    public List<Accounts> getAllAccounts(){
        return allAccounts;
    }

    public void insertAccount(Accounts account){
        new insertAccountTask(accountsDao).execute(account);
    }

    public void getAccountAppointments(Accounts account){
        new getAccountAppointmentsTask(accountsDao).execute(account);
    }

    public void insertAppointment(Appointments appointment){
        new insertAppointmentTask(appointmentsDao).execute(appointment);
    }

    public void deleteAccount(Accounts account){
        new deleteAccountTask(accountsDao).execute(account);
    }

    public void deleteAppointment(Appointments appointment){
        new deleteAppointmentTask(appointmentsDao).execute(appointment);
    }

    /*get appointments in background*/
    private static class getAccountAppointmentsTask extends AsyncTask<Accounts, Void, List<Appointments>> {
        private AccountsDao asyncAccountsDao;

        getAccountAppointmentsTask(AccountsDao dao){
            asyncAccountsDao = dao;
        }

        @Override
        protected List<Appointments> doInBackground(Accounts... account) {
            return asyncAccountsDao.getAccountAppointments(account[0].getUsername());
        }
    }

    /*insert account in background*/
    private static class insertAccountTask extends AsyncTask<Accounts, Void, Void>{

        private AccountsDao asyncAccountsDao;

        insertAccountTask(AccountsDao dao){
            asyncAccountsDao = dao;
        }

        @Override
        protected Void doInBackground(Accounts... account) {
            asyncAccountsDao.insertUserAccount(account[0]);
            return null;

        }
    }

    /*insert appointment in background*/
    private static class insertAppointmentTask extends AsyncTask<Appointments, Void, Void>{
        private AppointmentsDao asyncAppointmentsDao;

        insertAppointmentTask(AppointmentsDao dao){
            asyncAppointmentsDao = dao;
        }

        @Override
        protected Void doInBackground(Appointments... appointment) {
            asyncAppointmentsDao.insertAppointment(appointment[0]);
            return null;
        }
    }

    /*delete account in background*/
    private static class deleteAccountTask extends AsyncTask<Accounts,Void,Void>{
        private AccountsDao asyncAppointmentsDao;

        deleteAccountTask(AccountsDao dao){
            asyncAppointmentsDao = dao;
        }


        @Override
        protected Void doInBackground(Accounts... account) {
            asyncAppointmentsDao.deleteAccount(account[0]);
            return null;
        }
    }

    /*delete appointment in background*/
    private static class deleteAppointmentTask extends AsyncTask<Appointments,Void,Void>{
        private AppointmentsDao asyncAppointmentsDao;

        deleteAppointmentTask(AppointmentsDao dao){
            asyncAppointmentsDao = dao;
        }

        @Override
        protected Void doInBackground(Appointments... appointment) {
            asyncAppointmentsDao.deleteAppointment(appointment[0]);
            return null;
        }
    }
}
