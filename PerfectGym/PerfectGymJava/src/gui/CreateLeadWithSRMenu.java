package gui;

import PerfectGym.SalesRepresentative;
import PerfectGym.Trainer;
import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;

import javax.swing.*;
import java.util.ArrayList;

public class CreateLeadWithSRMenu {
    private JPanel pane;
    private JTextField nameTextField;
    private JSpinner ageSpinner;
    private JComboBox genderCombo;
    private JButton cancelButton;
    private JButton confirmButton;
    private JTextField nationalityTextField;
    private JComboBox srCombo;


    private Main parent;

    private ArrayList<SalesRepresentative> salesRepresentatives;

    public CreateLeadWithSRMenu(Main parent) {
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

    public void setSalesRepresentatives(ArrayList<SalesRepresentative> salesRepresentatives){
        this.salesRepresentatives = salesRepresentatives;
        srCombo.removeAllItems();
        salesRepresentatives.forEach(u -> srCombo.addItem(u.getName()));
    }

    public SalesRepresentative getSR(){
        return salesRepresentatives.get(srCombo.getSelectedIndex());
    }
}
