package search;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

import solve.Solution;



public abstract class CommonSearcher<T> implements Searcher<T>{
	
	protected Queue<State<T>> openList;
	private int evaluatedNodes;
	protected HashSet<State<T>> closedSet = new HashSet<>();
	
	public CommonSearcher() {
	this.openList = new PriorityQueue<State<T>>();
	evaluatedNodes=0;
	
	}
	
	protected State<T> popOpenList() {
	evaluatedNodes++;
	return openList.poll();
	}

	protected void addToOpenList(State<T> state) {
		this.openList.add(state);
	}
	
	protected Solution<T> backTrace (State<T> initialState, State<T> goalState)
	{
		Solution<T> solution = new Solution<>();
		int i=0;
	
		
		while(!(goalState.equals(initialState))) {
			//System.out.println("backtrace counter: "+ i++);
			solution.add(goalState);
			goalState = goalState.getCameFrom();
		}
		solution.add(initialState);
		return solution;
	}
	
	
	@Override
	public int getNumberOfNodesEvaluated() {
	return evaluatedNodes;
	}


}
