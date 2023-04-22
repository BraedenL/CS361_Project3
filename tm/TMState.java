import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class TMState {
    private int name;
    private boolean isAcceptState = false;
    private boolean isStartState = false;
    private int transitionCnt;
    private ArrayList<TranInfo> Transitions;
    

    public TMState(int n, boolean isAcc, boolean isStart) {
        name = n;
        isAcceptState = isAcc;
        isStartState = isStart;
        transitionCnt = 0;
        Transitions = new ArrayList<TranInfo>();
    }

    public TMState() {
        transitionCnt = 0;
        Transitions = new ArrayList<TranInfo>();
    }

    //getters and setters
    public void setName(int newName) {
        name = newName;
    }

    public void setIsAccept(boolean isAcc) {
        isAcceptState = isAcc;
    }

    public void setIsStart(boolean isStart) {
        isStartState = isStart;
    }

    public int getName() {
        return name;
    }

    public boolean isAcceptState() {
        return isAcceptState;
    }

    public boolean isStart() {
        return isStartState;
    }

    public void incTransCnt() {
        transitionCnt++;
    }

    public int getTransCnt() {
        return transitionCnt;
    }

    /**
     * Returns false if an exception is thrown or if failed to create and add new TranInfo to list
     * @param from
     * @param dir
     * @param oldChar
     * @param newChar
     * @param next
     * @return
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
    public int getNextState(int transition)
    {
        return Transitions.get(transition).getNextStateName();
    }
    public int getWriteValue(int transition)
    {
        return Transitions.get(transition).writeChar();
    }
    public String getTapeDirection(int input)
    {
        String direction = Transitions.get(input).getDirection();
        return direction;
    }

}
