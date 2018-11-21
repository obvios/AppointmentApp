package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class CreateAppointmentFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private AppViewModel appViewModel;
    private Button createAppointmentButton;
    private EditText dateEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Spinner timeSpinner;
    private String appointmentTime = "8:00";

    public CreateAppointmentFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //initialize properties
        View view = inflater.inflate(R.layout.fragment_create_appointment,container,false);
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        createAppointmentButton = view.findViewById(R.id.create_appointment_fragment_CreateAppointmentButton);
        createAppointmentButton.setOnClickListener(this);
        dateEditText = view.findViewById(R.id.create_appointment_fragment_Date);
        timeSpinner = view.findViewById(R.id.create_appointment_fragment_Spinner);
        timeSpinner.setOnItemSelectedListener(this);
        usernameEditText = view.findViewById(R.id.create_appointment_fragment_Username);
        passwordEditText = view.findViewById(R.id.create_appointment_fragment_Password);

        //set spinner properties
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.times_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        String date = dateEditText.getText().toString();
        String date_Time = date + " " + appointmentTime;

        Context context = this.getContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence toastText = "";
        Toast toast;
        if(appViewModel.checkAvailability(date_Time)){
            Appointments newAppointment = new Appointments(date,appointmentTime, usernameEditText.getText().toString());
            appViewModel.insertAppointment(newAppointment);
            toastText = "Appointment Booked";
            toast = Toast.makeText(context,toastText,duration);
            toast.show();
        }else{
            toastText = "Already Booked!";
            toast = Toast.makeText(context,toastText,duration);
            toast.show();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        appointmentTime = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
