package search;

import java.util.ArrayList;

public class PipeParser implements Parser<CharMatrix,ArrayList<String>>{

	private int row;
	private int col;
	@Override
	public CharMatrix parse(ArrayList<String> problem) {
		int rows,cols;
		rows = problem.size();
		cols = problem.get(0).length();
		this.setRow(rows);
		this.setCol(cols);
		char[][] char2d = new char[rows][cols];
		for (int i=0;i<rows;i++)
			for(int j=0; j<cols;j++)
				char2d[i][j] = problem.get(i).charAt(j);//convert the board to char2d
		
		return new CharMatrix(char2d);
	}
	public State<String> parseToArray(State<char[][]> problem) {
		State<String> temp;
		ArrayList<String> board = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<problem.getState().length; i++) {
			for (int j=0; j<problem.getState()[0].length; j++)
			{
				sb.append(problem.getState()[i][j]);
				}
			board.add(sb.toString());
			sb = new StringBuilder();
		}
		temp = new State<>();
		temp.setState(board.toString());
		return temp;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public ArrayList<String> parseToSteps(ArrayList<State<CharMatrix>> backTrace) {
		State<CharMatrix> goalState=backTrace.get(0);
		State<CharMatrix> initialState=backTrace.get(backTrace.size()-1);
		
		char[][] initial=initialState.getState().getMatrix();
		char[][] goal=goalState.getState().getMatrix();
		
		int rows = initial.length;		//rows of the initial state
		int columns = initial[0].length; //columns of the initial state
		
		ArrayList<String> list=new ArrayList<>();
		int counter=0;
				
		for(int i=0;i<rows;i++)
		{	for(int j=0;j<columns;j++)
			{
			if(initial[i][j]!=goal[i][j]) {
				counter= numberOfRotationsNeeded(initial[i][j], goal[i][j]);
				list.add(new String(i+","+j+","+counter));
			}
		}
	}
		list.add("done");
		ArrayList<String> solution= new ArrayList<>(list);
		return solution;

	}

	public int numberOfRotationsNeeded(char current,char needed) {
		int counter=0;
		while(current != needed )
		{
			if(current=='|')
				current ='-';
			else if(current=='-')
				current = '|';
			else if(current== 'F')
				current = '7';
			else if(current =='7')
				current = 'J';
			else if(current =='J')
				current = 'L';
			else if(current =='L')
				current = 'F';
			counter++;
		}
		return counter;
	}
}
