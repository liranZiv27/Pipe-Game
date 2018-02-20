package search;

import java.util.ArrayList;
import java.util.HashSet;

public class PipeGameSearchable implements Searchable<CharMatrix> {
	public final State<CharMatrix> initialState;
	public PipeParser parser;
	public static int costForState;
	public ArrayList<State<CharMatrix>> possibleStates;
	public HashSet<State<CharMatrix>> hashStates;
	private int counter;

	public PipeGameSearchable(State<CharMatrix> problem) {
		this.parser = new PipeParser();
		this.initialState = problem;
		this.initialState.setCameFrom(null);
		this.possibleStates = new ArrayList<>();
		this.hashStates = new HashSet<>(); 
		this.counter = problem.getState().getMatrix().length*problem.getState().getMatrix()[0].length +7; // in case we in circle
		this.initialState.setCost(counter);
	}

	@Override
	public State<CharMatrix> getInitialState() {
		return this.initialState;
	}

	@Override
	public boolean isGoalState(State<CharMatrix> s) {
		Point end = endPos(s.getState());
		if (isGoalStateRecursion(s, new Point(-1,-1), end.getX(),end.getY(), 's',counter))
			return true;


		return false;
	}

	public boolean isGoalStateRecursion(State<CharMatrix> state, Point cameFrom, int row, int col, char dest,int count) {
		boolean found = false; 
		if (state.getState().getMatrix()[row][col] == dest) {
			state.setCost(count);
			return true;
		}
		if(count ==0) {
			return false;
		}
		if (row == 0 && col == 0) //in case its the upper left corner 
		{
			if(col+1<state.getState().getMatrix()[0].length) {
				if (checkRight(state.getState().getMatrix()[row][col]) && isLeft(state.getState().getMatrix()[row][col+1]) && (cameFrom.getY() != col+1) && found == false) // right 
					found = isGoalStateRecursion(state, new Point(row, col), row , col+1,dest,count-1);
			}
			if( row+1 <state.getState().getMatrix().length) {
				if (checkDown(state.getState().getMatrix()[row][col]) && isUp(state.getState().getMatrix()[row+1][col]) && (cameFrom.getX() != row+1)&& found == false) // down 
					found = isGoalStateRecursion(state, new Point(row, col), row+1, col,dest,count-1);
			}
		}
		else if (row == 0 && col == state.getState().getMatrix()[0].length-1 ) // in case its upper right corner
		{
			if(col-1>=0) {
				if (checkLeft(state.getState().getMatrix()[row][col]) && isRight(state.getState().getMatrix()[row][col-1]) && (cameFrom.getY() != col-1)&& found == false) // left 
					found = isGoalStateRecursion(state, new Point(row, col), row , col-1,dest,count-1);
			}
			if(row+1<state.getState().getMatrix().length) {
				if (checkDown(state.getState().getMatrix()[row][col]) && isUp(state.getState().getMatrix()[row+1][col])&&(cameFrom.getX() != row+1)&& found == false) // down
					found = isGoalStateRecursion(state, new Point(row, col), row+1, col,dest,count-1);
			}
		}
		else if (row == state.getState().getMatrix().length-1 && col == 0) // in case its bottom left corner
		{
			if(row-1>=0) {
				if (checkUp(state.getState().getMatrix()[row][col]) && isDown(state.getState().getMatrix()[row-1][col])&& (cameFrom.getX() != row-1)&& found == false) // up 
					found = isGoalStateRecursion(state, new Point(row, col), row-1 , col,dest,count-1);
			}
			if(col+1<state.getState().getMatrix()[0].length) {
				if (checkRight(state.getState().getMatrix()[row][col]) && isLeft(state.getState().getMatrix()[row][col+1]) && (cameFrom.getY() != col+1)&& found == false) // right
					found = isGoalStateRecursion(state, new Point(row, col), row, col+1,dest,count-1);
			}
		}
		else if (row == state.getState().getMatrix().length-1 && col == state.getState().getMatrix()[0].length-1) // in case its bottom right corner
		{
			if(row-1>=0) {
				if (checkUp(state.getState().getMatrix()[row][col]) && isDown(state.getState().getMatrix()[row-1][col]) && (cameFrom.getX() != row-1)&& found == false) // up 
					found = isGoalStateRecursion(state, new Point(row, col), row-1 , col,dest,count-1);
			}
			if(col-1>=0) {
				if (checkLeft(state.getState().getMatrix()[row][col]) && isRight(state.getState().getMatrix()[row][col-1])&& (cameFrom.getY() != col-1)&& found == false) // left
					found = isGoalStateRecursion(state, new Point(row, col), row, col-1,dest,count-1);
			}
		}

		else if (col ==0)// in case its the left column and not a corner
		{
			if(row-1>=0) {
				if (checkUp(state.getState().getMatrix()[row][col]) && isDown(state.getState().getMatrix()[row-1][col]) && (cameFrom.getX() != row-1)&& found == false) // up 
					found = isGoalStateRecursion(state, new Point(row, col), row-1 , col,dest,count-1);
			}
			if( row+1 <state.getState().getMatrix().length) {
				if (checkDown(state.getState().getMatrix()[row][col]) && isUp(state.getState().getMatrix()[row+1][col]) && (cameFrom.getX() != row+1)&& found == false) // down
					found = isGoalStateRecursion(state, new Point(row, col), row+1, col,dest,count-1);
			}
			if(col+1<state.getState().getMatrix()[0].length) {
				if (checkRight(state.getState().getMatrix()[row][col]) && isLeft(state.getState().getMatrix()[row][col+1]) && (cameFrom.getY() != col+1)&& found == false) // right
					found = isGoalStateRecursion(state, new Point(row, col), row, col+1,dest,count-1);
			}
		}
		else if (col == state.getState().getMatrix()[0].length-1) // in case its the right column and not a corner
		{
			if(row-1>=0) {
				if (checkUp(state.getState().getMatrix()[row][col]) && isDown(state.getState().getMatrix()[row-1][col]) && (cameFrom.getX() != row-1)&& found == false) // up 
					found = isGoalStateRecursion(state, new Point(row, col), row-1 , col,dest,count-1);
			}
			if( row+1 <state.getState().getMatrix().length) {
				if (checkDown(state.getState().getMatrix()[row][col]) && isUp(state.getState().getMatrix()[row+1][col]) && (cameFrom.getX() != row+1)&& found == false) // down
					found = isGoalStateRecursion(state, new Point(row, col), row+1, col,dest,count-1);
			}
			if(col-1>=0) {
				if (checkLeft(state.getState().getMatrix()[row][col]) && isRight(state.getState().getMatrix()[row][col-1]) && (cameFrom.getY() != col-1)&& found == false) // left
					found = isGoalStateRecursion(state, new Point(row, col), row, col-1,dest,count-1);
			}
		}
		else if (row == 0 ) // in case its the upper row and not a corner
		{
			if(col+1<state.getState().getMatrix()[0].length) {
				if (checkRight(state.getState().getMatrix()[row][col]) && isLeft(state.getState().getMatrix()[row][col+1]) && (cameFrom.getY() != col+1)&& found == false) // right
					found = isGoalStateRecursion(state, new Point(row, col), row, col+1,dest,count-1);
			}
			if(col-1>=0) {
				if (checkLeft(state.getState().getMatrix()[row][col]) && isRight(state.getState().getMatrix()[row][col-1]) && (cameFrom.getY() != col-1)&& found == false) // left
					found = isGoalStateRecursion(state, new Point(row, col), row, col-1,dest,count-1);
			}
			if( row+1 <state.getState().getMatrix().length) {
				if (checkDown(state.getState().getMatrix()[row][col]) && isUp(state.getState().getMatrix()[row+1][col]) && (cameFrom.getX() != row+1)&& found == false) // down
					found = isGoalStateRecursion(state, new Point(row, col), row+1, col,dest,count-1);
			}
		}
		else if (row == state.getState().getMatrix().length-1  ) // in case its the bottom row and not a corner
		{
			if(col+1<state.getState().getMatrix()[0].length) {
				if (checkRight(state.getState().getMatrix()[row][col]) && isLeft(state.getState().getMatrix()[row][col+1]) && (cameFrom.getY() != col+1)&& found == false) // right
					found = isGoalStateRecursion(state, new Point(row, col), row, col+1,dest,count-1);
			}
			if(col-1>=0) {
				if (checkLeft(state.getState().getMatrix()[row][col]) && isRight(state.getState().getMatrix()[row][col-1]) && (cameFrom.getY() != col-1)&& found == false) // left
					found = isGoalStateRecursion(state, new Point(row, col), row, col-1,dest,count-1);
			}
			if(row-1>=0) {
				if (checkUp(state.getState().getMatrix()[row][col]) && isDown(state.getState().getMatrix()[row-1][col]) && (cameFrom.getX() != row-1)&& found == false) // up 
					found = isGoalStateRecursion(state, new Point(row, col), row-1 , col,dest,count-1);
			}
		}
		else // all the other options
		{
			if(col+1<state.getState().getMatrix()[0].length) {
				if (checkRight(state.getState().getMatrix()[row][col]) && isLeft(state.getState().getMatrix()[row][col+1]) && (cameFrom.getY() != col+1)&& found == false) // right
					found = isGoalStateRecursion(state, new Point(row, col), row, col+1,dest,count-1);
			}
			if(col-1>=0) {
				if (checkLeft(state.getState().getMatrix()[row][col]) && isRight(state.getState().getMatrix()[row][col-1]) && (cameFrom.getY() != col-1)&& found == false) // left
					found = isGoalStateRecursion(state, new Point(row, col), row, col-1,dest,count-1);
			}
			if(row-1>=0) {
				if (checkUp(state.getState().getMatrix()[row][col]) && isDown(state.getState().getMatrix()[row-1][col]) && (cameFrom.getX() != row-1)&& found == false) // up 
					found = isGoalStateRecursion(state, new Point(row, col), row-1 , col,dest,count-1);
			}
			if( row+1 <state.getState().getMatrix().length) {
				if (checkDown(state.getState().getMatrix()[row][col]) && isUp(state.getState().getMatrix()[row+1][col]) && (cameFrom.getX() != row+1)&& found == false) // down
					found = isGoalStateRecursion(state, new Point(row, col), row+1, col,dest,count-1);
			}
		}

		return found;
	}


