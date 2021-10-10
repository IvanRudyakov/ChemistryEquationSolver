package chemistry;

import java.util.*; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import java.util.stream.Collectors; 
import static java.lang.Math.*;

public class EquationBalancer {
	
  private static ArrayList<Element> getElementsFromEquation(Equation equation){ 
    var elementsSet = new HashSet<Element>(); 
    for(Formula formula : equation.getReactants()){ 
      elementsSet.addAll(formula.getSubscripts().keySet()); 
    } 
    for(Formula formula : equation.getProducts()){ 
      elementsSet.addAll(formula.getSubscripts().keySet()); 
    } 
    return new ArrayList<Element>(elementsSet); 
  } 
   
  public static void main(String[] args){ 
   
	Scanner scan = new Scanner(System.in);
	
    System.out.println("Give chemistry equation to solve: "); 
    var equation = new Equation(scan.nextLine()); 
   
    var elements = getElementsFromEquation(equation); 
   
    Fraction[][] array = new Fraction[elements.size()][equation.getReactants().length + equation.getProducts().length]; 
   
    //Fills array with relevant subscripts   
    for(int element = 0; element < elements.size(); element++){ 
    
      for(int formula = 0; formula < equation.getReactants().length; formula++){ 
        var subscriptOptional = Optional.ofNullable(equation 
          .getReactants()[formula] 
          .getSubscripts() 
          .get( 
            elements.get(element) 
          )   
        ); 
        array[element][formula] = new Fraction(subscriptOptional.orElse(0)); 
      } 
      for(int formula = 0; formula < equation.getProducts().length; formula++){ 
        var subscriptOptional = Optional.ofNullable(equation 
          .getProducts()[formula] 
          .getSubscripts() 
          .get( 
            elements.get(element) 
          ) 
        ); 
        array[element][formula + equation.getReactants().length] = new Fraction(-subscriptOptional.orElse(0));   
      } 
    } 
   
    Matrix matrix = new Matrix(array); 
   
    Map<Integer, Integer> pivots = matrix.gaussJordan(); 
   
    boolean[] zeroed = matrix.getZeroedRows(); 
   
    int zeroedNum = 0; 
   
    for(boolean bool : zeroed) if(bool) zeroedNum++; 
    
    //check whether there are any solutions or infinite solutions 
    if(matrix.get().length - zeroedNum + 1 < matrix.get()[0].length){ 
      System.out.println("Infinite solutions"); 
    } else if(matrix.get().length - zeroedNum + 1 > matrix.get()[0].length){ 
    	System.out.println("No solutions"); 
    } else if(pivots.size() + 1 != matrix.get()[0].length){ 
  	  System.out.println("Infinite solutions"); 
    } else { 
    
      //finds free column 
      int freeColumnIndex = 0; 
      for(int i = 0; i < matrix.get()[0].length; i++){ 
        if(!pivots.values().contains(i))freeColumnIndex = i; 
      } 
      Fraction[] freeColumn = matrix.getColumn(freeColumnIndex); 
   
      //Gets rid of the denominators in the free column fractions 
      int[] freeColumnDenominators = new int[freeColumn.length]; 
      for(int i = 0; i < freeColumnDenominators.length; i++){ 
        freeColumnDenominators[i] = freeColumn[i].getDen(); 
      } 
   
      int multiple = lcm(freeColumnDenominators); 
      Fraction multipleFrac = new Fraction(multiple); 
      for(Fraction fraction : freeColumn){ 
        fraction.multiply(multipleFrac); 
      } 
   
      //Fills coefficient array with relevant free column ints 
      int[] coefficients = new int[matrix.get()[0].length]; 
      for(int i = 0; i < matrix.get().length; i++){ 
        if(pivots.keySet().contains(i)){ 
          coefficients[pivots.get(i)] = -freeColumn[i].getNum(); 
        } 
      } 
      coefficients[freeColumnIndex] = multiple; 
   
      //checks whether the coefficients are all positive 
      boolean allPositive = true; 
      for(int n : coefficients){ 
        if(n <= 0) allPositive = false; 
      } 
    
      if(allPositive){ 
        System.out.println("Your coefficients: "); 
        for(int coefficient : coefficients){ 
          System.out.println(coefficient);   
        } 
      } else { 
        System.out.println("No completely positive solutions"); 
      } 
    
    } 
  } 
 
  public static int lcm(int[] a){ 
    int max = a[0]; 
    for(int i : a) max = (i > max ? i : max); 
    int temp = max; 
    while(true){ 
      boolean b = true; 
      for(int i : a) if(temp % i != 0) b = false; 
      if(b) return temp; else temp += max; 
    } 
  } 

}
