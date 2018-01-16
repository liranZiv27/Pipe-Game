package server;

import java.util.ArrayList;

import Search.PipeGameSearchable;
import Search.State;
import Solve.PipeSolver;

public class MainTrain {

	public static void main(String[] args) {
		
//		SingleClientServer myServer = new SingleClientServer(6400, new StringSingleClientHandler(new GameCacheManager(), new PipeSolver()));
//		try {
//			myServer.start();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // should be in a different thread…
//		// ... wait for administrator to close
//		myServer.stop();
//		
		
		ArrayList<String> board = new ArrayList<>();
			board.add("F--7");
			board.add("|  -");
			board.add("g sJ");
			//F--7|  |g sJ
		State<ArrayList<String>> problem = new State<>(board);		
		PipeGameSearchable pgs = new PipeGameSearchable(problem);
		System.out.println(pgs.isGoalState(pgs.initialState));
		
	}

}
