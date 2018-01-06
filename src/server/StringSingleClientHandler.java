package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class StringSingleClientHandler extends SingleClientHandler<String>{
	
	CacheManager<String> cacheManager;
	Solver<String> solver;
	
	public StringSingleClientHandler(CacheManager<String> cacheManager, Solver<String> solver) {
		this.cacheManager = cacheManager;
		this.solver = solver;
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		String line;
		StringBuilder stage = new StringBuilder();
		Collection<String> problem = new ArrayList<String>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(outToClient));
		try {
			line = bufferedReader.readLine();
			while(!line.equals("done")){
				problem.add(line);
				line = bufferedReader.readLine();
				}
			for (String s : problem)
				stage.append(s);
			if (cacheManager.doesSolutionExist(stage.toString()) == null) {
				problem = solver.solveProblem(stage.toString());//problem is now the solution
				cacheManager.saveSolution(stage.toString(), problem);
				}
			for (String s : problem) {
				writer.write(s);
			}
//			bufferedReader.close();
//			writer.close();
		} catch (IOException e) { e.printStackTrace();}
	}
}