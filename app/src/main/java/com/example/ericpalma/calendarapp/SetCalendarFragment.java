package com.example.ericpalma.calendarapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class SetCalendarFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private AppViewModel appViewModel;
    private RadioGroup radioGroup;
    private Button setCalendarViewButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private String newCalendarViewType = "Month";

    public SetCalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set_calendar, container, false);
        radioGroup = (RadioGroup) view.findViewById(R.id.set_calendarView_RadioButtonGroup);
        radioGroup.setOnCheckedChangeListener(this);
        setCalendarViewButton = view.findViewById(R.id.set_calendarView_SetButton);
        setCalendarViewButton.setOnClickListener(this);
        usernameEditText = view.findViewById(R.id.set_calendarView_Username);
        passwordEditText = view.findViewById(R.id.set_calendarView_Password);

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_Day:
                newCalendarViewType = "Day";
                break;
            case R.id.radio_Week:
                newCalendarViewType = "Week";
                break;
            case R.id.radio_Month:
                newCalendarViewType = "Month";
                break;
        }
    }

    @Override
    public void onClick(View v) {
        appViewModel.changeCalendarViewType(usernameEditText.getText().toString(),passwordEditText.getText().toString(),newCalendarViewType);
    }
}
