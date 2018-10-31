package com.example.ericpalma.calendarapp;


import android.arch.lifecycle.ViewModelProviders;
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
    private AppViewModel appViewModel;
    /*needed to handle input*/
    private Button createButton;
    EditText firstNameText;
    EditText lastNameText;
    EditText usernameText;
    EditText passwordText;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        createButton = view.findViewById(R.id.createAccountButton);
        createButton.setOnClickListener(this);
        firstNameText = view.findViewById(R.id.createAccountFirstName);
        lastNameText = view.findViewById(R.id.createAccountLastName);
        usernameText = view.findViewById(R.id.createAccountUsername);
        passwordText = view.findViewById(R.id.createAccounPassword);
        return view;
    }

    @Override
    public void onClick(View v) {
        Accounts newAccount = new Accounts(firstNameText.getText().toString(), lastNameText.getText().toString(), usernameText.getText().toString(), passwordText.getText().toString(),
                "Month", "Default");
        appViewModel.insert(newAccount);
    }
}
