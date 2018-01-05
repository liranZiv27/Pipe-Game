package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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

		URL path = GameCacheManager.class.getResource(Integer.toString(problem.hashCode()));
        File file = new File(path.getFile());
        BufferedReader reader = null;
    	ArrayList <String> tempList = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
    	for (String s : tempList) {
			try {
				s = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		tempList.add(s);
    	}
        	cache.put(problem.hashCode(), tempList);
        	
    	try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	return true;		
   }

	@Override
	public void saveSolution(String problem, Collection<String> solution) {
		BufferedWriter writer = null;
		String fname = Integer.toString(problem.hashCode());
		try
		{
		    writer = new BufferedWriter(new FileWriter(fname));
		    for (String s : solution)
		    {
		    	if(!s.equals("done"))
		    		writer.write(s);
		    	else
		    		break;
		    }
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public Collection<String> doesSolutionExist(String problem) {
		if (cache.containsKey(problem.hashCode())) {
			return cache.get(problem.hashCode());
		}
		else
		{
			if (this.loadSolution(problem))
				return cache.get(problem.hashCode());
			}
		return null;
		}
}
