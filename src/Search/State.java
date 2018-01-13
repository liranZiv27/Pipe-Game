package Search;

public class State<T> {
	private T state;
	private double cost; // cost to reach this state
	private State<T> cameFrom; // the state we came from to this state
	
	public State(T state) {
		this.state = state;
	}
	
	public boolean equals(State<T> s) {
		return state.equals(s.state);
	}
}
