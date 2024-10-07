package com.example.travailpratique1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.travailpratique1.models.Reservation;
import com.example.travailpratique1.models.Restaurant;

import java.util.ArrayList;
import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    private Restaurant selectedRestaurant;
    private ArrayList<Reservation> reservations;
    private String selectedDate = "";
    private int selectedPlaces = 0;
    private String selectedTime = "";
    private String endTime = "";
    private int position;
    TextView tvRemainingSeats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);


    }

}