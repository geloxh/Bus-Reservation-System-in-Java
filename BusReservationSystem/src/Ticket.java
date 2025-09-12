package BusReservationSystem.src;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ticket {
    private JFrame frame;

    public Ticket(String[] data) {
        frame = new JFrame();
        frame.setBounds(100, 100, 611, 386);
        frame.setTitle("Ticket Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 2, 5, 6));

        String[] colNames = {
            "Reservation ID: ",
            "User ID: ",
            "Bus ID: ",
            "Reserved Seats: ",
            "Total Fare: ",
            "Reservation Date: ",
            "Name: ",
            "Email: ",
            "Phone: ",
            "Address: ",
            "Bus No.: ",
            "Bus Type: ",
            "Total Seats: ",
            " Available Seats: ",
            "Departure City: ",
            "Arrival City: ",
            "Departure Time: ",
            "Arrival Time: ",
            "Fare: "
        };

        for (int i = 0; i < data.length; i++) {
            frame.getContentPane().add(new JLabel(colNames[i] + data[i]));
        }

        frame.setVisible(true);
    }
}