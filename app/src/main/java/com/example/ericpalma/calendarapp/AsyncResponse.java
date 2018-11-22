package com.example.ericpalma.calendarapp;

import java.util.List;

public interface AsyncResponse {
    void onAccountAppointmentsRetrieved(List<Appointments> appointmentsList);
}
