package gui;

import PerfectGym.*;
import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;
import PerfectGym.quotes.OwnerQuote;
import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.VDMSet;

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
    private JButton viewGroupButton;
    private JTextArea groupsTextArea;
    private JComboBox trainersCombo2;
    private JComboBox traineeCombo;
    private JSpinner feeSpinner;
    private JComboBox ptUserCombo;
    private JButton addPersonalTrainingButton;
    private JTextArea personalTrainingTextArea;
    private JButton removePersonalTrainingButton;

    private Main parent;

    private Club club;

    private Owner owner;

    private ArrayList<User> usersAtLeastEmployeeAccess = new ArrayList<>();
    private ArrayList<User> usersOwnerAccess = new ArrayList<>();
    private ArrayList<Client> clients= new ArrayList<>();
    private ArrayList<Trainer> trainers= new ArrayList<>();
    private ArrayList<SalesRepresentative> salesRepresentatives= new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();

    public ClubMenu(Main parent) {
        this.parent = parent;
    }

    public void setClub(Club club){
        this.club = club;
        owner = club.getOwner();
        ownerTextArea.setText(owner.toString());

        usersOwnerAccess.add(owner);
        usersAtLeastEmployeeAccess.add(owner);


        club.getClients().forEach(c -> {
            clientsCombo.addItem(((Client) c).getName());
            traineeCombo.addItem(((Client) c).getName());
            clients.add((Client)c);
        });

        club.getTrainers().forEach(t -> {
            trainersCombo.addItem(((Trainer) t).getName());
            trainersCombo2.addItem(((Trainer) t).getName());
            if(((User) t).getAccess().equals(OwnerQuote.getInstance()))
                usersOwnerAccess.add((User) t);
            else {
                usersOwnerAccess.add((User) t);
                usersAtLeastEmployeeAccess.add((User) t);
            }
            trainers.add((Trainer) t);
        });

        club.getSalesRepresentatives().forEach(s -> {
            salesRepresentativesCombo.addItem(((SalesRepresentative) s).getName());
            if(((User) s).getAccess().equals(OwnerQuote.getInstance()))
                usersOwnerAccess.add((User) s);
            else
            {
                usersOwnerAccess.add((User) s);
                usersAtLeastEmployeeAccess.add((User) s);
            }
            salesRepresentatives.add((SalesRepresentative) s);
        });

        club.getGroups().keySet().forEach(g -> {
            groupsCombo.addItem(g);
            groups.add((Group) club.getGroups().get(g));
        });


        usersAtLeastEmployeeAccess.forEach(u -> ptUserCombo.addItem(u.getName()));

        setClientsTextArea();
        setTrainerTextArea();
        setSalesRepresentativesTextArea();
        setGroupsTextArea();
        setPersonalTrainingArea();

        addListeners();

    }

    private void addListeners() {
        addPersonalTrainingButton.addActionListener(e->{

            club.addPersonalTraining(trainers.get(trainersCombo2.getSelectedIndex()), clients.get(traineeCombo.getSelectedIndex()), (int) feeSpinner.getValue(), usersAtLeastEmployeeAccess.get(ptUserCombo.getSelectedIndex()));

            setClientsTextArea();
            setTrainerTextArea();
            setPersonalTrainingArea();
        });
        removePersonalTrainingButton.addActionListener(e->{

            club.removeTraineeFromTrainer(trainers.get(trainersCombo2.getSelectedIndex()), clients.get(traineeCombo.getSelectedIndex()), usersAtLeastEmployeeAccess.get(ptUserCombo.getSelectedIndex()));

            setClientsTextArea();
            setTrainerTextArea();
            setPersonalTrainingArea();
        });
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

    private void setPersonalTrainingArea() {
        StringBuilder sb = new StringBuilder();
        for (Object c: club.getClients()) {
            Client client = (Client) c;
            if(client.getTrainer() != null)
                sb.append("Trainee: " +  client.getName() + ", trainer: " + client.getTrainer().getName()).append("\n");
        }
        personalTrainingTextArea.setText(sb.toString());
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


