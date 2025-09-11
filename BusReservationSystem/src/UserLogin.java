package BusReservationSystem.src;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserLogin {
    private JFrame frame;
    private JTextField idField;
    private JPasswordField passwordField;

    public UserLogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setTitle("User Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID: ");
        lblId.setBounds(0, 12, 173, 36);
        frame.getContentPane().add(lblId);
        
        idField = new JTextField();
        idField.setBounds(222, 13, 173, 36);
        frame.getContentPane().add(idField);
        idField.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(0, 79, 173, 36);
        frame.getContentPane().add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(220, 80, 184, 36);
        frame.getContentPane().add(passwordField);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(145, 126, 150, 50);
        btnLogin.addActionListener (new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (DatabaseOperations.validatePassword(idField.getText(), new String(passwordField.getPassword()))) {
                        frame.dispose();
                        Reservation reservation = new Reservation();
                    } else {
                        JOptionPane.showMessageDialog(btnLogin, "ID or Password does not match", "InvalidID/Password", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(btnLogin);

        JButton btnRegister = new JButton("Register ");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Register register = new Register();
            }
        });

        JLabel lblNewUser = new JLabel("New User? ");
        lblNewUser.setBounds(150, 175, 173, 36);
        frame.getContentPane().add(lblNewUser);
        btnRegister.setBounds(145, 208, 150, 50);
        frame.getContentPane().add(btnRegister);
        frame.setVisible(true);
    }
}