package gui;

import PerfectGym.Client;
import PerfectGym.Employee;
import PerfectGym.Trainer;
import PerfectGym.User;
import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;

import javax.swing.*;
import java.util.ArrayList;

public class SendMessageToUserMenu {
    private JComboBox destinatariesCombo;
    private JTextField msgTextField;
    private JComboBox usersCombo;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel panel;

    private String destinatary;

    private Main parent;
    private ArrayList<Client> clients;
    private ArrayList<Employee> employees;
    private ArrayList<User> usersOwnerAccess;


    public SendMessageToUserMenu(Main parent) {
        this.parent = parent;
    }

    public void setVisible() {
        this.panel.setVisible(true);
    }

    public JPanel getPane() {
        return panel;
    }


    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }


    public void setUsersOwnerAccess(ArrayList<User> usersOwnerAccess) {
        this.usersOwnerAccess = usersOwnerAccess;
        usersCombo.removeAllItems();
        usersOwnerAccess.forEach(u -> usersCombo.addItem(u.getName()));
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
        destinatariesCombo.removeAllItems();
        clients.forEach(u -> destinatariesCombo.addItem(u.getName()));
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
        destinatariesCombo.removeAllItems();
        employees.forEach(u -> destinatariesCombo.addItem(u.getName()));
    }

    public String getMessage() {
        return msgTextField.getText();
    }

    public Client getClient() {
        return clients.get(destinatariesCombo.getSelectedIndex());
    }

    public Employee getEmployee() {
        return employees.get(destinatariesCombo.getSelectedIndex());
    }

    public User getUser() {
        return usersOwnerAccess.get(usersCombo.getSelectedIndex());
    }

    public String getDestinatary() {
        return destinatary;
    }

    public void setDestinatary(String destinatary){
        this.destinatary = destinatary;
    }


}
