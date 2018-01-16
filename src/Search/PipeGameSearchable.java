package Search;

import java.util.ArrayList;

public class PipeGameSearchable implements Searchable<char[][]> {
	public final State<char[][]> initialState;
	public PipeParser parser;
	public static int costForState;
	
	public PipeGameSearchable(State<ArrayList<String>> problem) {
		this.parser = new PipeParser();
		this.initialState = this.parser.parse(problem);
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
		else if (row == board.length-1 && col == 0) // in case its bottom right corner
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
		else if (row == 0) // in case its the upper column and not a corner
		{
			if (checkRight(board[row][col]) && isLeft(board[row][col+1]) && (cameFrom.getY() != col+1)) // right
				found = isGoalStateRecursion(board, new Point(row, col), row, col+1);
			if (checkLeft(board[row][col]) && isRight(board[row][col-1]) && (cameFrom.getY() != col-1)) // left
				found = isGoalStateRecursion(board, new Point(row, col), row, col-1);
			if (checkDown(board[row][col]) && isUp(board[row+1][col]) && (cameFrom.getX() != row+1)) // down
				found = isGoalStateRecursion(board, new Point(row, col), row+1, col);
		}
		else if (row == board.length-1) // in case its the bottom column and not a corner
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
	public ArrayList<State<char[][]>> getAllPossibleStates(State<char[][]> s) {
		// TODO Auto-generated method stub
		return null;
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
	
	
	

//
//	public boolean isInRange(Point point ,char[][] board)
//	{
//		if (point.getX() < board.length && point >= 0)
//			if(point[1] < board[0].length && point[1] >=0)
//				return true;
//		return false;		
//	}
}
