package gui;

import PerfectGym.*;
import org.overture.codegen.runtime.VDMSeq;

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
    private JTextArea activePaymentsTextArea;
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
    private JComboBox productsToBuyCombo;
    private JSpinner productToBuySpinner;
    private JTextField gymFeeTextField;
    private JTextField gymFeePayDateTextField;
    private JTextField gymFeePayHourTextField;
    private JButton payGymFeeButton;
    private JTextField personalTrainingPayDateTextField;
    private JTextField personalTrainingPayHourTextField;
    private JButton payPTFeeButton;
    private JButton buyProductButton;
    private JTextField productPayDateTextField;
    private JTextField productPayHourTextField;
    private JComboBox prodPaymentsCombo;
    private JComboBox productsAddPaymentCombo;
    private JSpinner quantityAddProdPaySpinner;
    private JButton addProdToPaymentButton;
    private JTextArea historyPaymentsTextArea;

    private Main parent;

    private Client client;


    private ArrayList<User> receivers;
    private Map<String, Group> groups;
    private ArrayList<String> senders;
    private ArrayList<Map<String, Integer>> inbox;
    private ArrayList<Product> products;
    private ArrayList<ProductPayment> productPayments;

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
            groups.get(groupsCombo.getSelectedItem().toString()).sendMessage(client, newMsgTextField.getText());
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

        payGymFeeButton.addActionListener(e -> {
            client.payGymFee(Integer.parseInt(gymFeePayDateTextField.getText()),
                    Integer.parseInt(gymFeePayHourTextField.getText()));
            setActivePaymentsTextArea();
            setHistoryPaymentsTextArea();

        });

        payPTFeeButton.addActionListener(e -> {
            client.payPersonalTrainingFee(Integer.parseInt(personalTrainingPayDateTextField.getText()),
                    Integer.parseInt(personalTrainingPayHourTextField.getText()));
            setActivePaymentsTextArea();
            setHistoryPaymentsTextArea();
        });

        buyProductButton.addActionListener(e -> {
            ProductPayment p = new ProductPayment(client, products.get(productsToBuyCombo.getSelectedIndex()),
                    (Number) productToBuySpinner.getValue(), Integer.parseInt(productPayDateTextField.getText()),
                    Integer.parseInt(productPayHourTextField.getText()));
            setActivePaymentsTextArea();
            setHistoryPaymentsTextArea();
            productPayments.add(p);

            prodPaymentsCombo.addItem(products.get(productsAddPaymentCombo.getSelectedIndex()).getName() + ", ");
        });

        addProdToPaymentButton.addActionListener(e -> {
            if (productPayments.size() > 0) {
                int index = prodPaymentsCombo.getSelectedIndex();
                ProductPayment p = productPayments.get(index);
                client.addProductToPayment(p,
                        products.get(productsAddPaymentCombo.getSelectedIndex()), (Number) quantityAddProdPaySpinner.getValue());
                setActivePaymentsTextArea();
                setHistoryPaymentsTextArea();

                String payment = "";
                for (Object pro : p.getProducts()) {
                    payment += ((Product) pro).getName() + ", ";
                }
                prodPaymentsCombo.insertItemAt(payment, index);
                prodPaymentsCombo.removeItemAt(index + 1);

            }
        });

    }

    public void setClient(Client client) {
        this.client = client;
        start();
    }

    public void start() {
        Club club = client.getClub();
        idTextField.setText(client.getID().toString());
        nameTextField.setText(client.getName());
        ageTextField.setText(client.getAge().toString());
        genderTextField.setText(client.getGender().toString());
        nationalityTextField.setText(client.getNationality());
        clubTextField.setText(club.toString());
        accessTextField.setText(client.getAccess().toString());
        trainerTextField.setText(client.getTrainer().toString());
        feeTextField.setText(client.getPersonalTrainingFee().toString());
        gymFeeTextField.setText(club.getFee().toString());
        ;

        receivers = new ArrayList<>();
        groups = new HashMap<>();
        senders = new ArrayList<>();
        inbox = new ArrayList<>();
        products = new ArrayList<>();
        productPayments = new ArrayList<>();

        receivedCombo.removeAllItems();
        groupsCombo.removeAllItems();
        receiversCombo.removeAllItems();
        prodPaymentsCombo.removeAllItems();
        productsToBuyCombo.removeAllItems();
        productsAddPaymentCombo.removeAllItems();

        club.getUsers().forEach(u -> {
            receivers.add((User) u);
            receiversCombo.addItem(((User) u).getName());
        });

        receivers.remove((User) client);

        club.getGroups().forEach((n, g) -> {
            if (((Group) g).getClients().contains(client)) {

                groupsCombo.addItem(n);
                groups.put(n.toString(), (Group) g);
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

        club.getProducts().forEach(p -> {
            products.add((Product) p);
            productsAddPaymentCombo.addItem(((Product) p).getName());
            productsToBuyCombo.addItem(((Product) p).getName());
        });

        client.getProductPayments().forEach(p -> {
            productPayments.add((ProductPayment) p);
            String payment = "";
            for (Object pro : ((ProductPayment) p).getProducts()) {
                payment += ((Product) pro).getName() + ", ";
            }
            prodPaymentsCombo.addItem(payment);
        });


        setGymClassesTextArea();
        setTrainingSessionTextArea();
        setGymAttendencsTextArea();
        setGroupsTextArea();
        setMessagesTextArea();
        setActivePaymentsTextArea();
        setHistoryPaymentsTextArea();
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
        groups.forEach((n, g) -> {
            sb.append("Group " + n + "\n" + "messages: ").append("\n");
            g.checkInbox(client).forEach((s, msgs) -> {
                for (Object m : ((VDMSeq) msgs)) {
                    sb.append(s + ": " + m).append("\n");
                }
            });
            sb.append("offers: ").append("\n");
            g.checkOffers(client).forEach(m -> {
                sb.append(m.toString()).append("\n");
            });

        });

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

    private void setActivePaymentsTextArea() {
        StringBuilder sb = new StringBuilder();
        client.getProductPayments().forEach(p -> {
            sb.append(p.toString()).append("\n");
        });

        client.getGymFeePayments().forEach(p -> {
            sb.append(p.toString()).append("\n");
        });

        client.getPersonalTrainingPayments().forEach(p -> {
            sb.append(p.toString()).append("\n");
        });
        activePaymentsTextArea.setText(sb.toString());
    }

    private void setHistoryPaymentsTextArea() {
        StringBuilder sb = new StringBuilder();
        client.getHistoryProductPayments().forEach(p -> {
            sb.append(p.toString()).append("\n");
        });

        client.getHistoryGymFeePayments().forEach(p -> {
            sb.append(p.toString()).append("\n");
        });

        client.getHistoryPersonalTrainingPayments().forEach(p -> {
            sb.append(p.toString()).append("\n");
        });
        historyPaymentsTextArea.setText(sb.toString());
    }
}
