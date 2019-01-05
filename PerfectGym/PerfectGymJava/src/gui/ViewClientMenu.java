package gui;

import PerfectGym.Client;
import PerfectGym.Group;
import PerfectGym.User;
import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewClientMenu {
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField genderTextField;
    private JTextField nationalityTextField;
    private JTextArea gymClassesTextArea;
    private JTextArea trainingSessionsTextArea;
    private JTextArea gymAttendencesTextArea;
    private JTextArea messagesTextArea;
    private JTextArea productsTextArea;
    private JTextArea paymentsTextArea;
    private JButton backButton;
    private JPanel pane;
    private JTextField clubTextField;
    private JTextField accessTextField;
    private JTextField trainerTextField;
    private JTextField feeTextField;
    private JTextField newMsgTextField;
    private JComboBox receiversCombo;
    private JComboBox groupsCombo;
    private JButton sendMsgUserButton;
    private JComboBox receivedCombo;
    private JButton removeMessageButton;
    private JButton sendMsgGroupButton;
    private JTextArea groupsTextArea;

    private Main parent;

    private Client client;


    private ArrayList<User> receivers;
    private ArrayList<Group> groups;
    private ArrayList<String> senders;
    private ArrayList<Map<String, Integer>> inbox;

    public ViewClientMenu(Main parent) {
        this.parent = parent;

        addListeners();

    }

    private void addListeners() {
        sendMsgUserButton.addActionListener(e -> {
            client.sendMessage(receivers.get(receiversCombo.getSelectedIndex()), newMsgTextField.getText());
        });

        sendMsgGroupButton.addActionListener(e -> {
            client.sendMessageToGroup(newMsgTextField.getText(), groupsCombo.getSelectedItem().toString());
            groups.get(groupsCombo.getSelectedIndex()).sendMessage(client, newMsgTextField.getText());
            setGroupsTextArea();
        });

        removeMessageButton.addActionListener(e -> {
            Map map;
            int index = receivedCombo.getSelectedIndex();
            map = inbox.get(index);
            client.deleteMessageNFromUser(Integer.parseInt(map.get(0).toString()), senders.get(index));
            senders.remove(index);
            receivedCombo.removeItemAt(index);
            inbox.remove(index);
            setMessagesTextArea();
        });

    }

    public void setClient(Client client){
        this.client = client;
        start();
    }

    public void start() {
        idTextField.setText(client.getID().toString());
        nameTextField.setText(client.getName());
        ageTextField.setText(client.getAge().toString());
        genderTextField.setText(client.getGender().toString());
        nationalityTextField.setText(client.getNationality());
        clubTextField.setText(client.getClub().toString());
        accessTextField.setText(client.getAccess().toString());
        trainerTextField.setText(client.getTrainer().toString());
        feeTextField.setText(client.getPersonalTrainingFee().toString());

        receivers = new ArrayList<>();
        groups = new ArrayList<>();
        senders = new ArrayList<>();
        inbox = new ArrayList<>();


        client.getClub().getUsers().forEach(u -> {
            receivers.add((User) u);
            receiversCombo.addItem(((User) u).getName());
        });

        receivers.remove((User) client);

        client.getClub().getGroups().forEach((n, g) -> {
            if (((Group) g).getClients().contains(client)) {
                groupsCombo.addItem(n);
                groups.add((Group) g);
            }
        });


        client.checkInbox().forEach((sender, msgs) -> {
            int i = 1;
            for (Object m : ((VDMSeq) msgs)) {
                receivedCombo.addItem(sender + ": " + m);
                Map map = new HashMap<String, Integer>();
                map.put(sender.toString(), i);
                inbox.add(map);
                senders.add(sender.toString());
                i++;
            }
        });

        setGymClassesTextArea();
        setTrainingSessionTextArea();
        setGymAttendencsTextArea();
        setGroupsTextArea();
        setMessagesTextArea();
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

    private void setGymClassesTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object g : client.getClasses()) {
            sb.append(g.toString()).append("\n");
        }
        gymClassesTextArea.setText(sb.toString());
    }

    private void setTrainingSessionTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t : client.getTrainingSessions()) {
            sb.append(t.toString()).append("\n");
        }
        trainingSessionsTextArea.setText(sb.toString());
    }

    private void setGymAttendencsTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t : client.getGymAttendences()) {
            sb.append(t.toString()).append("\n");
        }
        gymAttendencesTextArea.setText(sb.toString());
    }

    private void setGroupsTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t : groups) {
            sb.append(t.toString()).append("\n");
        }
        groupsTextArea.setText(sb.toString());
    }

    private void setMessagesTextArea() {
        StringBuilder sb = new StringBuilder();
        client.checkInbox().forEach((sender, msgs) -> {
            for (Object m : ((VDMSeq) msgs)) {
                sb.append(sender + ": " + m).append("\n");
            }
        });
        messagesTextArea.setText(sb.toString());
    }
}
