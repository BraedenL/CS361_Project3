

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

}
