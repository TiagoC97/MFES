package gui;

import PerfectGym.Club;
import PerfectGym.Owner;
import PerfectGym.Trainer;
import PerfectGym.User;
import org.overture.codegen.runtime.VDMSeq;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewTrainerMenu {
    private JPanel pane;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField ageTextField;
    private JTextField genderTextField;
    private JTextField nationalityTextField;
    private JTextField clubTextField;
    private JTextField accessTextField;
    private JTextArea gymClassesTextArea;
    private JTextArea trainingSessionsTextArea;
    private JTextField newMsgTextField;
    private JComboBox receiversCombo;
    private JComboBox receivedCombo;
    private JButton sendMsgUserButton;
    private JButton removeMessageButton;
    private JButton backButton;
    private JTextArea claendarTextArea;
    private JTextArea messagesTextArea;
    private JTextField traineesTextField;

    private Main parent;

    private Trainer trainer;

    private ArrayList<User> receivers;
    private ArrayList<String> senders;
    private ArrayList<Map<String, Integer>> inbox;

    public ViewTrainerMenu(Main parent) {
        this.parent = parent;

        addListeners();

    }

    private void addListeners() {
        sendMsgUserButton.addActionListener(e -> {
            trainer.sendMessage(receivers.get(receiversCombo.getSelectedIndex()), newMsgTextField.getText());
        });

        removeMessageButton.addActionListener(e -> {
            Map map;
            int index = receivedCombo.getSelectedIndex();
            map = inbox.get(index);
            trainer.deleteMessageNFromUser(Integer.parseInt(map.get(senders.get(index)).toString()), senders.get(index));
            senders.remove(index);
            receivedCombo.removeItemAt(index);
            inbox.remove(index);
            setMessagesTextArea();
        });
    }

    public void setTrainer(Trainer trainer){
        this.trainer = trainer;
        start();
    }

    public void start() {
        Club club = trainer.getClub();
        idTextField.setText(trainer.getID().toString());
        nameTextField.setText(trainer.getName());
        ageTextField.setText(trainer.getAge().toString());
        genderTextField.setText(trainer.getGender().toString());
        nationalityTextField.setText(trainer.getNationality());
        clubTextField.setText(club.toString());
        accessTextField.setText(trainer.getAccess().toString());
        traineesTextField.setText(trainer.getTrainees().toString());

        receivers = new ArrayList<>();
        senders = new ArrayList<>();
        inbox = new ArrayList<>();

        receivedCombo.removeAllItems();
        receiversCombo.removeAllItems();

        club.getUsers().forEach(u -> {
            if (!(((User) u).getID() == trainer.getID())) {
                receivers.add((User) u);
                receiversCombo.addItem(((User) u).getName());
            }
        });

        receivers.remove((User) trainer);

        trainer.checkInbox().forEach((sender, msgs) -> {
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
        setGymClassesTextArea();
        setTrainingSessionTextArea();
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
        trainer.checkInbox().forEach((sender, msgs) -> {
            for (Object m : ((VDMSeq) msgs)) {
                sb.append(sender + ": " + m).append("\n");
            }
        });
        messagesTextArea.setText(sb.toString());
    }

    private void setGymClassesTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object g : trainer.getClasses()) {
            sb.append(g.toString()).append("\n");
        }
        gymClassesTextArea.setText(sb.toString());
    }

    private void setTrainingSessionTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Object t : trainer.getTrainingSessions()) {
            sb.append(t.toString()).append("\n");
        }
        trainingSessionsTextArea.setText(sb.toString());
    }

    private void setCalendarTextArea() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmployeeCalendar{calendar := {").append("\n");
        trainer.getCalendar().getTasks().forEach((d, tasks) ->{
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
