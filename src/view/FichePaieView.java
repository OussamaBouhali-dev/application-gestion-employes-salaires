package view;

import javax.swing.*;
import java.awt.*;

public class FichePaieView extends JFrame {
    public JTextArea ficheArea;
    public JButton exporterBtn;

    public FichePaieView() {
        setTitle("Fiche de paie");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ficheArea = new JTextArea();
        ficheArea.setEditable(false);
        add(new JScrollPane(ficheArea), BorderLayout.CENTER);

        exporterBtn = new JButton("Exporter / Imprimer");
        add(exporterBtn, BorderLayout.SOUTH);
    }
}
