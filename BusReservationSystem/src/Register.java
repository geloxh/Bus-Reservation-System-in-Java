package BusReservationSystem.src;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Register {
    private JFrame frame;
    private JTextField userIdField;
    private JLabel lblName;
    private JTextField nameField;
    private JLabel lblEmail;
    private JTextField emailField;
    private JLabel lblPassword;
    private JPasswordField passwordField;
    private JLabel lblPhone;
    private JTextField phoneNumField;
    private JLabel lblAddress;
    private JTextField addressField;
    private JButton btnBack;
    private JButton btnRegister;

    public Register() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 598, 372);
        frame.setTitle("User Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 2, 5, 5));

        JLabel lblId = new JLabel("User ID: ");
        frame.getContentPane().add(lblId);

        userIdField = new JTextField();
        frame.getContentPane().add(userIdField);
        userIdField.setColumns(10);

        lblName = new JLabel("Name: ");
        frame.getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setColumns(10);
        frame.getContentPane().add(nameField);

        lblEmail = new JLabel("Email: ");
        frame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setColumns(10);
        frame.getContentPane().add(emailField);

        lblPassword = new JLabel("Password: ");
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        frame.getContentPane().add(passwordField);

        lblPhone = new JLabel("Phone: ");
        frame.getContentPane().add(lblPhone);

        phoneNumField = new JTextField();
        phoneNumField.setColumns(10);
        frame.getContentPane().add(phoneNumField);

        lblAddress = new JLabel("Address: ");
        frame.getContentPane().add(lblAddress);

        addressField = new JTextField();
        addressField.setColumns(10);
        frame.getContentPane().add(addressField);

        btnBack = new JButton("Back ");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UserLogin userLogin = new UserLogin();
            }
        });
        frame.getContentPane().add(btnBack);

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseOperations.addUser(Integer.valueOf(userIdField.getText()),
                                                nameField.getText(),
                                                emailField.getText(),
                                                phoneNumField.getText(),
                                                new String(passwordField.getPassword()),
                                                addressField.getText());
                    JOptionPane.showMessageDialog(btnRegister, "User Added Successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(btnRegister, "Can't Add User\n" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(btnRegister);
        frame.setVisible(true);
    }
}