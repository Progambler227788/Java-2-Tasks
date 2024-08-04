import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class PointsManager {
    private final ArrayList<Team> teams;

    public PointsManager() {
        teams = new ArrayList<>();
    }

    public void addTeam(String teamName, ArrayList<String> teamMembers) {
        Team team = new Team(teamName, teamMembers);
        teams.add(team);
        System.out.println("Team " + teamName + " added successfully.");
    }
    public void addTeam(String teamName, ArrayList<String> teamMembers, int points, ArrayList<QuizEvent> events) {
        Team team = new Team(teamName, teamMembers);
        team.addPoints(points);
        team.setBookEvents(events);

        teams.add(team);

        System.out.println("Team " + teamName + " added successfully.");
    }
    public Team getTeamByName(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        // If team not found
        return null;
    }

    public void deleteBookingForTeam(String teamName, String bookingName) {
        // Find the team by name
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                // Find the booking by name and remove it
                for (int i = 0; i < team.getBookedEvents().size(); i++) {
                    if (team.getBookedEvents().get(i).getName().equals(bookingName)) {
                        team.getBookedEvents().remove(i);
                        System.out.println("Booking \"" + bookingName + "\" deleted successfully for Team " + teamName + ".");
                        return;
                    }
                }
                // If booking not found
                System.out.println("Booking \"" + bookingName + "\" not found for Team " + teamName + ".");
                return;
            }
        }
        // If team not found
        System.out.println("Team " + teamName + " not found.");
    }


    public void recordPoints(String teamName, int points) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                team.addPoints(points);
                return;
            }
        }
        System.out.println("Team not found!");
    }

    public void generateLeaderboardTable() {
        if (teams.isEmpty()) {
            System.out.println("Please add teams first before generating the leaderboard table.");
            System.out.println();
            return;
        }
        sortTeamsByPoints();
        System.out.println("Leaderboard Table");
        System.out.println("------------------------------");
        System.out.println("Team\tPoints");
        System.out.println("------------------------------");
        for (Team team : teams) {
            System.out.println(team.getName() + "\t" + team.getPoints());
        }
        System.out.println();
    }

    public void bookEventForTeam(String teamName, QuizEvent event) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                team.addBookedEvent(new QuizEvent(teamName, event.getName(), event.getDate()));
                System.out.println();
                System.out.println("Event booked successfully for team: " + teamName);
                System.out.println();
                return;
            }
        }
        System.out.println("Team not found!");
    }

    public void sortTeamsByPoints() {
        teams.sort((team1, team2) -> Integer.compare(team2.getPoints(), team1.getPoints()));
    }


    public int getTeamCount() {
        return teams.size();
    }

    public String getTeamName(int index) {
        if (index >= 0 && index < teams.size()) {

            return teams.get(index).getName();

        }

        return null;
    }
    public String getTeamNameForBooking(int index) {
        if (index >= 0 && index < teams.size() && !teams.get(index).getBookedEvents().isEmpty()) {

            return teams.get(index).getName();
        }
        return null;
    }
    public void deleteTeam(String teamName) {
        // Iterate through the teams list
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            // Check if the team's name matches the provided teamName
            if (team.getName().equals(teamName)) {
                // Remove the team from the list
                teams.remove(i);
                System.out.println("Team " + teamName + " deleted successfully.");
                return; // Exit the method after deleting the team
            }
        }
        // If no team with the provided name is found
        System.out.println("Team " + teamName + " not found.");
    }
    public int getAvailableBookedTeams(){
        int count = 0;
        for (Team team : teams) {
            if (!team.getBookedEvents().isEmpty()) {
                count++;
                //System.out.println(count+" count");
            }
        }
        return  count;

    }


    public void generateBookingReport() {
        boolean anyBookings = false;
      //  System.out.println("Fetched :: "+ teams.get(0).getBookedEvents().get(0).getName());
        for (Team team : teams) {
            if (!team.getBookedEvents().isEmpty()) {
                anyBookings = true;
                break;
            }
        }

        if (!anyBookings) {
            System.out.println("There are currently no bookings");
            return;
        }

        System.out.println("Booking Report");
        System.out.println("------------------------------");
        for (Team team : teams) {
            if (!team.getBookedEvents().isEmpty()) {
                System.out.println("Team: " + team.getName());
                System.out.println("Booked Events:");
                for (QuizEvent event : team.getBookedEvents()) {
                    System.out.println("- " + event.getName()+ " on " + event.getDate());
                }
                System.out.println("------------------------------");
            }
        }
    }
    // Method to write team data to a file
    public void writeTeamDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("teams.txt", false))) {
            for (Team team : teams) {
                writer.println("Team Name: " + team.getName());
                writer.println("Team-Members: "+ team.getMembers().size());
                for (String memberName : team.getMembers()) {
                    writer.println(memberName);
                }
                writer.println("Quiz Events:");
                if(team.getBookedEvents()!=null) {
                    for (QuizEvent event : team.getBookedEvents()) {
                        writer.println(event.getName() + ", " + event.getDate());
                    }
                }
                writer.println("Total Points: " + team.getPoints()); // Write total points for the team
                writer.println(); // Add an empty line between teams
            }
            System.out.println("Team data written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read team data from a file
    public void readTeamDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("teams.txt"))) {
            String line;
            String teamName = null;
            ArrayList<String> teamMembers =  new ArrayList<>();
            int totalPoints = 0;
            ArrayList<QuizEvent> events = new ArrayList<>();


            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Team Name: ")) {
                    if (teamName != null ) {
                        // Add the previously read team to the list of teams
                      // System.out.println(events.get(0).getName()+ " Fetched Here");

                        addTeam(teamName, teamMembers, totalPoints,events);

                    }
                    // Start reading data for a new team
                    teamName = line.substring("Team Name: ".length());
                    teamMembers = new ArrayList<>();
                    totalPoints = 0; // Move initialization here
                    events = new ArrayList<>();
                    
                } else if (line.startsWith("Team-Members:")) {
                    String[] parts = line.split(" ");
                    int countMembers = 0;
                    if (parts.length >= 2) {
                        String integerPart = parts[parts.length - 1]; // Get the last part which should be the integer
                        countMembers  = Integer.parseInt(integerPart);

                    }
                    // Read team members
                    while (  countMembers>0 && (  line = reader.readLine()) != null && !line.isEmpty() ) {
                        System.out.println(line);


                         teamMembers.add(line);
                         countMembers--;
                    }

                }
                else  if (line.startsWith("Quiz Events:")) {


                        while ((line = reader.readLine()) != null && !line.isEmpty()) {

                            if (line.startsWith("Total Points: ")) {
                                totalPoints = Integer.parseInt(line.substring("Total Points: ".length()));

                            }
                            else {
                                String[] parts = line.split(",");
                                if (parts.length >= 1) {
                                    String eventName = parts[0];
                                    String eventDate = parts[1];
                                    // Create a new QuizEvent object and add it to the events list

                                    events.add(new QuizEvent(teamName, eventName, eventDate));

                                }
                            }
                        }

                }            }
            // Add the last team read from the file
            if (teamName != null ) {
                addTeam(teamName, teamMembers, totalPoints,events);
            }
        //    System.out.println("Fetched :: "+ teams.get(0).getBookedEvents().get(0).getName());
            System.out.println("Team data read from file successfully.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
      //  printTeams();
    }

}