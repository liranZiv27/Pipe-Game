package algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import search.CommonSearcher;
import search.Searchable;
import search.State;
import solve.Solution;

public class BFS<T> extends CommonSearcher<T> { 

	@Override
	public Solution<T> search(Searchable<T> s) {
		 // Mark all the vertices as not visited(By default
   		State<T> st = s.getInitialState();
		ArrayList<State<T>> visited = new ArrayList<>();
        // Mark the current node as visited and enqueue it
        visited.add(st);
        openList.add(st);
 
        while (openList.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            st = popOpenList();
            if(s.isGoalState(st))
				return backTrace(s.getInitialState(), st);
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            else {
            Iterator<State<T>> i = s.getAllPossibleStates(st).listIterator();
            while (i.hasNext())
            {
                st = (i.next());
                if (!visited.contains(st))
                {
                	openList.add(st);
                }
            }
           }
       }
		return null;
}
	@Override
	protected void setDeterminedCost(State<T> s) {
		s.setCost(s.getCameFrom().getCost() + s.getCost());
	}
}
