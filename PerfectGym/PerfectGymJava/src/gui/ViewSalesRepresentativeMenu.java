package gui;

import PerfectGym.Club;
import PerfectGym.SalesRepresentative;
import PerfectGym.User;
import org.overture.codegen.runtime.VDMSeq;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewSalesRepresentativeMenu {
    private JPanel pane;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField ageTextField;
    private JTextField genderTextField;
    private JTextField nationalityTextField;
    private JTextField clubTextField;
    private JTextField accessTextField;
    private JTextArea claendarTextArea;
    private JTextArea messagesTextArea;
    private JTextField newMsgTextField;
    private JComboBox receiversCombo;
    private JComboBox receivedCombo;
    private JButton sendMsgUserButton;
    private JButton removeMessageButton;
    private JTextField leadsTextField;
    private JButton backButton;

    private Main parent;

    private SalesRepresentative salesRepresentative;

    private ArrayList<User> receivers;
    private ArrayList<String> senders;
    private ArrayList<Map<String, Integer>> inbox;

    public ViewSalesRepresentativeMenu(Main parent) {
        this.parent = parent;

        addListeners();

    }

    private void addListeners() {
        sendMsgUserButton.addActionListener(e -> {
            salesRepresentative.sendMessage(receivers.get(receiversCombo.getSelectedIndex()), newMsgTextField.getText());
        });

        removeMessageButton.addActionListener(e -> {
            Map map;
            int index = receivedCombo.getSelectedIndex();
            map = inbox.get(index);
            salesRepresentative.deleteMessageNFromUser(Integer.parseInt(map.get(senders.get(index)).toString()), senders.get(index));
            senders.remove(index);
            receivedCombo.removeItemAt(index);
            inbox.remove(index);
            setMessagesTextArea();
        });
    }

    public void setSalesRepresentative(SalesRepresentative salesRepresentative){
        this.salesRepresentative = salesRepresentative;
        start();
    }

    public void start() {
        Club club = salesRepresentative.getClub();
        idTextField.setText(salesRepresentative.getID().toString());
        nameTextField.setText(salesRepresentative.getName());
        ageTextField.setText(salesRepresentative.getAge().toString());
        genderTextField.setText(salesRepresentative.getGender().toString());
        nationalityTextField.setText(salesRepresentative.getNationality());
        clubTextField.setText(club.toString());
        accessTextField.setText(salesRepresentative.getAccess().toString());
        leadsTextField.setText(salesRepresentative.getLeads().toString());

        receivers = new ArrayList<>();
        senders = new ArrayList<>();
        inbox = new ArrayList<>();

        receivedCombo.removeAllItems();
        receiversCombo.removeAllItems();

        club.getUsers().forEach(u -> {
            if (!(((User) u).getID() == salesRepresentative.getID())) {
                receivers.add((User) u);
                receiversCombo.addItem(((User) u).getName());
            }
        });

        receivers.remove((User) salesRepresentative);

        salesRepresentative.checkInbox().forEach((sender, msgs) -> {
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
        setCalendarTextArea();
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
        salesRepresentative.checkInbox().forEach((sender, msgs) -> {
            for (Object m : ((VDMSeq) msgs)) {
                sb.append(sender + ": " + m).append("\n");
            }
        });
        messagesTextArea.setText(sb.toString());
    }

    private void setCalendarTextArea() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmployeeCalendar{calendar := {").append("\n");
        salesRepresentative.getCalendar().getTasks().forEach((d, tasks) ->{
            sb.append(d.toString() + " |-> ["  ).append("\n");
            for (Object t : ((VDMSeq) tasks)) {
                sb.append(t + ", ").append("\n");
            }
            sb.append(" ], "  ).append("\n");
        });
        sb.append("}"  ).append("\n");

        claendarTextArea.setText(sb.toString());
    }
}
