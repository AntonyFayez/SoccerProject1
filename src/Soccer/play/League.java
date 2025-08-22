package Soccer.play;

import java.util.ArrayList;
import java.util.Collections;

/**
 * League class - manages all teams and games and builds a league table.
 */
public class League {
    private String leagueName;
    private ArrayList<Team> theTeams;
    private ArrayList<Game> theGames;

    public League(String leagueName) {
        this.leagueName = leagueName;
        this.theTeams = new ArrayList<>();
        this.theGames = new ArrayList<>();
    }

    // Add a team to this league
    public void addTeam(Team team) {
        theTeams.add(team);
    }

    // Add a game to this league
    public void addGame(Game game) {
        theGames.add(game);
    }

    /**
     * Creates a league table as a 2D array of DisplayData items.
     * Table format:
     * - Row 0: column headers (team names + "Points" + "Goals")
     * - Column 0: row headers (team names)
     * - Cells in between: Game results or "X" for same team
     */
    public DisplayData[][] createLeagueTable() {
        // Sort teams by points and then goals (compareTo in Team handles this)
        Collections.sort(theTeams);

        int numTeams = theTeams.size();
        // Create table with extra columns for Points and Goals
        DisplayData[][] table = new DisplayData[numTeams + 1][numTeams + 3];

        // ---- Fill header row ----
        table[0][0] = new DisplayString(" "); // top-left blank corner
        for (int i = 0; i < numTeams; i++) {
            table[0][i + 1] = theTeams.get(i); // team names as column headers
        }
        table[0][numTeams + 1] = new DisplayString("Points");
        table[0][numTeams + 2] = new DisplayString("Goals");

        // ---- Fill rows for each team ----
        for (int i = 0; i < numTeams; i++) {
            Team currentTeam = theTeams.get(i);

            // First cell in row = team name (row header)
            table[i + 1][0] = currentTeam;

            // Fill each opponent column
            for (int j = 0; j < numTeams; j++) {
                if (i == j) {
                    // Same team cell - mark with X
                    table[i + 1][j + 1] = new DisplayString("X");
                } else {
                    // Find the game between team i and team j
                    Game g = findGame(theTeams.get(i), theTeams.get(j));
                    if (g != null) {
                        table[i + 1][j + 1] = g; // Game implements DisplayData
                    } else {
                        table[i + 1][j + 1] = new DisplayString("-"); // No game played
                    }
                }
            }

            // Last two columns = Points and Goals
            table[i + 1][numTeams + 1] =
                    new DisplayString(String.valueOf(currentTeam.getPointsTotal()));
            table[i + 1][numTeams + 2] =
                    new DisplayString(String.valueOf(currentTeam.getGoalsTotal()));
        }

        return table;
    }

    /**
     * Helper method to find the game played between two teams, regardless of home/away order.
     */
    private Game findGame(Team team1, Team team2) {
        for (Game game : theGames) {
            if ((game.getHomeTeam().equals(team1) && game.getAwayTeam().equals(team2)) ||
                    (game.getHomeTeam().equals(team2) && game.getAwayTeam().equals(team1))) {
                return game;
            }
        }
        return null;
    }

    // Getters
    public ArrayList<Team> getTheTeams() {
        return theTeams;
    }

    public ArrayList<Game> getTheGames() {
        return theGames;
    }

    public String getLeagueName() {
        return leagueName;
    }
}
