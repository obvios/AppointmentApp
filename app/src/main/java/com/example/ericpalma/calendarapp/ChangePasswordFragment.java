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
public class ChangePasswordFragment extends Fragment implements View.OnClickListener {
    private AppViewModel appViewModel;
    private Button changePasswordButton;
    private EditText usernameEditText;
    private EditText currentPasswordEditText;
    private EditText newPasswordEditText;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        changePasswordButton = view.findViewById(R.id.change_password_fragment_ChangePasswordButton);
        changePasswordButton.setOnClickListener(this);
        usernameEditText = view.findViewById(R.id.change_password_fragment_Username);
        currentPasswordEditText = view.findViewById(R.id.change_password_fragment_CurrentPassword);
        newPasswordEditText = view.findViewById(R.id.change_password_fragment_NewPassword);

        return view;
    }

    @Override
    public void onClick(View view){
        appViewModel.changePassword(usernameEditText.getText().toString(),currentPasswordEditText.getText().toString(),newPasswordEditText.getText().toString());
    }
}
