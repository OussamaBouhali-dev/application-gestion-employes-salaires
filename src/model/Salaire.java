package model;

public class Salaire {
    private int idSalaire;
    private Employe employe;
    private int mois;
    private int annee;
    private double montant;
    private double primes;
    private double retenues;

    public Salaire() {}

    public Salaire(int idSalaire, Employe employe, int mois, int annee, double montant, double primes, double retenues) {
        this.idSalaire = idSalaire;
        this.employe = employe;
        this.mois = mois;
        this.annee = annee;
        this.montant = montant;
        this.primes = primes;
        this.retenues = retenues;
    }

    public Salaire(int idSalaire, Employe employe, int mois, int annee, double primes, double retenues) {
        this.idSalaire = idSalaire;
        this.employe = employe;
        this.mois = mois;
        this.annee = annee;
        this.primes = primes;
        this.retenues = retenues;
        this.montant = employe.getSalaireDeBase() + primes - retenues;
    }

    // Getters et setters

    public int getIdSalaire() {
        return idSalaire;
    }

    public void setIdSalaire(int idSalaire) {
        this.idSalaire = idSalaire;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getPrimes() {
        return primes;
    }

    public void setPrimes(double primes) {
        this.primes = primes;
    }

    public double getRetenues() {
        return retenues;
    }

    public void setRetenues(double retenues) {
        this.retenues = retenues;
    }

    @Override
    public String toString() {
        return "Salaire de " + employe.getNom() + " - " + mois + "/" + annee + " : " + montant + " DH";
    }
}
