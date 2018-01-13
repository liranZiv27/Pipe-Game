package Search;

import java.util.ArrayList;

public class PipeGameSearchable implements Searchable<ArrayList<String>> {
	public final State<ArrayList<String>> initialState;
	
	public PipeGameSearchable(State<ArrayList<String>> problem) {
		this.initialState = problem;
	}
		
	@Override
	public State<ArrayList<String>> getInitialState() {
		return this.initialState;
	}

	@Override
	public State<ArrayList<String>> getGoalState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<State<ArrayList<String>>> getAllPossibleStates(State<ArrayList<String>> s) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
