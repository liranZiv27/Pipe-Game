package search;

import java.util.ArrayList;

public interface Searchable<T> {
	public State<T> getInitialState();
	public boolean isGoalState(State<T> s);
	public ArrayList<State<T>> getAllPossibleStates(State<T> s);
	public void setAllPossibleStates(State<T> s);
	public double increaseCost();
}
