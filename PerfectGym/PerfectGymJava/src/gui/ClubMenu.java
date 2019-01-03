package gui;

import PerfectGym.*;

import javax.swing.*;

public class ClubMenu {
    private JPanel pane;
    private JButton backButton;
    private JComboBox clientsCombo;
    private JButton viewClientButton;
    private JButton viewOwnerButton;
    private JButton addClientButton;
    private JButton viewSalesRepresentativeButton;
    private JComboBox salesRepresentativesCombo;
    private JButton addSalesRepresentativeButton;
    private JButton addTrainerButton;
    private JComboBox trainersCombo;
    private JButton viewTrainerButton;
    private JTextArea clientsTextArea;
    private JTextArea trainersTextArea;
    private JTextArea salesRepresentativesTextArea;
    private JTextArea ownerTextArea;

    private Main parent;

    private Club club;

    public ClubMenu(Main parent) {
        this.parent = parent;

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

    public void setClub(Club club){
        this.club = club;
    }
}


