

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



}
