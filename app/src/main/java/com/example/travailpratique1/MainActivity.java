package com.example.travailpratique1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import com.example.travailpratique1.models.Restaurant;

public class MainActivity extends AppCompatActivity {

    private Restaurant[] restaurants = {
            new Restaurant(1, "Chez Alain", 30),
            new Restaurant(2, "Chez Mehdi", 16)
    };
    private Restaurant selectedRestaurant;
    Spinner spinnerRestaurants;
    private TextView tvRemainingSeats;
    Button btnReserveTable;
    Button btnViewReservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerRestaurants = findViewById(R.id.spn_restaurantsDeroulants);
        tvRemainingSeats = findViewById(R.id.tv_remaining_seats);
        btnReserveTable = findViewById(R.id.btn_reserve_table);
        btnViewReservations = findViewById(R.id.btn_view_reservations);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[] {"Chez Alain", "Chez Mehdi"} );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRestaurants.setAdapter(adapter);

        spinnerRestaurants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRestaurant = restaurants[position];
                updateRemainingSeats();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedRestaurant = restaurants[0];

            }
        });

        btnReserveTable.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReservationActivity.class);
            intent.putExtra("selectedRestaurant", selectedRestaurant);
            startActivity(intent);
        });

        btnViewReservations.setOnClickListener(v -> {
            Intent intent = new Intent(this, AffichageActivity.class);
            intent.putExtra("selectedRestaurant", selectedRestaurant);
            startActivity(intent);
        });


    }

    private void updateRemainingSeats() {
        int remainingSeats = selectedRestaurant.getNbPlacesRestantes();

        String formattedText = getString(R.string.x_places_restantes, remainingSeats);
        tvRemainingSeats.setText(formattedText);

        if (remainingSeats <= 4) {
            tvRemainingSeats.setTextColor(getResources().getColor(R.color.red));
        } else {
            tvRemainingSeats.setTextColor(getResources().getColor(R.color.black));
        }
    }
}