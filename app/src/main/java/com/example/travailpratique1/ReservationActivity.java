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

    private TextView tvRestaurant;
    private TextView tvRemainingSeats;
    private TextView tvSelectedDate;
    private Button btnSelectDate;
    private SeekBar seekBarPlaces;
    private TextView tvPlacesSelected;
    private Spinner spinnerTime;
    private TextView tvEndTime;
    private EditText etName;
    private EditText etPhone;
    private Button btnSubmitReservation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        tvRestaurant = findViewById(R.id.tv_restaurant_name);
        tvRemainingSeats = findViewById(R.id.tv_remaining_seats);
        tvSelectedDate = findViewById(R.id.tv_selected_date);
        btnSelectDate = findViewById(R.id.btn_select_date);
        seekBarPlaces = findViewById(R.id.seekbar_places);
        tvPlacesSelected = findViewById(R.id.tv_places_selected);
        spinnerTime = findViewById(R.id.spinner_time);
        tvEndTime = findViewById(R.id.tv_end_time);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        btnSubmitReservation = findViewById(R.id.btn_submit_reservation);

        selectedRestaurant = (Restaurant) getIntent().getSerializableExtra("selectedRestaurant");
        reservations = (ArrayList<Reservation>) getIntent().getSerializableExtra("reservations");
        position = getIntent().getIntExtra("selectedRestaurantIndex", -1);

        tvRestaurant.setText(selectedRestaurant.getNomRestaurant());
        tvRemainingSeats.setText(String.valueOf(selectedRestaurant.getNbPlacesRestantes()));

        btnSelectDate.setOnClickListener(v -> {

            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ReservationActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        tvSelectedDate.setText(selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });


        seekBarPlaces.setMax(10);

        seekBarPlaces.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedPlaces = progress;
                tvPlacesSelected.setText("Places: " + selectedPlaces);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });





    }

}