package org.firstinspires.ftc.teamcode.util.glob;

/**
 * state management
 * @param <T> type of variable for which to watch
 */
public class StupidStateful<T> {
    private T prev;
    private T curr;
    private State state = State.Accepted;

    public enum State {
        Edited,
        Accepted
    }

    public StupidStateful(T initial) {
        this.prev = initial;
    }

    public void set(T newState) {
        curr = newState;
        if(prev != curr) {
            state = State.Edited;
        }
    }

    public void accept() {
        state = State.Accepted;
        prev = curr;
    }

    public State getState() {
        return state;
    }

    public T getVal() {
        return curr;
    }
}
