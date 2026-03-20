import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {

    JTextField amountField;
    JComboBox<String> categoryBox, typeBox;
    JButton addButton;

    DefaultTableModel model;
    JTable table;

    JLabel incomeLabel, expenseLabel, balanceLabel;

    ArrayList<Transaction> transactions = new ArrayList<>();

    public Main() {
        setTitle("Finance Tracker");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel(new GridLayout(4,2,5,5));

        amountField = new JTextField();

        String[] categories = {"Food", "Travel", "Bills", "Shopping"};
        categoryBox = new JComboBox<>(categories);

        String[] types = {"Income", "Expense"};
        typeBox = new JComboBox<>(types);

        addButton = new JButton("Add Transaction");

        topPanel.add(new JLabel("Amount:"));
        topPanel.add(amountField);
        topPanel.add(new JLabel("Category:"));
        topPanel.add(categoryBox);
        topPanel.add(new JLabel("Type:"));
        topPanel.add(typeBox);
        topPanel.add(new JLabel(""));
        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);

        // ---------- CENTER PANEL ----------
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Amount", "Category", "Type", "Date"});

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ---------- BOTTOM PANEL ----------
        JPanel bottomPanel = new JPanel(new GridLayout(3,1));

        incomeLabel = new JLabel("Income: 0");
        expenseLabel = new JLabel("Expense: 0");
        balanceLabel = new JLabel("Balance: 0");

        bottomPanel.add(incomeLabel);
        bottomPanel.add(expenseLabel);
        bottomPanel.add(balanceLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // ---------- BUTTON ACTION ----------
        addButton.addActionListener(e -> addTransaction());

        // Load existing data
        loadFromFile();

        setVisible(true);
    }

    private void addTransaction() {
        try {
            String amountText = amountField.getText();
            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter amount");
                return;
            }

            double amount = Double.parseDouble(amountText);
            String category = (String) categoryBox.getSelectedItem();
            String type = (String) typeBox.getSelectedItem();
            String date = java.time.LocalDate.now().toString();

            Transaction t = new Transaction(amount, category, type, date);
            transactions.add(t);

            model.addRow(new Object[]{amount, category, type, date});

            updateSummary();
            saveToFile();

            amountField.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void updateSummary() {
        double income = 0;
        double expense = 0;

        for (Transaction t : transactions) {
            if (t.type.equals("Income")) {
                income += t.amount;
            } else {
                expense += t.amount;
            }
        }

        double balance = income - expense;

        incomeLabel.setText("Income: " + income);
        expenseLabel.setText("Expense: " + expense);
        balanceLabel.setText("Balance: " + balance);
    }

    private void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));

            for (Transaction t : transactions) {
                writer.write(t.type + "," + t.category + "," + t.amount + "," + t.date);
                writer.newLine();
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String type = parts[0];
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                String date = parts[3];

                Transaction t = new Transaction(amount, category, type, date);
                transactions.add(t);

                model.addRow(new Object[]{amount, category, type, date});
            }

            reader.close();
            updateSummary();

        } catch (FileNotFoundException e) {
            // first run → no file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}