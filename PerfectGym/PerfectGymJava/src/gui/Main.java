package gui;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private MainMenu mainMenu;
    private PerfectGymMenu perfectGymMenu;
    private CreateUserMenu createUserMenu;
    private ClubMenu clubMenu;
    private CreateGroupMenu createGroupMenu;
    private CreateGymClassMenu createGymClassMenu;
    private CreateTrainingSessionMenu createTrainingSessionMenu;
    private SendMessageDestMarkedMenu sendMessageDestMarkedMenu;
    private SendMessageToUserMenu sendMessageToUserMenu;
    private SendMessageOfferGroupMenu sendMessageOfferGroupMenu;
    private CreateProductMenu createProductMenu;
    private CreateLeadWithSRMenu createLeadWithSRMenu;
    private CreateInvoiceMenu createInvoiceMenu;

    private ViewClientMenu viewClientMenu;
    private ViewOwnerMenu viewOwnerMenu;
    private ViewTrainerMenu viewTrainerMenu;
    private ViewSalesRepresentativeMenu viewSalesRepresentativeMenu;


    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main frame = new Main();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setTitle("Perfect Gym");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel(new CardLayout());
        setContentPane(contentPane);
        initialize();
        setVisible(true);

//        this.setSize(1300, 1200);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        mainMenu = new gui.MainMenu();
        mainMenu.setVisible();

        perfectGymMenu = new PerfectGymMenu(this);
        perfectGymMenu.setVisible();

        createUserMenu = new CreateUserMenu(this);
        createUserMenu.setVisible();

        clubMenu = new ClubMenu(this);
        clubMenu.setVisible();

        createGroupMenu = new CreateGroupMenu(this);
        createGroupMenu.setVisible();

        createGymClassMenu = new CreateGymClassMenu(this);
        createGymClassMenu.setVisible();

        createTrainingSessionMenu = new CreateTrainingSessionMenu(this);
        createTrainingSessionMenu.setVisible();

        sendMessageDestMarkedMenu = new SendMessageDestMarkedMenu(this);
        sendMessageDestMarkedMenu.setVisible();

        sendMessageToUserMenu = new SendMessageToUserMenu(this);
        sendMessageToUserMenu.setVisible();

        sendMessageOfferGroupMenu = new SendMessageOfferGroupMenu(this);
        sendMessageOfferGroupMenu.setVisible();

        createProductMenu = new CreateProductMenu(this);
        createProductMenu.setVisible();

        createLeadWithSRMenu = new CreateLeadWithSRMenu(this);
        createLeadWithSRMenu.setVisible();

        createInvoiceMenu = new CreateInvoiceMenu(this);
        createInvoiceMenu.setVisible();

        viewClientMenu = new ViewClientMenu(this);
        viewClientMenu.setVisible();

        viewOwnerMenu = new ViewOwnerMenu(this);
        viewOwnerMenu.setVisible();

        viewTrainerMenu = new ViewTrainerMenu(this);
        viewTrainerMenu.setVisible();

        viewSalesRepresentativeMenu = new ViewSalesRepresentativeMenu(this);
        viewSalesRepresentativeMenu.setVisible();

        contentPane.add(mainMenu.getPane(), "Main Menu");
        contentPane.add(perfectGymMenu.getPane(), "PerfectGYM Menu");
        contentPane.add(createUserMenu.getPane(), "CreateUserMenu Menu");
        contentPane.add(createGroupMenu.getPane(), "CreateGroupMenu Menu");
        contentPane.add(createGymClassMenu.getPane(), "CreateGymClassMenu Menu");
        contentPane.add(createTrainingSessionMenu.getPane(), "CreateTrainingSessionMenu Menu");
        contentPane.add(sendMessageDestMarkedMenu.getPane(), "SendMessageDestMarkedMenu Menu");
        contentPane.add(sendMessageToUserMenu.getPane(), "SendMessageToUserMenu Menu");
        contentPane.add(sendMessageOfferGroupMenu.getPane(), "SendMessageOfferGroupMenu Menu");
        contentPane.add(createProductMenu.getPane(), "CreateProductMenu Menu");
        contentPane.add(clubMenu.getPane(), "ClubMenu Menu");
        contentPane.add(createLeadWithSRMenu.getPane(), "CreateLeadWithSRMenu Menu");
        contentPane.add(createInvoiceMenu.getPane(), "CreateInvoiceMenu Menu");

        contentPane.add(viewClientMenu.getPane(), "ViewClientMenu Menu");
        contentPane.add(viewOwnerMenu.getPane(), "ViewOwnerMenu Menu");
        contentPane.add(viewTrainerMenu.getPane(), "ViewTrainerMenu Menu");
        contentPane.add(viewSalesRepresentativeMenu.getPane(), "ViewSalesRepresentativeMenu Menu");

        addListeners();

        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, "Main Menu");
    }

    private void addListeners() {
        mainMenu.getPerfectGymButton().addActionListener(e -> showLayout("PerfectGYM Menu"));

        perfectGymMenu.getBackButton().addActionListener(e -> showLayout("Main Menu"));
        perfectGymMenu.getCreateOwnerButton().addActionListener(e -> {
            createUserMenu.setUser("Owner");
            showLayout("CreateUserMenu Menu");
        });
        perfectGymMenu.getViewClubButton().addActionListener(e ->
        {
            clubMenu.setClub(perfectGymMenu.getSelectedClub());
            showLayout("ClubMenu Menu");
        });

        createUserMenu.getCancelButton().addActionListener(e -> {
            if (createUserMenu.getUser().equals("Owner"))
                showLayout("PerfectGYM Menu");
            else
                showLayout("ClubMenu Menu");

        });
        createUserMenu.getConfirmButton().addActionListener(e -> {
            switch (createUserMenu.getUser()) {
                case "Owner":
                    perfectGymMenu.addOwner(createUserMenu.getName(), createUserMenu.getAge(), createUserMenu.getGender(), createUserMenu.getNationality());
                    showLayout("PerfectGYM Menu");
                    break;
                case "Client":
                    clubMenu.addClient(createUserMenu.getName(), createUserMenu.getAge(), createUserMenu.getGender(), createUserMenu.getNationality());
                    showLayout("ClubMenu Menu");
                    break;
                case "Trainer":
                    clubMenu.addTrainer(createUserMenu.getName(), createUserMenu.getAge(), createUserMenu.getGender(), createUserMenu.getNationality());
                    showLayout("ClubMenu Menu");
                    break;
                case "SalesRepresentative":
                    clubMenu.addSalesRepresentative(createUserMenu.getName(), createUserMenu.getAge(), createUserMenu.getGender(), createUserMenu.getNationality());
                    showLayout("ClubMenu Menu");
                    break;
                case "Lead":
                    clubMenu.addLead(createUserMenu.getName(), createUserMenu.getAge(), createUserMenu.getGender(), createUserMenu.getNationality());
                    showLayout("ClubMenu Menu");
                    break;
            }

        });

        createGroupMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        createGroupMenu.getConfirmButton().addActionListener(e -> {
            clubMenu.addGroup(createGroupMenu.getGroupName(), createGroupMenu.getGroupMembers(), createGroupMenu.getUserResponsible());
            showLayout("ClubMenu Menu");
        });

        clubMenu.getBackButton().addActionListener(e -> showLayout("PerfectGYM Menu"));
        clubMenu.getAddClientButton().addActionListener(e -> {
            createUserMenu.setUser("Client");
            showLayout("CreateUserMenu Menu");
        });
        clubMenu.getAddSalesRepresentativeButton().addActionListener(e -> {
            createUserMenu.setUser("SalesRepresentative");
            showLayout("CreateUserMenu Menu");
        });
        clubMenu.getAddTrainerButton().addActionListener(e -> {
            createUserMenu.setUser("Trainer");
            showLayout("CreateUserMenu Menu");
        });
        clubMenu.getBackButton().addActionListener(e -> showLayout("PerfectGYM Menu"));
        clubMenu.getAddLeadButton().addActionListener(e -> {
            createUserMenu.setUser("Lead");
            showLayout("CreateUserMenu Menu");
        });
        clubMenu.getAddGroupButton().addActionListener(e -> {
            createGroupMenu.start();
            createGroupMenu.setClients(clubMenu.getClients());
            createGroupMenu.setUsersAtLeastEmployees(clubMenu.getUsersAtLeastEmployeeAccess());
            showLayout("CreateGroupMenu Menu");
        });
        clubMenu.getAddGymClassButton().addActionListener(e -> {
            createGymClassMenu.setTrainers(clubMenu.getTrainers());
            createGymClassMenu.setUsersAtLeastEmployees(clubMenu.getUsersAtLeastEmployeeAccess());
            showLayout("CreateGymClassMenu Menu");
        });

        clubMenu.getAddTrainingSessionButton().addActionListener(e -> {
            createTrainingSessionMenu.setClients(clubMenu.getClients());
            createTrainingSessionMenu.setUsersAtLeastEmployees(clubMenu.getUsersAtLeastEmployeeAccess());
            showLayout("CreateTrainingSessionMenu Menu");
        });

        clubMenu.getSendMsgToClientButton().addActionListener(e -> {
            sendMessageToUserMenu.setDestinatary("Client");
            sendMessageToUserMenu.setClients(clubMenu.getClients());
            sendMessageToUserMenu.setUsersOwnerAccess(clubMenu.getUsersOwnerAccess());
            showLayout("SendMessageToUserMenu Menu");
        });

        clubMenu.getSendMsgToEmployeeButton().addActionListener(e -> {
            sendMessageToUserMenu.setDestinatary("Employee");
            sendMessageToUserMenu.setEmployees(clubMenu.getEmployees());
            sendMessageToUserMenu.setUsersOwnerAccess(clubMenu.getUsersOwnerAccess());
            showLayout("SendMessageToUserMenu Menu");
        });

        clubMenu.getSendMsgAllClientsButton().addActionListener(e -> {
            sendMessageDestMarkedMenu.setDestinatary("AllClients");
            sendMessageDestMarkedMenu.setUsersOwnerAccess(clubMenu.getUsersOwnerAccess());
            showLayout("SendMessageDestMarkedMenu Menu");
        });

        clubMenu.getSendMsgAllTrainersButton().addActionListener(e -> {
            sendMessageDestMarkedMenu.setDestinatary("AllTrainers");
            sendMessageDestMarkedMenu.setUsersOwnerAccess(clubMenu.getUsersOwnerAccess());
            showLayout("SendMessageDestMarkedMenu Menu");
        });

        clubMenu.getSendMsgAllSRsButton().addActionListener(e -> {
            sendMessageDestMarkedMenu.setDestinatary("AllSRs");
            sendMessageDestMarkedMenu.setUsersOwnerAccess(clubMenu.getUsersOwnerAccess());
            showLayout("SendMessageDestMarkedMenu Menu");
        });

        clubMenu.getSendMsgGroupButton().addActionListener(e -> {
            sendMessageOfferGroupMenu.setType("Group");
            sendMessageOfferGroupMenu.setGroups(clubMenu.getClub().getGroups());
            sendMessageOfferGroupMenu.setUsersOwnerAccess(clubMenu.getUsersOwnerAccess());
            showLayout("SendMessageOfferGroupMenu Menu");
        });

        clubMenu.getSendOfferToGroupButton().addActionListener(e -> {
            sendMessageOfferGroupMenu.setType("OfferGroup");
            sendMessageOfferGroupMenu.setGroups(clubMenu.getClub().getGroups());
            sendMessageOfferGroupMenu.setUsersOwnerAccess(clubMenu.getUsersAtLeastEmployeeAccess());
            showLayout("SendMessageOfferGroupMenu Menu");
        });

        sendMessageToUserMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        sendMessageToUserMenu.getConfirmButton().addActionListener(e -> {
            switch (sendMessageToUserMenu.getDestinatary()) {
                case "Client":
                    clubMenu.sendMsgToClient(sendMessageToUserMenu.getMessage(), sendMessageToUserMenu.getClient(),
                            sendMessageToUserMenu.getUser());
                    showLayout("ClubMenu Menu");
                    break;
                case "Employee":
                    clubMenu.sendMsgToEmployee(sendMessageToUserMenu.getMessage(), sendMessageToUserMenu.getEmployee(),
                            sendMessageToUserMenu.getUser());
                    showLayout("ClubMenu Menu");
                    break;
            }

        });

        sendMessageDestMarkedMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        sendMessageDestMarkedMenu.getConfirmButton().addActionListener(e -> {
            switch (sendMessageDestMarkedMenu.getDestinatary()) {
                case "AllClients":
                    clubMenu.sendMsgAllClients(sendMessageDestMarkedMenu.getMessage(),
                            sendMessageDestMarkedMenu.getUser());
                    showLayout("ClubMenu Menu");
                    break;
                case "AllTrainers":
                    clubMenu.sendMsgAllTrainers(sendMessageDestMarkedMenu.getMessage(),
                            sendMessageDestMarkedMenu.getUser());
                    showLayout("ClubMenu Menu");
                    break;
                case "AllSRs":
                    clubMenu.sendMessageAllSalesRepresentatives(sendMessageDestMarkedMenu.getMessage(),
                            sendMessageDestMarkedMenu.getUser());
                    showLayout("ClubMenu Menu");
                    break;
            }

        });

        sendMessageOfferGroupMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        sendMessageOfferGroupMenu.getConfirmButton().addActionListener(e -> {
            switch (sendMessageOfferGroupMenu.getType()) {
                case "Group":
                    clubMenu.sendMsgGroup(sendMessageOfferGroupMenu.getMessage(), sendMessageOfferGroupMenu.getGroupName(),
                            sendMessageOfferGroupMenu.getUser());
                    showLayout("ClubMenu Menu");
                    break;
                case "OfferGroup":
                    clubMenu.sendSendOfferToGroup(sendMessageOfferGroupMenu.getMessage(), sendMessageOfferGroupMenu.getGroupName(),
                            sendMessageOfferGroupMenu.getUser());
                    showLayout("ClubMenu Menu");
                    break;

            }

        });

        createGymClassMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        createGymClassMenu.getConfirmButton().addActionListener(e -> {
            clubMenu.addGymClass(createGymClassMenu.getDescription(),
                    createGymClassMenu.getName(),
                    createGymClassMenu.getTrainer(),
                    createGymClassMenu.getDayOfWeek(),
                    createGymClassMenu.getStartHour(),
                    createGymClassMenu.getEndHour(),
                    createGymClassMenu.getDate(),
                    createGymClassMenu.getUserResponsible());
            showLayout("ClubMenu Menu");
        });

        createTrainingSessionMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        createTrainingSessionMenu.getConfirmButton().addActionListener(e -> {
            clubMenu.addTrainingSession(createTrainingSessionMenu.getDescription(),
                    createTrainingSessionMenu.getClient(),
                    createTrainingSessionMenu.getDayOfWeek(),
                    createTrainingSessionMenu.getStartHour(),
                    createTrainingSessionMenu.getEndHour(),
                    createTrainingSessionMenu.getDate(),
                    createTrainingSessionMenu.getUserResponsible());
            showLayout("ClubMenu Menu");
        });


        clubMenu.getAddProductButton().addActionListener(e -> {
            showLayout("CreateProductMenu Menu");
        });

        clubMenu.getAddLeadWithSalesButton().addActionListener(e -> {
            createLeadWithSRMenu.setSalesRepresentatives(clubMenu.getSalesRepresentatives());
            showLayout("CreateLeadWithSRMenu Menu");
        });

        createProductMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        createProductMenu.getConfirmButton().addActionListener(e -> {
            clubMenu.addProduct(createProductMenu.getName(), createProductMenu.getValue(), createProductMenu.getQuantity());
            showLayout("ClubMenu Menu");
        });

        createLeadWithSRMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        createLeadWithSRMenu.getConfirmButton().addActionListener(e -> {
            clubMenu.addleadSR(createLeadWithSRMenu.getName(), createLeadWithSRMenu.getAge(),
                    createLeadWithSRMenu.getAge(), createLeadWithSRMenu.getNationality(),
                    createLeadWithSRMenu.getSR());
            showLayout("ClubMenu Menu");
        });

        clubMenu.getAddInvoiceButton().addActionListener(e -> {
            createInvoiceMenu.start();
            createInvoiceMenu.setClients(clubMenu.getClients());
            createInvoiceMenu.setUsersAtLeastEmployees(clubMenu.getUsersAtLeastEmployeeAccess());
            showLayout("CreateInvoiceMenu Menu");
        });

        createInvoiceMenu.getCancelButton().addActionListener(e -> showLayout("ClubMenu Menu"));
        createInvoiceMenu.getConfirmButton().addActionListener(e -> {
            clubMenu.addInvoice(createInvoiceMenu.getPayments(),
                    createInvoiceMenu.getDate(),
                    createInvoiceMenu.getHour(),
                    createInvoiceMenu.getType(),
                    createInvoiceMenu.getAllActivePayments(),
                    createInvoiceMenu.getClientNow(),
                    createInvoiceMenu.getUser());
            showLayout("ClubMenu Menu");
        });

        clubMenu.getViewClientButton().addActionListener(e -> {
            viewClientMenu.setClient(clubMenu.getClient());
            showLayout("ViewClientMenu Menu");
        });

        viewClientMenu.getBackButton().addActionListener(e -> showLayout("ClubMenu Menu"));

        clubMenu.getViewOwnerButton().addActionListener(e -> {
            viewOwnerMenu.setOwner(clubMenu.getOwner());
            showLayout("ViewOwnerMenu Menu");
        });

        viewOwnerMenu.getBackButton().addActionListener(e -> showLayout("ClubMenu Menu"));

        clubMenu.getViewTrainerButton().addActionListener(e -> {
            viewTrainerMenu.setTrainer(clubMenu.getTrainer());
            showLayout("ViewTrainerMenu Menu");
        });

        viewTrainerMenu.getBackButton().addActionListener(e -> showLayout("ClubMenu Menu"));

        clubMenu.getViewSalesRepresentativeButton().addActionListener(e -> {
            viewSalesRepresentativeMenu.setSalesRepresentative(clubMenu.getSalesRepresentative());
            showLayout("ViewSalesRepresentativeMenu Menu");
        });

        viewSalesRepresentativeMenu.getBackButton().addActionListener(e -> showLayout("ClubMenu Menu"));
    }

    public void showLayout(String layout) {
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, layout);
    }

//    public gui.ChampionshipViewer getChampionshipViewer() {
//        return championshipViewer;
//    }

//    public ArrayList<Club> getClubs() {
//        return this.clusMenu.getTeams();
//    }
}
