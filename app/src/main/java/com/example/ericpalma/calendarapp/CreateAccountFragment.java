package com.example.ericpalma.calendarapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment implements View.OnClickListener {
    /*needed to handle input*/
    private Button createButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        createButton = view.findViewById(R.id.createAccountButton);
        firstNameEditText = view.findViewById(R.id.createAccountFirstName);
        lastNameEditText = view.findViewById(R.id.createAccountLastName);
        usernameEditText = view.findViewById(R.id.createAccountUsername);
        passwordEditText = view.findViewById(R.id.createAccounPassword);
        createButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Log.d(android.content.ContentValues.TAG, firstNameEditText.getText().toString());
        Log.d(android.content.ContentValues.TAG, lastNameEditText.getText().toString());
        Log.d(android.content.ContentValues.TAG, usernameEditText.getText().toString());
        Log.d(android.content.ContentValues.TAG, passwordEditText.getText().toString());

    }
}
