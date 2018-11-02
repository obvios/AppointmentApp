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


/**
 * A simple {@link Fragment} subclass.

 */
public class ChangeUsernameFragment extends Fragment implements View.OnClickListener {
    private AppViewModel appViewModel;
    private Button changeUsernameButton;
    private EditText oldUsernameText;
    private EditText newUsernameText;
    private EditText passwordText;

    public ChangeUsernameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_username, container, false);
        changeUsernameButton = view.findViewById(R.id.changeUsernameButton);
        changeUsernameButton.setOnClickListener(this);
        oldUsernameText = view.findViewById(R.id.change_username_fragment_OldUsername);
        newUsernameText = view.findViewById(R.id.change_username_fragment_NewUsername);
        passwordText = view.findViewById(R.id.change_username_fragment_Password);

        return view;
    }

    @Override
    public void onClick(View view){
        appViewModel.changeUsername(oldUsernameText.getText().toString(),newUsernameText.getText().toString(),passwordText.getText().toString());
    }
}
