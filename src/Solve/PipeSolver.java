package Solve;

import java.util.ArrayList;

import Search.CommonSearcher;
import Search.PipeGameSearchable;
import Search.PipeState;
import Search.Searchable;
import Search.Searcher;
import Search.State;

public class PipeSolver implements Solver<char[][],ArrayList<String>> {

	@Override
	public Solution<char[][]> solveProblem(ArrayList<String> problem) {
		State<ArrayList<String>> state = new PipeState(problem);
		Searchable pipeGameBoard = new PipeGameSearchable(state);
		//Searcher searcher = new CommonSearcher();//create a new instance of an algorithm
		return searcher.search(pipeGameBoard);
 
	}

}
