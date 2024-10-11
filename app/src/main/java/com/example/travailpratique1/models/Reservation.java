package com.example.travailpratique1.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Reservation implements Serializable {
    private int noReservation = 0;
    private String dateReservation;
    private int nbPlace;
    private String blocReservationDebut;
    private String blocReservationFin;
    private String nomPersonne;
    private String telPersonne;
    private String restaurant;


    public Reservation(String dateReservation, int nbPlace, String blocReservationDebut, String blocReservationFin, String nomPersonne, String telPersonne, String restaurant) {
        this.noReservation++;
        this.dateReservation = dateReservation;
        this.nbPlace = nbPlace;
        this.blocReservationDebut = blocReservationDebut;
        this.blocReservationFin = calculateEndTime(blocReservationDebut);
        this.nomPersonne = nomPersonne;
        this.telPersonne = telPersonne;
        this.restaurant = restaurant;
    }

    public int getNoReservation() {
        return noReservation;
    }

    public void setNoReservation(int noReservation) {
        this.noReservation = noReservation;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getBlocReservationDebut() {
        return blocReservationDebut;
    }

    public void setBlocReservationDebut(String blocReservationDebut) {
        this.blocReservationDebut = blocReservationDebut;
    }

    public String getBlocReservationFin() {
        return blocReservationFin;
    }

    public void setBlocReservationFin(String blocReservationFin) {
        this.blocReservationFin = blocReservationFin;
    }

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public String getTelPersonne() {
        return telPersonne;
    }

    public void setTelPersonne(String telPersonne) {
        this.telPersonne = telPersonne;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant (String restaurant) {
        this.restaurant = restaurant;
    }

    public static String calculateEndTime(String startTime) {

        switch (startTime) {
            case "16:00": return "17:29";
            case "17:30": return "18:59";
            case "19:00": return "20:29";
            case "20:30": return "21:59";
            case "22:00": return "23:29";
            default: return "17:29";
        }

    }


    @NonNull
    @Override
    public String toString() {
        return noReservation + " " + dateReservation + " " + nbPlace + " " + blocReservationDebut +
                " " + blocReservationFin + " " + nomPersonne + " " + telPersonne + " " + restaurant;
    }
}
