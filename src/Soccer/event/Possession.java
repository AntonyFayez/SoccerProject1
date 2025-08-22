package Soccer.event;

// Represents a team gaining possession of the ball
public class Possession extends GameEvent {

    public Possession(int theTime, String theTeam) {
        super(theTime, theTeam);
    }

    @Override
    public String toString() {
        return "Possession: " + theTeam + " at " + theTime + " minutes";
    }
}
