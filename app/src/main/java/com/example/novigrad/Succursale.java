package com.example.novigrad;
import java.util.ArrayList;
public class Succursale {
    private int id;

    private String nomSuc;

    private String adresseSuc;

    private ArrayList services;

    private String[] joursOuvrables;

    private String heuresOuvertures;

    public Succursale (int id, String nomSuc, String adresseSuc, String[] joursOuvrables, String heuresOuvertures) {

        this.id = id;
        services=new ArrayList();
        this.nomSuc = nomSuc;

        this.adresseSuc = adresseSuc;


        this.joursOuvrables = joursOuvrables;

        this.heuresOuvertures = heuresOuvertures;

    }

    // Methode pour afficher toutes les variables de la succursale

    public void afficherVariables() {

        System.out.println ("ID:" + id);

        System.out.println("Nom:" + nomSuc);

        System.out.println("Adresse:" + adresseSuc);

        System.out.println("Heures d'Ouvertures:" + heuresOuvertures);

        System.out.println("Jours Ouvrables:" );

        for (String jour : joursOuvrables) {

            System.out.println(jour + ",");

        }

        System.out.println();

    }
    public void addServices(Service service) {
        services.add(service);
    }}

