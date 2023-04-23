import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class TMState {
    private int name;
    private boolean isAcceptState = false;
    private boolean isStartState = false;
    private int transitionCnt;
    private ArrayList<TranInfo> Transitions;
    
    /**
     * Constructor for a state when information is available
     * @param n - name of the state
     * @param isAcc - is accepting state
     * @param isStart - is a starting state
     */
    public TMState(int n, boolean isAcc, boolean isStart) {
        name = n;
        isAcceptState = isAcc;
        isStartState = isStart;
        transitionCnt = 0;
        Transitions = new ArrayList<TranInfo>();
    }

    /**
     * A blank constructor for the states, used to fill an initial creation
     */
    public TMState() {
        transitionCnt = 0;
        Transitions = new ArrayList<TranInfo>();
    }

    /**
     * Sets the name of the state to a given value
     * @param newName - new name of state
     */
    public void setName(int newName) {
        name = newName;
    }

    /**
     * Toggles accept for the state
     * @param isAcc - true/false flag
     */
    public void setIsAccept(boolean isAcc) {
        isAcceptState = isAcc;
    }

    /**
     * Toggles start for the state
     * @param isStart - true/false flag
     */
    public void setIsStart(boolean isStart) {
        isStartState = isStart;
    }

    /**
     * Return the name of the state
     * @return - name of state
     */
    public int getName() {
        return name;
    }

    /**
     * Returns is state is accept or not
     * @return - true/false flag
     */
    public boolean isAcceptState() {
        return isAcceptState;
    }

    /**
     * Returns if the state is starting state or not
     * @return true/false flag
     */
    public boolean isStart() {
        return isStartState;
    }

    /**
     * Increases the total transition counter on the state
     */
    public void incTransCnt() {
        transitionCnt++;
    }
    /**
     * Returns how many transitions the state has total
     * @return - transition count
     */
    public int getTransCnt() {
        return transitionCnt;
    }

    /**
     * Returns false if an exception is thrown or if failed to create and add new TranInfo to list
     * @param dir - Direction of tape read
     * @param oldChar - Read character to transition on
     * @param newChar - New character to write to tape
     * @param next - The next state to transition to
     * @return - true if state can be create, false for any error
     */
    public boolean addNewTranInfo(String dir, int oldChar, int newChar, TMState next) {
        try {
            TranInfo TI = new TranInfo(dir, oldChar, newChar, next);
            Transitions.add(TI);
            incTransCnt();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Returns true/false if there is a transiton on the machine
     * @param input - character to move on
     * @return - true/false flag 
     */
    public boolean isTransition(int input)
    {
        if(Transitions.isEmpty()){
            return false;
        }
        /*
        for (TranInfo tranInfo : Transitions) {
            if(tranInfo.getNextStateName() == input)
            {
                return true;
            }
        }
        */
        for(int i = 0; i < Transitions.size(); i++)
        {
            if(input == i)
            {
                return true;
            }
        }
        
        return false;
    }
    /**
     * Determines what the next state should be
     * @param transition - character to transition on
     * @return - The next state
     */
    public int getNextState(int transition)
    {
        return Transitions.get(transition).getNextStateName();
    }
    /**
     * Determines what value should be written to the tape
     * @param transition - character to transition on
     * @return - write value
     */
    public int getWriteValue(int transition)
    {
        return Transitions.get(transition).writeChar();
    }
    /**
     * Gets the direction to move on the tape
     * @param input - what character we are moving on
     * @return - The tape direction L/R
     */
    public String getTapeDirection(int input)
    {
        String direction = Transitions.get(input).getDirection();
        return direction;
    }

}
