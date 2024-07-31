import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DestinationService {

    public void listDestinations() {
        String sql = "SELECT id, name, state, city, description FROM place";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String state = rs.getString("state");
                String city = rs.getString("city");
                String description = rs.getString("description");
    
                System.out.println("S.No: " + id + ", Name: " + name + ", State: " + state + ", City: " + city + ", Description: " + description);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving destinations: " + e.getMessage());
        }
    }
    
    public void searchDestinations(Scanner scanner) {
        System.out.println("Search by:");
        System.out.println("1. Serial Number (S.No)");
        System.out.println("2. City");
        System.out.println("3. State");
        System.out.print("Enter your choice (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
    
        String sql = "";
    
        switch (choice) {
            case 1:  // Search by Serial Number
                System.out.print("Enter the Serial Number (ID): ");
                int id = scanner.nextInt();
                sql = "SELECT id, name, state, city, cost_adult, cost_child, description FROM Place WHERE id = ?";
                try (Connection conn = DBConnection.connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id") +
                                           ", Name: " + rs.getString("name") +
                                           ", State: " + rs.getString("state") +
                                           ", City: " + rs.getString("city") +
                                           ", Cost for Adults: " + rs.getFloat("cost_adult") +
                                           ", Cost for Children: " + rs.getFloat("cost_child") +
                                           ", Description: " + rs.getString("description"));
                    }
                } catch (SQLException e) {
                    System.out.println("Error searching for destination: " + e.getMessage());
                }
                break;
    
            case 2:  // Search by City
                System.out.print("Enter the City Name: ");
                String city = scanner.nextLine();
                sql = "SELECT id, name, state, city, cost_adult, cost_child, description FROM Place WHERE city LIKE ?";
                try (Connection conn = DBConnection.connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                    pstmt.setString(1, "%" + city + "%");
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id") +
                                           ", Name: " + rs.getString("name") +
                                           ", State: " + rs.getString("state") +
                                           ", City: " + rs.getString("city") +
                                           ", Cost for Adults: " + rs.getFloat("cost_adult") +
                                           ", Cost for Children: " + rs.getFloat("cost_child") +
                                           ", Description: " + rs.getString("description"));
                    }
                } catch (SQLException e) {
                    System.out.println("Error searching for destination: " + e.getMessage());
                }
                break;
    
            case 3:  // Search by State
                System.out.print("Enter the State Name: ");
                String state = scanner.nextLine();
                sql = "SELECT id, name, state, city, cost_adult, cost_child, description FROM Place WHERE state LIKE ?";
                try (Connection conn = DBConnection.connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                    pstmt.setString(1, "%" + state + "%");
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id") +
                                           ", Name: " + rs.getString("name") +
                                           ", State: " + rs.getString("state") +
                                           ", City: " + rs.getString("city") +
                                           ", Cost for Adults: " + rs.getFloat("cost_adult") +
                                           ", Cost for Children: " + rs.getFloat("cost_child") +
                                           ", Description: " + rs.getString("description"));
                    }
                } catch (SQLException e) {
                    System.out.println("Error searching for destination: " + e.getMessage());
                }
                break;
    
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                break;
        }
    }
}
