package BusReservationSystem.src;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Reservation {

    private JFrame frame;
    private JTextField idTextField;
    private JTextField dateField;

    public Reservation() {
        
        initialize();

    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 611, 386);
        frame.setTitle("Reservation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 2, 5, 5));

        JLabel lblId = new JLabel("ID: ");
        frame.getContentPane().add(lblId);

        idTextField = new JTextField();
        frame.getContentPane().add(idTextField);
        idTextField.setColumns(10);

        JLabel lblUserId = new JLabel("User ID: ");
        frame.getContentPane().add(lblUserId);

        JComboBox<String> uidComboBox = new JComboBox<String>();
        frame.getContentPane().add(uidComboBox);

        JLabel lblBusId = new JLabel("Bus ID/Route: ");
        frame.getContentPane().add(lblBusId);

        JComboBox<String> bidComboBox = new JComboBox<String>();
        frame.getContentPane().add(bidComboBox);

        JLabel lblSeatCount = new JLabel("No. of Seats to Reserve:");
        frame.getContentPane().add(lblSeatCount);
        
        JSpinner seatSpinner = new JSpinner();
        seatSpinner.setModel(new SpinnerNumberModel(0, 0, 5, 1));
        
        frame.getContentPane().add(seatSpinner);
        
        JLabel lblTotalFare = new JLabel("Total Fare:");
        frame.getContentPane().add(lblTotalFare);

        JTextArea totalFareArea = new JTextArea();
        totalFareArea.setText("Php.0");
        totalFareArea.setEditable(false);
        seatSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

                String id = (String)bidComboBox.getSelectedItem();
                id = id.substring(0, id.indexOf("|"));
                try {

                    int totalFare = DatabaseOperations.getBusFare(Integer.valueOf(id)) * (Integer)seatSpinner.getValue();
                    totalFareArea.setText("Php." + totalFare);

                } catch (NumberFormatException e1) {

                    e1.printStackTrace();

                } catch (SQLException e1) {

                    e1.printStackTrace();

                }
            }
        });

        frame.getContentPane().add(totalFareArea);

        JLabel lblDate = new JLabel("Date: ");
        frame.getContentPane().add(lblDate);

        dateField = new JTextField();
        dateField.setColumns(10);
        frame.getContentPane().add(dateField);

        JButton btnCheckDetails = new JButton("Check Reservation Details");
        btnCheckDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    String[] data = DatabaseOperations.getReservation(Integer.valueOf(idTextField.getText()));
                    Ticket t = new Ticket(data);

                } catch (NumberFormatException e1) {
                    
                    JOptionPane.showMessageDialog(btnCheckDetails, "Invalid ID", "Please Enter A Valid ID", JOptionPane.ERROR_MESSAGE);
                    
                    e1.printStackTrace();

                } catch (SQLException e1) {
                    
                    e1.printStackTrace();

                }
            }
        });

        frame.getContentPane().add(btnCheckDetails);

        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uid = (String)uiComboBox.getSelectedItem();
                uid = uid.substring(0, uid.indexOf("|"));
                String bid = (String)bidComboBox.getSelectedItem();
                bid = bid.substring(0, bid.indexOf("|"));
                try {
                    if((Integer)seatSpinner.getValue()  > 0) {

                        DatabaseOperations.addReservation(Integer.valueOf(idTextField.getText()),
                                                                            Integer.valueOf(uid),
                                                                            Integer.valueOf(bid),
                                                            Integer.valueOf(totalFareArea.getText().subString(3)),
                                                            (Integer)seatSpinner.getValue(),
                                                            dateField.getText());
                        JOptionPane.showMessageDialog(btnBook, "Booked Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    } else {

                        JOptionPane.showMessageDialog(btnBook, "At least select one seat", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    }
                } catch (NumberFormatException e1) {

                    JOptionPane.showMessageDialog(btnBook, "Please enter a Valid ID", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    e1.printStackTrace();

                } catch (SQLException e1) {

                    e1.printStackTrace();

                }
            }
        });

        frame.getContentPane().add(btnBook);
        frame.setVisible(true);

        try {

            DatabaseOperations.updateCombox("Users", uidComboBox);
            DatabaseOperations.updateCombox("Buses", bidComboBox);
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }
}