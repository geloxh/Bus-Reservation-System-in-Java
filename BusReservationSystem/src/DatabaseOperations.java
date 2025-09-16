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
    //  declaring connection and dataSource variables
    private static Connection conn;
    private static SQLiteDataSource ds;
    
    //  initialize method to initialize the database with all the tables
    public static void dbInit() {
        ds = new SQLiteDataSource();
        
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:BusReservationSystem/lib/Bus.db");
        } catch ( Exception e ) {
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
           
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
              }
        
        }
    

    }

    /*
     * ----------------------------------- Bus Operations--------------------------------------------------------
     * 
     */
    //  Method to add Buses into the database
    public static void addBus(int id, String number, String type, int seats, int availableSeats,
                                String depCity, String arrCity, String depTime, String arrTime, int fare) throws SQLException {
        conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO "
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
    
    //  Method to fetch Bus data from the database
    public static int getBusFare(int id) throws SQLException {
        conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT fare FROM buses WHERE bus_id ="+id+";");
        
        ResultSet rs = ps.executeQuery();
        int fare = 0;
        if (rs.next()) {
            fare =rs.getInt("fare");
        }

        rs.close();
        ps.close();
        conn.close();
        return fare;

        
    }
    
    /*
     * ----------------------------------- User Operations--------------------------------------------------------
     */
    
    //  Method to add User into the database
    public static void addUser(int id, String name, String email, String password, String phone, String address) throws SQLException {
        conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO "
                                                    + "users(user_id,name,email,password,phone_number,address)"
                                                    + "VALUES(?,?,?,?,?,?)");
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, email);
        ps.setString(5, password);
        ps.setString(4, phone);
        ps.setString(6, address);
        
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    
    //  Method to fetch Email and Password from the database and validate it 
    public static boolean validatePassword(String id, String password) throws SQLException {
        conn = ds.getConnection();
        String sql = "SELECT user_id,password FROM users WHERE user_id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id );
        ResultSet rs =  ps.executeQuery();
        
        
        if (rs.next() &&id.equals(rs.getString("user_id"))  && password.equals(rs.getString("password"))) {
            rs.close();
            ps.close();
            conn.close();
            return true;
        }
        
        rs.close();
        ps.close();
        conn.close();
        return false;
    }
    

    /*
     * ----------------------------------- Reservations Operations--------------------------------------------------------
     */
    //  Method to add Reservation into the database
    public static void addReservation(int id, int userID, int busID, int reservedSeats, int totalFare, String reservationDate)throws SQLException {
        conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO "
                                                    + "reservations(reservation_id,user_id,bus_id,reserved_seats,total_fare,reservation_date)"
                                                    + "VALUES(?,?,?,?,?,?);");
        ps.setInt(1, id);
        ps.setInt(2, userID);
        ps.setInt(3, busID);
        ps.setInt(5, reservedSeats);
        ps.setInt(4, totalFare);
        ps.setString(6, reservationDate);
        
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    //  Method to fetch Reservation data from the database
    public static String[] getReservation(int id) throws SQLException {
        conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT reservation_id,users.user_id,buses.bus_id,\n"
                + "reserved_seats,total_fare,\n"
                + "reservation_date,name,email,phone_number,\n"
                + "address,bus_number,bus_type,total_seats,\n"
                + "available_seats,departure_city,arrival_city,\n"
                + "departure_time,arrival_time,fare\n"
                + "\n"
                + "  from reservations \n"
                + "INNER join buses on\n"
                + "reservations.bus_id = buses.bus_id\n"
                + "INNER join users on\n"
                + "reservations.user_id = users.user_id "
                + "WHERE reservation_id = ? ;");
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery(); 
        String[] data = new String[18];
        
        if (rs.next()) {
            for (int j = 0; j < data.length; j++) {
                data[j] = rs.getString(j+1);
            }
        }
        
        ps.close();
        conn.close();
        return data;
        
    }
    
    //  Method to update the comboBoxes with data from the database
    public static void updateCombox(String table, JComboBox<String> cbx) throws SQLException {
        cbx.removeAllItems();
        conn = ds.getConnection();
        String sql = "SELECT * FROM " + table + ";";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(table.equalsIgnoreCase("users")) {
            while(rs.next()) {
                cbx.addItem(rs.getString("user_id") +"|"+ rs.getString("name"));
            }
        }
        else {
            while(rs.next()) {
                cbx.addItem(rs.getString("bus_id") +"|"+ rs.getString("departure_city")+">"+rs.getString("arrival_city"));
            }
        }
        rs.close();
        ps.close();
        conn.close();

    }

    //  Method to delete any specific record from a specific table 
    public static void delete(String table, int id) throws SQLException {
        conn = ds.getConnection();
        String tableName = getTableName(table);
        String idColumn;
        if (tableName.equalsIgnoreCase("buses")) {
            idColumn = "bus_id";
        } else if (tableName.equalsIgnoreCase("users")) {
            idColumn = "user_id";
        } else if (tableName.equalsIgnoreCase("reservations")) {
            idColumn = "reservation_id";
        } else {
            conn.close();
            throw new SQLException("Invalid table name: " + table);
        }
        String sql = "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        
        ps.close();
        conn.close();
    }

    private static String getTableName(String table) {
        return table;
    }
    
    //  Method to Load Data from the database into the table
    public static void loadData(DefaultTableModel model, String table) throws SQLException {
        model.setRowCount(0);
        conn = ds.getConnection();
        String sql = "SELECT * FROM " + table + ";";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Object[] row = new Object[model.getColumnCount()]; 
        while (rs.next()) {
            for (int i = 0; i < row.length; i++) {
                row[i] = rs.getObject(i+1);
            }
            model.addRow(row);

        }
        ps.close();
        conn.close();
    }
}