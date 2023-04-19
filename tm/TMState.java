

public class TMState {
    private int name;
    private boolean isAcceptState = false;
    private boolean isStartState = false;
    private int transitionCnt;

    public TMState(int n, boolean isAcc, boolean isStart) {
        name = n;
        isAcceptState = isAcc;
        isStartState = isStart;
        transitionCnt = 0;
    }

    public TMState() {
        
    }

    //getters and setters

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



}
