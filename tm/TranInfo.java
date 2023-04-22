
enum Direction {
    L,
    R
}

public class TranInfo {
    private String tapeDir;
    private int tapeCharRead;
    private int tapeCharReplacement;
    private TMState nextState;


    public TranInfo(String dir, int oldChar, int newChar, TMState next) {
        tapeDir = dir;
        tapeCharRead = oldChar;
        tapeCharReplacement = newChar;
        nextState = next;
    }

    //getters and setters


    public TMState getNextState() {
        return nextState;
    }

    public int getNextStateName()
    {
        return nextState.getName();
    }

    public String getDirection() {
        return tapeDir;
    }

    public int readChar() {
        return tapeCharRead;
    }

    public int writeChar() {
        return tapeCharReplacement;
    }

    
}
