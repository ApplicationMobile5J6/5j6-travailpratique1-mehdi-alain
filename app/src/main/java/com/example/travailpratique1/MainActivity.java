package com.example.travailpratique1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
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
    private int position;

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
                MainActivity.this.position = position;
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
            intent.putExtra("selectedRestaurantIndex", position);
            startActivityForResult(intent, 1);
        });

        btnViewReservations.setOnClickListener(v -> {
            Intent intent = new Intent(this, AffichageActivity.class);
            intent.putExtra("selectedRestaurant", selectedRestaurant);
            startActivity(intent);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Get the updated Restaurant object
            Restaurant updatedRestaurant  = (Restaurant) data.getSerializableExtra("updatedRestaurant");

            int selectedRestaurantIndex = data.getIntExtra("selectedRestaurantIndex", -1);

            restaurants[selectedRestaurantIndex] = updatedRestaurant;
            selectedRestaurant = restaurants[selectedRestaurantIndex];

            // Update the TextView to show the remaining seats
            updateRemainingSeats();
        }


    }

    private void updateRemainingSeats() {
        int remainingSeats = selectedRestaurant.getNbPlacesRestantes();

        String formattedText = getString(R.string.x_places_restantes, remainingSeats);
        tvRemainingSeats.setText(formattedText);

        if (remainingSeats <= 4) {
            tvRemainingSeats.setTextColor(getResources().getColor(R.color.redBold));
        } else {
            tvRemainingSeats.setTextColor(getResources().getColor(R.color.black));
        }
    }
}