	public boolean checkpath(State<CharMatrix> s, Point cameFrom, int row, int col)
	{
		if (isGoalStateRecursion(s, cameFrom, row, col, 'g',counter))
			return true;
		else
			return false;
	}


	public CharMatrix deepcopy(State<CharMatrix> s) {

		char[][] copy = new char[s.getState().getMatrix().length][s.getState().getMatrix()[0].length];

		for(int i=0;i<s.getState().getMatrix().length;i++) {
			for(int j=0;j<s.getState().getMatrix()[0].length;j++) {
				copy[i][j] = s.getState().getMatrix()[i][j];
			}
		}
		CharMatrix copy2 = new CharMatrix(copy);

		return copy2;
	}
	
	@Override
	public void setAllPossibleStates(State<CharMatrix> s) 
	{
		State<CharMatrix> newState = new State<CharMatrix>();
		State<CharMatrix> temp = new State<CharMatrix>(deepcopy(s));
		int pipeLen;


		for(int i=0;i<s.getState().getMatrix().length;i++)
		{
			for (int j=0;j<s.getState().getMatrix()[i].length;j++)
			{
				if (isLegal(s.getState().getMatrix()[i][j])) // check if the char is a pipe , s g or space are not a pipe 
				{
					pipeLen = checkAllpossibilities(temp.getState().getMatrix()[i][j]);
					for (int k=0;k<pipeLen;k++) {
						newState = new State<CharMatrix>(deepcopy(temp));
					
						newState.getState().getMatrix()[i][j] =	rotate(newState.getState().getMatrix()[i][j]); // rotate the pipe	

						if (!hashStates.contains(newState))
						{
							this.hashStates.add(newState);//put the stage into the state hash set

							if (checkpath(newState, new Point(-1,-1), i, j))
							{
								this.possibleStates.add(newState);
							}
						}
						temp = new State<CharMatrix>(deepcopy(newState));
					}
					temp = new State<CharMatrix>(deepcopy(s));
				}
			}
		}
	}

