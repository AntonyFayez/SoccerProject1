package Soccer.util;

import Soccer.play.Player;
import java.util.ArrayList;

/**
 * Manages all players in the system.
 */
public class PlayerDatabase {
    private ArrayList<Player> players;

    public PlayerDatabase() {
        this.players = new ArrayList<>();
    }

    /**
     * Adds a player if the ID doesn't exist yet.
     * @throws PlayerDatabaseException if duplicate ID found.
     */
    public void addPlayer(Player player) throws PlayerDatabaseException {
        for (Player p : players) {
            if (p.getPlayerID() == player.getPlayerID()) {
                throw new PlayerDatabaseException("Player with ID " + player.getPlayerID() + " already exists");
            }
        }
        players.add(player);
    }

    /**
     * Finds and returns a player by ID.
     * @throws PlayerDatabaseException if the ID is not found.
     */
    public Player getPlayer(int playerID) throws PlayerDatabaseException {
        for (Player player : players) {
            if (player.getPlayerID() == playerID) {
                return player;
            }
        }
        throw new PlayerDatabaseException("Player with ID " + playerID + " not found");
    }

    /**
     * Returns a fresh copy of all players.
     */
    public ArrayList<Player> getAllPlayers() {
        return new ArrayList<>(players);  // Defensive copy
    }

    // --- New suggestions below: ---

    /**
     * Finds all players with the given name (case-insensitive).
     */
    public ArrayList<Player> findPlayersByName(String name) {
        ArrayList<Player> matches = new ArrayList<>();
        for (Player p : players) {
            if (p.getPlayerName().equalsIgnoreCase(name)) {
                matches.add(p);
            }
        }
        return matches;
    }

    /**
     * Removes a player by ID, returns true if removed, false if not found.
     */
    public boolean removePlayer(int playerID) {
        return players.removeIf(p -> p.getPlayerID() == playerID);
    }

    /**
     * Returns a string with all player info for easy printing.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Player p : players) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}