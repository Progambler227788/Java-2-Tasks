import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuizEvent {
    private String teamName;
    private String name;
    private String date;
    private static ArrayList<QuizEvent> availableEvents = new ArrayList<>();


    // Constructor with team name, event name, and date parameters
    public QuizEvent(String teamName, String name, String date) {
        this.teamName = teamName;
        this.name = name;
        this.date = date; // Parse date string to Date object

    }

    // Getter for team name
    public String getTeamName() {
        return teamName;
    }

    // Getter for event name
    public String getName() {
        return name;
    }

    // Getter for date
    public String getDate() {
        return date;
    }

    // Method to add an event to the list of available events
    public static void addAvailableEvent(QuizEvent event) {
        availableEvents.add(event);
    }

    // Method to get the list of available events
    public static ArrayList<QuizEvent> getAvailableEvents() {
        return availableEvents;
    }

    // Initialize the predefined set of events
    public static void initializeEvents() {
        // Clear existing events
        availableEvents.clear();

        // Add the predefined quiz events
        availableEvents.add(new QuizEvent("", "General Knowledge Quiz", "01/04/2024"));
        availableEvents.add(new QuizEvent("", "80's Music Quiz", "15/04/2024"));
        availableEvents.add(new QuizEvent("", "Disney Quiz", "05/05/2024"));
        availableEvents.add(new QuizEvent("", "Sports Quiz", "20/05/2024"));
        availableEvents.add(new QuizEvent("", "Movie & TV Quiz", "10/06/2024"));
    }


}