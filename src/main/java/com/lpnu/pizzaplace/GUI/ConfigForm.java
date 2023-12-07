package com.lpnu.pizzaplace.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Map;

import com.formdev.flatlaf.*;

public class ConfigForm extends JFrame {

    // UI Components
    private JRadioButton manualSettingsRadioButton;
    private JRadioButton readFromFileRadioButton;
    private JSpinner payDeskCountSpinner;
    private JSpinner cookCountSpinner;
    private JSpinner differentPizzasCountSpinner;
    private JSpinner minimalTimeToCookPizzaSpinner;
    private JComboBox<String> clientGenerationStrategyComboBox;
    private JComboBox<String> cookModeComboBox;
    private JPanel mainGrid;
    private JLabel headerLabel;
    private JPanel manualInputPanel;
    private JButton submitFormButton;
    private JButton loadFileButton;
    private JLabel fileNameLabel;
    private JPanel readFromFilePanel;
    private JPanel cookOneModePanel;
    private JSpinner makingDoughtCooksCountSpinner;
    private JSpinner addingToppingCooksCountSpinner;
    private JSpinner bakingCooksSpinner;
    private JLabel cooksCountLabel;
    private ButtonGroup radioButtonsGroup;

    // Functional variables
    private File configJsonFile;

    // Map
    private final Map<String, Integer> clientGenerationStrategyMapping = Map.of("Кожні 5 секунд", 5000,
            "Кожні 10 секунд", 10000,
            "Кожні 20 секунд", 20000);

    private final Map<String, Boolean> cookModeMapping = Map.of("Всі дії", true,"Одна дія", false);

    public ConfigForm() {
        initializeNumberModels();
        initializeChooseBoxes();
        initializeListeners();
        initializeStyling();

        setContentPane(mainGrid);
        pack();
        setSize(400, 500);
        setVisible(true);
    }

    private void initializeStyling() {
        readFromFilePanel.hide();
        cookOneModePanel.hide();

        cookOneModePanel.setBackground(Color.decode("#C4BBAF"));
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

        makingDoughtCooksCountSpinner.setModel(new SpinnerNumberModel(1, 1, 8, 1));

        addingToppingCooksCountSpinner.setModel(new SpinnerNumberModel(1, 1, 8, 1));

        bakingCooksSpinner.setModel(new SpinnerNumberModel(1, 1, 4, 1));
    }

    private void initializeChooseBoxes() {
        String[] clientGenerationStrategyArray = {"Кожні 5 секунд", "Кожні 10 секунд", "Кожні 20 секунд"};

        for (var item : clientGenerationStrategyArray) {
            clientGenerationStrategyComboBox.addItem(item);
        }

        String[] cookModeArray = {"Всі дії", "Одна дія"};

        for (var item : cookModeArray) {
            cookModeComboBox.addItem(item);
        }
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

        cookModeComboBox.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (cookModeComboBox.getSelectedIndex() == 1) {
                    cookOneModePanel.show();
                    cookCountSpinner.hide();
                    cooksCountLabel.hide();
                } else {
                    cookOneModePanel.hide();
                    cookCountSpinner.show();
                    cooksCountLabel.show();
                }

                cookOneModePanel.updateUI();
            }
        });


    }
}
