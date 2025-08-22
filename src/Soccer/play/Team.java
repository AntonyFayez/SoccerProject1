package Soccer.play;

import java.util.ArrayList;

// Represents a soccer team
// Implements Comparable for ranking teams by points
// Implements IDisplayDataItem for display purposes
public class Team implements Comparable<Team>, DisplayData {
    private final int teamID;
    private String teamName;
    private ArrayList<Player> teamPlayers;
    private int pointsTotal;
    private int goalsTotal;

    public Team(int teamID, String teamName) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamPlayers = new ArrayList<>();
        this.pointsTotal = 0;
        this.goalsTotal = 0;
    }

    // Add a player to this team
    public void addPlayer(Player player) {
        if (!teamPlayers.contains(player)) {
            teamPlayers.add(player);
            player.addTeam(teamName);  // Update player's team list
        } else {
            System.out.println(player.getPlayerName() + " is already in team " + teamName);
        }
    }

    // Award points to team (3 for win, 1 for draw, 0 for loss)
    public void incPointsTotal(int points) {
        pointsTotal += points;
    }

    // Add goals when team scores
    public void incGoalsTotal(int goals) {
        goalsTotal += goals;
    }

    // Getters
    public int getTeamID() { return teamID; }
    public String getTeamName() { return teamName; }
    public ArrayList<Player> getTeamPlayers() { return teamPlayers; }
    public int getPointsTotal() { return pointsTotal; }
    public int getGoalsTotal() { return goalsTotal; }

    // Implementation of Comparable - sort by points, then by goals
    @Override
    public int compareTo(Team other) {
        if (this.pointsTotal != other.pointsTotal) {
            return Integer.compare(other.pointsTotal, this.pointsTotal); // Higher points first
        }
        return Integer.compare(other.goalsTotal, this.goalsTotal); // Then by goals
    }

    // Implementation of IDisplayDataItem
    @Override
    public boolean isDetailAvailable() {
        return true;
    }

    @Override
    public String getDisplayDetail() {
        return "Team: " + teamName + ", Points: " + pointsTotal + ", Goals: " + goalsTotal;
    }

    @Override
    public int getID() {
        return teamID;
    }

    @Override
    public String getDetailType() {
        return "Team";
    }

    @Override
    public String toString() {
        return teamName + " (Points: " + pointsTotal + ", Goals: " + goalsTotal + ")";
    }
}