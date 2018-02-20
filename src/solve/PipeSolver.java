package solve;

import java.util.ArrayList;

import algorithms.BestFirstSearch;
import search.CharMatrix;
import search.PipeGameSearchable;
import search.PipeParser;
import search.Searchable;
import search.Searcher;
import search.State;

public class PipeSolver implements Solver<ArrayList<String>,CharMatrix> {

	private PipeParser parser;
	private Searcher<CharMatrix> searcher;
	public PipeSolver() {
		this.parser = new PipeParser();
	}

	@Override
	public ArrayList<String> solveProblem(CharMatrix problem) {
		State<CharMatrix> stateProblem = new State<>(problem);
		Searchable<CharMatrix> pipeGameBoard = new PipeGameSearchable(stateProblem);
		ArrayList<State<CharMatrix>> solution = new ArrayList<>();
		searcher = new BestFirstSearch<CharMatrix>();
		solution = searcher.search(pipeGameBoard).getSolution();
		ArrayList<String> res = parser.parseToSteps(solution);
		return res;
	}
	


}
