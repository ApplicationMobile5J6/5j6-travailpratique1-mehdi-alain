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


    }

}