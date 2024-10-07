package com.example.travailpratique1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;


import com.example.travailpratique1.adapters.AdapterReservation;
import com.example.travailpratique1.models.Reservation;
import com.example.travailpratique1.models.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.text.ParseException;

public class AffichageActivity extends AppCompatActivity {

    private Restaurant selectedRestaurant;
    private ArrayList<Reservation> reservations;
    private ArrayList<String> uniqueDates;
    ListView lv_reservations;
    TextView tvRestaurantName;
    private int position;
    Spinner spinnerDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);

        tvRestaurantName = findViewById(R.id.tv_restaurant_name2);
        lv_reservations = findViewById(R.id.lv_reservations);
        spinnerDates = findViewById(R.id.spinner_dates);



        selectedRestaurant = (Restaurant) getIntent().getSerializableExtra("selectedRestaurant");
        reservations = (ArrayList<Reservation>) getIntent().getSerializableExtra("reservations");
        position = getIntent().getIntExtra("selectedRestaurantIndex", -1);

        tvRestaurantName.setText(selectedRestaurant.getNomRestaurant());


        Set<String> dateSet = new HashSet<>();
        for (Reservation reservation : reservations) {
            dateSet.add(reservation.getDateReservation());
        }

        uniqueDates = new ArrayList<>(dateSet);

        sortDates(uniqueDates);

        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, uniqueDates);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDates.setAdapter(dateAdapter);

        AdapterReservation adapter = new AdapterReservation(this, reservations);
        lv_reservations.setAdapter(adapter);

        spinnerDates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDate = uniqueDates.get(position);
                filterReservationsByDate(selectedDate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lv_reservations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reservation clickedReservation = (Reservation) parent.getItemAtPosition(position);

                int reservationNumber = clickedReservation.getNoReservation();
                String phone = clickedReservation.getTelPersonne();

                String message = getString(R.string.reservation_number) + " : " + reservationNumber + " " + getString(R.string.phone) + " : " + phone;
                Toast.makeText(AffichageActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void filterReservationsByDate(String selectedDate) {
        ArrayList<Reservation> filteredReservations = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getDateReservation().equals(selectedDate)) {
                filteredReservations.add(reservation);
            }
        }

        Collections.sort(filteredReservations, new Comparator<Reservation>() {
            @Override
            public int compare(Reservation res1, Reservation res2) {
                return res1.getBlocReservationDebut().compareTo(res2.getBlocReservationDebut());
            }
        });

        AdapterReservation adapter = new AdapterReservation(this, filteredReservations);
        lv_reservations.setAdapter(adapter);

    }

    private void sortDates(ArrayList<String> dates) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Collections.sort(dates, new Comparator<String>() {
            @Override
            public int compare(String date1, String date2) {
                try {
                    Date parsedDate1 = dateFormat.parse(date1);
                    Date parsedDate2 = dateFormat.parse(date2);
                    return parsedDate1.compareTo(parsedDate2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }

}