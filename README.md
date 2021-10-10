# Chemistry Equation Solver

Command-line program written in Java which balances Chemistry equations by making sure that both sides of a reaction have the same number of reactant atoms as product atoms. If possible, will return the unique coefficients which cannot be divided any more on both sides. Otherwise, will return that there is no solution, or that there are infinitely many most-divided solutions. Implemented using a Gauss-Jordan algorithm on fraction matrices.

## How to Use Chemistry Equation Solver

To run the equation balancer, you need to run the main function located in Main.java, which can be done either in an IDE or by compiling and running on a command line.

Once you run the balancer, you will be prompted for a chemistry equation to solve in STDOUT. You must input your equation in STDIN. This chemistry equation must be formatted using compounds separated by "+" signs on both sides of the equation, with a "->" between the two sides. You may include arbritrary white space between different compounds and the "->". Examples:

```
H2O + CO2 -> C + H2O3         // coefficients returned: 1, 1, 1, 1

FeS+O2->Fe2O3+SO2             // coefficients returned: 4, 7, 2, 4

PCl5    + H2O  -> H3PO4+ HCl  // coefficients returned: 1, 4, 1, 5

H2 + H3 -> H + H4             // Infinitely many solutions (ex. (1, 1, 1, 1), (1, 2, 4, 1))

H2 -> O2                      // No solutions
```

The compounds must be formatted in the style shown above. Make sure that elements with two letters start with a capital letter and end without a capital letter, and elements with a single letter are capitalized. If elements have a subscript of 1, the 1 may be omitted.

**Parenthesis cannot be included in the compounds. If you have a compound with parenthesis, distribute the group-subscript over the elements in the parenthesis.**

Only valid elements up to element 118 can work. Using any other elements will result in an error.

If the chemistry equation was formatted correctly, you will get one of four outputs: 

A list of balanced coefficients corresponding to the compounds, in order, from left to right;

The string "Infinite solutions";

The string "No solutions";

The string "No completely positive solutions";

These answers are self explanatory. For all intents and purposes "No solutions" and "No completely positive solutions" can be treated as the same, the only difference is that in the latter case, there is a set of unique balanced coefficients which uses negative coefficients (which, obviously, can't happen in the real world).
