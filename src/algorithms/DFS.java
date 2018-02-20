package algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

import search.CommonSearcher;
import search.Searchable;
import search.State;
import solve.Solution;

public class DFS<T> extends CommonSearcher<T>{

	@Override
	public Solution<T> search(Searchable<T> s) {
        HashSet<State<T>> visitedStates = new HashSet<>();//create a visited state array list
        State<T> state = s.getInitialState();
        Stack<State<T>> stack = new Stack<>();// Create a stack for DFS
        
        stack.push(state); // Push the current source node
         
        while(!stack.isEmpty()) 
        {
            // Pop a state from stack and add it to the solution
        	state = stack.pop();
            if (s.isGoalState(state)) {
            	return backTrace(s.getInitialState(), state);
           } 
           else {
                // if it is not visited put it in visited 
                 if (!visitedStates.contains(state))
                	 visitedStates.add(state);
                 //get all the popped state's neighbors
                 Iterator<State<T>> itr = s.getAllPossibleStates(state).iterator();
               
                 while (itr.hasNext()) {
                     state = itr.next();
                     if(!visitedStates.contains(state))
                         stack.push(state);
                 }  	
            }
          
        }
        return null;
	}


	@Override
	protected void setDeterminedCost(State<T> s) {
		// TODO Auto-generated method stub
		
	}
	

}
