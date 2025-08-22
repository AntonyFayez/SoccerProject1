package Soccer.util;

// Manages application-wide settings and configuration
public class Settings {
    private static Settings instance;  // Singleton pattern
    private int maxPlayersPerTeam;
    private int matchDuration;
    private String leagueFormat;

    // Private constructor for singleton
    private Settings() {
        this.maxPlayersPerTeam = 25;
        this.matchDuration = 90;
        this.leagueFormat = "Round Robin";
    }

    // Get singleton instance
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    // Getters and setters
    public int getMaxPlayersPerTeam()
        { return maxPlayersPerTeam; }
    public void setMaxPlayersPerTeam(int maxPlayersPerTeam)
        { this.maxPlayersPerTeam = maxPlayersPerTeam; }

    public int getMatchDuration()
        { return matchDuration; }
    public void setMatchDuration(int matchDuration)
        { this.matchDuration = matchDuration; }

    public String getLeagueFormat()
        { return leagueFormat; }
    public void setLeagueFormat(String leagueFormat)
        { this.leagueFormat = leagueFormat; }
}
