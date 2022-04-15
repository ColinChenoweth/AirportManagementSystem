import java.sql.*;

class amsInterface {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams", "root", "password");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT p.aircraftID FROM Plane p, Schedule s1, Schedule s2"
                                        + "WHERE p.aircraftID = s1.aircraftID AND c.p.aircraftID = s2.aircraftID AND s1.DepartDate = s2.DepartDate");

        while (rs.next()) {
            System.out.println(rs.getString("aircraftID"));
        }

        con.close();
    }
}
