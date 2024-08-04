import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<String> members;
    private int points;
    private ArrayList<QuizEvent> bookedEvents; // New field to store booked quiz events
    
    // Constructor that accepts both team name and members
    public Team(String name, ArrayList<String> members) {
        this.name = name; // Set the team name to the provided value
        this.members = members; // Set the list of team members to the provided value
        this.points = 0; // Initialize points to zero when a team is created
        this.bookedEvents = new ArrayList<>(); // Initialize the list of booked events
    }

    // Method to add points to the team
    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd; setPoints(points);
    }

    public void setBookEvents(ArrayList<QuizEvent> events){
        bookedEvents = events;

    }

    //Method to add booked events to the team
    public void addBookedEvent(QuizEvent event) {
        // Check if the event name already exists in the bookedEvents list
        boolean eventExists = false;

        if(bookedEvents!=null) {
            for (QuizEvent bookedEvent : bookedEvents) {
                if (bookedEvent.getName().equals(event.getName())) {
                    eventExists = true;
                    break;
                }
            }
        }
        else {
            this.bookedEvents = new ArrayList<>();
            bookedEvents.add(event);
            return;
        }

        // If the event doesn't already exist, add it to the bookedEvents list
        if (!eventExists) {
            bookedEvents.add(event);
        } else {
            System.out.println("Event with name " + event.getName() + " is already booked.");
        }
    }

    // Getter for booked events
    public ArrayList<QuizEvent> getBookedEvents() {
        return bookedEvents;
    }

    // Getters and setters for name and members (if needed)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; // Set the name of the team
    }

    public ArrayList<String> getMembers() {
        return members; // Get the list of members of the team
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members; // Set the list of members of the team
    }

    public void setPoints(int totalPoints) {
        this.points = totalPoints;
    }

    public int getPoints() {
        return points; // Get the points scored by the team
    }
}
