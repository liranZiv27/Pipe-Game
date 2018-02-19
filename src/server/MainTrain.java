package server;


import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;

import algorithms.BFS;
import algorithms.BestFirstSearch;
import algorithms.DFS;
import search.PipeGameSearchable;
import search.Searchable;
import search.Searcher;
import search.State;

public class MainTrain {

	public static void main(String[] args) {
		
//		SingleClientServer myServer = new SingleClientServer(6400, new StringSingleClientHandler(new GameCacheManager(), new PipeSolver()));
//		try {
//		myServer.start();
//		} catch (Exception e) {
//			
//		}	
//			
//		myServer.stop();
		
		
//		ArrayList<String> board = new ArrayList<>();
//			board.add("s-7");
//			board.add(" F|");
//			board.add(" g-");
//			//F--7|  |g sJ
//		State<ArrayList<String>> problem = new State<>(board);		
//		PipeGameSearchable pgs = new PipeGameSearchable(problem);
//		//System.out.println(pgs.isGoalState(pgs.initialState));
//		//pgs.setAllPossibleStates(pgs.initialState);
//		System.out.println(pgs.getAllPossibleStates(pgs.initialState));
		
		char[][] board = {{'s','J','F'},{'-','|','L'},{'-','7','g'}};
		//char[][] board = {{'s','J','F','7'},{'-','|','L','F'},{'|','7','-','L'},{'-','J','L','g'}};
		//char[][] board = {{'s','J',' ','-'},{' ','-','L','-'},{'-','g','-',' '},{'7','|','L','-'}};
		//char[][] board = {{'s','L'},{'7','g'}};
		State<char[][]> problem = new State<>(board);
	//	problem.setState(board);
		Searchable<char[][]> searchable = new PipeGameSearchable(problem);
		ArrayList<ArrayList<State<char[][]>>> solutions = new ArrayList<>();
		long startTime = 0, endTime = 0;
		Searcher<char[][]> searcherBestFirstSearch = new BestFirstSearch<>();
		Searcher<char[][]> searcherDFS = new DFS<>();
		Searcher<char[][]> searcherBFS = new BFS<>();
		LinkedList<Searcher<char[][]>> searches = new LinkedList<>();
		searches.add(searcherBestFirstSearch);
		searches.add(searcherBFS);
		searches.add(searcherDFS);
		for (Searcher<char[][]> search : searches) {
			startTime = System.nanoTime();
			solutions.add(search.search(searchable).getSolution());
			endTime = System.nanoTime() - startTime;
			System.out.println("Time took " + search.getClass() + " = " + endTime);
		}
		for(ArrayList<State<char[][]>> solution : solutions) {
			for (State<char[][]> state : solution)
			{
				for (int n=0; n<state.getState().length; n++)
					System.out.println(state.getState()[n]);
			}
		}
	}

}
