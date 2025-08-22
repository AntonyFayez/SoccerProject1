package Soccer.play;

// Interface that allows different types of objects to be displayed in a unified way
// This follows the Interface pattern - any class can implement this to be "displayable"
public interface DisplayData {
    // Check if detailed information is available for this item
    public boolean isDetailAvailable();

    // Get detailed display information
    public String getDisplayDetail();

    // Get unique identifier for this item
    public int getID();

    // Get the type of this display item (for categorization)
    public String getDetailType();
}