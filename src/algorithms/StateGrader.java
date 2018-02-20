package algorithms;

import search.State;

public interface StateGrader<T> {
	int grade(State<T> state);
}
