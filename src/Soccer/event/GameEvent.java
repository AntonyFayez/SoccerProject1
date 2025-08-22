package Soccer.event;

// Abstract class representing any event that can occur during a soccer match
// We use abstract because we don't want to create generic GameEvent objects directly
// Each specific event type (Goal, Kickoff, etc.) will extend this class
public abstract class GameEvent {
    protected int theTime;        // Time in minutes when event occurred
    protected String theTeam;     // Which team the event is associated with

    // Constructor - every GameEvent must have a time and team
    public GameEvent(int theTime, String theTeam) {
        this.theTime = theTime;
        this.theTeam = theTeam;
    }

    // Getter methods for encapsulation
    public int getTheTime() {
        return theTime;
    }

    public String getTheTeam() {
        return theTeam;
    }

    // Abstract method - forces each subclass to provide its own implementation
    // This ensures every event can describe itself properly
    public abstract String toString();
}