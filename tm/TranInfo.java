
enum Direction {
    L,
    R
}

public class TranInfo {
    private TMState fromState;
    private Direction tapeDir;
    private char tapeCharRead;
    private char tapeCharReplacement;
    private TMState nextState;


    public TranInfo(TMState from, Direction dir, char oldChar, char newChar, TMState next) {
        fromState = from;
        tapeDir = dir;
        tapeCharRead = oldChar;
        tapeCharReplacement = newChar;
        nextState = next;
    }

    //getters and setters


    public TMState getNextState() {
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
