package solve;

import java.util.ArrayList;

import search.CommonSearcher;
import search.PipeGameSearchable;
import search.Searchable;
import search.Searcher;
import search.State;

public class PipeSolver implements Solver<char[][],ArrayList<String>> {

	@Override
	public Solution<char[][]> solveProblem(ArrayList<String> problem) {
		return null;
		
	}

//	@Override
//	public Solution<char[][]> solveProblem(ArrayList<String> problem) {
//		State<ArrayList<String>> state = new State<>(problem);
//		Searchable pipeGameBoard = new PipeGameSearchable(state);
//		//Searcher searcher = new CommonSearcher();//create a new instance of an algorithm
//		return searcher.search(pipeGameBoard);
// 
//	}

}
