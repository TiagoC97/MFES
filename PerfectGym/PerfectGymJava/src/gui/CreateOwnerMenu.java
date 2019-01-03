package gui;

import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;

import javax.swing.*;

public class CreateOwnerMenu {


    private JTextField nameTextField;
    private JSpinner ageSpinner;
    private JComboBox<Object> genderCombo;
    private JTextField nationalityTextField;

    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;

    private Main parent;


    public CreateOwnerMenu(Main parent) {
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


}
