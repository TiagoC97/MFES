package gui;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private MainMenu mainMenu;
    private PerfectGymMenu perfectGymMenu;
    private CreateUserMenu createUserMenu;
    private ClubMenu clubMenu;

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

        contentPane.add(mainMenu.getPane(), "Main Menu");
        contentPane.add(perfectGymMenu.getPane(), "PerfectGYM Menu");
        contentPane.add(createUserMenu.getPane(), "CreateUserMenu Menu");
        contentPane.add(clubMenu.getPane(), "ClubMenu Menu");

        addListeners();

        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, "Main Menu");
    }

    private void addListeners(){
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

        createUserMenu.getCancelButton().addActionListener(e -> showLayout("PerfectGYM Menu"));
        createUserMenu.getConfirmButton().addActionListener(e -> {
            switch (createUserMenu.getUser()){
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

            }


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
        //clubMenu.getBackButton().addActionListener(e -> showLayout("Main Menu"));
//        championshipManager.getBackButton().addActionListener(e -> showLayout("Main Options"));
//        championshipViewer.getBackButton().addActionListener(e -> showLayout("Championship Manager"));
    }

    public void showLayout(String layout){
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
