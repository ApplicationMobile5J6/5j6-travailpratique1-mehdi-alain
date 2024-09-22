package com.example.travailpratique1.models;

public class Restaurant {
    private int noRestaurant;
    private String nomRestaurant;
    private int nbPlacesMax;
    private int nbPlacesRestantes;

    public Restaurant(String nomRestaurant, int noRestaurant, int nbPlacesMax, int nbPlacesRestantes) {
        this.nomRestaurant = nomRestaurant;
        this.noRestaurant = noRestaurant;
        this.nbPlacesMax = nbPlacesMax;
        this.nbPlacesRestantes = nbPlacesRestantes;
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