	@Override
	public ArrayList<State<CharMatrix>> getAllPossibleStates(State<CharMatrix> s) {
		this.possibleStates= new ArrayList<>();
		setAllPossibleStates(s);
		return this.possibleStates;
	}

	public State<CharMatrix> copyState(State<CharMatrix> s)
	{

		char [][] temp = new char[s.getState().getMatrix().length][s.getState().getMatrix()[0].length];
		for (int i=0;i<s.getState().getMatrix().length;i++)
		{
			for(int j=0;j<s.getState().getMatrix()[i].length;j++)
			{
				temp[i][j] = s.getState().getMatrix()[i][j];		
			}
		}
		CharMatrix temp2 = new CharMatrix(temp);
		State<CharMatrix> newState = new State<>(temp2);

		return newState;
	}

	public boolean isLegal(char value) {
		switch (value) {
		case '|':
			return true;
		case '-':
			return true;
		case 'F':
			return true;
		case '7':
			return true;
		case 'L':
			return true;
		case 'J':
			return true;
		default:
			return false;
		}
	}
	public char rotate(char charToRotate) {
		switch (charToRotate) {
		case '|': {
			charToRotate = '-';
			return charToRotate;
		}
		case '-': {
			charToRotate = '|';
			return charToRotate;
		}
		case '7': {
			charToRotate = 'J';
			return charToRotate;
		}
		case 'J': {
			charToRotate = 'L';
			return charToRotate;
		}
		case 'F': {
			charToRotate = '7';
			return charToRotate;
		}
		case 'L':{
			charToRotate = 'F';
			return charToRotate;
		}

		default:
			return charToRotate;
		}

	}
	public boolean isLeft(char value) {
		switch (value) {
		case '-':
			return true;
		case 'J':
			return true;
		case 's':
			return true;
		case 'g':
			return true;
		case '7':
			return true;
		default:
			return false;
		}
	}
	public boolean isUp(char value) {
		switch (value) {
		case '|':
			return true;
		case 'J':
			return true;
		case 'L':
			return true;
		case 's':
			return true;
		case 'g':
			return true;
		default:
			return false;
		}
	}
	public boolean isRight(char value) {
		switch (value) {
		case '-':
			return true;
		case 'L':
			return true;
		case 'F':
			return true;
		case 's':
			return true;
		case 'g':
			return true;
		default:
			return false;
		}
	}
	public boolean isDown(char value) {
		switch (value) {
		case '7':
			return true;
		case 'F':
			return true;
		case '|':
			return true;
		case 's':
			return true;
		case 'g':
			return true;
		default:
			return false;
		}
	}
	public boolean checkRight(char right)
	{
		switch(right)
		{
		case '-':
			return true;
		case 'F':
			return true;
		case 'L':
			return true;
		case 's':
			return true;
		case 'g':
			return true;
		default:
			return false;
		}
	}
	public boolean checkDown(char down)
	{
		switch(down)
		{
		case '|':
			return true;
		case 'F':
			return true;
		case '7':
			return true;
		case 's':
			return true;
		case 'g':
			return true;
		default:
			return false;
		}
	}
	public boolean checkUp(char up)
	{
		switch(up)
		{
		case '|':
			return true;
		case 'J':
			return true;
		case 'L':
			return true;
		case 's':
			return true;
		case 'g':
			return true;
		default:
			return false;
		}
	}
	public boolean checkLeft(char left)
	{
		switch(left)
		{
		case '-':
			return true;
		case 'J':
			return true;
		case '7':
			return true;
		case 's':
			return true;
		case 'g':
			return true;
		default:
			return false;
		}
	}
	public Point startPos(CharMatrix board){
		for (int i=0;i<board.getMatrix().length;i++) {
			for(int j=0; j<board.getMatrix()[0].length;j++) {
				if (board.getMatrix()[i][j] == 's') {
					return new Point(i,j);
				}
			}
		}
		return new Point(-1,-1);
	}
	public Point endPos(CharMatrix board){
		for (int i=0;i<board.getMatrix().length;i++) {
			for(int j=0; j<board.getMatrix()[0].length;j++) {
				if (board.getMatrix()[i][j] == 'g') {
					return new Point(i,j);
				}
			}
		}
		return new Point(-1,-1);
	}



	@Override
	public double increaseCost() {
		return 1;
	}


	public int checkAllpossibilities(char pipe)

	{
		if (pipe == 'L' || pipe == 'F' || pipe == '7' || pipe == 'J')
			return 4;
		else if (pipe == '|' || pipe == '-')
			return 2;
		else 
			return 0;
	}
}

