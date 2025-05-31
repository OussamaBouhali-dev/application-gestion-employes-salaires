package model;

import java.time.LocalDate;

public class Employe {
    private int idEmploye;
    private String nom;
    private String prenom;
    private String poste;
    private int idService;
    private LocalDate dateEmbauche;
    private double salaireDeBase;

    public Employe() {}

    public Employe(int idEmploye, String nom, String prenom, String poste, int idService, LocalDate dateEmbauche, double salaireDeBase) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.idService = idService;
        this.dateEmbauche = dateEmbauche;
        this.salaireDeBase = salaireDeBase;
    }


    // Getters et setters

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public double getSalaireDeBase() {
        return salaireDeBase;
    }

    public void setSalaireDeBase(double salaireDeBase) {
        this.salaireDeBase = salaireDeBase;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + " - " + poste;
    }
}
