package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import search.CommonSearcher;
import search.Searchable;
import search.State;
import solve.Solution;

public class BestFirstSearch<T> extends CommonSearcher<T> { 

	@Override
	public Solution<T> search(Searchable<T> s) {
			addToOpenList(s.getInitialState());
			HashSet<State<T>> closedSet = new HashSet<State<T>>();
			while(openList.size() > 0){
				State<T> n = popOpenList();// dequeue
				closedSet.add(n);
				if(s.isGoalState(n))
					return backTrace(s.getInitialState(), n);
		
				ArrayList<State<T>> successors = s.getAllPossibleStates(n);
				for(State<T> state: successors){
					if(!closedSet.contains(state) && !openList.contains(state)){
						state.setCameFrom(n);
						setDeterminedCost(state);
						addToOpenList(state);
						}
					else {
					if(state.getCost() < (n.getCost() + s.increaseCost())) {		
						if(!openList.contains(state)){
							setDeterminedCost(state);
							state.setCameFrom(n);
							addToOpenList(state);
						}
						else {
							adjustPriority(openList,state);
						}	
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

