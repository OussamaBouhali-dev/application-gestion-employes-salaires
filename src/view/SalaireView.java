package view;

import model.Employe;
import model.Salaire;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SalaireView extends JFrame {
    private JTable salaireTable;
    public JButton ajouterSalaireBtn;
    public JButton voirHistoriqueBtn;
    public JButton genererFichePaieBtn;
    public JComboBox<Employe> employeComboBox;
    private DefaultTableModel tableModel;

    public SalaireView() {
        setTitle("Gestion des salaires");
        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuration du tableau
        String[] columns = {"ID", "Employé", "Mois", "Année", "Salaire de base", "Primes", "Retenues", "Net à payer"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        salaireTable = new JTable(tableModel);
        salaireTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(salaireTable);

        // Panel des boutons
        JPanel controlPanel = new JPanel(new BorderLayout());

        // Panel de sélection d'employé
        JPanel employePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        employePanel.add(new JLabel("Employé:"));
        employeComboBox = new JComboBox<>();
        employeComboBox.setPreferredSize(new Dimension(200, 25));
        employePanel.add(employeComboBox);

        // Panel des boutons d'actions
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        ajouterSalaireBtn = new JButton("Ajouter Salaire");
        voirHistoriqueBtn = new JButton("Voir Historique");
        genererFichePaieBtn = new JButton("Générer Fiche de Paie");

        buttonPanel.add(ajouterSalaireBtn);
        buttonPanel.add(voirHistoriqueBtn);
        buttonPanel.add(genererFichePaieBtn);

        controlPanel.add(employePanel, BorderLayout.WEST);
        controlPanel.add(buttonPanel, BorderLayout.EAST);

        // Configuration du layout principal
        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public void setEmployes(List<Employe> employes) {
        employeComboBox.removeAllItems();
        for (Employe e : employes) {
            employeComboBox.addItem(e);
        }
    }

    public void setSalaireTableData(List<Salaire> salaires) {
        tableModel.setRowCount(0);
        for (Salaire s : salaires) {
            double netAPayer = s.getMontant() + s.getPrimes() - s.getRetenues();
            Object[] row = {
                    s.getIdSalaire(),
                    s.getEmploye().getNom() + " " + s.getEmploye().getPrenom(),
                    s.getMois(),
                    s.getAnnee(),
                    String.format("%.2f MAD", s.getMontant()),
                    String.format("%.2f MAD", s.getPrimes()),
                    String.format("%.2f MAD", s.getRetenues()),
                    String.format("%.2f MAD", netAPayer)
            };
            tableModel.addRow(row);
        }
    }

    public Employe getSelectedEmploye() {
        return (Employe) employeComboBox.getSelectedItem();
    }

    public int getSelectedSalaireId() {
        int row = salaireTable.getSelectedRow();
        return row >= 0 ? (int) tableModel.getValueAt(row, 0) : -1;
    }

    public Object[] showAjouterSalaireDialog() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField moisField = new JTextField();
        JTextField anneeField = new JTextField();
        JTextField primesField = new JTextField("0");
        JTextField retenuesField = new JTextField("0");

        panel.add(new JLabel("Mois (1-12):"));
        panel.add(moisField);
        panel.add(new JLabel("Année:"));
        panel.add(anneeField);
        panel.add(new JLabel("Primes:"));
        panel.add(primesField);
        panel.add(new JLabel("Retenues:"));
        panel.add(retenuesField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Ajouter un salaire",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int mois = Integer.parseInt(moisField.getText().trim());
                int annee = Integer.parseInt(anneeField.getText().trim());
                double primes = Double.parseDouble(primesField.getText().trim());
                double retenues = Double.parseDouble(retenuesField.getText().trim());

                if (mois < 1 || mois > 12) {
                    showMessage("Le mois doit être entre 1 et 12");
                    return null;
                }
                if (annee < 2000 || annee > 2100) {
                    showMessage("Année invalide");
                    return null;
                }

                return new Object[]{mois, annee, primes, retenues};
            } catch (NumberFormatException e) {
                showMessage("Veuillez entrer des valeurs numériques valides");
            }
        }
        return null;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton getAjouterSalaireBtn() {
        return ajouterSalaireBtn;
    }

    public JButton getVoirHistoriqueBtn() {
        return voirHistoriqueBtn;
    }

    public JButton getGenererFichePaieBtn() {
        return genererFichePaieBtn;
    }
}