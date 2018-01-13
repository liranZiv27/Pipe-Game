package Solve;

import java.util.ArrayList;

public class PipeSolution implements Solution<ArrayList<String>> {
	
	private ArrayList<String> solution;
	public PipeSolution() {};
	@Override
	public ArrayList<String> getSolution() {
		return this.solution;
		}
	@Override
	public void setSolution(ArrayList<String> solution) {
		this.solution = solution;		
	}
	
}
