package gui;

import PerfectGym.User;

import javax.swing.*;
import java.util.ArrayList;

public class SendMessageDestMarkedMenu {
    private JTextField msgTextField;
    private JComboBox userCombo;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;

    private String destinatary;

    private Main parent;
    private ArrayList<User> usersOwnerAccess;


    public SendMessageDestMarkedMenu(Main parent) {
        this.parent = parent;
    }

    public void setVisible() {
        this.pane.setVisible(true);
    }

    public JPanel getPane() {
        return pane;
    }


    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }


    public void setUsersOwnerAccess(ArrayList<User> usersOwnerAccess) {
        this.usersOwnerAccess = usersOwnerAccess;
        userCombo.removeAllItems();
        usersOwnerAccess.forEach(u -> userCombo.addItem(u.getName()));
    }


    public String getMessage() {
        return msgTextField.getText();
    }

    public User getUser() {
        return usersOwnerAccess.get(userCombo.getSelectedIndex());
    }

    public String getDestinatary() {
        return destinatary;
    }

    public void setDestinatary(String destinatary){
        this.destinatary = destinatary;
    }
}
