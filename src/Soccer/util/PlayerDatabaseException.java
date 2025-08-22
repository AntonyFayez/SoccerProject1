package Soccer.util;

// Custom exception for player database operations
// Extends Exception to create a checked exception that must be handled
public class PlayerDatabaseException extends Exception {

    // Constructor with error message
    public PlayerDatabaseException(String message) {
        super(message);
    }

    // Constructor with error message and cause
    public PlayerDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
