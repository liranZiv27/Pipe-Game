package server;

import java.util.Collection;

public interface Solver<T> {
	public Collection<T> solveProblem (T problem);
	
}
