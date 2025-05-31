package view;

import model.Service;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ServiceView extends JFrame {
    private JButton ajouterBtn = new JButton("Ajouter");
    private JButton modifierBtn = new JButton("Modifier");
    private JButton supprimerBtn = new JButton("Supprimer");
    private JTextField nomField = new JTextField(20);
    private JTextArea descriptionArea = new JTextArea(5, 20);
    private JTable serviceTable;
    private DefaultTableModel tableModel;

    public ServiceView() {
        setTitle("Gestion des Services");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize table
        String[] columns = {"ID", "Nom", "Description"};
        tableModel = new DefaultTableModel(columns, 0);
        serviceTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(serviceTable);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);

        // Main layout
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getAjouterBtn() { return ajouterBtn; }
    public JButton getModifierBtn() { return modifierBtn; }
    public JButton getSupprimerBtn() { return supprimerBtn; }
    public JTable getServiceTable() { return serviceTable; }
    public JTextField getNomField() { return nomField; }
    public JTextArea getDescriptionArea() { return descriptionArea; }

    public void refreshTable(List<Service> services) {
        tableModel.setRowCount(0);
        for (Service service : services) {
            tableModel.addRow(new Object[]{
                    service.getIdService(),
                    service.getNom(),
                    service.getDescription()
            });
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearFields() {
        nomField.setText("");
        descriptionArea.setText("");
    }

    public Service getSelectedService() {
        int row = serviceTable.getSelectedRow();
        if (row >= 0) {
            return new Service(
                    (int) tableModel.getValueAt(row, 0),
                    (String) tableModel.getValueAt(row, 1),
                    (String) tableModel.getValueAt(row, 2)
            );
        }
        return null;
    }

    public void fillFields(Service service) {
        nomField.setText(service.getNom());
        descriptionArea.setText(service.getDescription());
    }
}