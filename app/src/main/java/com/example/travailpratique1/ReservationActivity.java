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
    private int compteur = 0;

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

        if (savedInstanceState != null) {

            selectedRestaurant = (Restaurant) savedInstanceState.getSerializable("selectedRestaurant");
            reservations = (ArrayList<Reservation>) savedInstanceState.getSerializable("reservations");
            position = savedInstanceState.getInt("position");
            selectedDate = savedInstanceState.getString("selectedDate");

            if (selectedDate != null && !selectedDate.isEmpty()) {
                tvSelectedDate.setText(selectedDate);
            }

            Intent returnIntent = new Intent();
            returnIntent.putExtra("updatedRestaurant", selectedRestaurant);
            returnIntent.putExtra("selectedRestaurantIndex", position);
            returnIntent.putExtra("reservations", reservations);
            setResult(RESULT_OK, returnIntent);

        } else {

            selectedRestaurant = (Restaurant) getIntent().getSerializableExtra("selectedRestaurant");
            reservations = (ArrayList<Reservation>) getIntent().getSerializableExtra("reservations");
            position = getIntent().getIntExtra("selectedRestaurantIndex", -1);

        }

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
                        selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    },
                    year, month, day
            );
            datePickerDialog.show();
            tvSelectedDate.setText(selectedDate);
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


        String[] timeOptions = {"16:00", "17:30", "19:00", "20:30", "22:00"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapter);

        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = timeOptions[position];

                endTime = calculateEndTime(selectedTime);
                tvEndTime.setText("Heure de fin : " + endTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        btnSubmitReservation.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            boolean validForm = isValidEntries();

            if (validForm) {

                compteur++;
                Reservation newReservation = new Reservation(compteur, selectedDate, selectedPlaces, selectedTime, endTime, name, phone, selectedRestaurant.getNomRestaurant());
                reservations.add(newReservation);


                selectedRestaurant.reservePlaces(selectedPlaces);
                tvRemainingSeats.setText(String.valueOf(selectedRestaurant.getNbPlacesRestantes()));
                updateRemainingSeatsColor();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("updatedRestaurant", selectedRestaurant);
                returnIntent.putExtra("selectedRestaurantIndex", position);
                returnIntent.putExtra("reservations", reservations);
                setResult(RESULT_OK, returnIntent);


                Toast.makeText(ReservationActivity.this, "La réservation a été sauvegardée", Toast.LENGTH_SHORT).show();


                Log.d("reservation", newReservation.toString());


                etName.setText("");
                etPhone.setText("");
                seekBarPlaces.setProgress(0);
                tvPlacesSelected.setText("0 places");
                tvSelectedDate.setText("");
                spinnerTime.setSelection(0);
                tvEndTime.setText("");

            }



        });

    }



    private String calculateEndTime(String startTime) {
        String[] timeParts = startTime.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        minutes += 29;
        if (minutes >= 60) {
            hours += 1;
            minutes -= 60;
        }
        hours += 1;

        return String.format("%02d:%02d", hours, minutes);
    }

    private boolean isValidEntries() {
        boolean valid = true;
        String message = "";

        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (name.isEmpty()) {
            message = "Veuillez entrer un nom";
            valid = false;
        }

        if (phone.isEmpty() || !isValidPhoneNumber(phone)) {
            message = "Veuillez entrer un numéro de téléphone";
            valid = false;
        }

        if (selectedDate.isEmpty()) {
            message = "Veuillez sélectionner une date";
            valid = false;
        }

        if (selectedPlaces == 0 || selectedPlaces > selectedRestaurant.getNbPlacesRestantes()) {
            message = "Veuillez sélectionner un bon nombre de places";
            valid = false;
        }

        if (selectedDate.isEmpty()) {
            message = "Veuillez sélectionner une date";
            valid = false;
        }

        if (!valid) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        return valid;
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("selectedRestaurant", selectedRestaurant);
        outState.putSerializable("reservations", reservations);
        outState.putInt("position", position);
        outState.putString("selectedDate", selectedDate);

    }

    private void updateRemainingSeatsColor() {

        int remainingSeats = selectedRestaurant.getNbPlacesRestantes();

        if (remainingSeats <= 4) {
            tvRemainingSeats.setTextColor(getResources().getColor(R.color.redBold));
        } else {
            tvRemainingSeats.setTextColor(getResources().getColor(R.color.black));
        }
    }


}