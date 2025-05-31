package view;

import model.Pointage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PointageView extends JFrame {
    private JTable pointageTable;
    public JButton pointerArriveeBtn;
    public JButton pointerDepartBtn;

    public PointageView() {
        setTitle("Système de pointage");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        pointageTable = new JTable();
        add(new JScrollPane(pointageTable), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        pointerArriveeBtn = new JButton("Arrivée");
        pointerDepartBtn = new JButton("Départ");
        panel.add(pointerArriveeBtn);
        panel.add(pointerDepartBtn);

        add(panel, BorderLayout.SOUTH);
    }

    public void setTableData(List<Pointage> pointages) {
        String[] columns = {"Date", "Heure d'arrivée", "Heure de départ"};
        String[][] data = new String[pointages.size()][3];

        for (int i = 0; i < pointages.size(); i++) {
            Pointage p = pointages.get(i);
            data[i][0] = p.getDate().toString();
            data[i][1] = p.getHeureArrivee() != null ? p.getHeureArrivee().toString() : "--";
            data[i][2] = p.getHeureDepart() != null ? p.getHeureDepart().toString() : "--";
        }

        pointageTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }
}
