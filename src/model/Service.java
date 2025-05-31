package model;

public class Service {
    private int idService;
    private String nom;
    private String description;

    public Service() {}

    public Service(String nom) {
        this.nom = nom;
    }

    public Service(int idService, String nom, String description) {
        this.idService = idService;
        this.nom = nom;
        this.description = description;
    }

    public Service(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }


    // Getters and Setters
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return nom;
    }
}