package Search;

import java.util.PriorityQueue;

import Solve.Solution;

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
	
	@Override
	public abstract Solution<T> search(Searchable<T> s);
	
	@Override
	public int getNumberOfNodesEvaluated() {
	return evaluatedNodes;
	}
}
