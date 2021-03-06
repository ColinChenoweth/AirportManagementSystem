import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class amsInterface {
    public static void main(String[] args) throws Exception {
        // Ensure that lines 12, 122, 124, 126, 128, 130, and 133 are all correct before running
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        //make sure the platform (currently mysql), server (currently localhost), port (currently 3306), and database (currently ams) are all correct
        //also make sure you are using the correct user and password (currently root and password)
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams", "root", "password");
        Scanner in = new Scanner(System.in);
        
        setup();

        Boolean Exit = false;
        while(!Exit){
            System.out.println("Enter an option number below.");
            System.out.println("1: Input SQL code");
            System.out.println("2: Run a premade query");
            System.out.println("3: Exit");
            System.out.print("Option Selection: ");
            
            int opt = Integer.parseInt(in.nextLine());
            
            System.out.println();

            if(opt == 1){
                System.out.println("Type \"END\" on its own line when done writing your query.");
                String query = "";
                String nl = in.nextLine();
                while(!nl.toLowerCase().equals("end")){
                    query += nl + " ";
                    nl = in.nextLine();
                }
                try{
                    Statement st = con.createStatement();

                    if(query.substring(0,6).toLowerCase().equals("select")){
                        ResultSet rs = st.executeQuery(query);

                        ArrayList<String> columns = new ArrayList<String>();
                        String temp = query;
                        while(!temp.toLowerCase().endsWith("from")){
                            temp = temp.substring(0, temp.length()-1);
                        }

                        temp = temp.substring(0, temp.length()-5);

                        while(!temp.toLowerCase().endsWith("select ")){
                            String col = "";
                            while(!temp.endsWith(", ") && !temp.toLowerCase().endsWith("select ")){
                                col = temp.substring(temp.length() - 1) + col;
                                temp = temp.substring(0, temp.length() - 1);
                            }
                            if(temp.endsWith(", "))
                                temp = temp.substring(0, temp.length()-2);
                            columns.add(col);
                        }

                        while (rs.next()) {
                            for (int x = columns.size() - 1; x > 0; x--){
                                System.out.print(rs.getString(columns.get(x)) + ", ");
                            }
                            System.out.println(rs.getString(columns.get(0)));
                        }
                    }
                    else{
                        st.executeUpdate(query);
                    }

                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            else if(opt == 2){
                System.out.println("Enter an query number below.");
                System.out.println("1: List people who will fly into BWI from bigger airport on April 28, 2023.");
                System.out.println("2: List people who will fly first class more than three times with the same company.");
                System.out.println("3: Rank the airline companies in terms of sales of first class tickets in ascending order.");
                System.out.println("4: List the customers who will fly on their birthdays.");
                System.out.println("5: List the customers who will never fly first class.");
                System.out.println("6: Go back.");
                System.out.print("Query Selection: ");

                int query = Integer.parseInt(in.nextLine());

                if(query == 1)
                    dateQuery(con);
                else if(query == 2)
                    multipleFirstQuery(con);
                else if(query == 3)
                    salesQuery(con);
                else if(query == 4)
                    birthdayQuery(con);
                else if(query == 5)
                    neverFirstQuery(con);
                else if(query == 6){}
                else{
                    System.out.println("Invalid option");
                    System.out.println();
                }
                System.out.println();
            }
            else if(opt == 3){
                Exit = true;
            }
            else{
                System.out.println("Invalid option");
                System.out.println();
                System.out.println();
            }
        }
    }

    private static void setup() throws Exception{
        // Opens command prompt to run two files to makes sure tables are made correctly and the default data is added
        Runtime.getRuntime().exec("cmd /c start cmd.exe /K "
                                // File location for mysql.exe
                                + "\"\"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe\""
                                // Ensure server, user, password are all correct
                                + " -h localhost -u root -ppassword ams --local-infile=1 < "
                                // File location of ams_setup.sql
                                + "\"C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\ams_Setup.sql\" "
                                // File location for mysql.exe again
                                + "&& \"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe\""
                                // Ensure server, user, and password are all correct
                                + " -h localhost -u root -ppassword ams --local-infile=1 < "
                                // File location of inputData.sql
                                // Remember to edit the location of the data files in inputData.sql
                                + "\"C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\inputData.sql\" "
                                + "&& exit\"");
    }

    private static void dateQuery(Connection con) throws Exception{
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

    private static void multipleFirstQuery(Connection con) throws Exception{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT c.CustName, p.AirlineName"
                                        + " FROM Customer c, Seat s, Plane p,  Ticket t"
                                        + " WHERE c.PassportNum = t.PassportNum AND"
                                                + " t.SeatNum = s.SeatNum AND"
                                                + " t.AircraftID = s.AircraftID AND"
                                                + " s.AircraftID = p.AircraftID AND"
                                                + " s.Class = \"First\""
                                        + " GROUP BY c.CustName, p.AirlineName"
                                        + " HAVING count(c.PassportNum) > 3;");

        while (rs.next()) {
            System.out.print(rs.getString("CustName") + ", ");
            System.out.println(rs.getString("AirlineName"));
        }

    }

    private static void salesQuery(Connection con) throws Exception{
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

    private static void birthdayQuery(Connection con) throws Exception{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT Distinct c.CustName"
                                        + " FROM Customer c, Ticket t, Schedule s"
                                        + " WHERE c.PassportNum = t.PassportNum AND"
                                                + " t.FlightNum = s.FlightNum AND"
                                                + " floor(c.DOB / 10000) = floor(s.departDate / 10000);");

        while (rs.next()) {
            System.out.println(rs.getString("CustName"));        }
    }

    private static void neverFirstQuery(Connection con) throws Exception{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT Distinct c.CustName"
                                        + " FROM Customer c, Ticket t, Seat s, Plane p"
                                        + " WHERE c.PassportNum = t.PassportNum AND"
                                                + " t.SeatNum = s.SeatNum AND"
                                                + " t.AircraftID = p.AircraftID AND"
                                                + " s.AircraftID = p.AircraftID AND"
                                                + " s.Class <> \"First\" AND"
                                                + " c.CustName NOT IN (SELECT c.CustName"
                                                                    + " FROM Customer c, Ticket t, Seat s, Plane p"
                                                                    + " WHERE t.seatNum = s.SeatNum AND"
                                                                            + " c.PassportNum = t.PassportNum AND"
                                                                            + " t.AircraftID = p.AircraftID AND"
                                                                            + " s.AircraftID = p.AircraftID AND"
                                                                            + " s.Class = \"First\" );");

        while (rs.next()) {
            System.out.println(rs.getString("CustName"));        }
    }
}
