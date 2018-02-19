package search;

import java.util.ArrayList;
import java.util.HashSet;

public class PipeGameSearchable implements Searchable<char[][]> {
	public final State<char[][]> initialState;
	public PipeParser parser;
	public static int costForState;
	public ArrayList<State<char[][]>> possibleStates;
	public HashSet<String> hashStates;

	public PipeGameSearchable(State<char[][]> problem) {
		this.parser = new PipeParser();
		//this.initialState = this.parser.parse(problem);
		this.initialState = problem;
		this.possibleStates = new ArrayList<>();
		this.hashStates = new HashSet<>(); 
	}

	@Override
	public State<char[][]> getInitialState() {
		return this.initialState;
	}

	@Override
	public boolean isGoalState(State<char[][]> s) {
		Point end = endPos(s.getState());
		if (isGoalStateRecursion(s.getState(), new Point(-1,-1), end.getX(),end.getY()))
			return true;


		return false;
	}

	public boolean isGoalStateRecursion(char[][] board, Point cameFrom, int row, int col) {
		boolean found = false; 
		if (board[row][col] == 's')
			return true;
		if (row == 0 && col == 0) //in case its the upper left corner 
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right 
				found = isGoalStateRecursion(board, new Point(row, col), row , col+1);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down 
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}
		else if (row == 0 && col == board[0].length-1) // in case its upper right corner
		{
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left 
				found = isGoalStateRecursion(board, new Point(row, col), row , col-1);
			if (checkDown(board[row][col]) && isUp(board[row+1][col])&&(cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}
		else if (row == board.length-1 && col == 0) // in case its bottom left corner
		{
			if (checkUp(board[row][col]) && isDown(board[row-1][col])&& (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
		}
		else if (row == board.length-1 && col == board[0].length-1) // in case its bottom right corner
		{
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1])&& (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
		}

		else if (col ==0)// in case its the left column and not a corner
		{
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
		}
		else if (col == board[0].length-1) // in case its the right column and not a corner
		{
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
		}
		else if (row == 0) // in case its the upper row and not a corner
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}
		else if (row == board.length-1) // in case its the bottom row and not a corner
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
		}
		else // all the other options
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}

