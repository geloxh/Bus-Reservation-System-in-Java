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

        /**
         * Create the application
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
            frmBusReservationSystem.setTitle("Bus Reservation System");
            frmBusReservationSystem.setBounds(100, 100, 440, 202);
            frmBusReservationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frmBusReservationSystem.getContentPane().setLayout(null);
        }
    }
}