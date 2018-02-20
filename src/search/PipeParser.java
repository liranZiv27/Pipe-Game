package search;

import java.util.ArrayList;

public class PipeParser implements Parser<State<char[][]>, State<ArrayList<String>>>{

	private int row;
	private int col;
	@Override
	public State<char[][]> parse(State<ArrayList<String>> problem) {
		State<char[][]> temp;
		int rows,cols;
		rows = problem.getState().size();
		cols = problem.getState().get(0).length();
		this.setRow(rows);
		this.setCol(cols);
		
		char[][] board = new char[rows][cols];
		for (int i=0;i<rows;i++)
			for (int j=0;j<cols;j++)
				board[i][j] = problem.getState().get(i).charAt(j);//convert the board to char2d
		
		temp = new State<>(board);
		
		return temp;
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

}
