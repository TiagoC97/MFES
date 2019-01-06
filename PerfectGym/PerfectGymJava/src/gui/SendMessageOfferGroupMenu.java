package gui;

import PerfectGym.Group;
import PerfectGym.User;
import org.overture.codegen.runtime.VDMMap;
import org.overture.codegen.runtime.VDMSet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SendMessageOfferGroupMenu {
    private JTextField msgTextField;
    private JComboBox userCombo;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;
    private JComboBox groupsCombo;


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

    public void setGroups(VDMMap newGroups) {
        groupsCombo.removeAllItems();

        if(newGroups.size() == 0)
            confirmButton.setEnabled(false);
        else
            confirmButton.setEnabled(true);

        newGroups.forEach((n, g) -> {
            groupsCombo.addItem(n);
        });

    }


    public String getMessage() {
        return msgTextField.getText();
    }

    public String getGroupName() {
        return groupsCombo.getSelectedItem().toString();
    }

    public User getUser() {
        return usersOwnerAccess.get(userCombo.getSelectedIndex());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
