package search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import solve.Solution;



public abstract class CommonSearcher<T> implements Searcher<T>{
	
	protected PriorityQueue<State<T>> openList;
	private int evaluatedNodes;
	
	public CommonSearcher() {
	openList=new PriorityQueue<State<T>>();
	evaluatedNodes=0;
	}
	
	protected State<T> popOpenList() {
	evaluatedNodes++;
	return openList.poll();
	}

	protected void addToOpenList(State<T> state) {
		openList.add(state);
	}
	
	protected Solution<T> backTrace (State<T> initialState, State<T> goalState)
	{
		Solution<T> solution = new Solution<>();
		State<T> temp = goalState;
		while(temp.getCameFrom() != initialState) {
			solution.add(temp);
			temp = temp.getCameFrom();
		}
		solution.add(initialState);
		return solution;
	}
	
	
	@Override
	public abstract Solution<T> search(Searchable<T> s);
	
	@Override
	public int getNumberOfNodesEvaluated() {
	return evaluatedNodes;
	}

	protected abstract void setDeterminedCost(State<T> s);
	
	protected void adjustPriority(PriorityQueue<State<T>> openList, State<T> state) {
		Iterator<State<T>> itr = openList.iterator();
		State<T> checkedState = null;
		
		while(itr.hasNext()){
			checkedState = itr.next();
			if(checkedState.getState().equals(state.getState()))
			{
				openList.remove(checkedState);
				setDeterminedCost(checkedState);
				openList.add(checkedState);
				return;
			}
			
		}
	}
}
