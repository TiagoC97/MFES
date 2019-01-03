package gui;

import PerfectGym.*;
import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PerfectGymMenu extends JFrame{
    private Main parent;
    private JPanel pane;
    private JButton backButton;
    private JComboBox<String> ownersCombo;
    private JSpinner clubFeeSpinner;
    private JButton createOwnerButton;
    private JButton addClubButton;
    private JComboBox<String> clubsCombo;
    private JButton viewClubButton;
    private JTextField clubNameTextField;
    private JTextArea clubsTextArea;
    private JTextArea ownersTextArea;

    private ArrayList<Owner> owners = new ArrayList<>();
    private ArrayList<Club> clubs= new ArrayList<>();

    public PerfectGymMenu(Main parent) {
        this.parent = parent;
        initOwners();
        initClubs();


        addListeners();


    }


    private void addListeners() {
        addClubButton.addActionListener(e->{
            addClub(clubNameTextField.getText(), owners.get(ownersCombo.getSelectedIndex()), (int) clubFeeSpinner.getValue());
        });
    }

    public void addOwner(String n, int a, Object g, String nat){
        owners.add(new Owner(n, a, g, nat));
        ownersCombo.addItem(n);
        setOwnersShowArea();
    }

    public void addClub(String n, Owner o, int f){
        clubs.add(new Club(n, o, f));
        clubsCombo.addItem(n);
        setClubsShowArea();
    }


    private void initOwners(){
        owners.add(new Owner("Rui", 21, MaleQuote.getInstance(), "portuguese"));
        owners.add(new Owner("Tiago", 21, MaleQuote.getInstance(), "portuguese"));

        setOwnersShowArea();
        owners.forEach(o -> ownersCombo.addItem(o.getName()));
    }

    private void initClubs(){
        clubs.add(new Club("Bombados", owners.get(0), 17));
        clubs.add(new Club("PuxaFerro", owners.get(1), 35));

        setClubsShowArea();
        clubs.forEach(c -> clubsCombo.addItem(c.getName()));
    }


    private void setOwnersShowArea() {
        StringBuilder sb = new StringBuilder();
        for (Owner o: owners) {
            sb.append(o.toString()).append("\n");
        }
        ownersTextArea.setText(sb.toString());
    }

    private void setClubsShowArea() {
        StringBuilder sb = new StringBuilder();
        for (Club c: clubs) {
            sb.append(c.toString()).append("\n");
        }
        clubsTextArea.setText(sb.toString());
    }

    public void setVisible() {
        this.pane.setVisible(true);
    }

    public Club getSelectedClub(){
        return clubs.get(clubsCombo.getSelectedIndex());
    }


    public JPanel getPane() {
        return pane;
    }

    public JButton getBackButton() {
        return backButton;
    }
    public JButton getCreateOwnerButton() {
        return createOwnerButton;
    }

    public  JButton getViewClubButton(){
        return viewClubButton;
    }

}
