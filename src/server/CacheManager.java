package server;

import java.util.Collection;

public interface CacheManager<T> {
	public boolean loadSolution(T problem);//doesn't have to be generic
	public void saveSolution(T problem , Collection<T> solution);
	public Collection<T> doesSolutionExist (T problem);
}
