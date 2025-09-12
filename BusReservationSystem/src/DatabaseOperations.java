package BusReservationSystem.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import org.sqlite.SQLiteDataSource;

public class DatabaseOperations {

    // Declaring connection And dataSource variables
    private static Connection conn;
    private static SQLiteDataSource ds;

    // Initialize method to initialize the database with all the tables
    public static void dbInit() {
        ds = new SQLiteDataSource();

        try {

            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:BusDB.db");

        } catch (SQLException e) {
            e.printStackTrace();

            System.exit(0);
        }
        try {
            conn = ds.getConnection();

                      Statement statement = conn.createStatement();
             statement.executeUpdate("CREATE TABLE IF NOT EXISTS buses (\n"
                 + "    bus_id INTEGER PRIMARY KEY,\n"
                 + "    bus_number TEXT NOT NULL,\n"
                 + "    bus_type TEXT NOT NULL,\n"
                 + "    total_seats INTEGER NOT NULL,\n"
                 + "    available_seats INTEGER NOT NULL,\n"
                 + "    departure_city TEXT NOT NULL,\n"
                 + "    arrival_city TEXT NOT NULL,\n"
                 + "    departure_time TEXT NOT NULL,\n"
                 + "    arrival_time TEXT NOT NULL,\n"
                 + "    fare INTEGER NOT NULL\n"
                 + ");\n"
                 );
            
             statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (\n"
                 + "    user_id INTEGER PRIMARY KEY,\n"
                 + "    name TEXT NOT NULL,\n"
                 + "    email TEXT NOT NULL UNIQUE,\n"
                 + "    password TEXT NOT NULL,\n"
                 + "    phone_number TEXT NOT NULL,\n"
                 + "    address TEXT NOT NULL\n"
                 + ");"
                  );
             
             statement.executeUpdate("CREATE TABLE IF NOT EXISTS reservations (\n"
                 + "    reservation_id INTEGER PRIMARY KEY,\n"
                 + "    user_id INTEGER NOT NULL,\n"
                 + "    bus_id INTEGER NOT NULL,\n"
                 + "    reserved_seats INTEGER NOT NULL,\n"
                 + "    total_fare INTEGER NOT NULL,\n"
                 + "    reservation_date TEXT NOT NULL,\n"
                 + "    FOREIGN KEY(user_id) REFERENCES users(user_id),\n"
                 + "    FOREIGN KEY(bus_id) REFERENCES buses(bus_id)\n"
                 + ");"
                );

                // Closing statement and connection
                statement.close();
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

/**
 *  ===== Bus Operations =====
 */

 // Method to add Buses into the database

 public static void addBus(int id, String number, String type, int seats, int availableSeats,
                            String depCity, String arrCity, String depTime, String arrTime, 
                            int fare) throws SQLException {
                                conn = ds.getConnection();
                                PreparedStatement ps =conn.prepareStatement("INSERT INTO "
                                                    + "buses(bus_id,bus_number,bus_type,total_seats,available_seats,"
                                                    + "departure_city,arrival_city,departure_time,arrival_time,fare) "
                                                    + "VALUES(?,?,?,?,?,?,?,?,?,?)");
                                ps.setInt(1, id);
                                ps.setString(2, number);
                                ps.setString(3, type);
                                ps.setInt(4, seats);
                                ps.setInt(5, availableSeats);
                                ps.setString(6, depCity);
                                ps.setString(7, arrCity);
                                ps.setString(8, depTime);
                                ps.setString(9, arrTime);
                                ps.setInt(10, fare);

                                ps.executeUpdate();
                                ps.close();
                                conn.close();


                            }
            // Method to fetch Bus data from database
            public static int getBusFare(int id) throws SQLException {
                conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT fare FROM buses WHERE bus_id=" + id + ";");

                ResultSet rs = ps.executeQuery();
                int fare = rs.getInt("fare");

                rs.close();
                ps.close();
                conn.close();
                return fare;
            }

            /**
             * ===== USER OPERATIONS =====
             */

             // Method to add User into the database
            public static void addUser(int id, String name, String email, String password, String phone, String address) throws SQLException