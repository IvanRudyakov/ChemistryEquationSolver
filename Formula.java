package chemistry;

import java.util.EnumMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Formula{ 
	 
	  private EnumMap<Element, Integer> subscripts; 
	  private String formula; 
	 
	  private static final Pattern pattern = 
	    Pattern.compile("(\\p{Upper}\\p{Lower}?)(\\p{Digit}*)"); 
	 
	  public Formula(String formula){ 
	    this.formula = formula; 
	    subscripts = new EnumMap(pattern 
	      .matcher(formula) 
	      .results() 
	      .collect( 
	        Collectors.toMap( 
	          match -> Element.valueOf(match.group(1)), 
	          match -> match.group(2).isEmpty() ? 1 : 
	Integer.parseInt(match.group(2)), 
	          (n1, n2) -> n1 + n2 
	        ) 
	      ) 
	    ); 
	  } 
	 
	  public String toString(){ 
	    return formula; 
	  } 
	 
	  public EnumMap<Element, Integer> getSubscripts(){ 
	    return subscripts; 
	  } 
	} 