package Search;

import java.util.ArrayList;

public class PipeParser implements Parser<char[][], ArrayList<String>>{

	@Override
	public char[][] parse(ArrayList<String> problem) {
		int rows,cols;
		rows = problem.size();
		cols = problem.get(0).length();
		char[][] board = new char[rows][cols];
		for (int i=0;i<rows;i++)
			for (int j=0;j<cols;j++)
				board[i][j] = problem.get(i).charAt(j);//convert the board to char2d
		
		return board;
	}

}
