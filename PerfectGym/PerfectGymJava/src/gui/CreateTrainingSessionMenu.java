package gui;

import PerfectGym.Client;
import PerfectGym.Trainer;
import PerfectGym.User;
import PerfectGym.quotes.*;

import javax.swing.*;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class CreateTrainingSessionMenu {
    private JTextField descriptionTextField;
    private JComboBox<Object> dayOfWeekCombo;
    private JTextField startHourTextField;
    private JTextField endHourTextField;
    private JTextField dateTextField;
    private JComboBox<String> usersCombo;
    private JButton cancelButton;
    private JButton confirmButton;
    private JComboBox<String> clientsCombo;

    private JPanel pane;

    private Main parent;
    private ArrayList<Client> clients;
    private ArrayList<User> usersAtLeastEmployees;

    public CreateTrainingSessionMenu(Main parent) {
        this.parent = parent;

        setDaysOfWeek();
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

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
        clientsCombo.removeAllItems();
        clients.forEach(c -> clientsCombo.addItem(c.getName()));
    }

    private void setDaysOfWeek(){
        dayOfWeekCombo.removeAllItems();
        dayOfWeekCombo.addItem(MondayQuote.getInstance());
        dayOfWeekCombo.addItem(TuesdayQuote.getInstance());
        dayOfWeekCombo.addItem(WednesdayQuote.getInstance());
        dayOfWeekCombo.addItem(ThursdayQuote.getInstance());
        dayOfWeekCombo.addItem(FridayQuote.getInstance());
        dayOfWeekCombo.addItem(SaturdayQuote.getInstance());
        dayOfWeekCombo.addItem(SundayQuote.getInstance());

    }

    public String getDescription(){ return descriptionTextField.getText();}
    public Client getClient(){ return clients.get(clientsCombo.getSelectedIndex());}
    public Object getDayOfWeek(){ return dayOfWeekCombo.getSelectedItem();}
    public Number getStartHour(){ return  Integer.parseInt(startHourTextField.getText());}
    public Number getEndHour(){ return  Integer.parseInt(endHourTextField.getText());}
    public Number getDate(){ return  Integer.parseInt(dateTextField.getText());}

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
}
