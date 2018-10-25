package com.example.ericpalma.calendarapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {

    /*needed to handle input*/
    private Button createButton;
    private EditText mEditText;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        createButton = view.findViewById(R.id.createAccountButton);
        mEditText = view.findViewById(R.id.createAccountFirstName);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //process input
                Log.d(android.content.ContentValues.TAG, mEditText.getText().toString());
            }
        });
        return view;
    }

}
