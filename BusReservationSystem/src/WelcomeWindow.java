package BusReservationSystem.src;

// Welcome Screen

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

public class WelcomeWindow {
    
    private JFrame frmBusReservationSystem;

    /**
     * Launch the application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WelcomeWindow window = new WelcomeWindow();
                    window.frmBusReservationSystem.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public WelcomeWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame
     */
    private void initialize() {
        frmBusReservationSystem = new JFrame();
        frmBusReservationSystem.getContentPane().setBackground(new Color(54, 70, 82));
        frmBusReservationSystem.setTitle("===== Bus Reservation System =====");
        frmBusReservationSystem.setBounds(100, 100, 440, 202);
        frmBusReservationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmBusReservationSystem.getContentPane().setLayout(null);

        JLabel lblWelcome = new JFrame("Welcome to Bus Reservation System");
        lblWelcome.setForeground(new Color(255, 255, 255));
        lblWelcome.setBounds(0, 0, 440, 35);
        lblWelcome.setFont(new Font("Jua", Font.BOLD, 20));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        frmBusReservationSystem.getContentPane().add(lblWelcome);

        JLabel lblInstruction = new JLabel ("Please Select Login Profile: ");
        lblInstruction.setForeground(new Color(255, 255, 255));
        lblInstruction.setBounds(113, 13, 218, 52);
        frmBusReservationSystem.getContentPane().add(lblInstruction);

        JButton btnAdmin = new JButton("Admin");
        btnAdmin.setBounds(161, 50, 117, 50);
        btnAdmin.addActionListener(new ActionListener() {

            @Override
            public void actionPeformed(ActionEvent e) {
                frmBusReservationSystem.dispose();
                AdminLogin adminLogin = new AdminLogin();

            }
            
        });
        frmBusReservationSystem.getContentPane().add(btnAdmin);

        JButton btnUser = new JButton("User");
        btnUser.setBounds(161, 105, 117, 50);
        btnUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frmBusReservationSystem.dispose();
                UserLogin userLogin = new UserLogin();

            }
        });
        frmBusReservationSystem.getContentPane().add(btnUser);
    }
}