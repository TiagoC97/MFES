package gui;

import PerfectGym.Client;
import PerfectGym.User;

import javax.swing.*;
import java.util.ArrayList;

public class CreateInvoiceMenu {
    private JComboBox paymentsToAddCombo;
    private JButton addPaymentButton;
    private JButton removePaymentButton;
    private JList addedPaymentsList;
    private JTextField dateTextField;
    private JTextField hourTextField;
    private JComboBox typeCombo;
    private JComboBox allACtiveCombo;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;

    private Main parent;


    private ArrayList<Client> clients;
    private ArrayList<User> usersAtLeastEmployees;

    public CreateInvoiceMenu(Main parent) {
        this.parent = parent;

        addListeners();

    }
}
