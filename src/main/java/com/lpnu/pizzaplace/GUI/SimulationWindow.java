package com.lpnu.pizzaplace.GUI;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimulationWindow extends JFrame {
    private JTable cooksTable;
    private JTable ordersTable;
    private JPanel mainGrid;
    private JPanel kitchenPanel;
    private JPanel queuePanel;

    private final int payDesksCount = 10;

    public SimulationWindow() {
        initializeStyling();
        initializeTableModels();

        setContentPane(mainGrid);
        pack();
        setSize(1200, 900);


        queuePanel.setLayout(new GridLayout(payDesksCount, 10));
        for (int row = 0; row < payDesksCount; row++) {
            for (int col = 0; col < 10; col++) {

                var cellPane = new ImagePanel();
                Border border = null;
                cellPane.setBackground(Color.decode("#C4BBAF"));
                if (row < payDesksCount - 1) {
                    if (col < payDesksCount - 1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.BLACK);
                    }
                } else {
                    if (col < 9) {
                        border = new MatteBorder(1, 1, 1, 0, Color.BLACK);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
                    }
                }
                cellPane.setBorder(border);
                queuePanel.add(cellPane);
            }
        }

        ((ImagePanel)queuePanel.getComponent(0)).setShouldPaint(true);

        queuePanel.repaint();

        setVisible(true);
    }

    private void initializeTableModels() {
        String[] ordersTableColumnNames = {"Ім'я клієнта", "Назва піци", "Стадія приготування"};
        String[] cooksTableColumnNames = {"Ім'я кухара", "Спеціалізація", "Готує", "Зупинити кухара"};

        String[][] ordersTableMockData = {
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"},
                {"Mike", "Pepperoni", "Done"}
        };

        String[][] cooksTableMockData = {
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"},
                {"John", "Topping", "Pepperoni"}
        };

        EventQueue.invokeLater(() -> {
            DefaultTableModel ordersTableModel = (DefaultTableModel) ordersTable.getModel();
            ordersTableModel.setDataVector(ordersTableMockData, ordersTableColumnNames);
            ordersTable.updateUI();

            DefaultTableModel cooksTableModel = (DefaultTableModel) cooksTable.getModel();
            cooksTableModel.setDataVector(cooksTableMockData, cooksTableColumnNames);

            TableColumnModel cookTableColumnModel = cooksTable.getColumnModel();
            cookTableColumnModel.getColumn(3).setCellRenderer(new ButtonRenderer());

            cookTableColumnModel.getColumn(3).setCellEditor(new ButtonEditor());


            ordersTable.updateUI();
        });


    }

    private void initializeStyling() {
        mainGrid.setBackground(Color.decode("#C4BBAF"));

        queuePanel.setBackground(Color.decode("#C4BBAF"));
        kitchenPanel.setBackground(Color.decode("#C4BBAF"));


        ordersTable.getTableHeader().setBackground(Color.decode("#918a81"));
        ordersTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
        ordersTable.setBackground(Color.decode("#C4BBAF"));

        cooksTable.getTableHeader().setBackground(Color.decode("#918a81"));
        cooksTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
        cooksTable.setBackground(Color.decode("#C4BBAF"));

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
            SwingUtilities.updateComponentTreeUI(this);
        });

        ordersTable.updateUI();
    }

    private class ImagePanel extends JPanel{

        private BufferedImage image;

        private boolean shouldPaint;

        public ImagePanel() {
            try {
                image = ImageIO.read(new File("src/main/resources/Images/customer.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null && shouldPaint) {
                // Scale the image to fit the panel
                Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, this);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        public void setShouldPaint(boolean shouldPaint) {
            this.shouldPaint = shouldPaint;
        }
    }

    public class ButtonRenderer extends JButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Зупинити");
            setForeground(Color.decode("#FFFFFF"));
            setBackground(Color.decode("#928473"));
            return this;
        }
    }

    public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor() {
            button = new JButton();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Perform action when button is pressed
                System.out.println(label + ": Button pressed!");
            }
            isPushed = false;
            return label;
        }
    }
}
