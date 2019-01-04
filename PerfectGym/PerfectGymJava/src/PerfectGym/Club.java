package PerfectGym;

import java.util.*;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Club {
    private String name;
    private String newsletter;
    private VDMSet clients = SetUtil.set();
    private VDMSet salesRepresentatives = SetUtil.set();
    private VDMSet trainers = SetUtil.set();
    private VDMMap groups = MapUtil.map();
    private VDMSet classes = SetUtil.set();
    private VDMSet trainingSessions = SetUtil.set();
    private VDMSet invoices = SetUtil.set();
    private VDMSet products = SetUtil.set();
    private Number fee;
    private CRM crm;
    private Owner clubOwner;

    public void cg_init_Club_1(final String newName, final Owner owner, final Number newFee) {

        newsletter = null;
        name = newName;
        clubOwner = owner;
        clubOwner.setClub(this);
        crm = new CRM();
        fee = newFee;
        return;
    }

    public Club(final String newName, final Owner owner, final Number newFee) {

        cg_init_Club_1(newName, owner, newFee);
    }

    public void addClient(final Client client, final User user) {

        clients = SetUtil.union(Utils.copy(clients), SetUtil.set(client));
        client.setClub(this);
    }

    public void addTrainer(final Trainer trainer, final User user) {

        trainers = SetUtil.union(Utils.copy(trainers), SetUtil.set(trainer));
        trainer.setClub(this);
    }

    public void addSalesRepresentative(
            final SalesRepresentative salesRepresentative, final User user) {

        salesRepresentatives =
                SetUtil.union(Utils.copy(salesRepresentatives), SetUtil.set(salesRepresentative));
        salesRepresentative.setClub(this);
    }

    public void addGroup(final String newName, final VDMSet newClients, final User user) {

        groups =
                MapUtil.munion(
                        Utils.copy(groups),
                        MapUtil.map(new Maplet(newName, new Group(Utils.copy(newClients)))));
    }

    public void addPersonalTraining(
            final Trainer trainer, final Client client, final Number newFee, final User user) {

        trainer.addTrainee(client);
        client.addTrainer(trainer, newFee);
    }

    public void removeTraineeFromTrainer(final Trainer trainer, final Client client, final User user) {

        trainer.removeTrainee(client);
        client.removeTrainer();
    }

    public void addGymClass(
            final String description,
            final String className,
            final Trainer trainer,
            final Object dayOfWeek,
            final Number startHour,
            final Number endHour,
            final Number date,
            final User user) {

        GymClass gymClass =
                new GymClass(
                        description, className, trainer, ((Object) dayOfWeek), startHour, endHour, date);
        classes = SetUtil.union(Utils.copy(classes), SetUtil.set(gymClass));

    }

    public void addGymClass(
            final GymClass gymClass,
            final User user) {

        classes = SetUtil.union(Utils.copy(classes), SetUtil.set(gymClass));

    }


    public void addAttendeeToGymClass(final GymClass gymClass, final Client client, final User user) {

        gymClass.addAttendee(client);
    }

    public void addTrainingSession(
            final String newDescription,
            final Client client,
            final Object newDayOfWeek,
            final Number newStartHour,
            final Number newEndHour,
            final Number newDate,
            final User user) {

        TrainingSession trainingSession =
                new TrainingSession(
                        newDescription,
                        client,
                        ((Object) newDayOfWeek),
                        newStartHour,
                        newEndHour,
                        newDate);
        trainingSessions = SetUtil.union(Utils.copy(trainingSessions), SetUtil.set(trainingSession));

    }

    public void addTrainingSession(
           final TrainingSession trainingSession,
            final User user) {

        trainingSessions = SetUtil.union(Utils.copy(trainingSessions), SetUtil.set(trainingSession));

    }

    public void setUserAccess(final User user, final Employee targetEmployee, final Object access) {

        targetEmployee.setAccess(access);
    }

    public void addNewsletter(final String message, final User user) {

        newsletter = message;
    }

    public void sendMessageClient(final String msg, final Client receiver, final User user) {

        receiver.receiveMessage(msg, user);
        if (!(Utils.equals(newsletter, null))) {
            receiver.receiveMessage(newsletter, user);
        }
    }

    public void sendMessageEmployee(final String msg, final Employee receiver, final User user) {

        receiver.receiveMessage(msg, user);
    }

    public void sendMessageAllClients(final String msg, final User user) {

        for (Iterator iterator_21 = clients.iterator(); iterator_21.hasNext(); ) {
            Client client = (Client) iterator_21.next();
            sendMessageClient(msg, client, user);
        }
    }

    public void sendMessageAllTrainers(final String msg, final User user) {

        for (Iterator iterator_22 = trainers.iterator(); iterator_22.hasNext(); ) {
            Trainer trainer = (Trainer) iterator_22.next();
            trainer.receiveMessage(msg, user);
        }
    }

    public void sendMessageAllSalesRepresentatives(final String msg, final User user) {

        for (Iterator iterator_23 = salesRepresentatives.iterator(); iterator_23.hasNext(); ) {
            SalesRepresentative salesRepresentative = (SalesRepresentative) iterator_23.next();
            salesRepresentative.receiveMessage(msg, user);
        }
    }

    public void sendMessageToGroup(final String msg, final String groupName, final User user) {

        ((Group) Utils.get(groups, groupName)).sendMessage(user, msg);
    }

    public void sendOfferToGroup(final String offer, final String groupName, final User user) {

        ((Group) Utils.get(groups, groupName)).sendOffer(offer);
    }

    public void addGroupClient(final String groupName, final Client client, final User user) {

        ((Group) Utils.get(groups, groupName)).addClient(client);
    }

    public void removeGroupClient(final String groupName, final Client client, final User user) {

        ((Group) Utils.get(groups, groupName)).removeClient(client);
    }

    public void addInvoice(
            final Client client,
            final VDMSet payments,
            final Number date,
            final Number hour,
            final String type,
            final User user) {

        invoices =
                SetUtil.union(
                        Utils.copy(invoices),
                        SetUtil.set(new Invoice(Utils.copy(payments), date, hour, type, false, client)));
    }

    public void addInvoiceWithAllActivePayments(
            final Client client,
            final Number date,
            final Number hour,
            final String type,
            final User user) {

        invoices =
                SetUtil.union(
                        Utils.copy(invoices),
                        SetUtil.set(
                                new Invoice(client.getPaymentsOfGivenType(type), date, hour, type, true, client)));
    }

    public void addPaymentToInvoice(final Invoice invoice, final VDMSet payments, final User user) {

        invoice.addPayment(Utils.copy(payments));
    }

    public void removePaymentFromInvoice(
            final Invoice invoice, final VDMSet payments, final User user) {

        invoice.removePayment(Utils.copy(payments));
    }

    public void addLeadToCRM(final Lead lead, final User user) {

        crm.addLead(lead);
    }

    public void addLeadSRToCRM(final Lead lead, final SalesRepresentative sr, final User user) {

        crm.addLeadWithSR(lead, sr);
    }

    public void setCRMLeadSR(final Lead lead, final SalesRepresentative sr, final User user) {

        crm.setLeadSR(lead, sr);
    }

    public void removeLeadSR(final Lead lead, final SalesRepresentative sr, final User user) {

        sr.removeLead(lead);
    }

    public void removeCRMLead(final Lead lead, final User user) {

        crm.removeLead(lead);
    }

    public void transformLeadIntoClient(final Lead lead, final User user) {

        Client client =
                new Client(
                        lead.getName(), lead.getAge(), ((Object) lead.getGender()), lead.getNationality());
        crm.removeLead(lead);
        addClient(client, user);
    }

    public void getReportOnClubStatistics(final User user) {

        Number numClients = clients.size();
        Number numTrainers = trainers.size();
        Number numSalesRepresentatives = salesRepresentatives.size();
        Number numClasses = classes.size();
        Number numTrainingSessiosn = trainingSessions.size();
        IO.println("********* CLUB STATISTICS *********");
        IO.println("Number of clients: " + SeqUtil.toStr(SeqUtil.seq(numClients)));
        IO.println("Number of trainers: " + SeqUtil.toStr(SeqUtil.seq(numTrainers)));
        IO.println(
                "Number of sales representatives: " + SeqUtil.toStr(SeqUtil.seq(numSalesRepresentatives)));
        IO.println("Number of gym classes: " + SeqUtil.toStr(SeqUtil.seq(numClasses)));
        IO.println("Number of training sessions: " + SeqUtil.toStr(SeqUtil.seq(numTrainingSessiosn)));
        IO.println("");
        IO.println("************************************");
    }

    public void getClientActivity(final Client client, final User user) {

        client.getActivity();
    }

    public void getEmployeeActivity(
            final Employee employee, final Boolean showAllTasks, final User user) {

        employee.getActivity(showAllTasks);
    }

    public void addTaskToEmployee(final Employee employee, final User user, final Task task) {

        employee.addTask(task);
    }

    public void addProduct(final String prod_name, final Number prod_value, final Number qtt) {

        Product prod = new Product(prod_name, prod_value, qtt);
        products = SetUtil.union(Utils.copy(products), SetUtil.set(prod));
    }

    public void addStockOfProduct(final Product prod, final Number qtt) {

        prod.addQuantity(qtt);
    }

    public void removeProduct(final Product prod) {

        prod.removeQuantity(prod.getQuantity());
        products = SetUtil.diff(Utils.copy(products), SetUtil.set(prod));
    }

    public String getName() {

        return name;
    }

    public String getNewsletter() {

        return newsletter;
    }

    public Owner getOwner() {

        return clubOwner;
    }

    public VDMSet getClients() {

        return Utils.copy(clients);
    }

    public VDMSet getTrainers() {

        return Utils.copy(trainers);
    }

    public VDMSet getSalesRepresentatives() {

        return Utils.copy(salesRepresentatives);
    }

    public VDMSet getEmployees() {

        return SetUtil.union(Utils.copy(trainers), Utils.copy(salesRepresentatives));
    }

    public VDMSet getUsers() {

        return SetUtil.union(
                SetUtil.union(
                        SetUtil.union(SetUtil.set(clubOwner), Utils.copy(clients)), Utils.copy(trainers)),
                Utils.copy(salesRepresentatives));
    }

    public VDMSet getClientByName(final String clientName) {

        VDMSet retClients = SetUtil.set();
        for (Iterator iterator_24 = clients.iterator(); iterator_24.hasNext(); ) {
            Client c = (Client) iterator_24.next();
            if (Utils.equals(c.getName(), clientName)) {
                retClients = SetUtil.union(Utils.copy(retClients), SetUtil.set(c));
            }
        }
        return Utils.copy(retClients);
    }

    public VDMSet getEmployeeByName(final String employeeName) {

        VDMSet retEmployees = SetUtil.set();
        for (Iterator iterator_25 = getEmployees().iterator(); iterator_25.hasNext(); ) {
            Employee e = (Employee) iterator_25.next();
            if (Utils.equals(e.getName(), employeeName)) {
                retEmployees = SetUtil.union(Utils.copy(retEmployees), SetUtil.set(e));
            }
        }
        return Utils.copy(retEmployees);
    }

    public VDMSet getUserByName(final String userName) {

        VDMSet retUsers = SetUtil.set();
        for (Iterator iterator_26 = getUsers().iterator(); iterator_26.hasNext(); ) {
            User u = (User) iterator_26.next();
            if (Utils.equals(u.getName(), userName)) {
                retUsers = SetUtil.union(Utils.copy(retUsers), SetUtil.set(u));
            }
        }
        return Utils.copy(retUsers);
    }

    public VDMSet getGymClasses() {

        return Utils.copy(classes);
    }

    public VDMSet getTrainingSessions() {

        return Utils.copy(trainingSessions);
    }

    public VDMMap getGroups() {

        return Utils.copy(groups);
    }

    public Group getGroupByName(final String groupName) {

        return ((Group) Utils.get(groups, groupName));
    }

    public Number getFee() {

        return fee;
    }

    public VDMSet getInvoices() {

        return Utils.copy(invoices);
    }

    public CRM getCRM() {

        return crm;
    }

    public VDMMap getEmployeeCalendar(final Employee employee) {

        return employee.getCalendar().getTasks();
    }

    public VDMSeq getEmployeeTasksForGivenDate(final Employee employee, final Number date) {

        return employee.getCalendar().getTasksForGivenDate(date);
    }

    public Club() {
    }

    public static Boolean isAtLeastEmployee(final User user) {

        throw new UnsupportedOperationException();
    }

    public static Boolean isOwner(final User user) {

        throw new UnsupportedOperationException();
    }

    public String toString() {

        return "Club{"
                + "name := "
                + Utils.toString(name)
                + ", newsletter := "
                + Utils.toString(newsletter)
                + ", clients := "
                + Utils.toString(clients)
                + ", salesRepresentatives := "
                + Utils.toString(salesRepresentatives)
                + ", trainers := "
                + Utils.toString(trainers)
                + ", groups := "
                + Utils.toString(groups)
                + ", classes := "
                + Utils.toString(classes)
                + ", trainingSessions := "
                + Utils.toString(trainingSessions)
                + ", invoices := "
                + Utils.toString(invoices)
                + ", products := "
                + Utils.toString(products)
                + ", fee := "
                + Utils.toString(fee)
                + ", crm := "
                + Utils.toString(crm)
                + ", clubOwner := "
                + Utils.toString(clubOwner)
                + "}";
    }
}
