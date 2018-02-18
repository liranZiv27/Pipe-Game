package server;


import algorithms.BestFirstSearch;
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
		
		char[][] board = {{'s','J',' '},{' ','-','L'},{'-','g',' '}};
		//char[][] board = {{'s','J',' ','-'},{' ','-','L','-'},{'-','g','-',' '},{'7','|','L','-'}};
		//char[][] board = {{'s','L'},{'7','g'}};
		State<char[][]> problem = new State<>(board);
	//	problem.setState(board);
		Searchable<char[][]> searchable = new PipeGameSearchable(problem);

		Searcher<char[][]> searcher = new BestFirstSearch<>();
		for(State<char[][]> state : searcher.search(searchable).getSolution()) {
			for(int i=0; i<state.getState().length; i++) {
					System.out.print(state.getState()[i]);
					System.out.println();
				}
			}
		
	}

}
