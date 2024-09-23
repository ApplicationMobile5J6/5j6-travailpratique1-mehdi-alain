package com.example.travailpratique1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import com.example.travailpratique1.models.Reservation;
import com.example.travailpratique1.models.Restaurant;

import java.util.ArrayList;
import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    private Restaurant selectedRestaurant;
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private String selectedDate = "";
    private int selectedPlaces = 0;
    private String selectedTime = "";
    private String endTime = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        selectedRestaurant = (Restaurant) getIntent().getSerializableExtra("selectedRestaurant");

        TextView tvRestaurantName = findViewById(R.id.tv_restaurant_name);
        TextView tvRemainingSeats = findViewById(R.id.tv_remaining_seats);
        Button btnSelectDate = findViewById(R.id.btn_select_date);
        TextView tvSelectedDate = findViewById(R.id.tv_selected_date);
        SeekBar seekbarPlaces = findViewById(R.id.seekbar_places);
        TextView tvPlacesSelected = findViewById(R.id.tv_places_selected);
        Spinner spinnerTime = findViewById(R.id.spinner_time);
        TextView tvEndTime = findViewById(R.id.tv_end_time);
        EditText etName = findViewById(R.id.et_name);
        EditText etPhone = findViewById(R.id.et_phone);
        Button btnSubmitReservation = findViewById(R.id.btn_submit_reservation);

        tvRestaurantName.setText(selectedRestaurant.getNomRestaurant());
        tvRemainingSeats.setText("Places remaining : " + selectedRestaurant.getNbPlacesRestantes());

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"16:00", "17:30", "19:00", "20:30", "22:00"});
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(timeAdapter);

        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = parent.getItemAtPosition(position).toString();
                endTime = calculateEndTime(selectedTime);
                tvEndTime.setText("End time : " + endTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTime = "16:00";
            }
        });

        btnSelectDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(ReservationActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                        tvSelectedDate.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        seekbarPlaces.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                selectedPlaces = progress;
                tvPlacesSelected.setText(selectedPlaces + " selections");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private String calculateEndTime(String startTime) {
        switch (startTime) {
            case "16:00": return "17:29";
            case "17:30": return "18:59";
            case "19:00": return "20:29";
            case "20:30": return "21:59";
            case "22:00": return "23:29";
            default: return "17:29";
        }
    }

}