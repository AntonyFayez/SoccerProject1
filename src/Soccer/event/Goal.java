package Soccer.event;

// Represents a goal scored during the match
// Inherits from GameEvent and adds goal-specific functionality
public class Goal extends GameEvent {
    private String thePlayer;     // Name of player who scored

    // Constructor calls parent constructor and sets the scorer
    public Goal(int theTime, String theTeam, String thePlayer) {
        super(theTime, theTeam);  // Call GameEvent constructor
        this.thePlayer = thePlayer;
    }

    public String getThePlayer() {
        return thePlayer;
    }

    // Implementation of abstract method from GameEvent
    @Override
    public String toString() {
        return "GOAL! " + thePlayer + " (" + theTeam + ") at " + theTime + " minutes";
    }
}
