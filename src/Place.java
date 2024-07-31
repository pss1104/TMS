import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Place {
    private int id;
    private String name;
    private String state;
    private String city;
    private float cost_child;
    private float cost_adult;
    private String description;

    public Place(int id, String name, String state, String city, float cost_adult, float cost_child, String description) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.city = city;
        this.cost_child = cost_child;
        this.cost_adult = cost_adult;
        this.description = description;
    }

    public Place(String name, float cost_child, float cost_adult) {
        this.name = name;
        this.cost_child = cost_child;
        this.cost_adult = cost_adult;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public float getCostChild() {
        return cost_child;
    }

    public float getCostAdult() {
        return cost_adult;
    }

    public String getDescription() {
        return description;
    }

    public static Place getPlaceDetails(String name) {
        String sql = "SELECT name, cost_adult, cost_child FROM place WHERE name = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Place(rs.getString("name"), rs.getFloat("cost_child"), rs.getFloat("cost_adult"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Handle null in your application logic
    }

    public static Place getPlaceDetailsById(int id) {
        String sql = "SELECT id, name, state, city, cost_adult, cost_child, description FROM Place WHERE id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Place(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("state"),
                    rs.getString("city"),
                    rs.getFloat("cost_adult"),
                    rs.getFloat("cost_child"),
                    rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving place details by ID: " + e.getMessage());
        }
        return null;
    }
}
