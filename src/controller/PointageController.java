package controller;

import dao.PointageDAO;
import model.Pointage;
import view.PointageView;

import javax.swing.*;
import java.time.LocalDateTime;

public class PointageController {
    private PointageView pointageView;
    private PointageDAO pointageDAO;
    private int idEmployeConnecte;

    public PointageController(PointageView view) {
        this.pointageView = view;
        this.pointageDAO = new PointageDAO();
        this.idEmployeConnecte = idEmployeConnecte;

        pointageView.pointerArriveeBtn.addActionListener(e -> enregistrerArrivee());
        pointageView.pointerDepartBtn.addActionListener(e -> enregistrerDepart());

        rafraichirTable();
    }

    private void enregistrerArrivee() {
        LocalDateTime now = LocalDateTime.now();
        Pointage pointage = new Pointage(
                idEmployeConnecte,
                now.toLocalDate(),
                now.toLocalTime(),
                null
        );
        pointageDAO.pointerArrivee(pointage);
        JOptionPane.showMessageDialog(pointageView, "Arrivée enregistrée !");
        rafraichirTable();
    }

    private void enregistrerDepart() {
        LocalDateTime now = LocalDateTime.now();
        pointageDAO.pointerDepart(idEmployeConnecte, now.toLocalDate(), now.toLocalTime());
        JOptionPane.showMessageDialog(pointageView, "Départ enregistré !");
        rafraichirTable();
    }

    private void rafraichirTable() {
        pointageView.setTableData(pointageDAO.getAllPointagesByEmploye(idEmployeConnecte));
    }
}
