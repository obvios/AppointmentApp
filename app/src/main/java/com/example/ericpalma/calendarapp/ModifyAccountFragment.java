package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ModifyAccountFragment extends Fragment implements View.OnClickListener {
    private AppViewModel appViewModel;
    private Button saveButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText newFirstNameEditText;
    private EditText newLastNameEditText;

    public ModifyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modify_account, container, false);
        saveButton = view.findViewById(R.id.modify_account_fragment_SaveButton);
        saveButton.setOnClickListener(this);
        usernameEditText = view.findViewById(R.id.modify_account_fragment_Username);
        passwordEditText = view.findViewById(R.id.modify_account_fragment_Password);
        newFirstNameEditText = view.findViewById(R.id.modify_account_fragment_NewFirstName);
        newLastNameEditText = view.findViewById(R.id.modify_account_fragment_NewLastName);

        return view;
    }


    @Override
    public void onClick(View v) {
        appViewModel.modifyAccountData(usernameEditText.getText().toString(),passwordEditText.getText().toString(),
                newFirstNameEditText.getText().toString(), newLastNameEditText.getText().toString());
    }
}
