package server;


import java.util.ArrayList;
import java.util.LinkedList;

import algorithms.BFS;
import algorithms.BestFirstSearch;
import algorithms.CostGrader;
import algorithms.DFS;
import algorithms.HillClimbing;
import algorithms.StateGrader;
import search.CharMatrix;
import search.PipeGameSearchable;
import search.Searchable;
import search.Searcher;
import search.State;

public class MainTrain {

	public static void main(String[] args) {
		
		Server myServer = new SingleClientServer(6400);
		myServer.start(new SingleClientHandler());
		
		myServer.stop();
		
		ArrayList<String> board = new ArrayList<>();
			board.add("s-7");
			board.add(" F|");
			board.add(" g-");
			//F--7|  |g sJ
		//State<ArrayList<String>> problem = new State<>(board);		
		//PipeGameSearchable pgs = new PipeGameSearchable(problem);
		//System.out.println(pgs.isGoalState(pgs.initialState));
		//pgs.setAllPossibleStates(pgs.initialState);
		//System.out.println(pgs.getAllPossibleStates(pgs.initialState));
		
		char[][] board1 = {{'s','J','F'},{'-','|','L'},{'-','7','g'}};
		//char[][] board = {{'s','J','F','7'},{'-','|','L','F'},{'|','7','-','L'},{'-','J','L','g'}};
		//char[][] board = {{'s','J',' ','-'},{' ','-','L','-'},{'-','g','-',' '},{'7','|','L','-'}};
		//char[][] board = {{'s','L'},{'7','g'}};
		//char[][] board = {{'s','|','g'}};
		CharMatrix char2d = new CharMatrix(board1);
		State<CharMatrix> problem = new State<>(char2d);
		problem.setState(char2d);
		Searchable<CharMatrix> searchable = new PipeGameSearchable(problem);
		ArrayList<ArrayList<State<CharMatrix>>> solutions = new ArrayList<>();
		long startTime = 0, endTime = 0;
		//Searcher<CharMatrix> searcherBestFirstSearch = new BestFirstSearch<>();
		//Searcher<CharMatrix> searcherDFS = new DFS<>();
		Searcher<CharMatrix> searcherHillClimbing = new HillClimbing<CharMatrix>(20000, new CostGrader());
		//Searcher<CharMatrix> searcherBFS = new BFS<>();
		LinkedList<Searcher<CharMatrix>> searches = new LinkedList<>();
		//searches.add(searcherBestFirstSearch);
		//searches.add(searcherBFS);
		//searches.add(searcherDFS);
		searches.add(searcherHillClimbing);
		for (Searcher<CharMatrix> search : searches) {
			startTime = System.nanoTime();
			solutions.add(search.search(searchable).getSolution());
			endTime = System.nanoTime() - startTime;
			System.out.println("Time took " + search.getClass() + " in millisec= " + endTime/1000);
		}
		for(ArrayList<State<CharMatrix>> solution : solutions) {
			for (State<CharMatrix> state : solution)
			{
				for (int n=0; n<state.getState().getMatrix().length; n++)
					System.out.println(state.getState().getMatrix()[n]);
			}
			System.out.println("\n");
		}
	}

}