package gui;

import PerfectGym.Client;
import PerfectGym.User;
import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMSet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class CreateGroupMenu {
    private JComboBox<String> clientsCombo;
    private JButton addMemberButton;
    private JButton removeMemberButton;
    private JList groupMembersList;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;
    private JTextField groupNameTextField;
    private JComboBox<String> usersCombo;
    private VDMSet members;
    private Vector<String> membersVector;

    private Main parent;


    private ArrayList<Client> clients;
    private ArrayList<User> usersAtLeastEmployees;

    public CreateGroupMenu(Main parent) {
        this.parent = parent;

        addListeners();

    }

    private Client getClient(String name){
        Client cli = null;
        for (Client client : clients) {
            if(client.getName().equals(name))
                cli = client;
        }
        return cli;
    }

    private void addListeners() {
        addMemberButton.addActionListener(e -> {
            if (clientsCombo.getSelectedItem() != null) {
                int index = clientsCombo.getSelectedIndex();
                String name = clientsCombo.getSelectedItem().toString();
                members = SetUtil.union(members, SetUtil.set(getClient(name)));
                membersVector.add(name);
                groupMembersList.setListData(membersVector);
                clientsCombo.removeItemAt(index);
            }
        });

        removeMemberButton.addActionListener(e -> {
            if (groupMembersList.getSelectedValue() != null) {
                int index = groupMembersList.getSelectedIndex();
                String name = groupMembersList.getSelectedValue().toString();
                members = SetUtil.diff(members, SetUtil.set(getClient(name)));
                clientsCombo.addItem(name);
                groupMembersList.setListData(membersVector);
                membersVector.removeElementAt(index);
            }
        });
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
        clientsCombo.removeAllItems();
        clients.forEach(c -> clientsCombo.addItem(c.getName()));
    }

    public JList getGroupMembersList() {
        return groupMembersList;
    }

    public VDMSet getGroupMembers() {
        return members;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public String getGroupName(){
        return groupNameTextField.getText();
    }

    public User getUserResponsible(){
        return usersAtLeastEmployees.get(usersCombo.getSelectedIndex());
    }


    public void setVisible() {
        this.pane.setVisible(true);
    }

    public JPanel getPane() {
        return pane;
    }

    public void setUsersAtLeastEmployees(ArrayList<User> usersAtLEastEmployees) {
        this.usersAtLeastEmployees = usersAtLEastEmployees;
        usersCombo.removeAllItems();
        usersAtLeastEmployees.forEach(u -> usersCombo.addItem(u.getName()));
    }

    public void start(){
        membersVector = new Vector<>();
        members = new VDMSet();
        groupMembersList.setListData(membersVector);
    }
}

