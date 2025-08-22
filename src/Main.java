import Soccer.play.*;
import Soccer.event.*;
import Soccer.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Load global settings singleton
            Settings settings = Settings.getInstance();
            System.out.println("League Format: " + settings.getLeagueFormat());
            System.out.println("Match Duration: " + settings.getMatchDuration() + " minutes");
            System.out.println("Max Players per Team: " + settings.getMaxPlayersPerTeam());
            System.out.println();

            // Create Player Database
            PlayerDatabase playerDB = new PlayerDatabase();

            // Create League
            League league = new League("Premier League");

            // Create Teams
            Team arsenal = new Team(1, "Arsenal");
            Team chelsea = new Team(2, "Chelsea");
            Team liverpool = new Team(3, "Liverpool");

            // Add teams to league
            league.addTeam(arsenal);
            league.addTeam(chelsea);
            league.addTeam(liverpool);

            // Create players
            Player[] players = {
                    new Player(101, "John Smith", "Forward"),
                    new Player(102, "Mike Johnson", "Goalkeeper"),
                    new Player(103, "David Wilson", "Midfielder"),
                    new Player(104, "Sarah Brown", "Defender"),
                    new Player(105, "Chris Green", "Midfielder")
            };

            // Add players to PlayerDatabase safely, handle duplicates
            for (Player player : players) {
                try {
                    playerDB.addPlayer(player);
                    System.out.println("Added player " + player.getPlayerName() + " to database.");
                } catch (PlayerDatabaseException e) {
                    System.err.println("Cannot add player: " + e.getMessage());
                }
            }

            System.out.println();

            // Add players to teams, obeying max players limit
            addPlayerToTeam(arsenal, players[0], settings);
            addPlayerToTeam(chelsea, players[1], settings);
            addPlayerToTeam(liverpool, players[2], settings); // David Wilson to Liverpool
            addPlayerToTeam(arsenal, players[0], settings);   // Chris Green to Arsenal

            System.out.println();

            // Create and simulate games
            Game game1 = new Game(201, arsenal, chelsea);
            game1.addGameEvent(new Kickoff(0, "Arsenal"));
            game1.addGameEvent(new Goal(15, "Arsenal", "John Smith"));
            game1.goalScored(arsenal);
            players[0].scoreGoal();

            game1.setScore(2, 1);
            players[0].scoreGoal(); // ✅ John Smith (index 0)
            players[4].scoreGoal();
            game1.endGame();

            Game game2 = new Game(202, chelsea, liverpool);
            game2.setScore(1, 1); // Draw
            game2.endGame();

            league.addGame(game1);
            league.addGame(game2);

            // Print league table
            System.out.println("=== " + league.getLeagueName() + " Table ===");
            DisplayData[][] table = league.createLeagueTable();
            printLeagueTable(table);

            // Demonstrate player search by name
            System.out.println("\nSearching for player named 'David Wilson':");
            var foundPlayers = playerDB.findPlayersByName("David Wilson");
            if (foundPlayers.isEmpty()) {
                System.out.println("No players found with that name.");
            } else {
                for (Player p : foundPlayers) {
                    System.out.println(p.getPlayerName() + " - Position: " +
                            p.getPosition() + ", Goals: " + p.getGoalsScored());
                }
            }

            // Try adding duplicate player to test exception
            try {
                System.out.println("\nTrying to add duplicate player " + players[0].getPlayerName() + " again.");
                playerDB.addPlayer(players[2]); // Add David Wilson to playerDB
            } catch (PlayerDatabaseException e) {
                System.out.println("Expected exception caught: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Fatal error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to add player to a team without exceeding max players
    private static void addPlayerToTeam(Team team, Player player, Settings settings) {
        if (team.getTeamPlayers().size() < settings.getMaxPlayersPerTeam()) {
            team.addPlayer(player);
            System.out.println("Added player " + player.getPlayerName() + " to team " + team.getTeamName());
        } else {
            System.out.println("Cannot add player " + player.getPlayerName() + " to " + team.getTeamName() + ": max players reached");
        }
    }

    // Helper method to print the league table on console
    private static void printLeagueTable(DisplayData[][] table) {
        for (DisplayData[] row : table) {
            for (DisplayData cell : row) {
                if (cell != null) {
                    if (cell instanceof Game) {
                        Game g = (Game) cell;
                        System.out.print(g.getHomeGoals() + "-" + g.getAwayGoals() + "\t");
                    } else {
                        System.out.print(cell.getDisplayDetail() + "\t");
                    }
                } else {
                    System.out.print("-\t");
                }
            }
            System.out.println();
        }
    }
}