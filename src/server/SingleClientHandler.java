package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import search.CharMatrix;
import search.PipeParser;
import solve.PipeSolver;
import solve.Solver;

public class SingleClientHandler implements ClientHandler{
	
	CacheManager<String> cacheManager;
	Solver<ArrayList<String>,CharMatrix> solver;
	PipeParser p;
	
	public SingleClientHandler(CacheManager<String> cacheManager, Solver<ArrayList<String>,CharMatrix> solver) {
		this.cacheManager = cacheManager;
		this.solver = solver;
	}
	public SingleClientHandler() {
		super();
	}
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		this.cacheManager = new GameCacheManager();
		this.solver = new PipeSolver();
		String line;
		p = new PipeParser();
		ArrayList<String> problem = new ArrayList<String>();
		CharMatrix problemCharMatrix;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(outToClient), true);
		ArrayList<String> solution = new ArrayList<>();
		
		try {
			line = bufferedReader.readLine();
			while(!line.equals("done")){
				problem.add(line);
				line = bufferedReader.readLine();
				}
			if (cacheManager.doesSolutionExist(problem.toString()) == null) {
				problemCharMatrix = p.parse(problem);
				solution = solver.solveProblem(problemCharMatrix);
				cacheManager.saveSolution(problem.toString(), solution);
				}
			else {
				solution = (ArrayList<String>) cacheManager.doesSolutionExist(problem.toString());
			}
				for (String s : solution)
					writer.println(s);
				writer.println("done");
		} catch (IOException e) {
			//e.printStackTrace();}
		}
	}
}
