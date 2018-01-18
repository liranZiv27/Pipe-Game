package search;

import solve.Solution;


public interface Searcher<T> {
	public Solution<T> search(Searchable<T> searchable);

	public int getNumberOfNodesEvaluated();
}
