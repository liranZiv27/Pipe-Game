package algorithms;

import search.CharMatrix;
import search.State;

public class CostGrader implements StateGrader<CharMatrix>{
		@Override
		public int grade(State<CharMatrix> state) {
			int temp =	state.getState().getMatrix().length*state.getState().getMatrix()[0].length+5;
			return (int) (temp-state.getCost());
		}
}
