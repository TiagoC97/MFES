package gui;

import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;

import javax.swing.*;

public class CreateUserMenu {


    private JTextField nameTextField;
    private JSpinner ageSpinner;
    private JComboBox<Object> genderCombo;
    private JTextField nationalityTextField;

    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;

    private String user;

    private Main parent;


    public CreateUserMenu(Main parent) {
        this.parent = parent;

        genderCombo.addItem(MaleQuote.getInstance());
        genderCombo.addItem(FemaleQuote.getInstance());
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

    public String getName() {
        return nameTextField.getText();
    }

    public int getAge() {
        return (int)(ageSpinner.getValue());
    }

    public Object getGender() {
        return genderCombo.getSelectedItem();
    }

    public String getNationality() {
        return nationalityTextField.getText();
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getUser(){
        return user;
    }



}
