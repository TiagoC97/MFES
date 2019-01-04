package gui;

import PerfectGym.User;

import javax.swing.*;
import java.util.ArrayList;

public class SendMessageOfferGroupMenu {
    private JTextField msgTextField;
    private JTextField groupNameTextField;
    private JComboBox userCombo;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;


    private String type;

    private Main parent;
    private ArrayList<User> usersOwnerAccess;


    public SendMessageOfferGroupMenu(Main parent) {
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

    public String getGroupName() {
        return groupNameTextField.getText();
    }

    public User getUser() {
        return usersOwnerAccess.get(userCombo.getSelectedIndex());
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
