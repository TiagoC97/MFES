package gui;

import PerfectGym.*;
import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;
import PerfectGym.quotes.OwnerQuote;
import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.VDMSet;

import javax.swing.*;
import java.time.DayOfWeek;
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
    private JComboBox<String> groupsCombo;
    private JButton viewGroupButton;
    private JTextArea groupsTextArea;
    private JComboBox<String> trainersCombo2;
    private JComboBox<String> traineeCombo;
    private JSpinner feeSpinner;
    private JComboBox<String> ptUserCombo;
    private JButton addPersonalTrainingButton;
    private JTextArea personalTrainingTextArea;
    private JButton removePersonalTrainingButton;
    private JButton addGymClassButton;
    private JComboBox<String> gymClassesCombo;
    private JButton viewGymClassButton;
    private JTextArea gymClassesTextArea;
    private JButton addTrainingSessionButton;
    private JComboBox<String> trainingSessionsCombo;
    private JButton viewTrainingSessionButton;
    private JTextArea trainingSessionTextArea;
    private JComboBox<String> attendeesCombo;
    private JComboBox<String> attendeesUserCombo;
    private JButton addAttendeeButton;
    private JComboBox userAccessUsersCombo;
    private JComboBox<String> employeeUserAccessCombo;
    private JButton giveOwnerAccessButton;
    private JTextField newsletterTextField;
    private JButton changeNewsletterButton;
    private JTextArea newsletterTextArea;
    private JComboBox<String> newsletterUserCombo;
    private JComboBox clientsNotInGroupCombo;
    private JButton addGroupClientButton;
    private JComboBox clientsInGroupCombo;
    private JButton removeGroupClientButton;
    private JButton sendMsgToClientButton;
    private JButton sendMsgToEmployeeButton;
    private JButton sendMsgAllClientsButton;
    private JButton sendMsgAllTrainersButton;
    private JButton sendMsgAllSRsButton;
    private JButton sendMsgGroupButton;
    private JButton sendOfferToGroupButton;

    private Main parent;

    private Club club;

    private Owner owner;


    private ArrayList<User> usersAtLeastEmployeeAccess = new ArrayList<>();
    private ArrayList<User> usersOwnerAccess = new ArrayList<>();
    private ArrayList<User> usersEmployeeAccess = new ArrayList<>();
    private ArrayList<Client> clients= new ArrayList<>();
    private ArrayList<Trainer> trainers= new ArrayList<>();
    private ArrayList<SalesRepresentative> salesRepresentatives= new ArrayList<>();
    private ArrayList<Employee> employees= new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<GymClass> gymClasses = new ArrayList<>();
    private ArrayList<TrainingSession> trainingSessios = new ArrayList<>();

    private ArrayList<Client> clientsInGroup = new ArrayList<>();
    private ArrayList<Client> clientsNotInGroup = new ArrayList<>();


    public ClubMenu(Main parent) {
        this.parent = parent;
    }

    public void setClub(Club club){
        this.club = club;
        start();
        owner = club.getOwner();
        ownerTextArea.setText(owner.toString());

        usersOwnerAccess.add(owner);
        usersAtLeastEmployeeAccess.add(owner);


        club.getClients().forEach(c -> {
            clientsCombo.addItem(((Client) c).getName());
            traineeCombo.addItem(((Client) c).getName());
            attendeesCombo.addItem(((Client) c).getName());
            clients.add((Client)c);
        });

        club.getTrainers().forEach(t -> {
            trainersCombo.addItem(((Trainer) t).getName());
            trainersCombo2.addItem(((Trainer) t).getName());

            trainers.add((Trainer) t);
        });

        club.getSalesRepresentatives().forEach(s -> {
            salesRepresentativesCombo.addItem(((SalesRepresentative) s).getName());

            salesRepresentatives.add((SalesRepresentative) s);
        });

        club.getEmployees().forEach(e -> {

            if(((User) e).getAccess().toString().equals(OwnerQuote.getInstance().toString())) {
                usersOwnerAccess.add((User) e);
                usersAtLeastEmployeeAccess.add((User) e);
            }
            else {

                usersAtLeastEmployeeAccess.add((User) e);
                usersEmployeeAccess.add((User) e);
            }
            employees.add((Employee) e);
        });

        club.getGroups().keySet().forEach(g -> {
            groupsCombo.addItem((String) g);
            groups.add((Group) club.getGroups().get(g));
        });

        club.getGymClasses().forEach(g -> {
            gymClassesCombo.addItem(((GymClass) g).getName() + " -> " + ((GymClass) g).getDescription());
            gymClasses.add((GymClass) g);
        });

        club.getTrainingSessions().forEach(t -> {
            trainingSessionsCombo.addItem(((TrainingSession) t).getDescription());
            trainingSessios.add((TrainingSession) t);
        });

        usersOwnerAccess.forEach(u -> {
            userAccessUsersCombo.addItem(u.getName());
            newsletterUserCombo.addItem(u.getName());
        });

        usersAtLeastEmployeeAccess.forEach(u -> {
            ptUserCombo.addItem(u.getName());
            attendeesUserCombo.addItem(u.getName());
        });

        usersEmployeeAccess.forEach(u ->{
            employeeUserAccessCombo.addItem(u.getName());
        });


        setClientsTextArea();
        setTrainerTextArea();
        setSalesRepresentativesTextArea();
        setGroupsTextArea();
        setPersonalTrainingArea();

        setGymClassesTextArea();
        setTrainingSessionTextArea();

        if(groupsCombo.getItemCount() > 0){
            Group gr = groups.get(groupsCombo.getSelectedIndex());
            gr.getClients().forEach(c -> {
                clientsInGroup.add(((Client) c));
                clientsInGroupCombo.addItem(((Client) c).getName());
            });
            SetUtil.diff(club.getClients(), gr.getClients()).forEach(c -> {
                clientsNotInGroup.add((Client) c);
                clientsNotInGroupCombo.addItem(((Client) c).getName());
            });
        }

        addListeners();

    }

    private void start(){
       usersAtLeastEmployeeAccess = new ArrayList<>();
       usersOwnerAccess = new ArrayList<>();
       usersEmployeeAccess = new ArrayList<>();
       clients= new ArrayList<>();
       trainers= new ArrayList<>();
       salesRepresentatives= new ArrayList<>();
       employees= new ArrayList<>();
       groups = new ArrayList<>();
       gymClasses = new ArrayList<>();
       trainingSessios = new ArrayList<>();
        clientsInGroup = new ArrayList<>();
        clientsNotInGroup = new ArrayList<>();

       employeeUserAccessCombo.removeAllItems();
       userAccessUsersCombo.removeAllItems();
       newsletterUserCombo.removeAllItems();
       gymClassesCombo.removeAllItems();
       trainingSessionsCombo.removeAllItems();
       attendeesUserCombo.removeAllItems();
       attendeesCombo.removeAllItems();
       groupsCombo.removeAllItems();
       ptUserCombo.removeAllItems();
       traineeCombo.removeAllItems();
       salesRepresentativesCombo.removeAllItems();
       trainersCombo.removeAllItems();
       clientsCombo.removeAllItems();
       trainersCombo2.removeAllItems();
       clientsInGroupCombo.removeAllItems();
       clientsNotInGroupCombo.removeAllItems();
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

        addAttendeeButton.addActionListener(e->{

            club.addAttendeeToGymClass(gymClasses.get(gymClassesCombo.getSelectedIndex()),
                    clients.get(attendeesCombo.getSelectedIndex()),
                    usersAtLeastEmployeeAccess.get(attendeesUserCombo.getSelectedIndex()));


            setClientsTextArea();
            setTrainerTextArea();
            setGymClassesTextArea();
        });

        giveOwnerAccessButton.addActionListener(e -> {
            User user = usersEmployeeAccess.get(employeeUserAccessCombo.getSelectedIndex());
            int index = employeeUserAccessCombo.getSelectedIndex();
            club.setUserAccess(usersOwnerAccess.get(userAccessUsersCombo.getSelectedIndex()), (Employee) user , OwnerQuote.getInstance());
            setTrainerTextArea();
            setSalesRepresentativesTextArea();
            userAccessUsersCombo.addItem(user.getName());
            usersOwnerAccess.add(user);
            employeeUserAccessCombo.removeItemAt(index);
            usersEmployeeAccess.remove(user);
        });

        changeNewsletterButton.addActionListener(e -> {
            club.addNewsletter(newsletterTextField.getText(), usersOwnerAccess.get(newsletterUserCombo.getSelectedIndex()));
            newsletterTextArea.setText("Newsletter: " + newsletterTextField.getText());
        });

        groupsCombo.addActionListener(e ->{
            clientsInGroup.clear();
            clientsNotInGroup.clear();
            clientsInGroupCombo.removeAllItems();
            clientsNotInGroupCombo.removeAllItems();
            Group gr = groups.get(groupsCombo.getSelectedIndex());
            gr.getClients().forEach(c -> {
                clientsInGroup.add(((Client) c));
                clientsInGroupCombo.addItem(((Client) c).getName());
            });
            SetUtil.diff(club.getClients(), gr.getClients()).forEach(c -> {
                clientsNotInGroup.add((Client) c);
                clientsNotInGroupCombo.addItem(((Client) c).getName());
            });
        });

        addGroupClientButton.addActionListener(e -> {
            if(clientsNotInGroupCombo.getItemCount() > 0) {
                int index = clientsNotInGroupCombo.getSelectedIndex();
                Client c1 = clientsNotInGroup.get(index);

                clientsInGroup.add(c1);
                clientsInGroupCombo.addItem(c1.getName());
                clientsNotInGroup.remove(c1);
                clientsNotInGroupCombo.removeItemAt(index);
                club.addGroupClient(groupsCombo.getSelectedItem().toString(), c1, owner);

                setGroupsTextArea();
            }
        });

        removeGroupClientButton.addActionListener(e -> {
            if(clientsInGroupCombo.getItemCount() > 0) {
                int index = clientsInGroupCombo.getSelectedIndex();
                Client c1 = clientsInGroup.get(index);

                clientsNotInGroup.add(c1);
                clientsNotInGroupCombo.addItem(c1.getName());
                clientsInGroup.remove(c1);
                clientsInGroupCombo.removeItemAt(index);
                club.removeGroupClient(groupsCombo.getSelectedItem().toString(), c1, owner);

                setGroupsTextArea();
            }
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

    public void addGroup(String n, VDMSet clients, User u){
        club.addGroup(n, clients, u);
        groupsCombo.addItem(n);
        setGroupsTextArea();
    }

    public void addGymClass(String d, String n, Trainer t, Object da, Number sh, Number eh, Number dat, User u){
        club.addGymClass(d, n, t, da, sh, eh, dat, u);
        gymClassesCombo.addItem(n + " -> " + d);
        setGymClassesTextArea();
    }

    public void addTrainingSession(String d, Client c, Object da, Number sh, Number eh, Number dat, User u){
        club.addTrainingSession(d, c, da, sh, eh, dat, u);
        trainingSessionsCombo.addItem(d);
        setTrainingSessionTextArea();
    }

    public void sendMsgToClient(String m, Client c, User u){
        club.sendMessageClient(m, c, u);
    }

    public void sendMsgToEmployee(String m, Employee e, User u){
        club.sendMessageEmployee(m, e, u);
    }

    public void sendMsgAllClients(String m, User u){
        club.sendMessageAllClients(m, u);
    }

    public void sendMsgAllTrainers(String m, User u){
        club.sendMessageAllTrainers(m, u);
    }

    public void sendMessageAllSalesRepresentatives(String m, User u){
        club.sendMessageAllSalesRepresentatives(m, u);
    }

    public void sendMsgGroup(String m, String g, User u){
        club.sendMessageToGroup(m, g, u);
    }

    public void sendSendOfferToGroup(String m, String g, User u){
        club.sendOfferToGroup(m, g, u);
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
            sb.append("Group " + g.toString() + ": " + club.getGroups().get(g).toString()).append("\n");
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

    private void setGymClassesTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object g: club.getGymClasses()) {
            sb.append(g.toString()).append("\n");
        }
        gymClassesTextArea.setText(sb.toString());
    }

    private void setTrainingSessionTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t: club.getTrainingSessions()) {
            sb.append(t.toString()).append("\n");
        }
        trainingSessionTextArea.setText(sb.toString());
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

    public JButton getAddGroupButton() {
        return addGroupButton;
    }

    public JButton getAddGymClassButton() {
        return addGymClassButton;
    }

    public JButton getAddTrainingSessionButton() {
        return addTrainingSessionButton;
    }

    public ArrayList<Client> getClients(){
        return clients;
    }

    public ArrayList<Trainer> getTrainers(){
        return trainers;
    }

    public ArrayList<SalesRepresentative> getSalesRepresentatives(){
        return salesRepresentatives;
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public ArrayList<User> getUsersAtLeastEmployeeAccess() {
        return usersAtLeastEmployeeAccess;
    }

    public ArrayList<User> getUsersOwnerAccess() {
        return usersOwnerAccess;
    }

    public JButton getSendMsgToClientButton() {
        return sendMsgToClientButton;
    }

    public JButton getSendMsgToEmployeeButton() {
        return sendMsgToEmployeeButton;
    }

    public JButton getSendMsgAllClientsButton() {
        return sendMsgAllClientsButton;
    }

    public JButton getSendMsgAllTrainersButton() {
        return sendMsgAllTrainersButton;
    }

    public JButton getSendMsgAllSRsButton() {
        return sendMsgAllSRsButton;
    }

    public JButton getSendMsgGroupButton() {
        return sendMsgGroupButton;
    }

    public JButton getSendOfferToGroupButton() {
        return sendOfferToGroupButton;
    }


}


