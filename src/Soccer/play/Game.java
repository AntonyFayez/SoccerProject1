package Soccer.play;

import Soccer.event.GameEvent;
import java.util.ArrayList;

// Represents a single soccer game between two teams
public class Game implements DisplayData {
    private final int gameID;
    private Team homeTeam;
    private Team awayTeam;
    private ArrayList<GameEvent> theEvents;
    private int homeGoals;
    private int awayGoals;
    private boolean gameComplete;

    public Game(int gameID, Team homeTeam, Team awayTeam) {
        this.gameID = gameID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.theEvents = new ArrayList<>();
        this.homeGoals = 0;
        this.awayGoals = 0;
        this.gameComplete = false;
    }

    public int getHomeGoals() { return homeGoals; }
    public int getAwayGoals() { return awayGoals; }

    // Add an event to the game
    public void addGameEvent(GameEvent event) {
        theEvents.add(event);
    }

    // End the game and calculate points
    public void endGame() {
        gameComplete = true;

        // Award points based on result
        if (homeGoals > awayGoals) {
            homeTeam.incPointsTotal(3);  // Home team wins
            awayTeam.incPointsTotal(0);  // Away team loses
        } else if (awayGoals > homeGoals) {
            awayTeam.incPointsTotal(3);  // Away team wins
            homeTeam.incPointsTotal(0);  // Home team loses
        } else {
            homeTeam.incPointsTotal(1);  // Draw
            awayTeam.incPointsTotal(1);  // Draw
        }

        // Update team goal totals
        homeTeam.incGoalsTotal(homeGoals);
        awayTeam.incGoalsTotal(awayGoals);
    }

    public void goalScored(Team scoringTeam) {
        // Increase the score for the correct team before setScore is called
        if (scoringTeam.equals(homeTeam)) {
            homeGoals++;
        } else if (scoringTeam.equals(awayTeam)) {
            awayGoals++;
        }
    }

    public void setScore(int homeGoals, int awayGoals) {
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    // Getters
    public int getGameID() { return gameID; }
    public Team getHomeTeam() { return homeTeam; }
    public Team getAwayTeam() { return awayTeam; }
    public ArrayList<GameEvent> getTheEvents() { return theEvents; }
    public String getScore() { return homeGoals + " - " + awayGoals; }

    // Implementation of IDisplayDataItem
    @Override
    public boolean isDetailAvailable() {
        return gameComplete;
    }

    @Override
    public String getDisplayDetail() {
        return homeTeam.getTeamName() + " " + getScore() + " " + awayTeam.getTeamName();
    }

    @Override
    public int getID() {
        return gameID;
    }

    @Override
    public String getDetailType() {
        return "Game";
    }

    @Override
    public String toString() {
        return homeTeam.getTeamName() + " vs " + awayTeam.getTeamName() +
                (gameComplete ? " (" + getScore() + ")" : " (In Progress)");
    }
}
