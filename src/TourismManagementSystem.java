import java.io.IOException;
import java.util.Scanner;

public class TourismManagementSystem {

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        DestinationService service = new DestinationService();
        TicketBooking bookingService = new TicketBooking();

        while (true) {
            System.out.println("Choose an option: \n1. List Destinations \n2. Search Destinations \n3. Make a Booking \n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
            switch (choice) {
                case 1:
                    service.listDestinations();
                    break;
                case 2:
                    service.searchDestinations(scanner);
                    break;
                case 3:
                    handleBooking(scanner, bookingService);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();  // Close scanner before exiting
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void handleBooking(Scanner scanner, TicketBooking bookingService) {
        System.out.println("Choose booking method:");
        System.out.println("1. By Place ID");
        System.out.println("2. By Place Name");
        System.out.print("Enter your choice (1/2): ");
        int method = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        Place place = null;
        if (method == 1) {
            System.out.print("Enter the Place ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            place = Place.getPlaceDetailsById(id);
        } else if (method == 2) {
            System.out.print("Enter the Place Name: ");
            String placeName = scanner.nextLine();
            place = Place.getPlaceDetails(placeName);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        if (place == null) {
            System.out.println("Place details not found. Please try another place.");
            return;
        }

        System.out.println("Enter tourist's name:");
        String name = scanner.nextLine();
        System.out.println("Enter number of adults:");
        int adults = scanner.nextInt();
        System.out.println("Enter number of children:");
        int children = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        Tourist tourist = new Tourist(name, children, adults);
        int numberOfTickets = adults + children;
        float estimatedCost = TicketBooking.calculateTotalCost(place, tourist);
        System.out.println("Estimated Total Cost: " + estimatedCost);
        System.out.println("Confirm booking? (yes/no)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            bookingService.bookTickets(tourist, place, numberOfTickets);
        } else {
            System.out.println("Booking cancelled.");
        }
    }
}
