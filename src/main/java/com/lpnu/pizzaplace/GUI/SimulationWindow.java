package com.lpnu.pizzaplace.GUI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.lpnu.pizzaplace.Backend.Configuration.Interfaces.ConfigSupplier;
import com.lpnu.pizzaplace.Backend.Integration.Contracts.*;
import com.lpnu.pizzaplace.Backend.Integration.Interfaces.*;
import com.lpnu.pizzaplace.Backend.PIzzeria.Cook;
import com.lpnu.pizzaplace.Backend.PIzzeria.PayDesk;
import com.lpnu.pizzaplace.Backend.PIzzeria.PayDeskCollection;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaCreationContext;
import com.lpnu.pizzaplace.Backend.Pizza.Contracts.PizzaStateEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class SimulationWindow extends JFrame
        implements NewCustomerRequestHandler,
                    PizzeriaInitializeRequestHandler,
                    PizzaOrderedRequestHandler,
                    PizzaReadinessRequestHandler,
                    ChangePizzaStateRequestHandler,
                    CookAcquiredPizzaRequestHandler{

    // UI Components
    private JTable cooksTable;
    private JTable ordersTable;
    private JPanel mainGrid;
    private JPanel kitchenPanel;
    private JPanel queuePanel;
    private JScrollPane cooksScrollPane;
    private JScrollPane ordersScrollPane;
    private JPanel makingDoughPanel;
    private JPanel addingToppingPanel;
    private JPanel bakingPanel;
    private JPanel standingPanel;

    // Styling constants
    private final Color backgroundColor = Color.decode("#C4BBAF");

    // Functional variables
    private final int payDesksCount;

    private final List<String[]> ordersTableData;

    private final List<String[]> cookTableData;

    private PayDeskCollection payDeskCollection;

    private List<Cook> cooksList;

    private final Map<PayDesk, Integer> payDeskIntegerMap;

    private final List<PizzaCreationContext> pizzaCreationContextList;

    //private final Mediator mediator;

    public SimulationWindow(ConfigSupplier configSupplier) {
        payDesksCount = configSupplier.getConfig().getPayDesksCount();
        ordersTableData = new ArrayList<>();
        cookTableData = new ArrayList<>();
        payDeskIntegerMap = new HashMap<>();
        pizzaCreationContextList = new ArrayList<>();

        showWindow();
    }

    public void showWindow() {
        initializeStyling();
        initializeTableModels();
        initializeCustomersQueue();

        setContentPane(mainGrid);

        pack();
        setSize(1200, 900);

        setVisible(true);
    }

    private void initializeKitchen(JPanel panel, String resourcePath, int cooksCount) {
        for (int row = 0; row < 10; row++) {
            ImagePanel cellPane = new ImagePanel(resourcePath, 100, 100);

            cellPane.setBackground(backgroundColor);

            if (row < cooksCount) {
                cellPane.setShouldPaint(true);
            }

            panel.add(cellPane);
        }

        panel.repaint();
    }

    private void initializeCustomersQueue() {
        queuePanel.setLayout(new GridLayout(payDesksCount, 10));
        for (int row = 0; row < payDesksCount; row++) {
            for (int col = 0; col < 10; col++) {

                ImagePanel cellPane;

                if (col == 0) {
                    cellPane = new ImagePanel("src/main/resources/Images/pay_desk.png", 200, 200);
                    cellPane.setShouldPaint(true);
                } else {
                    cellPane = new ImagePanel("src/main/resources/Images/customer.png", 200, 200);
                }

                cellPane.setBackground(backgroundColor);
                queuePanel.add(cellPane);
            }
        }

        queuePanel.repaint();
    }

    private void initializeTableModels() {
        String[] ordersTableColumnNames = {"Ім'я клієнта", "Назва піци", "Стадія приготування"};
        String[] cooksTableColumnNames = {"Ім'я кухара", "Спеціалізація", "Готує", "Зупинити кухара"};

        EventQueue.invokeLater(() -> {
            DefaultTableModel ordersTableModel = (DefaultTableModel) ordersTable.getModel();
            ordersTableModel.setDataVector(null, ordersTableColumnNames);
            ordersTable.updateUI();

            DefaultTableModel cooksTableModel = (DefaultTableModel) cooksTable.getModel();
            cooksTableModel.setDataVector(null, cooksTableColumnNames);

            TableColumnModel cookTableColumnModel = cooksTable.getColumnModel();
            cookTableColumnModel.getColumn(3).setCellRenderer(new ButtonRenderer());

            cookTableColumnModel.getColumn(3).setCellEditor(new ButtonEditor());


            ordersTable.updateUI();
        });


    }

    private void initializeStyling() {
        standingPanel.setBackground(backgroundColor);
        standingPanel.setLayout(new GridLayout(10, 1));

        makingDoughPanel.setBackground(backgroundColor);
        makingDoughPanel.setLayout(new GridLayout(10, 1));

        addingToppingPanel.setBackground(backgroundColor);
        addingToppingPanel.setLayout(new GridLayout(10, 1));

        bakingPanel.setBackground(backgroundColor);
        bakingPanel.setLayout(new GridLayout(10, 1));

        mainGrid.setBackground(backgroundColor);

        queuePanel.setBackground(backgroundColor);
        kitchenPanel.setBackground(backgroundColor);

        initializeStylingForTableAndScrollPane(cooksTable, cooksScrollPane);
        initializeStylingForTableAndScrollPane(ordersTable, ordersScrollPane);

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
            SwingUtilities.updateComponentTreeUI(this);
        });

        ordersTable.updateUI();
        cooksTable.updateUI();
    }

    private void initializeStylingForTableAndScrollPane(JTable table, JScrollPane scrollPane) {
        scrollPane.getViewport().setBackground(backgroundColor);

        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, backgroundColor));

        table.setSelectionBackground(Color.decode("#b7d0e2"));

        table.setSelectionForeground(Color.decode("#000000"));


        table.getTableHeader().setBackground(Color.decode("#918a81"));
        table.getTableHeader().setForeground(Color.decode("#FFFFFF"));
        table.setBackground(backgroundColor);
    }

    @Override
    public void handle(PizzaReadinessRequest request) {
        var customer = request.getPizza().getCustomer();

        if (pizzaCreationContextList.stream().
                filter(pizzaCreationContext -> pizzaCreationContext.getPizza().getCustomer().equals(customer)).
                allMatch(PizzaCreationContext::isReady)) {

            var payDeskWithCustomerToDelete = payDeskCollection.
                    getPayDesks().
                    stream().
                    filter(payDesk -> payDesk.getCustomers().
                            contains(request.getPizza().getCustomer())).
                    findFirst();

            EventQueue.invokeLater(() -> {
                ((ImagePanel) queuePanel.
                        getComponent((payDeskIntegerMap.get(payDeskWithCustomerToDelete.get()) - 1) * 10 + payDeskWithCustomerToDelete.get().getCustomersCount() + 1)).
                        setShouldPaint(false);

                queuePanel.repaint();
            });

            payDeskWithCustomerToDelete.get().deleteCustomer(customer);

        }
    }

    @Override
    public void handle(ChangeStateRequest request) {

        EventQueue.invokeLater(() -> {
            String[] ordersTableColumnNames = {"Ім'я клієнта", "Назва піци", "Стадія приготування"};

            ordersTableData.clear();

            pizzaCreationContextList.forEach(pizzaCreationContext -> {
                ordersTableData.add(new String[]{pizzaCreationContext.getPizza().getCustomer().getName(),
                        pizzaCreationContext.getPizza().getName(),
                        pizzaCreationContext.getPizzaState().asEnum().toString()});
            });

            var ordersTableModel = (DefaultTableModel) ordersTable.getModel();

            String[][] array = new String[ordersTableData.size()][];
            for (int i = 0; i < ordersTableData.size(); i++) {
                array[i] = ordersTableData.get(i);
            }

            ordersTableModel.setDataVector(array, ordersTableColumnNames);

            changeCooksStateOrPizza();
        });

        ordersTable.updateUI();
    }

    private void changeCooksStateOrPizza() {
        cookTableData.clear();

        EventQueue.invokeLater(() -> {
            String[] cooksTableColumnNames = {"Ім'я кухара", "Спеціалізація", "Готує", "Зупинити кухара"};

            cooksList.forEach((cook) -> {
                cookTableData.add(new String[]{cook.getName(),
                        cook.getSpecialization(),
                        cook.getCurrentContext() != null ? cook.getCurrentContext().getPizza().getName() : "Нічого"});
            });

            var cooksTableModel = (DefaultTableModel) cooksTable.getModel();

            String[][] array = new String[cookTableData.size()][];
            for (int i = 0; i < cookTableData.size(); i++) {
                array[i] = cookTableData.get(i);
            }

            cooksTableModel.setDataVector(array, cooksTableColumnNames);

            TableColumnModel cookTableColumnModel = cooksTable.getColumnModel();
            cookTableColumnModel.getColumn(3).setCellRenderer(new ButtonRenderer());

            cookTableColumnModel.getColumn(3).setCellEditor(new ButtonEditor());

            int standingCount = (int) cooksList.stream().filter(cook -> cook.getCurrentContext() == null).count();
            int makingDoughCount = (int) cooksList.stream().filter(cook -> cook.getCurrentContext() != null)
                    .filter(cook -> cook.getCurrentContext().getPizzaState().asEnum() == PizzaStateEnum.MakingDough).count();

            int addingToppingCount = (int) cooksList.stream().filter(cook -> cook.getCurrentContext() != null)
                    .filter(cook -> cook.getCurrentContext().getPizzaState().asEnum() == PizzaStateEnum.AddingTopping).count();

            int bakingCount = (int) cooksList.stream().filter(cook -> cook.getCurrentContext() != null)
                    .filter(cook -> cook.getCurrentContext().getPizzaState().asEnum() == PizzaStateEnum.Cooking).count();

            standingPanel.removeAll();
            standingPanel.revalidate();
            standingPanel.repaint();
            makingDoughPanel.removeAll();
            makingDoughPanel.revalidate();
            makingDoughPanel.repaint();
            addingToppingPanel.removeAll();
            addingToppingPanel.revalidate();
            addingToppingPanel.repaint();
            bakingPanel.removeAll();
            bakingPanel.revalidate();
            bakingPanel.repaint();

            initializeKitchen(standingPanel, "src/main/resources/Images/cook_standing.png", standingCount);
            initializeKitchen(makingDoughPanel, "src/main/resources/Images/making_dough.png", makingDoughCount);
            initializeKitchen(addingToppingPanel, "src/main/resources/Images/topping.png", addingToppingCount);
            initializeKitchen(bakingPanel, "src/main/resources/Images/baking.png", bakingCount);
        });

        cooksTable.updateUI();
    }

    @Override
    public void handle(NewCustomerRequest request) {
        ((ImagePanel) queuePanel.
                getComponent((payDeskIntegerMap.get(request.getPayDesk()) - 1) * 10 + request.getPayDesk().getCustomersCount())).
                setShouldPaint(true);

        queuePanel.repaint();
    }

    @Override
    public void handle(PizzaOrderedRequest request) {
        EventQueue.invokeLater(() -> {
            String[] ordersTableColumnNames = {"Ім'я клієнта", "Назва піци", "Стадія приготування"};

            pizzaCreationContextList.add(request.getContext());

            ordersTableData.add(new String[]{request.getContext().getPizza().getCustomer().getName(),
                    request.getContext().getPizza().getName(),
                    request.getContext().getPizzaState().asEnum().toString()});

            var ordersTableModel = (DefaultTableModel) ordersTable.getModel();

            String[][] array = new String[ordersTableData.size()][];
            for (int i = 0; i < ordersTableData.size(); i++) {
                array[i] = ordersTableData.get(i);
            }

            ordersTableModel.setDataVector(array, ordersTableColumnNames);
        });

        ordersTable.updateUI();
    }

    @Override
    public void handle(PizzeriaInitializeRequest request) {
        this.cooksList = request.getCooks();

        changeCooksStateOrPizza();

        this.payDeskCollection = request.getPayDesks();

        Integer id = 1;
        for (var payDesk : payDeskCollection.getPayDesks()) {
            payDeskIntegerMap.put(payDesk, id);
            id++;
        }
    }

    @Override
    public void handle(CookAcquiredPizzaRequest cookAcquiredPizzaRequest) {
        changeCooksStateOrPizza();
    }

    private class ImagePanel extends JPanel{

        private BufferedImage image;

        private final int preferredWidth;

        private final int preferredHeight;

        private boolean shouldPaint;

        public ImagePanel(String resourcePath, int preferredWidth, int preferredHeight) {
            this.preferredWidth = preferredWidth;
            this.preferredHeight = preferredHeight;
            try {
                image = ImageIO.read(new File(resourcePath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null && shouldPaint) {
                Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, this);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(preferredWidth, preferredHeight);
        }

        public void setShouldPaint(boolean shouldPaint) {
            this.shouldPaint = shouldPaint;
        }
    }

    public class ButtonRenderer extends JButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(cooksList.get(row).isStopped() ? "Відновити" : "Зупинити");
            setForeground(Color.decode("#FFFFFF"));
            setBackground(Color.decode("#928473"));
            return this;
        }
    }

    public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        protected JButton button;
        private String label;

        public ButtonEditor() {
            button = new JButton();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (cooksList.get(cooksTable.rowAtPoint(button.getLocation())).isStopped()) {
                        cooksList.get(cooksTable.rowAtPoint(button.getLocation())).resume();
                    } else {
                        cooksList.get(cooksTable.rowAtPoint(button.getLocation())).stop();
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }
}
