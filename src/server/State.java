package server;

public class State {
	private String state;
	private double cost; // cost to reach this state
	private State cameFrom; // the state we came from to this state

	public State(String state) {
		this.state = state;
	}
	
	public boolean equals(State s) {
		return state.equals(s.state);
	}
}
