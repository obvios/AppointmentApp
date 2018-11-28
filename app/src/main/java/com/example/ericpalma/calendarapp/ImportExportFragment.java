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
import android.widget.Button;

import java.io.File;

import static android.content.ContentValues.TAG;


public class ImportExportFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private AppViewModel appViewModel;
    private Button importButton;
    private Button exportButton;

    public ImportExportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        View view = inflater.inflate(R.layout.fragment_import_export,container,false);
        importButton = view.findViewById(R.id.import_export_importButton);
        importButton.setOnClickListener(this);
        exportButton = view.findViewById(R.id.import_export_exportButton);
        exportButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.import_export_exportButton:
                Log.d(TAG,"clicked button");
                appViewModel.exportAppointments(getContext());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
