package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import search.CommonSearcher;
import search.Searchable;
import search.State;
import solve.Solution;

public class HillClimbing<T> extends CommonSearcher<T>{

    private long timeToRun;	
    private StateGrader<T> grader;
    
    public HillClimbing(long timeToRun, StateGrader<T> grade) {
    	openList = new LinkedList<State<T>>();
        this.timeToRun = timeToRun;
        this.grader=grade;
    }

    @Override
    public Solution<T> search(Searchable<T> searchable) {
    	//Define the current state as an initial state
    	openList.add(searchable.getInitialState());

    	long time0 = System.currentTimeMillis();
    	
    	//Loop until the goal state is achieved or no more operators can be applied on the current state:
    	while ((System.currentTimeMillis() - time0 < timeToRun) && openList.size()>0) {

    		State<T> next = popOpenList();
    		closedSet.add(next);
    		
    		if(searchable.isGoalState(next)) {

    			return backTrace(searchable.getInitialState(), next);
    		}
    		
    		ArrayList<State<T>> neighbors = searchable.getAllPossibleStates(next);

    		if (Math.random() < 0.7) { // with a high probability
    			// find the best one

    			int grade = 0;
    			for (State<T> state : neighbors) {
                   // double g = state.getCost();
    				int g = grader.grade(state);
                    state.setCameFrom(next);//you forgot this line you idiot!!!!!!!
    				if (g > grade) {
    					grade = g;
    					next = state;
    					//add this step to the solution
    					//result.add
    					if(!closedSet.contains(state) && !openList.contains(state))
    					{
    						openList.add(state);
    					}
    					
    				}
    				else if(!closedSet.contains(state) && !openList.contains(state))
					{
						openList.add(state);
					}
    			}
    		}
    		
    		else 
    			next = neighbors.get(new Random().nextInt(neighbors.size()));
    	}
    	return null;
    }
}
