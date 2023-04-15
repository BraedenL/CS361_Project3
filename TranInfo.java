
enum Direction {
    L,
    R
}

public class TranInfo {
    private State fromState;
    private Direction tapedir;
    private char tapeCharReplacement;
    private State nextState;


    public TranInfo(State from, Direction dir, char newChar, State next) {
        fromState = from;
        tapedir = dir;
        tapeCharReplacement = newChar;
        nextState = next;
    }

    //getters and setters
}
