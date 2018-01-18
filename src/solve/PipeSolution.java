package solve;

import java.util.ArrayList;

import search.State;

public class PipeSolution implements Solution<ArrayList<State<String>>> {
	
	private ArrayList<State<String>> states;
	public PipeSolution() {};
	@Override
	public ArrayList<State<String>> getSolution() {
		return this.states;
		}
	@Override
	public void setSolution(ArrayList<State<String>> solution) {
		this.states = solution;		
	}
	
}
