package gui;

import PerfectGym.*;
import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;

import javax.swing.*;
import java.util.ArrayList;

public class ClubMenu {
    private JPanel pane;
    private JButton backButton;
    private JComboBox<String> clientsCombo;
    private JButton viewClientButton;
    private JButton viewOwnerButton;
    private JButton addClientButton;
    private JButton viewSalesRepresentativeButton;
    private JComboBox<String> salesRepresentativesCombo;
    private JButton addSalesRepresentativeButton;
    private JButton addTrainerButton;
    private JComboBox<String> trainersCombo;
    private JButton viewTrainerButton;
    private JTextArea clientsTextArea;
    private JTextArea trainersTextArea;
    private JTextArea salesRepresentativesTextArea;
    private JTextArea ownerTextArea;
    private JButton addGroupButton;
    private JComboBox groupsCombo;
    private JButton addGroupButton1;
    private JTextArea groupsTextArea;

    private Main parent;

    private Club club;

    private Owner owner;

    public ClubMenu(Main parent) {
        this.parent = parent;
    }

    public void setClub(Club club){
        this.club = club;
        owner = club.getOwner();
        ownerTextArea.setText(owner.toString());


        club.getClients().forEach(c -> {
            clientsCombo.addItem(((Client) c).getName());
        });

        club.getTrainers().forEach(t -> {
            trainersCombo.addItem(((Trainer) t).getName());
        });

        club.getSalesRepresentatives().forEach(s -> {
            salesRepresentativesCombo.addItem(((SalesRepresentative) s).getName());
        });

        club.getGroups().keySet().forEach(g -> {
            groupsCombo.addItem(g);
        });

        setClientsTextArea();
        setTrainerTextArea();
        setSalesRepresentativesTextArea();
        setGroupsTextArea();

    }

    public void addClient(String n, int a, Object g, String nat){
        club.addClient(new Client(n, a, g, nat), owner);
        clientsCombo.addItem(n);
        setClientsTextArea();
    }

    public void addTrainer(String n, int a, Object g, String nat){
        club.addTrainer(new Trainer(n, a, g, nat), owner);
        trainersCombo.addItem(n);
        setTrainerTextArea();
    }


    public void addSalesRepresentative(String n, int a, Object g, String nat){
        club.addSalesRepresentative(new SalesRepresentative(n, a, g, nat), owner);
        salesRepresentativesCombo.addItem(n);
        setSalesRepresentativesTextArea();
    }




    private void setClientsTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object c: club.getClients()) {
            sb.append(c.toString()).append("\n");
        }
        clientsTextArea.setText(sb.toString());
    }

    private void setTrainerTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t: club.getTrainers()) {
            sb.append(t.toString()).append("\n");
        }
        trainersTextArea.setText(sb.toString());
    }

    private void setSalesRepresentativesTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object s: club.getSalesRepresentatives()) {
            sb.append(s.toString()).append("\n");
        }
        salesRepresentativesTextArea.setText(sb.toString());
    }

    private void setGroupsTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object g: club.getGroups().keySet()) {
            sb.append(club.getGroups().get(g).toString()).append("\n");
        }
        groupsTextArea.setText(sb.toString());
    }

    public void setVisible() {
        this.pane.setVisible(true);
    }

    public JPanel getPane() {
        return pane;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAddClientButton() {
        return addClientButton;
    }

    public JButton getAddSalesRepresentativeButton() {
        return addSalesRepresentativeButton;
    }

    public JButton getAddTrainerButton() {
        return addTrainerButton;
    }
}


