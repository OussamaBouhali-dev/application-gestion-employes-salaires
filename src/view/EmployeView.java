package view;

import model.Employe;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.util.Properties;

public class EmployeView extends JFrame {
    private JTextField nomField = new JTextField(15);
    private JTextField prenomField = new JTextField(15);
    private JTextField posteField = new JTextField(15);
    private JTextField serviceField = new JTextField(15);
    private JTextField salaireField = new JTextField(15);
    private JButton ajouterButton = new JButton("Ajouter");
    private JTable table;
    private DefaultTableModel tableModel;
    private JDatePickerImpl datePicker;

    public EmployeView() {
        setTitle("Gestion des Employés");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Date picker initialization
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Table setup
        String[] columns = {"ID", "Nom", "Prénom", "Poste", "Service", "Date Embauche", "Salaire"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom:"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Poste:"));
        formPanel.add(posteField);
        formPanel.add(new JLabel("Service (ID):"));
        formPanel.add(serviceField);
        formPanel.add(new JLabel("Date Embauche:"));
        formPanel.add(datePicker);
        formPanel.add(new JLabel("Salaire de base:"));
        formPanel.add(salaireField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterButton);

        // Add to frame
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters

    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getPrenomField() {
        return prenomField;
    }

    public JTextField getPosteField() {
        return posteField;
    }

    public JTextField getServiceField() {
        return serviceField;
    }

    public JTextField getSalaireField() {
        return salaireField;
    }

    public JButton getBtnAjouter() {
        return ajouterButton;
    }

    public LocalDate getSelectedDate() {
        if (datePicker.getModel().getValue() == null) {
            return null;
        }
        return LocalDate.of(
                datePicker.getModel().getYear(),
                datePicker.getModel().getMonth() + 1,
                datePicker.getModel().getDay()
        );
    }

    public double getSalaireDeBase() {
        try {
            return Double.parseDouble(salaireField.getText().trim());
        } catch (NumberFormatException e) {
            return -1; // return -1 to signify invalid salary
        }
    }

    public int getSelectedServiceId() {
        try {
            return Integer.parseInt(serviceField.getText().trim());
        } catch (NumberFormatException e) {
            return -1;  // Invalid input
        }
    }

    public void refreshTable(List<Employe> employes) {
        tableModel.setRowCount(0);
        for (Employe emp : employes) {
            tableModel.addRow(new Object[]{
                    emp.getIdEmploye(),
                    emp.getNom(),
                    emp.getPrenom(),
                    emp.getPoste(),
                    emp.getIdService(),
                    emp.getDateEmbauche(),
                    emp.getSalaireDeBase()
            });
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        posteField.setText("");
        serviceField.setText("");
        salaireField.setText("");
        datePicker.getModel().setValue(null);
    }
}
