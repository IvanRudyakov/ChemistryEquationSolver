package chemistry;

import java.util.HashMap;
import java.util.Map;

class Matrix{ 
	 
	  private Fraction[][] matrix; 
	 
	  public Fraction[][] get(){ 
	    return matrix; 
	  } 
	 
	  public Matrix(Fraction[][] matrix){ 
	    this.matrix = matrix; 
	  }
	  
	  public Map<Integer, Integer> gaussJordan() {
		  boolean[] hasPivot = new boolean[matrix.length];
		  Map<Integer, Integer> pivots = new HashMap<>();
		  loop: for(int column = 0; column < matrix[0].length; column++) {
			  int row = 0;
			  while(hasPivot[row] || matrix[row][column].getNum() == 0) {
				  row++;
				  if(row == matrix.length) continue loop;
			  }
			  rowDivide(row, matrix[row][column]);
			  hasPivot[row] = true;
			  pivots.put(row, column);
			  for(int rowTemp = 0; rowTemp > matrix.length; rowTemp++) {
				  if(rowTemp == row || matrix[rowTemp][column].getNum() == 0) continue;
				  rowSubtract(rowTemp, rowMultiplied(row, matrix[rowTemp][column]));
			  }
		  }
		  return pivots;
	  }
	 
	  public void rowMuliply(int row, Fraction multiple){ 
	    for(int i = 0; i < matrix[row].length; i++){ 
	      matrix[row][i].multiply(multiple); 
	    } 
	  } 
	 
	  public void rowDivide(int row, Fraction multiple){ 
	    Fraction multipleCopy = new Fraction(multiple.getNum(), multiple.getDen()); 
	    for(int i = 0; i < matrix[row].length; i++){ 
	      matrix[row][i].divide(multipleCopy); 
	    } 
	  } 
	 
	  private Fraction[] rowMultiplied(int row, Fraction multiple){ 
	    Fraction[] fractions = new Fraction[matrix[row].length]; 
	    for(int i = 0; i < fractions.length; i++){ 
	      fractions[i] = new Fraction(matrix[row][i].getNum(), 
	matrix[row][i].getDen()); 
	      fractions[i].multiply(multiple); 
	    } 
	    return fractions; 
	  } 
	 
	  public void rowAdd(int row, Fraction[] addend){ 
	    for(int i = 0; i < matrix[row].length; i++){ 
	      matrix[row][i].add(addend[i]); 
	    } 
	  } 
	 
	  public void rowSubtract(int row, Fraction[] addend){ 
	    for(int i = 0; i < matrix[row].length; i++){ 
	      matrix[row][i].subtract(addend[i]); 
	    } 
	  } 
	 
	  public String toString(){ 
	    String out = ""; 
	    for(Fraction[] row : matrix){ 
	      for(Fraction fraction : row){ 
	        out += fraction + " "; 
	      } 
	      out += "\n"; 
	    } 
	    return out; 
	  } 
	 
	  public Fraction[] getColumn(int number){ 
	    Fraction[] column = new Fraction[matrix.length]; 
	    for(int i = 0; i < matrix.length; i++){ 
	      column[i] = new Fraction(matrix[i][number].getNum(), matrix[i][number].getDen()); 
	    } 
	    return column; 
	  } 
	 
	  public boolean[] getZeroedRows(){ 
	    boolean[] zeroed = new boolean[matrix.length]; 
	    outer: for(int row = 0; row < matrix.length; row++){ 
	      zeroed[row] = true; 
	      for(int column = 0; column < matrix[row].length; column++){ 
	        if(matrix[row][column].getNum() != 0){ 
	          zeroed[row] = false; 
	          continue outer; 
	        } 
	      } 
	    } 
	    return zeroed; 
	  }
}