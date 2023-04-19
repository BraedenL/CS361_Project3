import java.util.ArrayList;

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
    }

    public TMState() {
        transitionCnt = 0;
    }

    //getters and setters
    public void setName(int newName) {
        name = newName;
    }

    public void setIsAcc(boolean isAcc) {
        isAcceptState = isAcc;
    }

    public void setIsStart(boolean isStart) {
        isStartState = isStart;
    }

    public int getName() {
        return name;
    }

    public boolean isAcc() {
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
    public boolean addNewTranInfo(TMState from, Direction dir, char oldChar, char newChar, TMState next) {
        try {
            TranInfo TI = new TranInfo(from, dir, oldChar, newChar, next);
            Transitions.add(TI);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
