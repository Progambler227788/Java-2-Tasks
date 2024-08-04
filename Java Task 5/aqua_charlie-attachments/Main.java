import java.util.ArrayList;// Import ArrayList class for managing lists of objects
import java.util.Scanner; // Import Scanner class for user input


public class Main {
    private static final Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input
    private static final PointsManager manager = new PointsManager(); // Create a new instance of PointsManager

    public static void main(String[] args) { //This is my Main method
        QuizEvent.initializeEvents(); // Initialise the predefined set of events
        manager.readTeamDataFromFile(); // Load teams from file

        int choice;
        do { // Start a do-while loop for menu display and user interaction
            // Print title
            System.out.println("***********************************");
            System.out.println("            Main Menu             ");
            System.out.println("***********************************");
            System.out.println();
            // Display the menu options
            System.out.println("1. Add Team");
            System.out.println("2. Record Points");
            System.out.println("3. Generate Leaderboard Table");
            System.out.println("4. Book Quiz Event for Team");
            System.out.println("5. List Available Quiz Events");
            System.out.println("6. Generate Booking Report");
            System.out.println("7. Delete a Team");
            System.out.println("8. Delete a Team Booking");
            System.out.println("9. Exit");
            System.out.println(); // Blank line for separation
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(scanner.nextLine()); // Read user's choice as string and parse it to integer
            System.out.println(); // Blank line for separation between user input and the next output
            switch (choice) {
                case 1:
                    addTeam(); // Call the addTeam method to add a new team
                    break;
                case 2:
                    recordPoints(); // Call the recordPoints method to record points for a team
                    break;
                case 3:
                    manager.generateLeaderboardTable(); // Call the generateLeaderboardTable method in PointsManager to display the league table
                    break;
                case 4:
                    bookEventForTeam(); // Call the bookEventForTeam method to book a quiz event for a team
                    break;
                case 5:
                    listAvailableEvents(); // Call the listAvailableEvents method to list available quiz events
                    break;
                case 6:
                    manager.generateBookingReport(); // Call the generateBookingReport method in PointsManager to display the booking report
                    break;
                case 7:
                    listAndDeleteTeam(); // Call delete team function
                    break;
                case 8:
                    listAndDeleteBookingForTeam(); // Call delete team function
                    break;

                case 9:
                    System.out.println("Exiting..."); // Display exit message
                    break;
                default:
                    System.out.println("Invalid choice! Please try again."); // Handle invalid choice
            }
        } while (choice != 9); // Continue looping until the user chooses to exit
        scanner.close(); // Close the scanner to release resources
    }

    
    // Method to add a new team
    private static void addTeam() {
        System.out.println("Add Team");
        System.out.println("------------------------------");
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine(); // Read team name from user input
        ArrayList<String> teamMembers = new ArrayList<>(); // Create a new ArrayList to store team members

        // Prompt the user to add team members
        System.out.println("Enter team members (minimum 1, maximum 6, 'done' to finish):");
        int memberCount;
        String member;
        do {
            System.out.print("Enter member: ");
            member = scanner.nextLine(); // Read team member from user input
            if (!member.equalsIgnoreCase("done")) { // Check if the user wants to finish adding members
                teamMembers.add(member); // Add the member to the team
            }
            memberCount = teamMembers.size();
        } while (!member.equalsIgnoreCase("done") && memberCount < 6); // Continue loop until 'done' or max members
        System.out.println(); // Print an empty line for spacing
        if (teamMembers.isEmpty()) {
            System.out.println("A team must have at least 1 member. Team not added."); // Check if team has at least 1 member
            return;
        }
        manager.addTeam(teamName, teamMembers); // Add the team with its members to PointsManager
        manager.writeTeamDataToFile(); // Write team data to the file
        System.out.println(); // Print an empty line for spacing

    }



