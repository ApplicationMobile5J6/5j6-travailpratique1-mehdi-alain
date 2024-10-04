package com.example.travailpratique1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.travailpratique1.R;
import com.example.travailpratique1.models.Reservation;

import java.util.ArrayList;

public class AdapterReservation extends ArrayAdapter<Reservation> {
    public AdapterReservation(@NonNull Context context, ArrayList<Reservation> reservations) {
        super(context, R.layout.list_item, reservations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        Reservation reservation = getItem(position);


        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView tvNom = view.findViewById(R.id.tv_nom);
        TextView tvPlaces = view.findViewById(R.id.tv_places);
        TextView tvHeures = view.findViewById(R.id.tv_heures);

        tvNom.setText(reservation.getNomPersonne());
        tvPlaces.setText(String.valueOf(reservation.getNbPlace()));
        tvHeures.setText(reservation.getBlocReservationDebut() + " " + reservation.getBlocReservationFin());

        return view;

    }
}
