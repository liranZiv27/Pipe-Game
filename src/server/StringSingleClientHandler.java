package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class StringSingleClientHandler extends SingleClientHandler<String>{
	
	CacheManager<String> cacheManager;
	
	public StringSingleClientHandler(CacheManager<String> cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		String line;
		StringBuilder stage = new StringBuilder();
		List<String> problem = new ArrayList<String>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inFromClient));
		try {
			line = bufferedReader.readLine();
			while(!line.equals("done")){
				problem.add(line);
			}
			for (String s : problem)
				stage.append(s);
			if (cacheManager.doesSolutionExist(stage.toString()) == null) {
				//Solver
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}