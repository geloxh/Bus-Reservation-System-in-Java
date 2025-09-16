package BusReservationSystem.src;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class AdminPanel {

    private JFrame frame;
    private JTextField busIdField;
    private JTextField busNoField;
    private JTextField busTypeField;
    private JTextField totalSeatsField;
    private JTextField availableSeatsField;
    private JTextField depCityField;
    private JTextField arrCityField;
    private JTextField depTimeField;
    private JTextField arrTimeField;
    private JTextField fareField;
    private JTextField deletionIdField;
    private JTable busTable;
    private JTable userTable;
    private JTable reservationTable;

    AdminPanel() {

        frame = new JFrame();
        frame.setBounds(100, 100, 611, 500);
        frame.setTitle("Admin Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel deletePanel = new JPanel();
        frame.getContentPane().add(deletePanel);
        deletePanel.setLayout(new GridLayout(0, 3, 0, 0));
        
        JComboBox<String> tablesComboBox = new JComboBox<String>();
        tablesComboBox.addItem("Bus");
        tablesComboBox.addItem("User");
        tablesComboBox.addItem("Reservation");
        deletePanel.add(tablesComboBox);

        deletionIdField = new JTextField();
        deletePanel.add(deletionIdField);
        deletionIdField.setColumns(10);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseOperations.delete((String)tablesComboBox.getSelectedItem(), Integer.valueOf(deletionIdField.getText()));
                    JOptionPane.showMessageDialog(deleteButton, "Deleted Successfully!");
                    DatabaseOperations.loadData((DefaultTableModel)busTable.getModel(), "Buses");
                    DatabaseOperations.loadData((DefaultTableModel)reservationTable.getModel(), "Reservations");
                    DatabaseOperations.loadData((DefaultTableModel)userTable.getModel(), "Users");
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(deleteButton, "Invalid ID!");
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(deleteButton, e1.getMessage());
                    e1.printStackTrace();
                }
            }
        });
        deletePanel.add(deleteButton);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane);
        
        JScrollPane busScrollPane = new JScrollPane();
        tabbedPane.addTab("Buses", null, busScrollPane, null);
        
        
        busTable = new JTable();
        busTable.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "ID", "no", "Type", "Total Seats", "Available", "Departure City", "Arrival City","Departure Time", "Arrival Time", "Fare"
            }
        ));
        busScrollPane.setViewportView(busTable);

        JScrollPane usersScrollPane = new JScrollPane();
        tabbedPane.addTab("Users", null, usersScrollPane, null);

        userTable = new JTable();
        userTable.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "ID", "Name", "E-Mail", "Password", "Phone No.", "Address"
            }
        ));
        usersScrollPane.setViewportView(userTable);

        JScrollPane reservationsScrollPane = new JScrollPane();
        tabbedPane.addTab("Reservations", null, reservationsScrollPane, null);

        reservationTable = new JTable();
        reservationTable.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "ID", "Bus ID", "User ID", "Seats"
            }
        ));
        reservationsScrollPane.setViewportView(reservationTable);

        JPanel busPanel = new JPanel();
        tabbedPane.addTab("Add Bus", null, busPanel, null);
        busPanel.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel busIdLabel = new JLabel ("Bus ID: ");
        busPanel.add(busIdLabel);

        busIdField = new JTextField();
        busIdField.setColumns(10);
        busPanel.add(busIdField);

        JLabel busNoLabel = new JLabel("Bus No: ");
        busPanel.add(busNoLabel);

        busNoField = new JTextField();
        busNoField.setColumns(10);
        busPanel.add(busNoField);

        JLabel busTypeLabel = new JLabel("Bus Type: ");
        busPanel.add(busTypeLabel);

        busTypeField = new JTextField();
        busTypeField.setColumns(10);
        busPanel.add(busTypeField);

        JLabel totalSeatsLabel = new JLabel("Total Seats: ");
        busPanel.add(totalSeatsLabel);

        totalSeatsField = new JTextField();
        totalSeatsField.setColumns(10);
        busPanel.add(totalSeatsField);

        JLabel availableSeatsLabel = new JLabel("Available Seats: ");
        busPanel.add(availableSeatsLabel);

        availableSeatsField = new JTextField();
        availableSeatsField.setColumns(10);
        busPanel.add(availableSeatsField);
        
        JLabel departureCityLabel = new JLabel("Departure City: ");
        busPanel.add(departureCityLabel);

        depCityField = new JTextField();
        depCityField.setColumns(10);
        busPanel.add(depCityField);

        JLabel arrivalCityLabel = new JLabel("Arrival City: ");
        busPanel.add(arrivalCityLabel);

        arrCityField = new JTextField();
        arrCityField.setColumns(10);
        busPanel.add(arrCityField);

        JLabel depTimeLabel = new JLabel("Departure Time: ");
        busPanel.add(depTimeLabel);

        depTimeField = new JTextField();
        depTimeField.setColumns(10);
        busPanel.add(depTimeField);

        JLabel arrTimeLabel = new JLabel("Arrival Time: ");
        busPanel.add(arrTimeLabel);

        arrTimeField = new JTextField();
        arrTimeField.setColumns(10);
        busPanel.add(arrTimeField);

        JLabel fareLabel = new JLabel("Fare: ");
        busPanel.add(fareLabel);

        fareField = new JTextField();
        fareField.setColumns(10);
        busPanel.add(fareField);
        
        JButton btnNewButton = new JButton("Add Bus");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseOperations.addBus(Integer.valueOf(busIdField.getText()), 
                    busNoField.getText(), busTypeField.getText(), 
                    Integer.valueOf(totalSeatsField.getText()), 
                    Integer.valueOf(availableSeatsField.getText()),
                    depCityField.getText(),
                    arrCityField.getText(),
                    depTimeField.getText(),
                    arrTimeField.getText(),
                    Integer.valueOf(fareField.getText()));

                    DatabaseOperations.loadData((DefaultTableModel)busTable.getModel(), "buses");
                    JOptionPane.showMessageDialog(btnNewButton, "Bus Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(btnNewButton, "Please Enter Only Numeric Value In ID \nSeats \nFare", "Invalid Value",
                    JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(btnNewButton, "Can't add the bus\n" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });

        JLabel fareLabel_1 = new JLabel("");
        busPanel.add(fareLabel_1);
        busPanel.add(btnNewButton);
        frame.setVisible(true);

        try {
            DatabaseOperations.loadData((DefaultTableModel)busTable.getModel(), "Buses");
            DatabaseOperations.loadData((DefaultTableModel)reservationTable.getModel(), "Reservations");
            DatabaseOperations.loadData((DefaultTableModel)userTable.getModel(), "Users");
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(frame, "Failed to load data from the database: " + e1.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }

    }
}