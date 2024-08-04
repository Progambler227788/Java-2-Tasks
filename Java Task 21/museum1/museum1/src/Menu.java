import java.sql.*;
import java.util.Scanner;

public class Menu {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Funn";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        try {
            menu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void menu() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View all artifacts");
            System.out.println("2. View artifacts older than a specified year");
            System.out.println("3. Get total number of artifacts");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAllArtifacts(conn);
                    break;
                case 2:
                    System.out.print("Enter the year: ");
                    int year = scanner.nextInt();
                    viewArtifactsOlderThan(conn, year);
                    break;
                case 3:
                    getTotalNumberOfArtifacts(conn);
                    break;
                case 4:
                    conn.close();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void viewAllArtifacts(Connection conn) throws SQLException {
        String query = "SELECT Id, Funnsted, 'Mynt' as Type FROM Mynt " +
                "UNION " +
                "SELECT Id, Funnsted, 'Vaapen' as Type FROM Vaapen " +
                "UNION " +
                "SELECT Id, Funnsted, 'Smykke' as Type FROM Smykke";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getInt("Id") + " " + rs.getString("Funnsted") + " " + rs.getString("Type"));
        }

        rs.close();
        stmt.close();
    }

    private static void viewArtifactsOlderThan(Connection conn, int year) throws SQLException {
        String query = "SELECT Id, Funnsted, 'Mynt' as Type FROM Mynt WHERE Antatt_årstall < ? " +
                "UNION " +
                "SELECT Id, Funnsted, 'Vaapen' as Type FROM Vaapen WHERE Antatt_årstall < ? " +
                "UNION " +
                "SELECT Id, Funnsted, 'Smykke' as Type FROM Smykke WHERE Antatt_årstall < ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, year);
        ps.setInt(2, year);
        ps.setInt(3, year);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt("Id") + " " + rs.getString("Funnsted") + " " + rs.getString("Type"));
        }

        rs.close();
        ps.close();
    }

    private static void getTotalNumberOfArtifacts(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Mynt UNION SELECT COUNT(*) FROM Vaapen UNION SELECT COUNT(*) FROM Smykke");

        int total = 0;
        while (rs.next()) {
            total += rs.getInt(1);
        }

        System.out.println("Total number of artifacts: " + total);

        rs.close();
        stmt.close();
    }
}
