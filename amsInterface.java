import java.sql.*;

class amsInterface {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams", "root", "password");
        // dateQuery(con);
        // multipleFirstQuery(con);
        // salesQuery(con);
        // birthdayQuery(con);
        
        // Statement st = con.createStatement();
        // ResultSet rs = st.executeQuery("SELECT * FROM seat s "
        //                                 + "WHERE s.Class = 'Economy'");

        // while (rs.next()) {
        //     System.out.print(rs.getString("AircraftID") + ",");
        //     System.out.print(rs.getString("SeatNum") + ",");
        //     System.out.println(rs.getString("Class")+"");
        // }

        con.close();
    }

    public static void dateQuery(Connection con) throws Exception{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT c.CustName, c.PassportNum"
                                        + " FROM Customer c, Ticket t, Schedule s"
                                        + " WHERE c.PassportNum = t.PassportNum AND"
                                                + " t.FlightNum = s.FlightNum AND"
                                                + " s.DepartDate = \"4282023\" AND"
                                                + " s.DestAirport = \"BWI\" AND"
                                                + " s.OriginAirport IN (SELECT a2.Abbreviation"
                                                                        + " FROM Airport a1, Airport a2"
                                                                        + " WHERE a1.Abbreviation = \"BWI\" AND"
                                                                                + " a2.Size > a1.Size);");

        while (rs.next()) {
            System.out.print(rs.getString("CustName") + ", ");
            System.out.println(rs.getString("PassportNum"));
        }
    }

    public static void multipleFirstQuery(Connection con) throws Exception{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT c.CustName, p.AirlineName"
                                        + " FROM Customer c, Seat s, Plane p,  Ticket t"
                                        + " WHERE c.PassportNum = t.PassportNum AND"
                                                + " t.SeatNum = s.SeatNum AND"
                                                + " t.AircraftID = s.AircraftID AND"
                                                + " s.AircraftID = p.AircraftID AND"
                                                + " s.Class = \"First\""
                                        + " GROUP BY c.CustName, p.AirlineName"
                                        + " HAVING count(c.PassportNum) > 2;");

        while (rs.next()) {
            System.out.print(rs.getString("CustName") + ", ");
            System.out.println(rs.getString("AirlineName"));
        }

    }

    public static void salesQuery(Connection con) throws Exception{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT p.AirlineName, count(s.SeatNum) as numSold"
                                        + " FROM Ticket t, Seat s, Plane P"
                                        + " WHERE p.AircraftID = s.AircraftID AND"
                                                + " t.AircraftID = s.AircraftID AND"
                                                + " t.SeatNum = s.SeatNum AND"
                                                + " s.Class = \"First\""
                                        + " GROUP BY p.AirlineName"
                                        + " ORDER BY count(s.SeatNum);");

        while (rs.next()) {
            System.out.print(rs.getString("AirlineName") + ", ");
            System.out.println(rs.getString("numSold"));
        }
    }

    public static void birthdayQuery(Connection con) throws Exception{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT Distinct c.CustName"
                                        + " FROM Customer c, Ticket t, Schedule s"
                                        + " WHERE c.PassportNum = t.PassportNum AND"
                                                + " t.FlightNum = s.FlightNum AND"
                                                + " floor(c.DOB / 10000) = floor(s.departDate / 10000);");

        while (rs.next()) {
            System.out.println(rs.getString("CustName"));        }
    }

}
