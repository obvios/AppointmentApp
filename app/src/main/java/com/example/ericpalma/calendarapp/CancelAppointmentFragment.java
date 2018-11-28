package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class CancelAppointmentFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener,AsyncResponse {
    private AppViewModel appViewModel;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button getAppointmentsButton;
    private Button cancelAppointmentButton;
    private String appointmentSelected;
    private Spinner appointmentsSpinner;

    public CancelAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancel_appointment, container, false);
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        usernameEditText = view.findViewById(R.id.cancel_appointment_fragment_Username);
        passwordEditText = view.findViewById(R.id.cancel_appointment_fragment_Password);
        getAppointmentsButton = view.findViewById(R.id.cancel_appointment_fragment_GetAppointmentsButton);
        getAppointmentsButton.setOnClickListener(this);
        cancelAppointmentButton = view.findViewById(R.id.cancel_appointment_fragment_CancelAppointmentButton);
        cancelAppointmentButton.setOnClickListener(this);
        appointmentsSpinner = (Spinner) view.findViewById(R.id.cancel_appointment_fragment_AppointmentsSpinner);
        appointmentsSpinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int duration = Toast.LENGTH_SHORT;
        CharSequence toastText = "";
        Toast toast;
        switch (v.getId()){
            case R.id.cancel_appointment_fragment_GetAppointmentsButton:
                toastText = "Getting Appointments...";
                toast = Toast.makeText(this.getContext(),toastText,duration);
                toast.show();
                appViewModel.getAccountAppointments(usernameEditText.getText().toString(),this);
                break;
            case R.id.cancel_appointment_fragment_CancelAppointmentButton:
                toastText = "Cancelling Appointment";
                toast = Toast.makeText(this.getContext(),toastText,duration);
                toast.show();
                appViewModel.deleteAppointment(appointmentSelected);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        appointmentSelected = parent.getItemAtPosition(position).toString();
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
