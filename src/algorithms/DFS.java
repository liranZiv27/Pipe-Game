package algorithms;

import java.util.Stack;
import java.util.Vector;

import search.CommonSearcher;
import search.Searchable;
import search.State;
import solve.Solution;

public class DFS<T> extends CommonSearcher<T>{

	@Override
	public Solution<T> search(Searchable<T> s) {
		// Initially mark all vertices as not visited
        Vector<Boolean> visited = new Vector<Boolean>();
        State<T> state = s.getInitialState();
        for (int i = 0; i < V; i++)
            visited.add(false);
 
        // Create a stack for DFS
        Stack<State<T>> stack = new Stack<>();
         
        // Push the current source node
        stack.push(s);
         
        while(stack.empty() == false)
        {
            // Pop a vertex from stack and print it
            s = stack.peek();
            stack.pop();
             
            // Stack may contain same vertex twice. So
            // we need to print the popped item only
            // if it is not visited.
            if(visited.get(s) == false)
            {
                System.out.print(s + " ");
                visited.set(s, true);
            }
             
            // Get all adjacent vertices of the popped vertex s
            // If a adjacent has not been visited, then puah it
            // to the stack.
            Iterator<Integer> itr = adj[s].iterator();
             
            while (itr.hasNext()) 
            {
                int v = itr.next();
                if(!visited.get(v))
                    stack.push(v);
            }
             
        }	}

	@Override
	protected void setDeterminedCost(State<T> s) {
		// TODO Auto-generated method stub
		
	}
	

}
