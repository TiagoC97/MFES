package gui;

import PerfectGym.*;
import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;
import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.VDMSet;

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
    private ArrayList<Client> clients= new ArrayList<>();
    private ArrayList<Trainer> trainers= new ArrayList<>();
    private ArrayList<SalesRepresentative> salesRepresentatives= new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();

    public PerfectGymMenu(Main parent) {
        this.parent = parent;
        initOwners();
        initClubs();
        initClients();
        initTrainers();
        initSalesRepresentatives();
        initGroups();

        addListeners();


    }


    private void addListeners() {
        addClubButton.addActionListener(e->{
            Owner o = owners.get(ownersCombo.getSelectedIndex());
            addClub(clubNameTextField.getText(), o, (int) clubFeeSpinner.getValue());
            ownersCombo.removeItemAt(ownersCombo.getSelectedIndex());

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

    private void initClients(){
        clients.add(new Client("Rui A", 21, MaleQuote.getInstance(), "portuguese"));
        clients.add(new Client("Tiago B", 18, MaleQuote.getInstance(), "portuguese"));
        clients.add(new Client("Maria C", 27, FemaleQuote.getInstance(), "portuguese"));
        clients.add(new Client("Jorge D", 32, MaleQuote.getInstance(), "portuguese"));
        clients.add(new Client("Tiago E", 19, MaleQuote.getInstance(), "chinese"));
        clients.add(new Client("Gui G", 21, MaleQuote.getInstance(), "chinese"));
        clients.add(new Client("Helder Q", 23, MaleQuote.getInstance(), "chinese"));
        clients.add(new Client("Vitoria A", 28, FemaleQuote.getInstance(), "chinese"));
        clients.add(new Client("Marta S", 28, FemaleQuote.getInstance(), "chinese"));
        clients.add(new Client("Gianluigia E", 23, FemaleQuote.getInstance(), "italian"));

        clients.forEach(c -> clubs.get(0).addClient(c, clubs.get(0).getOwner()));

    }

    private void initTrainers(){
        trainers.add(new Trainer("John A", 21, MaleQuote.getInstance(), "english"));
        trainers.add(new Trainer("Gerson B", 27, MaleQuote.getInstance(), "brazilian"));
        trainers.add(new Trainer("Kuala C", 32, FemaleQuote.getInstance(), "indian"));
        trainers.add(new Trainer("Ranjit D", 24, MaleQuote.getInstance(), "american"));
        trainers.add(new Trainer("Vasco E", 29, MaleQuote.getInstance(), "french"));
        trainers.add(new Trainer("Gigi G", 21, FemaleQuote.getInstance(), "chinese"));
        trainers.add(new Trainer("Quae Q", 23, FemaleQuote.getInstance(), "chinese"));

        trainers.forEach(t -> clubs.get(0).addTrainer(t, clubs.get(0).getOwner()));
    }

    private void initSalesRepresentatives(){
        salesRepresentatives.add(new SalesRepresentative("Juju A", 21, FemaleQuote.getInstance(), "brazilian"));
        salesRepresentatives.add(new SalesRepresentative("Tita B", 27, MaleQuote.getInstance(), "peruan"));
        salesRepresentatives.add(new SalesRepresentative("Kika C", 32, FemaleQuote.getInstance(), "indian"));
        salesRepresentatives.add(new SalesRepresentative("Vivian D", 24, FemaleQuote.getInstance(), "american"));
        salesRepresentatives.add(new SalesRepresentative("Tito E", 29, MaleQuote.getInstance(), "french"));
        salesRepresentatives.add(new SalesRepresentative("Tai Luan G", 21, FemaleQuote.getInstance(), "chinese"));
        salesRepresentatives.add(new SalesRepresentative("We Wi", 23, FemaleQuote.getInstance(), "chinese"));

        salesRepresentatives.forEach(s -> clubs.get(0).addSalesRepresentative(s, clubs.get(0).getOwner()));
    }

    private void initGroups(){
        clubs.get(0).addGroup("PuxadoresDeFerro", SetUtil.set(clients.get(0), clients.get(1), clients.get(2)), clubs.get(0).getOwner());
        clubs.get(0).addGroup("LALALA", SetUtil.set(clients.get(3), clients.get(4), clients.get(5)), clubs.get(0).getOwner());
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
