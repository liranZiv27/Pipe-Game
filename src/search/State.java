package search;

public class State<T> {
	private T state;
	private double cost; // cost to reach this state
	private State<T> cameFrom; // the state we came from to this state
	
	public State() {}
	public State(T state) {
		this.state = state;
		this.cost = 0;
		this.cameFrom = null;
	}
	public State(State<T> state) {
		this.state = state.getState();
		this.cost = state.getCost();
		this.cameFrom = state.getCameFrom();
	}

	public boolean equals(State<T> s) {
		return state.equals(s.state);
		
	}

	public T getState() {
		return state;
	}
	
	public void setState(T state) {
		this.state = state;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}


}

