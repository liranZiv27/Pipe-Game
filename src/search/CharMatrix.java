package search;

public class CharMatrix{

	private char[][] char2D;

	public CharMatrix(char[][] matrix) {
		super();
		this.char2D = matrix;
	}

	public char[][] getMatrix() {
		return char2D;
	}

	@Override
	public int hashCode() {
		
		return this.toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

	@Override
	public String toString() {
		
		String str = new String();
		
		for(int i=0;i<this.char2D.length;i++)
			str=str.concat(String.valueOf(this.char2D[i]));
		
		
		return str;
	}


	
	
}