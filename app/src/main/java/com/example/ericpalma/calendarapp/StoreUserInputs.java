package com.example.ericpalma.calendarapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StoreUserInputs  extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_account);

        final Button createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameEditText = findViewById(R.id.createAccountFirstName);
                lastNameEditText = findViewById(R.id.createAccountLastName);
                usernameEditText = findViewById(R.id.createAccountUsername);
                passwordEditText = findViewById(R.id.createAccounPassword);
                String name = firstNameEditText.getText().toString();
                String last = lastNameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Accounts newAccount = new Accounts(name,last,username,password,"Month", "Green");
                LocalDataSource localDataSource = new LocalDataSource(getApplication());
                localDataSource.insertAccount(newAccount);
            }
        });
    }
}
