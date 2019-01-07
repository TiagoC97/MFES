package gui;

import PerfectGym.Client;
import PerfectGym.Trainer;
import PerfectGym.User;
import PerfectGym.quotes.*;
import org.overture.codegen.runtime.VDMSet;

import javax.swing.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Vector;

public class CreateGymClassMenu {
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JComboBox<String> trainersCombo;
    private JComboBox<Object> dayOfWeekCombo;
    private JTextField startHourTextField;
    private JTextField endHourTextField;
    private JTextField dateTextField;
    private JComboBox<String> usersCombo;



    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;

    private Main parent;
    private ArrayList<Trainer> trainers;
    private ArrayList<User> usersAtLeastEmployees;

    public CreateGymClassMenu(Main parent) {
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

    public void setTrainers(ArrayList<Trainer> trainers) {
        this.trainers = trainers;
        trainersCombo.removeAllItems();
        trainers.forEach(t -> trainersCombo.addItem(t.getName()));
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

    public String getName(){ return nameTextField.getText();}
    public String getDescription(){ return descriptionTextField.getText();}
    public Trainer getTrainer(){ return trainers.get(trainersCombo.getSelectedIndex());}
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

