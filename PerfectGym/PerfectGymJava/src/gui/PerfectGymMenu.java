package gui;

import PerfectGym.*;
import PerfectGym.quotes.*;
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
    private ArrayList<GymClass> gymClasses = new ArrayList<>();
    private ArrayList<TrainingSession> trainingSessions = new ArrayList<>();


    private ArrayList<Product> products = new ArrayList<>();

    private ArrayList<ProductPayment> productPayments = new ArrayList<>();
    private ArrayList<GymFeePayment> gymFeePayments = new ArrayList<>();
    private ArrayList<PersonalTrainingPayment> personalTrainingPayments= new ArrayList<>();

    private ArrayList<Invoice> invoices = new ArrayList<>();

    public PerfectGymMenu(Main parent) {
        this.parent = parent;
        initOwners();
        initClubs();
        initClients();
        initTrainers();
        initSalesRepresentatives();
        initGroups();
        initPersonalTrainings();
        initGymClasses();
        initTrainingSessions();
        initMessages();
        initProducts();
        initLeads();
        initProductPayments();
        initpersonalTrainingPayments();
        initGymFeePayments();
        initInvoices();

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

    private void initPersonalTrainings(){
        clubs.get(0).addPersonalTraining(trainers.get(0), clients.get(0), 20, clubs.get(0).getOwner());
        clubs.get(0).addPersonalTraining(trainers.get(1), clients.get(1), 20, clubs.get(0).getOwner());
        clubs.get(0).addPersonalTraining(trainers.get(1), clients.get(2), 20, clubs.get(0).getOwner());
    }

    private void initGymClasses(){
        gymClasses.add(new GymClass("Aula de baixa intensidade", "Pilates", trainers.get(0), TuesdayQuote.getInstance(),
                cg_Utils.CreateHour(9, 0), cg_Utils.CreateHour(10, 0), cg_Utils.CreateDate(2019, 1, 11)));
        gymClasses.add(new GymClass("Aula de alta intensidade", "Zumba", trainers.get(0), MondayQuote.getInstance(),
                cg_Utils.CreateHour(16, 0), cg_Utils.CreateHour(17, 0), cg_Utils.CreateDate(2019, 1, 14)));
        gymClasses.add(new GymClass("Aula de baixa intensidade", "Pilates", trainers.get(1), TuesdayQuote.getInstance(),
                cg_Utils.CreateHour(9, 0), cg_Utils.CreateHour(10, 0), cg_Utils.CreateDate(2019, 1, 15)));

        initAttendesGymClass();

        gymClasses.forEach(g -> clubs.get(0).addGymClass(g, clubs.get(0).getOwner()));
    }

    private void initAttendesGymClass(){
        gymClasses.get(0).addAttendee(clients.get(0));
        gymClasses.get(0).addAttendee(clients.get(1));
        gymClasses.get(0).addAttendee(clients.get(2));

        gymClasses.get(1).addAttendee(clients.get(2));
        gymClasses.get(1).addAttendee(clients.get(3));
        gymClasses.get(1).addAttendee(clients.get(4));
        gymClasses.get(1).addAttendee(clients.get(5));
        gymClasses.get(1).addAttendee(clients.get(6));
    }

    private void initTrainingSessions(){
        trainingSessions.add(new TrainingSession("Aula iniciante", clients.get(0), MondayQuote.getInstance(),
                cg_Utils.CreateHour(14, 0), cg_Utils.CreateHour(15, 0), cg_Utils.CreateDate(2019, 2, 4)));
        trainingSessions.add(new TrainingSession("Aula de treino instenso de abdominais", clients.get(1), TuesdayQuote.getInstance(),
                cg_Utils.CreateHour(17, 0), cg_Utils.CreateHour(18, 0), cg_Utils.CreateDate(2019, 2, 11)));
        trainingSessions.add(new TrainingSession("Aula de PUMP", clients.get(2), WednesdayQuote.getInstance(),
                cg_Utils.CreateHour(9, 0), cg_Utils.CreateHour(10, 0), cg_Utils.CreateDate(2019, 2, 23)));

        trainingSessions.forEach(t -> clubs.get(0).addTrainingSession(t, clubs.get(0).getOwner()));
    }

    private void initMessages(){
        clubs.get(0).addNewsletter("Sales", owners.get(0));
        clubs.get(0).sendMessageClient("Bom Ano", clients.get(0), owners.get(0));
        clubs.get(0).sendMessageEmployee("Bom Ano", trainers.get(0), owners.get(0));
        clubs.get(0).sendMessageAllClients("Feliz Natal", owners.get(0));
        clubs.get(0).sendMessageAllTrainers("Feliz Natal", owners.get(0));
        clubs.get(0).sendMessageAllSalesRepresentatives("Feliz Natal", owners.get(0));
        clubs.get(0).sendMessageToGroup("Feliz Natal", "PuxadoresDeFerro", owners.get(0));
        clubs.get(0).sendOfferToGroup("Prota com 10% de desconto esta semana!! :O", "PuxadoresDeFerro", owners.get(0));
    }

    private void initProducts(){
        products.add(new Product("Garrafa", 4.70, 40));
        products.add(new Product("Camisola", 8.20, 40));
        products.add(new Product("Calcoes", 10, 40));
        products.add(new Product("Sapatilhas", 23.12, 50));

        products.forEach(t -> clubs.get(0).addProduct(t, clubs.get(0).getOwner()));

    }

    private void initLeads(){
        clubs.get(0).addLeadToCRM(new Lead("Iola", 23, FemaleQuote.getInstance(), "portuguese"), owners.get(0));
        clubs.get(0).addLeadToCRM(new Lead("Xuala", 26, FemaleQuote.getInstance(), "portuguese"), owners.get(0));
        clubs.get(0).addLeadToCRM(new Lead("Lipton", 23, MaleQuote.getInstance(), "portuguese"), owners.get(0));

        clubs.get(0).addLeadSRToCRM(new Lead("Petra", 23, FemaleQuote.getInstance(), "portuguese"), salesRepresentatives.get(0), owners.get(0));
        clubs.get(0).addLeadSRToCRM(new Lead("Lion", 45, MaleQuote.getInstance(), "portuguese"), salesRepresentatives.get(1), owners.get(0));

    }

    private void initProductPayments(){
        productPayments.add(new ProductPayment(clients.get(0), products.get(0), 5, cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));
        productPayments.add(new ProductPayment(clients.get(0), products.get(2), 7, cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));
        productPayments.add(new ProductPayment(clients.get(1), products.get(0), 5, cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));
        productPayments.add(new ProductPayment(clients.get(2), products.get(3), 2, cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));

    }

    private void initGymFeePayments(){
        gymFeePayments.add(new GymFeePayment(clients.get(0), clubs.get(0).getFee(), cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));
        gymFeePayments.add(new GymFeePayment(clients.get(0), clubs.get(0).getFee(), cg_Utils.CreateDate(2019, 3, 23), cg_Utils.CreateHour(14, 23)));
        gymFeePayments.add(new GymFeePayment(clients.get(1), clubs.get(0).getFee(), cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));
        gymFeePayments.add(new GymFeePayment(clients.get(1), clubs.get(0).getFee(), cg_Utils.CreateDate(2019, 3, 23), cg_Utils.CreateHour(14, 23)));
        gymFeePayments.add(new GymFeePayment(clients.get(2), clubs.get(0).getFee(), cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));
        gymFeePayments.add(new GymFeePayment(clients.get(2), clubs.get(0).getFee(), cg_Utils.CreateDate(2019, 3, 23), cg_Utils.CreateHour(14, 23)));
        gymFeePayments.add(new GymFeePayment(clients.get(2), clubs.get(0).getFee(), cg_Utils.CreateDate(2019, 4, 23), cg_Utils.CreateHour(14, 23)));

    }

    private void initpersonalTrainingPayments(){
        personalTrainingPayments.add(new PersonalTrainingPayment(clients.get(0), clients.get(0).getPersonalTrainingFee(), cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));
        personalTrainingPayments.add(new PersonalTrainingPayment(clients.get(0), clients.get(0).getPersonalTrainingFee(), cg_Utils.CreateDate(2019, 3, 23), cg_Utils.CreateHour(14, 23)));
        personalTrainingPayments.add(new PersonalTrainingPayment(clients.get(1), clients.get(1).getPersonalTrainingFee(), cg_Utils.CreateDate(2019, 3, 23), cg_Utils.CreateHour(14, 23)));
        personalTrainingPayments.add(new PersonalTrainingPayment(clients.get(2), clients.get(2).getPersonalTrainingFee(), cg_Utils.CreateDate(2019, 2, 23), cg_Utils.CreateHour(14, 23)));
    }

    private void initInvoices(){
        clubs.get(0).addInvoice(clients.get(0), SetUtil.set(productPayments.get(0), productPayments.get(1)), cg_Utils.CreateDate(2019, 5, 23), cg_Utils.CreateHour(14, 23), "product", owners.get(0));
        clubs.get(0).addInvoice(clients.get(0), SetUtil.set(gymFeePayments.get(0)), cg_Utils.CreateDate(2019, 5, 27), cg_Utils.CreateHour(14, 23), "gymFee", owners.get(0));
        clubs.get(0).addInvoice(clients.get(1), SetUtil.set(gymFeePayments.get(2)), cg_Utils.CreateDate(2019, 5, 23), cg_Utils.CreateHour(14, 23), "gymFee", owners.get(0));
        clubs.get(0).addInvoice(clients.get(2), SetUtil.set(gymFeePayments.get(4)), cg_Utils.CreateDate(2019, 5, 23), cg_Utils.CreateHour(14, 23), "gymFee", owners.get(0));
        clubs.get(0).addInvoice(clients.get(0), SetUtil.set(personalTrainingPayments.get(0)), cg_Utils.CreateDate(2019, 5, 23), cg_Utils.CreateHour(14, 23), "personalTraining", owners.get(0));

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