    // Method to record points for a team
    private static void recordPoints() {
        System.out.println("Record Points");
        System.out.println("------------------------------");
        if (manager.getTeamCount() == 0) {
            System.out.println(); // Add an empty line for spacing
            System.out.println("No teams added yet. Please add teams first."); // Check if teams exist
            System.out.println(); // Add an empty line for spacing
            return;
        }
        System.out.println("Select team:");
        for (int i = 0; i < manager.getTeamCount(); i++) {
            System.out.println((i + 1) + ". " + manager.getTeamName(i)); // Display the list of teams to select from
        }
        
        // Prompt user to select a team
        System.out.println(); // Add an empty line for spacing
        System.out.print("Enter team number: ");
        String input = scanner.nextLine(); // Read team number from user input
        int teamIndex;
        try {
            teamIndex = Integer.parseInt(input) - 1; // Read team index from user input
            if (teamIndex < 0 || teamIndex >= manager.getTeamCount()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println(); // Add an empty line for spacing
            System.out.println("Invalid input. Please enter a valid team number."); // Handle invalid input
            System.out.println(); // Add an empty line for spacing
            return;
        }
        
        System.out.print("Enter points earned: ");
        int points;
        try {
            points = Integer.parseInt(scanner.nextLine()); // Read users input as string and parse as integer
            manager.recordPoints(manager.getTeamName(teamIndex), points);
            manager.writeTeamDataToFile(); // Update team data in the file
            System.out.println(); // Print an empty line for spacing
            System.out.println(points + " points added to team: " + manager.getTeamName(teamIndex)); // Success message
            System.out.println(); // Print an empty line for spacing
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer for points."); // Handle invalid input
        }

    }

    private static void listAndDeleteBookingForTeam() {
        // Check if there are any teams available
        if (manager.getAvailableBookedTeams() == 0) {
            System.out.println("No teams available to delete bookings for.");
            return;
        }

        // Print a numbered list of available teams
        System.out.println("Available Teams:");
        boolean check = false;
        for (int i = 0; i < manager.getTeamCount(); i++) {
            if(manager.getTeamNameForBooking(i)!= null) {
                check = true;
                System.out.println((i + 1) + ". " + manager.getTeamNameForBooking(i));
            }
        }
        if(!check) {
            System.out.println("Currently no teams have registered for events. Thanks you. \n\n");
            return;
        }

        // Prompt the user to enter the number corresponding to the team they want to delete bookings for
        System.out.print("Enter the number of the team you want to delete bookings for: ");
        Scanner scanner = new Scanner(System.in);
        int teamNumber = scanner.nextInt();

        // Validate the user input
        if (teamNumber < 1 || teamNumber > manager.getTeamCount()) {
            System.out.println("Invalid team number. Please enter a number between 1 and " + manager.getTeamCount() + ".");
            return;
        }

        // Get the name of the selected team
        String teamNameToDeleteBookingFor = manager.getTeamName(teamNumber - 1);

        Team selectedTeam = manager.getTeamByName(teamNameToDeleteBookingFor);

        if(selectedTeam.getBookedEvents().isEmpty()){
            System.out.println("Invalid Number entered\n");
            return;
        }

        // Display the available bookings for the selected team
        System.out.println("Available bookings for Team " + teamNameToDeleteBookingFor + ":");
        int position = 1;

        for (QuizEvent event : selectedTeam.getBookedEvents()) {
            System.out.println(position + ". "+ event.getName());
            position++;
        }

        // Prompt the user to select a booking to delete
        System.out.print("Enter the number corresponding to the booking you want to delete: ");
        int bookingNumber = scanner.nextInt();

        // Validate the user input
        if (bookingNumber < 1 || bookingNumber > selectedTeam.getBookedEvents().size()) {
            System.out.println("Invalid booking number. Please enter a number between 1 and " + selectedTeam.getBookedEvents().size() + ".");
            return;
        }

        // Get the name of the selected booking
        String bookingNameToDelete = selectedTeam.getBookedEvents().get(bookingNumber - 1).getName();

        // Call the deleteTeam function to delete the selected booking for the selected team
        manager.deleteBookingForTeam(teamNameToDeleteBookingFor, bookingNameToDelete);
        manager.writeTeamDataToFile();

    }

    // Function to list all available teams and prompt user to select one for deletion
    private static void listAndDeleteTeam() {
        // Check if there are any teams available
        if (manager.getTeamCount() == 0) {
            System.out.println("No teams available to delete.");
            return;
        }

        // Print a numbered list of available teams
        System.out.println("Available Teams:");
        for (int i = 0; i < manager.getTeamCount(); i++) {
            System.out.println((i + 1) + ". " + manager.getTeamName(i));
        }

        // Prompt the user to enter the number corresponding to the team they want to delete
        System.out.print("Enter the number of the team you want to delete: ");
        Scanner scanner = new Scanner(System.in);
        int teamNumber = scanner.nextInt();

        // Validate the user input
        if (teamNumber < 1 || teamNumber > manager.getTeamCount()) {
            System.out.println("Invalid team number. Please enter a number between 1 and " + manager.getTeamCount() + ".");
            return;
        }

        // Get the name of the selected team
        String teamNameToDelete = manager.getTeamName(teamNumber - 1);

        // Call the deleteTeam function to delete the selected team
        manager.deleteTeam(teamNameToDelete);
        manager.writeTeamDataToFile();

    }



    // Method to book a quiz event for a team
    private static void bookEventForTeam() {
        System.out.println("Book a Quiz Event for a Team");
        System.out.println("------------------------------");
        if (manager.getTeamCount() == 0) {
            System.out.println(); 
            System.out.println("No teams added yet. Please add teams first.");
            System.out.println(); 
            return;
        }

        // Display the list of teams
        System.out.println("Select team:");
        for (int i = 0; i < manager.getTeamCount(); i++) {
            System.out.println((i + 1) + ". " + manager.getTeamName(i));
        }

        // Prompt user to select a team
        System.out.println();
        System.out.print("Enter team number: ");
        int teamIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (teamIndex < 0 || teamIndex >= manager.getTeamCount()) {
            System.out.println("Invalid team number.");
            return;
        }

        String teamName = manager.getTeamName(teamIndex); // Retrieve the name of the selected team using its index
        // Display available events for selection
        listAvailableEvents(); // Call the method to list available quiz events for the user to select from

        // Prompt user to select an event
        System.out.print("Enter event number to book for the team: ");
        int eventNumber;
        try {
            eventNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer for the event number.");
            return;
        }

        // Validate the selected event number
        ArrayList<QuizEvent> availableEvents = QuizEvent.getAvailableEvents();
        if (eventNumber < 1 || eventNumber > availableEvents.size()) {
            System.out.println("Invalid event number.");
            return;
        }

        // Get the selected event and book it for the team
        QuizEvent selectedEvent = availableEvents.get(eventNumber - 1);
        manager.bookEventForTeam(teamName, selectedEvent); // Book the event for the selected team
        manager.writeTeamDataToFile(); // Update team data in the file


    }

    // Method to list available quiz events
    private static void listAvailableEvents() {
        // Instantiate SimpleDateFormat here
        // Display available quiz events
        ArrayList<QuizEvent> availableEvents = QuizEvent.getAvailableEvents();
        if (availableEvents.isEmpty()) {
            System.out.println("No quiz events available.");
            System.out.println("------------------------------");
        } else {
            System.out.println();
            System.out.println("Available Quiz Events:");
            System.out.println("------------------------------");
            for (int i = 0; i < availableEvents.size(); i++) {
                QuizEvent event = availableEvents.get(i);
                String formattedDate = event.getDate(); // Format the date
                System.out.println((i + 1) + ". " + event.getName() + " - " + formattedDate);
            }
            System.out.println(); // Add an empty line after listing the available quiz events
        }
    }
       
}