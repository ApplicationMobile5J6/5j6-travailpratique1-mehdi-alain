package com.example.travailpratique1.models;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private int noRestaurant;
    private String nomRestaurant;
    private int nbPlacesMax;
    private int nbPlacesRestantes;

    public Restaurant(int noRestaurant, String nomRestaurant, int nbPlacesMax) {
        this.noRestaurant = noRestaurant;
        this.nomRestaurant = nomRestaurant;
        this.nbPlacesMax = nbPlacesMax;
        this.nbPlacesRestantes = nbPlacesMax; //Initiallement toutes les places de disponnibles
    }

    public int getNoRestaurant() {
        return noRestaurant;
    }

    public void setNoRestaurant(int noRestaurant) {
        this.noRestaurant = noRestaurant;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public int getNbPlacesMax() {
        return nbPlacesMax;
    }

    public void setNbPlacesMax(int nbPlacesMax) {
        this.nbPlacesMax = nbPlacesMax;
    }

    public int getNbPlacesRestantes() {
        return nbPlacesRestantes;
    }

    public void setNbPlacesRestantes(int nbPlacesRestantes) {
        this.nbPlacesRestantes = nbPlacesRestantes;
    }

    public boolean reservePlaces(int nbPlaces) {
        if (nbPlacesRestantes >= nbPlaces) {
            nbPlacesRestantes -= nbPlaces;
            return true;
        } else {
            return false;
        }
    }
}
