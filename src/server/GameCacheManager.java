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

import search.State;


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
	public void saveSolution(String problem, Collection<String> solution) {
		BufferedWriter writer = null;
		PrintWriter pWriter = null;

		String workingDirectory = System.getProperty("user.dir");
		String fname = Integer.toString(problem.hashCode());
		try
		{
		    writer = new BufferedWriter(new FileWriter(fname));
		    pWriter = new PrintWriter(workingDirectory + "\\src\\server\\" + fname +".txt");
		    for (String s : solution)
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
		
		// load
//		else
//		{
//			if (this.loadSolution(problem))
//				return cache.get(hash);
//		}
		
		// save 
		ArrayList<String> soulion = new ArrayList<>();
		soulion.add("1,2,3");
		soulion.add("1,2,3");
		soulion.add("1,2,3");
		saveSolution(problem, soulion);
		return null;
		}
}
