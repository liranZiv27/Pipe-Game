package search;

import java.util.ArrayList;
import java.util.PriorityQueue;



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
	
	protected ArrayList<T> backTrace (State<T> initialState, State<T> goalState)
	{
		ArrayList<T> solution = new ArrayList<>();
		State<T> temp = goalState;
		while(temp.getCameFrom() != initialState) {
			solution.add(temp);
			temp = temp.getCameFrom();
		}
		solution.add(initialState);
		return solution;
	}
	
	
	@Override
	public abstract ArrayList<T> search(Searchable<T> s);
	
	@Override
	public int getNumberOfNodesEvaluated() {
	return evaluatedNodes;
	}
}
