package server;

import java.util.ArrayList;

public class StringSolver implements Solver<String> {

	@Override
	public ArrayList<String> solveProblem(String problem) {
		ArrayList<String> solution = new ArrayList<>();
		for (int i=0; i<4; i++) {
			solution.add("1,2,3");
		}
		return solution;
	}

}
