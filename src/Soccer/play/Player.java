package Soccer.play;
import java.util.ArrayList;

// Represents a soccer player
// Implements Comparable so players can be ranked/sorted
// Implements IDisplayDataItem so players can be displayed in tables
public class Player implements Comparable<Player>, DisplayData {
    private final int playerID;           // Unique identifier (final = cannot change)
    private String playerName;
    private String position;
    private int goalsScored;
    private ArrayList<String> teams;      // Teams this player has been on

    public Player(int playerID, String playerName, String position) {                 //how to usee:Player p = new Player(7, "Mohamed Salah", "Forward");
        this.playerID = playerID;
        this.playerName = playerName;
        this.position = position;
        this.goalsScored = 0;
        this.teams = new ArrayList<>();
    }

    // Getters and setters for encapsulation
    public int getPlayerID() { return playerID; }
    public String getPlayerName() { return playerName; }
    public String getPosition() { return position; }
    public int getGoalsScored() { return goalsScored; }

    public void scoreGoal() {
        goalsScored++;
    }

    public void addTeam(String teamName) {
        if (!teams.contains(teamName)) {
            teams.add(teamName);
        }
    }

    // Implementation of Comparable interface - allows sorting by goals scored
    @Override
    public int compareTo(Player other) {           //Returns:A negative number if this player has fewer goals than the other.Zero if goals equal Positive if this player has more goals.
        return Integer.compare(other.goalsScored, this.goalsScored); // Descending order
    }

    // Implementation of IDisplayDataItem interface
    @Override
    public boolean isDetailAvailable() {
        return true;
    }

    @Override
    public String getDisplayDetail() {
        return "Player: " + playerName + ", Position: " + position + ", Goals: " + goalsScored;
    }

    @Override
    public int getID() {
        return playerID;
    }

    @Override
    public String getDetailType() {
        return "Player";
    }

    @Override
    public String toString() {
        return playerName + " (" + position + ") - Goals: " + goalsScored;
    }
}
