package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class GameCacheManager implements CacheManager<String> {
	
	HashMap<Integer, ArrayList<String>> cache;
	
	public GameCacheManager() {
		this.cache = new HashMap<>();
	}
	
	@Override
	public boolean loadSolution(String problem) {//This method checks if the solution exists as a file,
		//if it does, it'll be loaded into the hashmap and return true, and if it doesn't, return false
		
		String workingDirectory = System.getProperty("user.dir");
        File file = new File(workingDirectory+"\\src\\server", Integer.toString(problem.hashCode())+".txt");
		
        if (file.isFile())
        {
          	ArrayList <String> tempList = new ArrayList<>();
        	BufferedReader reader = null;
        	String line;
        	try {
				reader = new BufferedReader(new FileReader(file));
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try {
        		
				while ((line  = reader.readLine()) != null) {
						if (! (line.equals("done")))
							tempList.add(line);}
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        		cache.put(problem.hashCode(), tempList);
        			return true;
        			}
        else
        	return false;		
   }

	@Override
	public void saveSolution(String problem, Collection<String> solution, int rows, int cols) {
		BufferedWriter writer = null;
		PrintWriter pWriter = null;
		String workingDirectory = System.getProperty("user.dir");
		String fname = Integer.toString(problem.hashCode());
		ArrayList<String> steps = new ArrayList<>();
		try
		{
		    writer = new BufferedWriter(new FileWriter(fname));
		    pWriter = new PrintWriter(workingDirectory + "\\src\\server\\" + fname +".txt");
		    steps = parseToSteps(solution.toArray()[solution.size()-1].toString(), solution.toArray()[0].toString(), rows, cols);
		    for (String s : steps)
		    {
		    	if(!s.equals("done")) {
		    	//	writer.write(s);
		    		//pWriter.println(s);
		    		pWriter.println(s);
		    		}
		    	else
		    		break;
		    }
		    writer.close();
		    pWriter.close();   
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Collection<String> doesSolutionExist(String problem) {
		int hash = problem.hashCode();
		if (cache.containsKey(hash)) {
			return cache.get(hash);
		}		
		 else
		{
			if (this.loadSolution(problem))
				return cache.get(hash);
		}
		return null;
	}

	public ArrayList<String> parseToSteps (String initialState, String goalState, int rows, int cols){
		int moves = 0, index=0;
		String pipes = "JLF7";
		StringBuilder steps = new StringBuilder();
		ArrayList<String> solution = new ArrayList<String>();
		initialState = initialState.substring(1, initialState.length()-1);
		goalState = goalState .substring(1, goalState .length()-1);
		initialState = initialState.replaceAll(", ", "");
		goalState = goalState.replaceAll(", ", "");
		for (int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				if(pipes.indexOf(initialState.charAt(index)) != -1){
				moves = (pipes.indexOf(goalState.charAt(index))+1) - (pipes.indexOf(initialState.charAt(index))+1);
				if (moves > 0) {
					steps.append(i + "," + j + "," + moves);
					solution.add(steps.toString());
					steps = new StringBuilder();
				}
				else if (moves < 0) {
					moves+=4;
					steps.append(i + "," + j + "," + moves);
					solution.add(steps.toString());
					steps = new StringBuilder();
				}
			}
				else if(initialState.charAt(index) != 's' && initialState.charAt(index) != 'g' && initialState.charAt(index) != ' '){
					if (initialState.charAt(index) != goalState.charAt(index)) {
						steps.append(i + "," + j + "," + "1");
						solution.add(steps.toString());
						steps = new StringBuilder();
					}
				}
				moves = 0;
				index++;
			}
		}
		return solution;
	}

}



