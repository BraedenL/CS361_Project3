

public class State {
    private String name;
    private boolean isAcceptState = false;
    private boolean isStartState = false;
    private int transitionCnt;

    public State(String n, boolean isAcc, boolean isStart) {
        name = n;
        isAcceptState = isAcc;
        isStartState = isStart;
        transitionCnt = 0;
    }

    //getters and setters

    public String toString() {
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



}
