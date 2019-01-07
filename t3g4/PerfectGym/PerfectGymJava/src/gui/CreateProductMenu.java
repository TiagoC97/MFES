package gui;

import PerfectGym.quotes.FemaleQuote;
import PerfectGym.quotes.MaleQuote;

import javax.swing.*;

public class CreateProductMenu {
    private JTextField nameTextField;
    private JTextField valueTextField;
    private JSpinner qttSpinner;
    private JButton cancelButton;
    private JButton confirmButton;
    private JPanel pane;

    private Main parent;


    public CreateProductMenu(Main parent) {
        this.parent = parent;

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

    public String getName(){ return nameTextField.getText();}

    public Number getValue(){ return Integer.parseInt(valueTextField.getText());}

    public Number getQuantity(){ return (Number) qttSpinner.getValue();}

}
