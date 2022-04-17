import java.sql.*;

class amsInterface {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams", "root", "password");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM seat s "
                                        + "WHERE s.Class = 'Economy'");

        while (rs.next()) {
            System.out.print(rs.getString("AircraftID") + ",");
            System.out.print(rs.getString("SeatNum") + ",");
            System.out.println(rs.getString("Class")+"");
        }

        con.close();
    }
}
