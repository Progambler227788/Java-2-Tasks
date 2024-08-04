import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class CreateDatabase {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "Funn";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        try {
            if (!databaseExists(DB_NAME)) {
                createDatabaseAndTables("funn.sql");
                importData("funn.txt");
            } else {
                System.out.println("Database already exists.");
                if (!dataExists()) {
                    importData("funn.txt");
                } else {
                    System.out.println("Data is already inserted in the database.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean databaseExists(String dbName) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ResultSet rs = conn.getMetaData().getCatalogs();

        while (rs.next()) {
            if (rs.getString(1).equalsIgnoreCase(dbName)) {
                rs.close();
                conn.close();
                return true;
            }
        }

        rs.close();
        conn.close();
        return false;
    }

    private static void createDatabaseAndTables(String sqlFilePath) throws SQLException, IOException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();

        String sqlScript = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
        String[] queries = sqlScript.split(";");

        for (String query : queries) {
            if (!query.trim().isEmpty()) {
                stmt.execute(query);
            }
        }

        stmt.close();
        conn.close();
    }

    private static boolean dataExists() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Person");
        rs.next();
        boolean exists = rs.getInt(1) > 0;

        rs.close();
        stmt.close();
        conn.close();

        return exists;
    }

    public static void importData(String fileName) throws SQLException, IOException {
        Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String line;
        int section = 0;

        while ((line = br.readLine()) != null) {
            if (line.equals("Personer:")) {
                section = 1;
                br.readLine(); // skip number of persons
            } else if (line.equals("Museer:")) {
                section = 2;
                br.readLine(); // skip number of museums
            } else if (line.equals("Funn:")) {
                section = 3;
                br.readLine(); // skip finding ID
            } else {
                switch (section) {
                    case 1:
                        insertPerson(conn, br, line);
                        break;
                    case 2:
                        insertMuseum(conn, br, line);
                        break;
                    case 3:
                        insertFinding(conn, br, line);
                        break;
                }
            }
        }

        br.close();
        conn.close();
    }

    private static void insertPerson(Connection conn, BufferedReader br, String id) throws IOException, SQLException {
        String navn = br.readLine();
        String tlf = br.readLine();
        String epost = br.readLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Person (Id, Navn, Tlf, E_post) VALUES (?, ?, ?, ?)");
        ps.setInt(1, Integer.parseInt(id));
        ps.setString(2, navn);
        ps.setString(3, tlf);
        ps.setString(4, epost);
        ps.executeUpdate();
        ps.close();
    }

    private static void insertMuseum(Connection conn, BufferedReader br, String id) throws IOException, SQLException {
        String navn = br.readLine();
        String sted = br.readLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Museum (Id, Navn, Sted) VALUES (?, ?, ?)");
        ps.setInt(1, Integer.parseInt(id));
        ps.setString(2, navn);
        ps.setString(3, sted);
        ps.executeUpdate();
        ps.close();
    }

    private static void insertFinding(Connection conn, BufferedReader br, String id) throws IOException, SQLException {
        String[] coords = br.readLine().split(", ");
        String finnerId = br.readLine();
        String funntidspunkt = br.readLine();
        String antattArstall = br.readLine();
        String museumId = br.readLine();
        String type = br.readLine();

        if (type.equals("Mynt")) {
            String diameter = br.readLine();
            String metall = br.readLine();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Mynt (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Diameter, Metall) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, String.join(", ", coords));
            ps.setInt(3, Integer.parseInt(finnerId));
            ps.setDate(4, Date.valueOf(funntidspunkt));
            ps.setInt(5, Integer.parseInt(antattArstall));
            ps.setObject(6, museumId.isEmpty() ? null : Integer.parseInt(museumId), java.sql.Types.INTEGER);
            ps.setInt(7, Integer.parseInt(diameter));
            ps.setString(8, metall);
            ps.executeUpdate();
            ps.close();
        } else if (type.equals("Våpen")) {
            String vaapenType = br.readLine();
            String materiale = br.readLine();
            String vekt = br.readLine();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Vaapen (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Materiale, Vekt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, String.join(", ", coords));
            ps.setInt(3, Integer.parseInt(finnerId));
            ps.setDate(4, Date.valueOf(funntidspunkt));
            ps.setInt(5, Integer.parseInt(antattArstall));
            ps.setObject(6, museumId.isEmpty() ? null : Integer.parseInt(museumId), java.sql.Types.INTEGER);
            ps.setString(7, vaapenType);
            ps.setString(8, materiale);
            ps.setInt(9, Integer.parseInt(vekt));
            ps.executeUpdate();
            ps.close();
        } else if (type.equals("Smykke")) {
            String smykkeType = br.readLine();
            String verdiEstimat = br.readLine();
            String filnavn = br.readLine();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Smykke (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Verdiestimat, filnavn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, String.join(", ", coords));
            ps.setInt(3, Integer.parseInt(finnerId));
            ps.setDate(4, Date.valueOf(funntidspunkt));
            ps.setInt(5, Integer.parseInt(antattArstall));
            ps.setObject(6, museumId.isEmpty() ? null : Integer.parseInt(museumId), java.sql.Types.INTEGER);
            ps.setString(7, smykkeType);
            ps.setInt(8, Integer.parseInt(verdiEstimat));
            ps.setString(9, filnavn);
            ps.executeUpdate();
            ps.close();
        }

        // Skip the separator
        br.readLine();
    }
}
