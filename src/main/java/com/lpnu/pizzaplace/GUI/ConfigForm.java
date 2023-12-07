package com.lpnu.pizzaplace.GUI;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.*;

public class ConfigForm extends JFrame {

    // UI Components
    private JRadioButton manualSettingsRadioButton;
    private JRadioButton readFromFileRadioButton;
    private JSpinner payDeskCountSpinner;
    private JSpinner cookCountSpinner;
    private JSpinner differentPizzasCountSpinner;
    private JSpinner minimalTimeToCookPizzaSpinner;
    private JComboBox clientGenerationStrategyComboBox;
    private JComboBox cookModeComboBox;
    private JPanel mainGrid;
    private JLabel headerLabel;
    private JPanel manualInputPanel;
    private JButton submitFormButton;

    // Functional variables

    public ConfigForm() {
        mainGrid.setBackground(Color.decode("#C4BBAF"));
        manualInputPanel.setBackground(Color.decode("#C4BBAF"));
        headerLabel.setFont(new Font("Audi Type Wide Bold", Font.TRUETYPE_FONT, 20));

        setContentPane(mainGrid);
        pack();
        setSize(400, 500);
        setVisible(true);

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
            SwingUtilities.updateComponentTreeUI(this);
        });
    }
}
