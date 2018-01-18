package solve;

import java.util.ArrayList;

import search.State;

public class Solution<T> {
	
	private ArrayList<State<T>> states;
	public Solution() {
		this.states = new ArrayList<>();
	}
	
	public ArrayList<State<T>> getSolution() {
		return this.states;
		}
	
	public void setSolution(ArrayList<State<T>> solution) {
		this.states = solution;		
	}
	public void add(State<T> solution) {
		this.states.add(solution);
	}

}
