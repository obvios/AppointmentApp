package com.example.ericpalma.calendarapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DataRepository {
    private AccountsDao accountsDao;
    private AppointmentsDao appointmentsDao;
    private LiveData<List<Accounts>> allAccounts;
    private List<Appointments> allAppointments;

    DataRepository(Application application){
        CalendarAppDatabase db = CalendarAppDatabase.getINSTANCE(application);
        accountsDao = db.accountsDao();
        appointmentsDao = db.appointmentsDao();
        allAccounts = accountsDao.getAllAccounts();
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
}
