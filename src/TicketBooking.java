import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketBooking {

    public static float calculateTotalCost(Place place, Tourist tourist) {
        return (tourist.getChildren() * place.getCostChild()) + (tourist.getAdults() * place.getCostAdult());
    }

    public void bookTickets(Tourist tourist, Place place, int numberOfTickets) {
        float totalCost = calculateTotalCost(place, tourist);
        System.out.println("Booking successful for " + tourist.getName() + " at " + place.getName());
        System.out.println("Total cost: " + totalCost);

        saveBookingInDatabase(tourist, place, numberOfTickets, totalCost);
    }

    private void saveBookingInDatabase(Tourist tourist, Place place, int numberOfTickets, float totalCost) {
        String sql = "INSERT INTO Receipts (tourist_name, place_name, number_of_adults, number_of_children, total_cost) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tourist.getName());
            pstmt.setString(2, place.getName());
            pstmt.setInt(3, tourist.getAdults());
            pstmt.setInt(4, tourist.getChildren());
            pstmt.setFloat(5, totalCost);
            pstmt.executeUpdate();
            System.out.println("Booking receipt saved.");
        } catch (SQLException e) {
            System.out.println("Error saving booking receipt: " + e.getMessage());
        }
    }
}
