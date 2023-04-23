
enum Direction {
    L,
    R
}

public class TranInfo {
    private String tapeDir;
    private int tapeCharRead;
    private int tapeCharReplacement;
    private TMState nextState;

    /**
     * Constructor for transition information, to be paired with a TMState in the main class
     * @param dir - Direction to move on the tape
     * @param oldChar - The character transition on
     * @param newChar - The new character to write to tape
     * @param next - The next state to visit in the machine
     */
    public TranInfo(String dir, int oldChar, int newChar, TMState next) {
        tapeDir = dir;
        tapeCharRead = oldChar;
        tapeCharReplacement = newChar;
        nextState = next;
    }

    /**
     * Returns the next state
     * @return - next state
     */
    public TMState getNextState() {
        return nextState;
    }

    /**
     * Returns the next states name
     * @return - name of next state
     */
    public int getNextStateName()
    {
        return nextState.getName();
    }

    /**
     * Returns the direction we are moving on the tape
     * @return - tape string direction
     */
    public String getDirection() {
        return tapeDir;
    }

    /**
     * Returns the character we are trying to read
     * @return - reading character
     */
    public int readChar() {
        return tapeCharRead;
    }

    /**
     * Returns the character we are writing
     * @return - writing character
     */
    public int writeChar() {
        return tapeCharReplacement;
    }

    
}
