package solve;

import java.util.ArrayList;

import algorithms.BFS;
import algorithms.DFS;
import search.CommonSearcher;
import search.PipeGameSearchable;
import search.PipeParser;
import search.Searchable;
import search.State;

public class PipeSolver implements Solver<char[][],State<ArrayList<String>>> {

	private PipeParser parser;
	private CommonSearcher<char[][]> searcher;
	public PipeSolver() {
		this.parser = new PipeParser();
	}

	@Override
	public Solution<char[][]> solveProblem(State<ArrayList<String>> problem) {
		State<char[][]> stateProblem = new State<>(parser.parse(problem));
		Searchable<char[][]> pipeGameBoard = new PipeGameSearchable(stateProblem);
		ArrayList<State<char[][]>> solution = new ArrayList<State<char[][]>>();
		searcher = new DFS<char[][]>();
		solution = searcher.search(pipeGameBoard).getSolution();
		Solution<char[][]> res = new Solution<>();
		res.setSolution(solution);
		return res;
	}
	


}
