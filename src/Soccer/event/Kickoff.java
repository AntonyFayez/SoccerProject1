package Soccer.event;

// Represents a kickoff event (start of match or after goal)
public class Kickoff extends GameEvent {

    public Kickoff(int theTime, String theTeam) {
        super(theTime, theTeam);
    }

    @Override
    public String toString() {      
        return "Kickoff by " + theTeam + " at " + theTime + " minutes";
    }
}
