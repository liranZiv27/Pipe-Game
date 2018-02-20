package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import search.CommonSearcher;
import search.Searchable;
import search.State;
import solve.Solution;



public class BestFirstSearch<T> extends CommonSearcher<T> {
	@Override
	public Solution<T> search(Searchable<T> s) {
		this.openList = new PriorityQueue<>();
		this.openList.add(s.getInitialState());

		HashSet<State<T>> closedSet=new HashSet<State<T>>();
		
		while(openList.size()>0){
			State<T> n=this.popOpenList();// dequeue
			closedSet.add(n);
			
			if(s.isGoalState(n)) {
				return backTrace(s.getInitialState(), n);
			}
			// private method, back traces through the parents

			ArrayList<State<T>> successors=s.getAllPossibleStates(n); //however it is implemented

			for(int i=0;i<successors.size();i++){

				if(!closedSet.contains(successors.get(i)) && !openList.contains(successors.get(i))){
					successors.get(i).setCameFrom(n);
					this.openList.add(successors.get(i));
				} 
				else{
					if(n.getCost()>successors.get(i).getCost()) {//Otherwise, if this new path is better than previous one
						if(!openList.contains(successors.get(i))) {//i. If it is not in OPEN add it to OPEN.
							successors.get(i).setCameFrom(n);
							this.openList.add(successors.get(i));
						}
						else {//ii. Otherwise, adjust its priority in OPEN
							openList.remove(successors.get(i));
							openList.add(successors.get(i));
						}

					}

				}//end of else



			}//end of for
		}//end of while
		return null;
	}//end of Search	
}