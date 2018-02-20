package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import search.PipeParser;
import search.State;
import solve.Solution;
import solve.Solver;

public class StringSingleClientHandler extends SingleClientHandler<String>{
	
	CacheManager<String> cacheManager;
	Solver<char[][],State<ArrayList<String>>> solver;
	PipeParser p;
	
	public StringSingleClientHandler(CacheManager<String> cacheManager, Solver<char[][],State<ArrayList<String>>> solver) {
		this.cacheManager = cacheManager;
		this.solver = solver;
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		String line;
		p = new PipeParser();
		int boardRows=0,boardCols=0;
		StringBuilder stage = new StringBuilder();
		ArrayList<String> problem = new ArrayList<String>();
		ArrayList<String> solutionBitch = new ArrayList<String>();
		Solution<String> solution = new Solution<>();
		Solution<char[][]> solutionChar = new Solution<>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(outToClient));

		try {
			line = bufferedReader.readLine();
			while(!line.equals("done")){
				problem.add(line);
				line = bufferedReader.readLine();
				}
			for (String s : problem) {
				stage.append(s);
				boardRows++;
				boardCols = s.length();}
			if (cacheManager.doesSolutionExist(stage.toString()) == null) {
				State<ArrayList<String>> problemo = new State<>(problem);
				solutionChar = solver.solveProblem(problemo);
				for(State<char[][]> sol : solutionChar.getSolution()) {
					solution.add(p.parseToArray(sol));
				}
				for (State<String> string : solution.getSolution()) {
					solutionBitch.add(string.getState());
				}
				cacheManager.saveSolution(stage.toString(), solutionBitch, boardRows, boardCols);
				}
			solutionBitch = (ArrayList<String>) cacheManager.doesSolutionExist(stage.toString());
/*			for (String blue : solutionBitch) {
				System.out.println(blue);
			}*/
			for (String s : problem) {
				writer.write(s);
			}
//			bufferedReader.close();
//			writer.close();
		} catch (IOException e) { e.printStackTrace();}
	}
}