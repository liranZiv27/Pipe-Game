package server;

import java.util.ArrayList;

import search.PipeGameSearchable;
import search.State;
import solve.PipeSolver;

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
		
		
		ArrayList<String> board = new ArrayList<>();
			board.add("s-7");
			board.add(" F|");
			board.add(" g-");
			//F--7|  |g sJ
		State<ArrayList<String>> problem = new State<>(board);		
		PipeGameSearchable pgs = new PipeGameSearchable(problem);
		//System.out.println(pgs.isGoalState(pgs.initialState));
		//pgs.setAllPossibleStates(pgs.initialState);
		System.out.println(pgs.getAllPossibleStates(pgs.initialState));
		
	

		
	}

}
