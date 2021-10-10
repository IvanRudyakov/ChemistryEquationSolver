package chemistry;

import java.util.Arrays;

class Equation{ 
	 
	  private Formula[] reactants, products; 
	 
	  private String equation; 
	 
	  public Equation(String equation){ 
	 
	    this.equation = equation; 
	 
	    String[] sides = equation.split("->"); 
	 
	    reactants = getFormulasFromSide(sides[0]); 
	    products = getFormulasFromSide(sides[1]); 
	 
	  } 
	 
	  private static Formula[] getFormulasFromSide(String side){ 
	    return (Formula[]) Arrays.stream(side.split("\\+")) 
	      .map(String::strip) 
	      .map(Formula::new) 
	      .toArray(Formula[]::new) 
	    ; 
	  } 
	 
	  public Formula[] getReactants(){ 
	    return reactants; 
	  } 
	 
	  public Formula[] getProducts(){ 
	    return products; 
	  } 
	 
	  public String toString(){ 
	    return equation; 
	  } 
	 
	} 