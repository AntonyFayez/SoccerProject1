package Soccer.event;

// Represents a kickoff event (start of match or after goal)
public class Kickoff extends GameEvent {

    public Kickoff(int theTime, String theTeam) {
        super(theTime, theTeam);
    }

    @Override
    public String toString() {      //This means when toString() is called on the object, it returns that string at that moment.
                                    //toString is a method that returns a String, it does NOT store the string anywhere in the object.
                                   //What if I override toString multiple times in subclasses?
        //Every subclass can override toString().

        //The last overriding method (closest to the object's actual class) is the one invoked.

        //If a class does not override toString(), the method from its superclass is used instead.

        //The initial toString() method from Object class outputs something like ClassName@65ab7765—a string based on memory address, which is typically not human-readable.

        //Your overrides replace that default behavior with meaningful output.
        return "Kickoff by " + theTeam + " at " + theTime + " minutes";
    }
}
