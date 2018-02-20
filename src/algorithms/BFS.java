package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

import search.CommonSearcher;
import search.Searchable;
import search.State;
import solve.Solution;

public class BFS<T> extends CommonSearcher<T> {


	@Override
	public Solution<T> search(Searchable<T> s) {
		this.openList=new LinkedList<State<T>>();
		openList.add(s.getInitialState());
		while(!openList.isEmpty()) {
			State<T> n =popOpenList();
			closedSet.add(n);

			if(s.isGoalState(n))
				return backTrace(s.getInitialState(), n);

			ArrayList<State<T>> successors=s.getAllPossibleStates(n);

			for(State<T> state : successors){
				if((!(closedSet.contains(state))) && (!(openList.contains(state))))
				{
					state.setCameFrom(n);
					addToOpenList(state);
				}
			}

		}
		return null;
	}
}