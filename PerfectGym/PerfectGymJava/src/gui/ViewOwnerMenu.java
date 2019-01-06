package gui;

import PerfectGym.*;
import org.overture.codegen.runtime.VDMSeq;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewOwnerMenu {
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField genderTextField;
    private JTextField nationalityTextField;
    private JTextField clubTextField;
    private JTextField accessTextField;
    private JTextField newMsgTextField;
    private JComboBox receiversCombo;
    private JComboBox receivedCombo;
    private JButton sendMsgUserButton;
    private JButton removeMessageButton;
    private JTextArea messagesTextArea;
    private JButton backButton;
    private JPanel pane;

    private Main parent;

    private Owner owner;

    private ArrayList<User> receivers;
    private ArrayList<String> senders;
    private ArrayList<Map<String, Integer>> inbox;

    public ViewOwnerMenu(Main parent) {
        this.parent = parent;

        addListeners();

    }

    private void addListeners() {
        sendMsgUserButton.addActionListener(e -> {
            owner.sendMessage(receivers.get(receiversCombo.getSelectedIndex()), newMsgTextField.getText());
        });

        removeMessageButton.addActionListener(e -> {
            Map map;
            int index = receivedCombo.getSelectedIndex();
            map = inbox.get(index);
            owner.deleteMessageNFromUser(Integer.parseInt(map.get(0).toString()), senders.get(index));
            senders.remove(index);
            receivedCombo.removeItemAt(index);
            inbox.remove(index);
            setMessagesTextArea();
        });
    }

    public void setOwner(Owner owner){
        this.owner = owner;
        start();
    }

    public void start() {
        Club club = owner.getClub();
        idTextField.setText(owner.getID().toString());
        nameTextField.setText(owner.getName());
        ageTextField.setText(owner.getAge().toString());
        genderTextField.setText(owner.getGender().toString());
        nationalityTextField.setText(owner.getNationality());
        clubTextField.setText(club.toString());
        accessTextField.setText(owner.getAccess().toString());

        receivers = new ArrayList<>();
        senders = new ArrayList<>();
        inbox = new ArrayList<>();

        receivedCombo.removeAllItems();
        receiversCombo.removeAllItems();

        club.getUsers().forEach(u -> {
            receivers.add((User) u);
            receiversCombo.addItem(((User) u).getName());
        });

        receivers.remove((User) owner);

        owner.checkInbox().forEach((sender, msgs) -> {
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

    private void setMessagesTextArea() {
        StringBuilder sb = new StringBuilder();
        owner.checkInbox().forEach((sender, msgs) -> {
            for (Object m : ((VDMSeq) msgs)) {
                sb.append(sender + ": " + m).append("\n");
            }
        });
        messagesTextArea.setText(sb.toString());
    }
}
