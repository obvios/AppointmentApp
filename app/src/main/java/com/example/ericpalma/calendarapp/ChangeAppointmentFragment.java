package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ChangeAppointmentFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener,AsyncResponse {
    private AppViewModel appViewModel;
    private EditText usernameEditText;
    private Button getAppointmentsButton;
    private Button changeAppointmentButton;
    private Spinner appointmentsSpinner;
    private Spinner newAppointmentTimeSpinner;
    private String appointmentSelected;
    private EditText newAppointmentDate;
    private String newAppointmentTime;

    public ChangeAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_appointment, container, false);
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        usernameEditText = view.findViewById(R.id.change_appointment_fragment_UsernameEditText);
        newAppointmentDate = view.findViewById(R.id.change_appointment_fragment_NewAppointmentDateEditText);
        getAppointmentsButton = view.findViewById(R.id.change_appointment_fragment_GetAppointmentsButton);
        getAppointmentsButton.setOnClickListener(this);
        changeAppointmentButton = view.findViewById(R.id.change_appointment_fragment_ChangeAppointmentButton);
        changeAppointmentButton.setOnClickListener(this);
        appointmentsSpinner = view.findViewById(R.id.change_appointment_fragment_AppointmentsSpinner);
        appointmentsSpinner.setOnItemSelectedListener(this);
        newAppointmentTimeSpinner = view.findViewById(R.id.change_appointment_fragment_NewAppointmentTimeSpinner);
        newAppointmentTimeSpinner.setOnItemSelectedListener(this);

        //set newAppointmentTime spinner properties
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.times_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newAppointmentTimeSpinner.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        int duration = Toast.LENGTH_SHORT;
        CharSequence toastText = "";
        Toast toast;
        switch (v.getId()){
            case R.id.change_appointment_fragment_GetAppointmentsButton:
                toastText = "Getting Appointments...";
                toast = Toast.makeText(this.getContext(),toastText,duration);
                toast.show();
                appViewModel.getAccountAppointments(usernameEditText.getText().toString(),this);
                break;
            case R.id.change_appointment_fragment_ChangeAppointmentButton:
                String newAppointmentDayTime = newAppointmentDate.getText().toString() + " " + newAppointmentTime;
                if(appViewModel.checkAvailability(newAppointmentDayTime)){
                    toastText = "Changing Appointment";
                    toast = Toast.makeText(this.getContext(),toastText,duration);
                    toast.show();
                    appViewModel.changeAppointment(appointmentSelected,newAppointmentDate.getText().toString(),newAppointmentTime);
                }else{
                    toastText = "Already Booked";
                    toast = Toast.makeText(this.getContext(),toastText,duration);
                    toast.show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.change_appointment_fragment_AppointmentsSpinner:
                appointmentSelected = parent.getItemAtPosition(position).toString();
                break;
            case R.id.change_appointment_fragment_NewAppointmentTimeSpinner:
                newAppointmentTime = parent.getItemAtPosition(position).toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAccountAppointmentsRetrieved(List<Appointments> appointmentsList) {
        List<String> apptList = new ArrayList<String>();
        for(Appointments appt: appointmentsList){
            apptList.add(appt.getDate() + " " + appt.getTime());
        }

        //fill spinner with data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,apptList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointmentsSpinner.setAdapter(adapter);
    }
}
