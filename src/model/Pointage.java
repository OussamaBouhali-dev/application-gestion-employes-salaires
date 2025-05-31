package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pointage {
    private int idPointage;
    private int idEmploye;
    private LocalDate date;
    private LocalTime heureArrivee;
    private LocalTime heureDepart;

    public Pointage() {}

    public Pointage(int idPointage, int idEmploye, LocalDate date, LocalTime heureArrivee, LocalTime heureDepart) {
        this.idPointage = idPointage;
        this.idEmploye = idEmploye;
        this.date = date;
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
    }

    public Pointage(int idEmploye, LocalDate date, LocalTime heureArrivee, LocalTime heureDepart) {
        this.idEmploye = idEmploye;
        this.date = date;
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
    }

    public int getIdPointage() {
        return idPointage;
    }

    public void setIdPointage(int idPointage) {
        this.idPointage = idPointage;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }
}
