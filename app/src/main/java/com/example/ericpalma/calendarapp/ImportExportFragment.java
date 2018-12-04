package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static android.content.ContentValues.TAG;


public class ImportExportFragment extends Fragment implements View.OnClickListener {
    private AppViewModel appViewModel;
    private Button importButton;
    private Button exportButton;
    private EditText fileNameEditText;
    private EditText usernameEditText;

    public ImportExportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        View view = inflater.inflate(R.layout.fragment_import_export,container,false);
        fileNameEditText = view.findViewById(R.id.import_export_FileNameEditText);
        usernameEditText = view.findViewById(R.id.import_export_UsernameEditText);
        importButton = view.findViewById(R.id.import_export_importButton);
        importButton.setOnClickListener(this);
        exportButton = view.findViewById(R.id.import_export_exportButton);
        exportButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.import_export_importButton:
                try {
                    File root = Environment.getExternalStorageDirectory();
                    File dir = new File (root.getAbsolutePath() + "/download");
                    File file = new File(dir,  fileNameEditText.getText().toString() + ".txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    //Read text from file
                    String line;
                    StringBuilder text = new StringBuilder();

                    while ((line = br.readLine()) != null) {
                        String[] words = line.split("\\s+");
                        //if account does not exist, prompt user to create one first
                        if(!appViewModel.accountIsCreated(fileNameEditText.getText().toString())){
                            Toast toast = Toast.makeText(this.getContext(),"Must Create Account First",Toast.LENGTH_SHORT);
                            toast.show();
                        }else {
                            //account exists, merge appointments
                            Toast toast = Toast.makeText(this.getContext(),"Merging Appointment",Toast.LENGTH_SHORT);
                            toast.show();

                            //check for conflicts
                            String apptDate = words[0] + " " + words[1];
                            if(appViewModel.checkAvailability(apptDate)){
                                Appointments newAppointment = new Appointments(words[0],words[1],fileNameEditText.getText().toString());
                                appViewModel.insertAppointment(newAppointment);
                            }else{
                                toast = Toast.makeText(this.getContext(),"Conflict while merging appointment",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }
                    br.close();

                }catch (IOException e){
                    Log.d(TAG,"failed");
                }
                break;
            case R.id.import_export_exportButton:
                appViewModel.exportAppointments(usernameEditText.getText().toString());
                break;
            default:
                break;
        }
    }
}
