package Soccer.play;

// Utility class for displaying simple strings in the league table
// Implements IDisplayDataItem so it can be mixed with other display items
public class DisplayString implements DisplayData {
    private String theString;
    private static int nextID = 1000;  // Static counter for unique IDs
    private final int stringID;

    public DisplayString(String theString) {
        this.theString = theString;
        this.stringID = nextID++;
    }

    public String getTheString() {
        return theString;
    }

    // Implementation of IDisplayDataItem
    @Override
    public boolean isDetailAvailable() {
        return false;  // Simple strings don't have detailed info
    }

    @Override
    public String getDisplayDetail() {
        return theString;
    }

    @Override
    public int getID() {
        return stringID;
    }

    @Override
    public String getDetailType() {
        return "DisplayString";
    }

    @Override
    public String toString() {
        return theString;
    }
}
