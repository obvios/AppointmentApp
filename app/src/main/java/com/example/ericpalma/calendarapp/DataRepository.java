package com.example.ericpalma.calendarapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import static android.content.ContentValues.TAG;
import java.util.HashMap;
import java.util.List;

public class DataRepository {
    private AccountsDao accountsDao;
    private AppointmentsDao appointmentsDao;
    private LiveData<List<Accounts>> allAccounts;
    private HashMap<String,Appointments> allAppointmentsMap;

    DataRepository(Application application){
        CalendarAppDatabase db = CalendarAppDatabase.getINSTANCE(application);
        accountsDao = db.accountsDao();
        appointmentsDao = db.appointmentsDao();
        allAccounts = accountsDao.getAllAccounts();
        allAppointmentsMap = new HashMap<>();
        new mapAllAppointmentsTask(appointmentsDao).execute(allAppointmentsMap);
    }

    public LiveData<List<Accounts>> getAllAccounts(){
        return allAccounts;
    }

    public void insertAccount(Accounts account){
        new insertAccountTask(accountsDao).execute(account);
    }

    public void changeUsername(String oldUsername, String newUsername, String password){
        new changeUsernameTask(oldUsername, newUsername, password, accountsDao).execute();
    }

    public void changePassword(String username, String currentPassword, String newPassword){
        new changePasswordTask(username,currentPassword,newPassword,accountsDao).execute();
    }

    public void changeCalendarTypePreference(String username, String password, String newViewType){
        new changeCalendarTypePreferenceTask(username,password,newViewType,accountsDao).execute();
    }

    public void modifyAccountData(String username, String password, String newFirst, String newLast){
        new modifyAccountDataTask(username,password,newFirst,newLast,accountsDao).execute();
    }

    public Boolean checkAvailabitity(String date){
        return !allAppointmentsMap.containsKey(date);
    }

    public void getAccountAppointments(String username,AsyncResponse listener){
        new getAccountAppointmentsTask(accountsDao,listener).execute(username);
    }

    public void insertAppointment(Appointments appointment){
        new insertAppointmentTask(appointmentsDao).execute(appointment);
        updateAppointmentsMap(appointment);
    }

    public void deleteAccount(Accounts account){
        new deleteAccountTask(accountsDao).execute(account);
    }

    private void updateAppointmentsMap(Appointments appointment){
        String date_timeKey = appointment.getDate() + " " + appointment.getTime();
        allAppointmentsMap.put(date_timeKey,appointment);
    }
    public void deleteAppointment(Appointments appointment){
        new deleteAppointmentTask(appointmentsDao).execute(appointment);
    }

    /*get appointments in background*/
    private static class getAccountAppointmentsTask extends AsyncTask<String, Void, List<Appointments>> {
        private AccountsDao asyncAccountsDao;
        private AsyncResponse delegate;

        getAccountAppointmentsTask(AccountsDao dao,AsyncResponse delegate){
            asyncAccountsDao = dao;
            this.delegate = delegate;
        }

        @Override
        protected List<Appointments> doInBackground(String... strings) {
            return asyncAccountsDao.getAccountAppointments(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Appointments> appointments) {
            delegate.onAccountAppointmentsRetrieved(appointments);
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

    /*Change username in background*/
    private static class changeUsernameTask extends AsyncTask<String,Void,Void>{
        private AccountsDao asyncAccountsDao;
        private String oldUsername;
        private String newUsername;
        private String password;

        changeUsernameTask(String oldUsername, String newUsername, String password ,AccountsDao dao){
            asyncAccountsDao = dao;
            this.oldUsername = oldUsername;
            this.newUsername = newUsername;
            this.password = password;
        }

        @Override
        protected Void doInBackground(String... strings) {
            asyncAccountsDao.changeUsername(oldUsername,newUsername,password);
            return null;
        }
    }

    /*Change password in background*/
    private static class changePasswordTask extends AsyncTask<String,Void,Void>{
        private AccountsDao asyncAccountsDao;
        private String username;
        private String currentPassword;
        private String newPassword;

        changePasswordTask(String username, String currentPassword, String newPassword, AccountsDao dao){
            asyncAccountsDao = dao;
            this.username = username;
            this.currentPassword = currentPassword;
            this.newPassword = newPassword;
        }

        @Override
        protected Void doInBackground(String... strings) {
            asyncAccountsDao.changePassword(username, currentPassword,newPassword);
            return null;
        }
    }

    /*Change calendar view type in background*/
    private static class changeCalendarTypePreferenceTask extends AsyncTask<String,Void,Void>{
        private AccountsDao asyncAccountsDao;
        private String username;
        private String password;
        private String type;

        changeCalendarTypePreferenceTask(String username, String password, String type, AccountsDao dao){
            asyncAccountsDao = dao;
            this.username = username;
            this.password = password;
            this.type = type;
        }

        @Override
        protected Void doInBackground(String... strings) {
            asyncAccountsDao.changeCalTypePreference(username,password,type);
            return null;
        }
    }

    private static class modifyAccountDataTask extends AsyncTask<String,Void,Void>{
        private AccountsDao asyncAccountsDao;
        private String username;
        private String password;
        private String newFirstName;
        private String newLastName;

        modifyAccountDataTask(String username, String password, String newFirstName, String newLastName, AccountsDao dao){
            this.asyncAccountsDao = dao;
            this.username = username;
            this.password = password;
            this.newFirstName = newFirstName;
            this.newLastName = newLastName;
        }

        @Override
        protected Void doInBackground(String... strings) {
            asyncAccountsDao.modifyAccountData(username,password,newFirstName,newLastName);
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

    private static class mapAllAppointmentsTask extends AsyncTask<HashMap<String,Appointments>,Void,Void>{
        /*Map all appoinments based on date and time only*/
        private AppointmentsDao appointmentsDao;

        mapAllAppointmentsTask(AppointmentsDao dao){
            this.appointmentsDao = dao;
        }

        @Override
        protected Void doInBackground(HashMap<String,Appointments>... maps) {
            List<Appointments> allAppointments = appointmentsDao.getAllAppointments();
            for( Appointments appointment : allAppointments){
                String Date_And_Time = appointment.getDate() + " " + appointment.getTime();
                maps[0].put(Date_And_Time,appointment);
            }
            return null;
        }
    }


}
