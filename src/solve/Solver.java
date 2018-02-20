package solve;

public interface Solver<solutionType,ProblemType> {
	public solutionType solveProblem (ProblemType problem);
	
}
