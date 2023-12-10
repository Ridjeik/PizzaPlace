package com.lpnu.pizzaplace.GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.google.gson.*;

import com.formdev.flatlaf.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.lpnu.pizzaplace.Backend.Configuration.Contracts.PizzeriaConfig;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigFactory;
import com.lpnu.pizzaplace.Backend.Logging.Interfaces.Logger;

public class ConfigForm extends JFrame implements ConfigFactory {
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

    //DI dependencies
    private final Logger log;

    // Functional variables
    private File configJsonFile;

    private final CountDownLatch submitSynchronizer;

    // Map
    private final Map<String, Integer> clientGenerationStrategyMapping = Map.of("Кожні 5 секунд", 5000,
            "Кожні 10 секунд", 10000,
            "Кожні 20 секунд", 20000);

    private final Map<String, Boolean> cookModeMapping = Map.of("Всі дії", true,"Одна дія", false);

    public ConfigForm(Logger log) {
        this.log = log;
        this.submitSynchronizer = new CountDownLatch(1);

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
                fileChooser.setCurrentDirectory(new File("src/main/resources/PizzeriaConfigInputFiles"));
                FileNameExtensionFilter jsonFileFilter = new FileNameExtensionFilter(
                        "Only .json files", "json");

                fileChooser.setFileFilter(jsonFileFilter);

                int configFileAnswer = fileChooser.showOpenDialog(null);

                if (configFileAnswer == JFileChooser.APPROVE_OPTION) {
                    configJsonFile = fileChooser.getSelectedFile();
                    fileNameLabel.setText(configJsonFile.getName());
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


        submitFormButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                submitSynchronizer.countDown();
            }
        });
    }


    @Override
    public PizzeriaConfig createConfig() {
        this.setVisible(true);

        try {
            submitSynchronizer.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        if (manualSettingsRadioButton.isSelected()) {
            return getPizzeriaConfigFromForm();
        } else try {
            return getPizzeriaConfigFromFile();
        } catch (FileNotFoundException ex) {
            log.critical(ex.getMessage());
        }

        this.setVisible(false);

        return null;
    }

    private PizzeriaConfig getPizzeriaConfigFromFile() throws FileNotFoundException {
        if (!configJsonFile.exists()) {
            throw new FileNotFoundException();
        }

        var gsonConverter = new Gson();

        var configType = new TypeToken<PizzeriaConfig>() {
        }.getType();

        JsonReader jsonReader = new JsonReader(new FileReader(configJsonFile));

        PizzeriaConfig pizzeriaConfig = gsonConverter.fromJson(jsonReader, configType);

        return pizzeriaConfig;
    }

    private PizzeriaConfig getPizzeriaConfigFromForm() {
        var baseBuilder = PizzeriaConfig
                .createBuilder()
                .setPayDesksCount((Integer) payDeskCountSpinner.getValue())
                .setPizzaTypesCount((Integer) differentPizzasCountSpinner.getValue())
                .setMinimalTimeToCookPizza((Integer) minimalTimeToCookPizzaSpinner.getValue())
                .setOrderGenerationInterval(clientGenerationStrategyMapping.get((String) clientGenerationStrategyComboBox.getSelectedItem()))
                .setCookDoingAllOperations(cookModeMapping.get((String) cookModeComboBox.getSelectedItem()));


        if (cookModeComboBox.getSelectedIndex() == 0) {
             baseBuilder.setCooksCount((Integer) cookCountSpinner.getValue());
        }
        else
        {
            baseBuilder
                    .setMakingDoughCooksCount((Integer) makingDoughtCooksCountSpinner.getValue())
                    .setAddingToppingCooksCount((Integer) addingToppingCooksCountSpinner.getValue())
                    .setBakingCooksCount((Integer) bakingCooksSpinner.getValue());
        }

        PizzeriaConfig config = baseBuilder.createPizzeriaConfig();

        int confirm = JOptionPane.showOptionDialog(
                this,
                "Would You Like to Save Before Quitting?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        if (confirm == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("src/main/resources/PizzeriaConfigInputFiles"));
            FileNameExtensionFilter jsonFileFilter = new FileNameExtensionFilter(
                    "Only .json files", "json");

            fileChooser.setFileFilter(jsonFileFilter);
            fileChooser.setSelectedFile(new File(".json"));

            Gson gson = new Gson();

            String configJsonString = gson.toJson(config);

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                try (FileWriter writer = new FileWriter(fileToSave)) {
                    writer.write(configJsonString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return config;
    }
}
