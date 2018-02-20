package algorithms;

import java.util.ArrayList;
import java.util.Stack;

import search.CommonSearcher;
import search.Searchable;
import search.State;
import solve.Solution;

public class DFS<T> extends CommonSearcher<T>{
	
	@Override
	public Solution<T> search(Searchable<T> searchable) {

		Stack<State<T>> openList=new Stack<State<T>>();
		openList.push(searchable.getInitialState());
		while(!openList.isEmpty()) {
			State<T> n =openList.pop();
		//	this.evaluatedNodes++;
			closedSet.add(n);

			if(searchable.isGoalState(n)) {
				//System.out.println("number of evaluted: "+this.evaluatedNodes);
				return backTrace(searchable.getInitialState(), n);
			}
			ArrayList<State<T>> successors=searchable.getAllPossibleStates(n);

			for(State<T> state : successors){
				if((!(closedSet.contains(state))) && (!(openList.contains(state))))
				{
					state.setCameFrom(n);
					openList.push(state);
				}
			}

		}
		return null;
	}
}
	
	
	
	
	
	
	
//	public Solution<T> search(Searchable<T> s) {
//        ArrayList<State<T>> visitedStates = new ArrayList<>();//create a visited state array list
//        State<T> state = s.getInitialState();
//        Stack<State<T>> stack = new Stack<>();// Create a stack for DFS
//        stack.push(state); // Push the current source node
//         
//        while(!stack.isEmpty())
//        {
//            // Pop a state from stack and add it to the solution
//        	state = stack.pop();
//            if (s.isGoalState(state)) {
//            	return backTrace(s.getInitialState(), state);
//           } 
//           else {
//                // if it is not visited put it in visited 
//                // if (!visitedStates.contains(state))
//                	 visitedStates.add(state);
//                 //get all the popped state's neighbors
//                 ArrayList<State<T>> succesors = s.getAllPossibleStates(state);
//               
//                 for(State<T> st: succesors) {
//                     if (!visitedStates.contains(st) && !stack.contains(st))
//                     {
//                    	 st.setCameFrom(state);
//                    	 stack.push(st);
//                     }
//                 }  	
//            }
//          
//        }
//        return null;
//	}
//}
