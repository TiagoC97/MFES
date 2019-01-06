package gui;

import PerfectGym.*;
import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.VDMSet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class CreateInvoiceMenu {
    private JComboBox paymentsToAddCombo;
    private JButton addPaymentButton;
    private JButton removePaymentButton;
    private JList addedPaymentsList;
    private JTextField dateTextField;
    private JTextField hourTextField;
    private JComboBox typeCombo;
    private JComboBox<String> allACtiveCombo;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;
    private JComboBox clientsCombo;
    private JComboBox usersCombo;

    private Main parent;


    private ArrayList<Client> clients;
    private ArrayList<User> usersAtLeastEmployees;

    private ArrayList<ProductPayment> productPayments = new ArrayList<>();
    private ArrayList<GymFeePayment> gymFeePayments = new ArrayList<>();
    private ArrayList<PersonalTrainingPayment> personalTrainingPayments = new ArrayList<>();

    private VDMSet payments;
    private Vector<Payment> paymentsVector;

    public CreateInvoiceMenu(Main parent) {
        this.parent = parent;

        addListeners();
        typeCombo.addItem("product");
        typeCombo.addItem("gymFee");
        typeCombo.addItem("personalTraining");


        allACtiveCombo.addItem("false");
        allACtiveCombo.addItem("true");

    }

    private Client getClient(String name) {
        Client cli = null;
        for (Client client : clients) {
            if (client.getName().equals(name))
                cli = client;
        }
        return cli;
    }

    private void addListeners() {
        addPaymentButton.addActionListener(e -> {
            if (paymentsToAddCombo.getSelectedItem() != null) {
                int index = paymentsToAddCombo.getSelectedIndex();
                payments = SetUtil.union(payments, SetUtil.set(((Payment)paymentsToAddCombo.getSelectedItem())));
                paymentsVector.add(((Payment)paymentsToAddCombo.getSelectedItem()));
                addedPaymentsList.setListData(paymentsVector);
                paymentsToAddCombo.removeItemAt(index);
            }
        });

        removePaymentButton.addActionListener(e -> {
            if (addedPaymentsList.getSelectedValue() != null) {
                int index = addedPaymentsList.getSelectedIndex();
                String name = addedPaymentsList.getSelectedValue().toString();
                payments = SetUtil.diff(payments, SetUtil.set(((Payment)addedPaymentsList.getSelectedValue())));
                paymentsToAddCombo.addItem(((Payment)addedPaymentsList.getSelectedValue()));
                paymentsVector.removeElementAt(index);
                addedPaymentsList.setListData(paymentsVector);

            }
        });

        typeCombo.addActionListener(e -> {
            String type = typeCombo.getSelectedItem().toString();
            paymentsVector = new Vector<>();
            payments = new VDMSet();
            paymentsToAddCombo.removeAllItems();
            addedPaymentsList.setListData(paymentsVector);
            switch (type) {
                case "product":
                    productPayments.forEach(p -> paymentsToAddCombo.addItem(p));
                    break;
                case "gymFee":
                    gymFeePayments.forEach(p -> paymentsToAddCombo.addItem(p));
                    break;
                case "personalTraining":
                    personalTrainingPayments.forEach(p -> paymentsToAddCombo.addItem(p));
                    break;

            }
        });

        clientsCombo.addActionListener(e -> {
            Client client = clients.get(clientsCombo.getSelectedIndex());
            setGymFeePayments(client);
            setProductPayments(client);
            setPersonalTrainingPayments(client);
            typeCombo.setSelectedIndex(0);


        });
    }

    public void start() {
        paymentsVector = new Vector<>();
        payments = new VDMSet();
        paymentsToAddCombo.removeAllItems();
        addedPaymentsList.setListData(paymentsVector);
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
        clientsCombo.removeAllItems();
        clients.forEach(c -> clientsCombo.addItem(c.getName()));
    }

    public void setUsersAtLeastEmployees(ArrayList<User> usersAtLEastEmployees) {
        this.usersAtLeastEmployees = usersAtLEastEmployees;
        usersCombo.removeAllItems();
        usersAtLeastEmployees.forEach(u -> usersCombo.addItem(u.getName()));
    }

    public void setProductPayments(Client client) {
        productPayments.clear();
        client.getProductPayments().forEach(p -> {
            productPayments.add((ProductPayment) p);
        });
    }

    public void setGymFeePayments(Client client) {
        gymFeePayments.clear();
        client.getGymFeePayments().forEach(p -> {
            gymFeePayments.add((GymFeePayment) p);
        });
    }

    public void setPersonalTrainingPayments(Client client) {
        personalTrainingPayments.clear();
        client.getPersonalTrainingPayments().forEach(p -> {
            personalTrainingPayments.add((PersonalTrainingPayment) p);
        });
    }

    public void setVisible() {
        this.pane.setVisible(true);
    }

    public JPanel getPane() {
        return pane;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public VDMSet getPayments(){ return payments;}
    public Client getClientNow(){ return clients.get(clientsCombo.getSelectedIndex());}
    public Number getDate(){ return Integer.parseInt(dateTextField.getText()); }
    public Number getHour(){ return Integer.parseInt(hourTextField.getText()); }
    public User getUser(){ return usersAtLeastEmployees.get(usersCombo.getSelectedIndex());}
    public boolean getAllActivePayments(){ return (allACtiveCombo.getSelectedItem().toString().equals("true"));}
    public String getType(){ return typeCombo.getSelectedItem().toString();}

}
