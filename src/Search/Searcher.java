package Search;

import Solve.Solution;

public interface Searcher<T> {
	public Solution<T> search(Searchable<T> searchable);

	public int getNumberOfNodesEvaluated();
}
