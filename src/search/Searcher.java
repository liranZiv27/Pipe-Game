package search;

import java.util.ArrayList;


public interface Searcher<T> {
	public ArrayList<T> search(Searchable<T> searchable);

	public int getNumberOfNodesEvaluated();
}
