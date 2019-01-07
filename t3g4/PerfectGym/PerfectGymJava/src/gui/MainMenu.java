package gui;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private JPanel pane;
    private JButton perfectGymButton;
    private JButton exitButton;

    public MainMenu() {
        addListeners();
    }

    private void addListeners() {
        exitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Do you reeeally want to leave?", null,
                    JOptionPane.YES_NO_OPTION);

            if(result == JOptionPane.YES_OPTION){
                Container frame = exitButton.getParent();
                do
                    frame = frame.getParent();
                while (!(frame instanceof JFrame));
                ((JFrame) frame).dispose();
                System.exit(0);
            }
        });
    }

    public void setVisible() {
        this.pane.setVisible(true);
    }

    public JPanel getPane() {
        return pane;
    }

    public JButton getPerfectGymButton() {
        return perfectGymButton;
    }



}