		return found;

	}


	@Override
	public void setAllPossibleStates(State<char[][]> s) {
		State<char [][]> newState = new State<char[][]>();
		State<char [][]> temp = new State<char[][]>(s);
		int pipeLen;
		for(int i=0;i<s.getState().length;i++)
		{
			for (int j=0;j<s.getState()[i].length;j++)
			{
				if (isLegal(s.getState()[i][j])) // check if the char is a pipe , s g or space are not a pipe 
				{
					pipeLen = checkAllpossibilities(temp.getState()[i][j]);
					for (int k=0;k<pipeLen;k++) {
						newState = new State<char[][]>();
						newState = copyState(temp); // copy the stage
						newState.getState()[i][j] =	rotate(newState.getState()[i][j]); // rotate the pipe	
						//if(newState.getCameFrom() != null) {
						newState.setCameFrom(s);
						//newState.setCost(this.setCostForState(newState));

						//	}

						StringBuilder stateBuilder = new StringBuilder();
						for(int n=0; n<newState.getState().length; n++)
							for(int m=0; m<newState.getState()[i].length; m++)
								stateBuilder.append(newState.getState()[n][m]);//parse the stage into a string
						if (!hashStates.contains(stateBuilder.toString()))
						{
							this.hashStates.add(stateBuilder.toString());//put the stage into the state hash set
							this.possibleStates.add(newState);
							/*for(int q=0;q<newState.getState().length;q++)
							{
								
								System.out.print(newState.getState()[q]);
								System.out.print(" ");

							}
							System.out.println();*/
						}
						temp = copyState(newState);
					}
					temp = copyState(s);
				}
			}
		}
	}

	@Override
	public ArrayList<State<char[][]>> getAllPossibleStates(State<char[][]> s) {
		setAllPossibleStates(s);
		return this.possibleStates;
	}

	public State<char [][]> copyState(State<char [][]> s)
	{

		char [][] temp = new char[s.getState().length][s.getState()[0].length];
		for (int i=0;i<s.getState().length;i++)
		{
			for(int j=0;j<s.getState()[i].length;j++)
			{
				temp[i][j] = s.getState()[i][j];		
			}
		}
		State<char [][]> newState = new State<>(temp);

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
	public Point startPos(char[][] board){
		for (int i=0;i<board.length;i++) {
			for(int j=0; j<board[0].length;j++) {
				if (board[i][j] == 's') {
					return new Point(i,j);
				}
			}
		}
		return new Point(-1,-1);
	}
	public Point endPos(char[][] board){
		for (int i=0;i<board.length;i++) {
			for(int j=0; j<board[0].length;j++) {
				if (board[i][j] == 'g') {
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
			return 3;
		else if (pipe == '|' || pipe == '-')
			return 1;
		else 
			return 0;
	}

/*	public double setCostForState(State<char[][]> state) {
		if ((board[row][col] == 's') || (check right))
			return 1;
		if (row == 0 && col == 0) //in case its the upper left corner 
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right 
				found = isGoalStateRecursion(board, new Point(row, col), row , col+1);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down 
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}
		else if (row == 0 && col == board[0].length-1) // in case its upper right corner
		{
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left 
				found = isGoalStateRecursion(board, new Point(row, col), row , col-1);
			if (checkDown(board[row][col]) && isUp(board[row+1][col])&&(cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}
		else if (row == board.length-1 && col == 0) // in case its bottom left corner
		{
			if (checkUp(board[row][col]) && isDown(board[row-1][col])&& (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
		}
		else if (row == board.length-1 && col == board[0].length-1) // in case its bottom right corner
		{
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1])&& (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
		}

		else if (col ==0)// in case its the left column and not a corner
		{
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
		}
		else if (col == board[0].length-1) // in case its the right column and not a corner
		{
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
		}
		else if (row == 0) // in case its the upper row and not a corner
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}
		else if (row == board.length-1) // in case its the bottom row and not a corner
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
		}
		else // all the other options
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
			if (checkUp(board[row][col]) && isDown(board[row-1][col]) && (cameFrom.getX() != row-1)) // up 
				found = isGoalStateRecursion(board, new Point(row, col), row-1 , col);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}

		return found;
		int i,j;
		
		char pipe = 'i';
		char[][] board = state.getState();
		Point p;
		double cost = 0;
		p = endPos(board);
		pipe = board[p.getX()][p.getY()];
		i = p.getX();
		j = p.getY();
		while(i != -1 && j != -1 && i < board.length && j < board[0].length) {
				switch(pipe) {
				case 'J':
					if (isDown(board[i-1][j])) {
						pipe = board[i-1][j];
						cost++;
						i--;}
					if (isRight(board[i][j-1])) {
						pipe = board[i][j-1];
						cost++;
						j--;}
					;
				case 'F':
					if (isUp(board[i+1][j])) {
						pipe = board[i+1][j];
						cost++;
						i++;}
					if (isLeft(board[i][j+1])) {
						pipe = board[i][j+1];
						cost++;
						j++;}
					;
				case '7':
					if (isUp(board[i+1][j])) {
						pipe = board[i+1][j];
						cost++;
						i++;}
					if (isRight(board[i][j-1])) {
						pipe = board[i][j-1];
						cost++;
						j--;}
					;
				case 'L':
					if (isDown(board[i-1][j])) {
						pipe = board[i-1][j];
						cost++;
						i--;}
					if (isLeft(board[i][j+1])) {
						pipe = board[i][j+1];
						cost++;
						j++;}
					;
				case '-':
					if (isLeft(board[i][j+1])) {
						pipe = board[i][j+1];
						cost++;
						j++;}
					if (isRight(board[i][j-1])) {
						pipe = board[i][j-1];
						cost++;
						j--;}
					;
				case '|':
					if (isUp(board[i+1][j])) {
						pipe = board[i+1][j];
						cost++;
						i++;}
					if (isDown(board[i-1][j])) {
						pipe = board[i-1][j];
						cost++;
						i--;}
					;
				case 's':
					cost++;
					break;
				case 'g':
					if (isUp(board[i+1][j])) {
						pipe = board[i+1][j];
						cost++;
						i++;}
					if (isDown(board[i-1][j])) {
						pipe = board[i-1][j];
						cost++;
						i--;}
					if (isLeft(board[i][j+1])) {
						pipe = board[i][j+1];
						cost++;
						j++;}
					if (isRight(board[i][j-1])) {
						pipe = board[i][j-1];
						cost++;
						j--;}
				default:
					break;
				}
			}
		return cost;
		}		*/
} 