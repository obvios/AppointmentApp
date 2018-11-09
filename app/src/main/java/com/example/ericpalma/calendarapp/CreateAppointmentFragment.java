package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateAppointmentFragment extends Fragment {
    private AppViewModel appViewModel;
    private Button createAppointmentButton;
    private EditText dateEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Spinner timeSpinner;

    public CreateAppointmentFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_create_appointment,container,false);
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        createAppointmentButton = view.findViewById(R.id.create_appointment_fragment_CreateAppointmentButton);
        dateEditText = view.findViewById(R.id.create_appointment_fragment_Date);
        timeSpinner = view.findViewById(R.id.create_appointment_fragment_Spinner);
        usernameEditText = view.findViewById(R.id.create_appointment_fragment_Username);
        passwordEditText = view.findViewById(R.id.create_appointment_fragment_Password);

        return view;
    }
}
