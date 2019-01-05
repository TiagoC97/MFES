package gui;

import PerfectGym.*;
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
    private JButton addProductButton;
    private JComboBox productsCombo;
    private JButton removeProductButton;
    private JSpinner qttSpinner;
    private JButton addQuantityButton;
    private JTextArea productsTextArea;


    private JButton addInvoiceButton;
    private JComboBox invoicesCombo;
    private JComboBox<String> productsUserCombo;
    private JTextArea invoicesTextArea;
    private JButton addLeadButton;
    private JTextArea crmTextArea;



    private JButton addLeadWithSalesButton;
    private JComboBox leadsCombo;
    private JComboBox leadSRsCombo;
    private JButton removeLeadButton;
    private JComboBox crmUserCombo;
    private JButton removeSRButton;
    private JButton assignSRButton;
    private JButton transformLeadButton;
    private JComboBox invoicePaymentsCombo;
    private JButton addPaymentToInvoiceButton;

    private Main parent;

    private Club club;

    private Owner owner;


    private ArrayList<User> usersAtLeastEmployeeAccess = new ArrayList<>();
    private ArrayList<User> usersOwnerAccess = new ArrayList<>();
    private ArrayList<User> usersEmployeeAccess = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<Trainer> trainers = new ArrayList<>();
    private ArrayList<SalesRepresentative> salesRepresentatives = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<GymClass> gymClasses = new ArrayList<>();
    private ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Lead> leads = new ArrayList<>();
    private ArrayList<Invoice> invoices= new ArrayList<>();

    private ArrayList<Client> clientsInGroup = new ArrayList<>();
    private ArrayList<Client> clientsNotInGroup = new ArrayList<>();


    public ClubMenu(Main parent) {
        this.parent = parent;
    }

    public void setClub(Club club) {
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
            clients.add((Client) c);
        });

        club.getTrainers().forEach(t -> {
            trainersCombo.addItem(((Trainer) t).getName());
            trainersCombo2.addItem(((Trainer) t).getName());

            trainers.add((Trainer) t);
        });

        leadSRsCombo.addItem("");
        club.getSalesRepresentatives().forEach(s -> {
            salesRepresentativesCombo.addItem(((SalesRepresentative) s).getName());
            leadSRsCombo.addItem(((SalesRepresentative) s).getName());
            salesRepresentatives.add((SalesRepresentative) s);
        });

        club.getEmployees().forEach(e -> {

            if (((User) e).getAccess().toString().equals(OwnerQuote.getInstance().toString())) {
                usersOwnerAccess.add((User) e);
                usersAtLeastEmployeeAccess.add((User) e);
            } else {

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
            trainingSessions.add((TrainingSession) t);
        });

        club.getProducts().forEach(p -> {
            productsCombo.addItem(((Product) p).getName());
            products.add((Product) p);
        });

        club.getCRM().getLeads().keySet().forEach(l -> {
            leadsCombo.addItem(((Lead) l).getName());

            leads.add((Lead) l);
        });

        club.getInvoices().forEach(i -> {
            invoicesCombo.addItem("Client: " + ((Invoice) i).getClient().getName() + " Type: " + ((Invoice) i).getType() +
                    " Date: " + ((Invoice) i).getDate());
            invoices.add((Invoice) i);
        });


        usersOwnerAccess.forEach(u -> {
            userAccessUsersCombo.addItem(u.getName());
            newsletterUserCombo.addItem(u.getName());
        });

        usersAtLeastEmployeeAccess.forEach(u -> {
            ptUserCombo.addItem(u.getName());
            attendeesUserCombo.addItem(u.getName());
            productsUserCombo.addItem(u.getName());
            crmUserCombo.addItem(u.getName());
        });

        usersEmployeeAccess.forEach(u -> {
            employeeUserAccessCombo.addItem(u.getName());
        });

        setSRFromLead();


        setClientsTextArea();
        setTrainerTextArea();
        setSalesRepresentativesTextArea();
        setGroupsTextArea();
        setPersonalTrainingArea();
        newsletterTextArea.setText("Newsletter: " + club.getNewsletter());

        setGymClassesTextArea();
        setTrainingSessionTextArea();


        setProductsTextArea();
        setCRMTextArea();

        setInvoicesTextArea();

        if (groupsCombo.getItemCount() > 0) {
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

    private void start() {
        usersAtLeastEmployeeAccess = new ArrayList<>();
        usersOwnerAccess = new ArrayList<>();
        usersEmployeeAccess = new ArrayList<>();
        clients = new ArrayList<>();
        trainers = new ArrayList<>();
        salesRepresentatives = new ArrayList<>();
        employees = new ArrayList<>();
        groups = new ArrayList<>();
        gymClasses = new ArrayList<>();
        trainingSessions = new ArrayList<>();
        clientsInGroup = new ArrayList<>();
        clientsNotInGroup = new ArrayList<>();
        products = new ArrayList<>();
        leads = new ArrayList<>();
        invoices= new ArrayList<>();


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
        productsCombo.removeAllItems();
        leadsCombo.removeAllItems();
        crmUserCombo.removeAllItems();
        leadSRsCombo.removeAllItems();
        invoicesCombo.removeAllItems();
    }

    private void addListeners() {
        addPersonalTrainingButton.addActionListener(e -> {

            club.addPersonalTraining(trainers.get(trainersCombo2.getSelectedIndex()), clients.get(traineeCombo.getSelectedIndex()), (int) feeSpinner.getValue(), usersAtLeastEmployeeAccess.get(ptUserCombo.getSelectedIndex()));

            setClientsTextArea();
            setTrainerTextArea();
            setPersonalTrainingArea();
        });
        removePersonalTrainingButton.addActionListener(e -> {

            club.removeTraineeFromTrainer(trainers.get(trainersCombo2.getSelectedIndex()), clients.get(traineeCombo.getSelectedIndex()), usersAtLeastEmployeeAccess.get(ptUserCombo.getSelectedIndex()));

            setClientsTextArea();
            setTrainerTextArea();
            setPersonalTrainingArea();
        });

        addAttendeeButton.addActionListener(e -> {

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
            club.setUserAccess(usersOwnerAccess.get(userAccessUsersCombo.getSelectedIndex()), (Employee) user, OwnerQuote.getInstance());
            setTrainerTextArea();
            setSalesRepresentativesTextArea();
            userAccessUsersCombo.addItem(user.getName());
            usersOwnerAccess.add(user);
            employeeUserAccessCombo.removeItemAt(index);
            usersEmployeeAccess.remove(user);
        });

        changeNewsletterButton.addActionListener(e -> {
            club.addNewsletter(newsletterTextField.getText(), usersOwnerAccess.get(newsletterUserCombo.getSelectedIndex()));
            newsletterTextArea.setText("Newsletter: " + club.getNewsletter());
        });

        groupsCombo.addActionListener(e -> {
            if(groupsCombo.getItemCount() == 0 || groups.size() == 0)
                return;
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
            if (clientsNotInGroupCombo.getItemCount() > 0) {
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
            if (clientsInGroupCombo.getItemCount() > 0) {
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

        addQuantityButton.addActionListener(e -> {
            if (productsCombo.getItemCount() > 0) {
                club.addStockOfProduct(products.get(productsCombo.getSelectedIndex()),
                        (Number) qttSpinner.getValue(),
                        usersAtLeastEmployeeAccess.get(productsUserCombo.getSelectedIndex()));
            }
        });

        removeProductButton.addActionListener(e -> {
            if (productsCombo.getItemCount() > 0) {
                int index = productsCombo.getSelectedIndex();
                productsCombo.removeItemAt(index);
                products.remove(index);

            }
        });

        leadsCombo.addActionListener(e -> {
            setSRFromLead();
        });

        assignSRButton.addActionListener(e -> {
            Lead l = leads.get(leadsCombo.getSelectedIndex());
            int index = leadSRsCombo.getSelectedIndex();
            System.out.println(index);
            if (index == 0) {
                club.setCRMLeadSR(l, null, usersAtLeastEmployeeAccess.get(crmUserCombo.getSelectedIndex()));
            } else
                club.setCRMLeadSR(l, salesRepresentatives.get(index - 1), usersAtLeastEmployeeAccess.get(crmUserCombo.getSelectedIndex()));
            setCRMTextArea();
        });

        removeSRButton.addActionListener(e -> {
            Lead l = leads.get(leadsCombo.getSelectedIndex());
            if (club.getCRM().getLeadSR(leads.get(leadsCombo.getSelectedIndex())) != null) {
                club.removeLeadSR(l, usersAtLeastEmployeeAccess.get(crmUserCombo.getSelectedIndex()));
                setCRMTextArea();
                setSRFromLead();
            }

        });

        removeLeadButton.addActionListener(e -> {
            int index = leadsCombo.getSelectedIndex();
            Lead l = leads.get(index);
            leadsCombo.removeItemAt(index);
            leads.remove(l);
            club.removeCRMLead(l, usersAtLeastEmployeeAccess.get(crmUserCombo.getSelectedIndex()));
            setCRMTextArea();
        });

        transformLeadButton.addActionListener(e -> {
            int index = leadsCombo.getSelectedIndex();
            Lead l = leads.get(index);
            club.transformLeadIntoClient(l, usersAtLeastEmployeeAccess.get(crmUserCombo.getSelectedIndex()));
            leadsCombo.removeItemAt(index);
            leads.remove(l);
            clientsCombo.addItem(l.getName());
            traineeCombo.addItem(l.getName());
            attendeesCombo.addItem(l.getName());
            clients.add(new Client(l.getName(), l.getAge(), l.getGender(), l.getNationality()));
            setClientsTextArea();
            setCRMTextArea();

        });

        invoicesCombo.addActionListener(e -> {
            invoicePaymentsCombo.removeAllItems();
            Invoice i = invoices.get(invoicesCombo.getSelectedIndex());
            switch (i.getType()) {
                case "product":
                    if(i.getClient().getProductPayments().size() > 0)
                        i.getClient().getProductPayments().forEach(p -> {
                            invoicePaymentsCombo.addItem(p);
                        });
                    break;
                case "gymFee":
                    if(i.getClient().getGymFeePayments().size() > 0)
                        i.getClient().getGymFeePayments().forEach(p -> {
                            invoicePaymentsCombo.addItem(p);
                        });
                    break;
                case "personalTraining":
                    if(i.getClient().getPersonalTrainingPayments().size() > 0)
                        i.getClient().getPersonalTrainingPayments().forEach(p -> {
                            invoicePaymentsCombo.addItem(p);
                        });
                    break;

            }
        });

        addPaymentToInvoiceButton.addActionListener(e -> {
            System.out.println(SetUtil.set(invoicePaymentsCombo.getSelectedItem()));
            club.addPaymentToInvoice(invoices.get(invoicesCombo.getSelectedIndex()), SetUtil.set(invoicePaymentsCombo.getSelectedItem()) ,owner);
            invoicePaymentsCombo.removeItemAt(invoicePaymentsCombo.getSelectedIndex());
            setInvoicesTextArea();
        });
    }

    private void setSRFromLead() {
        if(leadsCombo.getItemCount() == 0 || leads.size() == 0)
            return;
        SalesRepresentative leadSR = club.getCRM().getLeadSR(leads.get(leadsCombo.getSelectedIndex()));
        if (club.getCRM().getLeadSR(leads.get(leadsCombo.getSelectedIndex())) == null) {
            leadSRsCombo.setSelectedIndex(0);
        } else {
            for (int i = 0; i < salesRepresentatives.size(); i++) {
                if (salesRepresentatives.get(i).getID().equals(leadSR.getID())) {
                    leadSRsCombo.setSelectedIndex(i + 1);
                }
            }
        }
    }

    public void addClient(String n, int a, Object g, String nat) {
        Client c = new Client(n, a, g, nat);
        club.addClient(c, owner);
        clientsCombo.addItem(c.getName());
        traineeCombo.addItem(c.getName());
        attendeesCombo.addItem(c.getName());
        clients.add(c);
        setClientsTextArea();
    }

    public void addTrainer(String n, int a, Object g, String nat) {
        Trainer t = new Trainer(n, a, g, nat);
        club.addTrainer(t, owner);
        trainersCombo.addItem(t.getName());
        trainersCombo2.addItem((t.getName()));
        trainers.add(t);

        usersAtLeastEmployeeAccess.add(t);
        usersEmployeeAccess.add(t);
        employees.add(t);

        ptUserCombo.addItem(n);
        attendeesUserCombo.addItem(n);
        productsUserCombo.addItem(n);
        employeeUserAccessCombo.addItem(n);
        setTrainerTextArea();
    }


    public void addSalesRepresentative(String n, int a, Object g, String nat) {
        SalesRepresentative s = new SalesRepresentative(n, a, g, nat);
        club.addSalesRepresentative(s, owner);
        salesRepresentativesCombo.addItem(n);
        salesRepresentatives.add(s);
        usersAtLeastEmployeeAccess.add(s);
        usersEmployeeAccess.add(s);
        employees.add(s);
        ptUserCombo.addItem(n);
        attendeesUserCombo.addItem(n);
        productsUserCombo.addItem(n);
        employeeUserAccessCombo.addItem(n);
        setSalesRepresentativesTextArea();
    }

    public void addLead(String n, int a, Object g, String nat) {
        Lead l = new Lead(n, a, g, nat);
        club.addLeadToCRM(l, owner);
        leadsCombo.addItem(l.getName());
        leads.add(l);
        setCRMTextArea();
    }

    public void addleadSR(String n, int a, Object g, String nat, SalesRepresentative s) {
        Lead l = new Lead(n, a, g, nat);
        club.addLeadSRToCRM(l, s, owner);
        leadsCombo.addItem(l.getName());
        leads.add(l);
        setCRMTextArea();
    }

    public void addGroup(String n, VDMSet clients, User u) {
        club.addGroup(n, clients, u);
        groupsCombo.addItem(n);
        groups.add((Group) club.getGroups().get(n));
        setGroupsTextArea();
    }

    public void addGymClass(String d, String n, Trainer t, Object da, Number sh, Number eh, Number dat, User u) {
        club.addGymClass(d, n, t, da, sh, eh, dat, u);
        gymClassesCombo.addItem(n + " -> " + d);
        club.getGymClasses().forEach(g -> {
            gymClasses.clear();
            gymClasses.add((GymClass) g);
        });
        setGymClassesTextArea();
    }

    public void addTrainingSession(String d, Client c, Object da, Number sh, Number eh, Number dat, User u) {
        club.addTrainingSession(d, c, da, sh, eh, dat, u);
        trainingSessionsCombo.addItem(d);
        club.getTrainingSessions().forEach(t -> {
            trainingSessions.clear();
            trainingSessions.add((TrainingSession) t);
        });
        setTrainingSessionTextArea();
    }

    public void sendMsgToClient(String m, Client c, User u) {
        club.sendMessageClient(m, c, u);
    }

    public void sendMsgToEmployee(String m, Employee e, User u) {
        club.sendMessageEmployee(m, e, u);
    }

    public void sendMsgAllClients(String m, User u) {
        club.sendMessageAllClients(m, u);
    }

    public void sendMsgAllTrainers(String m, User u) {
        club.sendMessageAllTrainers(m, u);
    }

    public void sendMessageAllSalesRepresentatives(String m, User u) {
        club.sendMessageAllSalesRepresentatives(m, u);
    }

    public void sendMsgGroup(String m, String g, User u) {
        club.sendMessageToGroup(m, g, u);
    }

    public void sendSendOfferToGroup(String m, String g, User u) {
        club.sendOfferToGroup(m, g, u);
    }

    public void addProduct(String n, Number v, Number q) {
        club.addProduct(n, v, q, usersAtLeastEmployeeAccess.get(productsUserCombo.getSelectedIndex()));
        productsCombo.addItem(n);
        club.getProducts().forEach(p -> {
            products.clear();
            products.add((Product) p);
        });
        setProductsTextArea();
    }

    public void addInvoice(VDMSet payments, Number date, Number hour, String type, boolean allActivePayments, Client c, User u) {
        System.out.println(payments);
        Invoice i = new Invoice(payments, date, hour, type, allActivePayments, c);
        if(!allActivePayments)
            club.addInvoice(c, payments, date, hour, type, u);
        else
            club.addInvoiceWithAllActivePayments(c, date, hour, type, u);

        invoices.add(i);
        invoicesCombo.addItem("Client: " + c.getName() + " Type: " + type +
                " Date: " + date);

        setInvoicesTextArea();
    }

    private void setClientsTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object c : club.getClients()) {
            sb.append(c.toString()).append("\n");
        }
        clientsTextArea.setText(sb.toString());
    }

    private void setTrainerTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t : club.getTrainers()) {
            sb.append(t.toString()).append("\n");
        }
        trainersTextArea.setText(sb.toString());
    }

    private void setSalesRepresentativesTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object s : club.getSalesRepresentatives()) {
            sb.append(s.toString()).append("\n");
        }
        salesRepresentativesTextArea.setText(sb.toString());
    }

    private void setGroupsTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object g : club.getGroups().keySet()) {
            sb.append("Group " + g.toString() + ": " + club.getGroups().get(g).toString()).append("\n");
        }
        groupsTextArea.setText(sb.toString());
    }

    private void setPersonalTrainingArea() {
        StringBuilder sb = new StringBuilder();
        for (Object c : club.getClients()) {
            Client client = (Client) c;
            if (client.getTrainer() != null)
                sb.append("Trainee: " + client.getName() + ", trainer: " + client.getTrainer().getName()).append("\n");
        }
        personalTrainingTextArea.setText(sb.toString());
    }

    private void setGymClassesTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object g : club.getGymClasses()) {
            sb.append(g.toString()).append("\n");
        }
        gymClassesTextArea.setText(sb.toString());
    }

    private void setTrainingSessionTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t : club.getTrainingSessions()) {
            sb.append(t.toString()).append("\n");
        }
        trainingSessionTextArea.setText(sb.toString());
    }



    private void setProductsTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t : club.getProducts()) {
            sb.append(t.toString()).append("\n");
        }
        productsTextArea.setText(sb.toString());
    }

    private void setCRMTextArea() {
        StringBuilder sb = new StringBuilder();
        sb.append("CRM{").append("\n");
        club.getCRM().getLeads().forEach((l, sr) -> {
            String srString = " nil";
            if (sr != null)
                    srString = sr.toString();
            sb.append(l.toString() + "|->" + srString).append("\n");
        });
        sb.append("}").append("\n");
        crmTextArea.setText(sb.toString());
    }

    private void setInvoicesTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t : club.getInvoices()) {
            sb.append(t.toString()).append("\n");
        }
        invoicesTextArea.setText(sb.toString());
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

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public ArrayList<SalesRepresentative> getSalesRepresentatives() {
        return salesRepresentatives;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Product> getProducts() {
        return products;
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

    public JButton getAddProductButton() {
        return addProductButton;
    }

    public JButton getAddLeadButton() {
        return addLeadButton;
    }

    public JButton getAddLeadWithSalesButton() {
        return addLeadWithSalesButton;
    }

    public JButton getAddInvoiceButton() {
        return addInvoiceButton;
    }

    public JButton getViewClientButton() {
        return viewClientButton;
    }

    public JButton getViewOwnerButton() {
        return viewOwnerButton;
    }

    public JButton getViewSalesRepresentativeButton() {
        return viewSalesRepresentativeButton;
    }

    public JButton getViewTrainerButton() {
        return viewTrainerButton;
    }

    public JButton getViewGroupButton() {
        return viewGroupButton;
    }

    public JButton getViewGymClassButton() {
        return viewGymClassButton;
    }

    public JButton getViewTrainingSessionButton() {
        return viewTrainingSessionButton;
    }

    public Client getClient() {
        return clients.get(clientsCombo.getSelectedIndex());
    }
}


