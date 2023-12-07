package com.lpnu.pizzaplace.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

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
    private JButton loadFileButton;
    private JLabel fileNameLabel;
    private JPanel readFromFilePanel;
    private ButtonGroup radioButtonsGroup;

    // Functional variables
    private File configJsonFile;

    public ConfigForm() {
        initializeNumberModels();
        initializeListeners();
        initializeStyling();

        setContentPane(mainGrid);
        pack();
        setSize(400, 500);
        setVisible(true);
    }

    private void initializeStyling() {
        readFromFilePanel.hide();

        mainGrid.setBackground(Color.decode("#C4BBAF"));
        manualInputPanel.setBackground(Color.decode("#C4BBAF"));
        headerLabel.setFont(new Font("Audi Type Wide Bold", Font.PLAIN, 20));

        readFromFilePanel.setBackground(Color.decode("#C4BBAF"));

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
            SwingUtilities.updateComponentTreeUI(this);
        });
    }

    private void initializeNumberModels() {
        payDeskCountSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));

        cookCountSpinner.setModel(new SpinnerNumberModel(1, 1, 20, 1));

        differentPizzasCountSpinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));

        minimalTimeToCookPizzaSpinner.setModel(new SpinnerNumberModel(10, 10, 40, 1));
    }

    private void initializeListeners() {
        var radioButtonsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manualSettingsRadioButton.isSelected()) {
                    readFromFilePanel.hide();
                    manualInputPanel.show();
                } else {
                    readFromFilePanel.show();
                    manualInputPanel.hide();
                }
            }
        };

        manualSettingsRadioButton.addActionListener(radioButtonsListener);
        readFromFileRadioButton.addActionListener(radioButtonsListener);

        loadFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                /*TODO Deal with file extensions*/

                int configFileAnswer = fileChooser.showOpenDialog(null);

                if (configFileAnswer == JFileChooser.APPROVE_OPTION) {
                    configJsonFile = fileChooser.getSelectedFile();
                    System.out.println(configJsonFile.getName());
                }
            }
        });
    }
}
