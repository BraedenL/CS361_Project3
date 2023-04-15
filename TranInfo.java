
enum Direction {
    L,
    R
}

public class TranInfo {
    private State fromState;
    private Direction tapeDir;
    private char tapeCharRead;
    private char tapeCharReplacement;
    private State nextState;


    public TranInfo(State from, Direction dir, char oldChar, char newChar, State next) {
        fromState = from;
        tapeDir = dir;
        tapeCharRead = oldChar;
        tapeCharReplacement = newChar;
        nextState = next;
    }

    //getters and setters


    public State getNextState() {
        return nextState;
    }

    public Direction getDirection() {
        return tapeDir;
    }

    public char readChar() {
        return tapeCharRead;
    }

    public char writeChar() {
        return tapeCharReplacement;
    }

    
}
