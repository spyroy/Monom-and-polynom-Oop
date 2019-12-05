#to do: InitFromString, fix isZero, fix Operation to himself (all in ComplexFunction).

# this project deals with Monom and Polynom.

## Monom: in this project Monom most be of shape +-ax\X^b where 'a' is double and 'b' Integer (https://en.wikipedia.org/wiki/Monomial)

## Polynom:in this project Polynom most be of shape +-a_1x\X^b_1 +-a_2x\X^b_2... where each element is a Monom (https://en.wikipedia.org/wiki/Polynomial)

### Monom has 7 main function:
1) *constructor* , receives two numbers: 'a' Double 'b' Integer and build a Monom with coefficient 'a' and power 'b'.
2) *derivative*, receives a Monom and returns the derivative. (https://en.wikipedia.org/wiki/Derivative)
3) *f function*,receives real number 'x' and returns the value of the Monom at - 'x' point.
4) *is zero*, returns if the the Monom equals to - 0 or not.
5) *String constructor*, receives string from user and converts it to Monom type.
6) *add*, receives Monom and add it to another Monom only if the degrees are equal.
7) *multiply*, receives Monom and multiply it to another Monom, by multiply the coefficient and adding the degree.

### Polynom has 13 main function:
1) *String constructor*, receives string from user and converts it to Polynom type.
2) *f function*, receives real number 'x' and returns the value of the Polynom at - 'x' point.
3) *add Polynom*, receives Polynom and add it to another Polynom by Polynomial arithmetic. (https://en.wikipedia.org/wiki/Polynomial_arithmetic)
4) *add Monom*, receives Monom and add it to another Polynom by Polynomial arithmetic. (https://en.wikipedia.org/wiki/Polynomial_arithmetic)
5) *subtract Polynom*, receives Polynom and subtract it from another Polynom by Polynomial arithmetic. (https://en.wikipedia.org/wiki/Polynomial_arithmetic)
6) *multiply Polynom*, receives Polynom and multiply it by another Polynom by Polynomial arithmetic. (https://en.wikipedia.org/wiki/Polynomial_arithmetic)
7) *equals*, receives Polynom and checks if it equal to another Polynom, returns true if it does and false otherwise.
8) *is zero*, returns if the the Polynom equals to - 0 or not.
9) *root*, returns the value, which the function represented by the given Polynom, gets zero (can only give one root between
specific range). (https://en.wikipedia.org/wiki/Zero_of_a_function)
10) *copy*, deep copy of the Polynom.
11) *derivative*, receives a Polynom and returns the derivative. (https://en.wikipedia.org/wiki/Derivative)
12) *area*, returns the area below the function represented by the given Polynom based on rimann's integral, returns 0 if x0
is bigger then x1 in the given area. (https://en.wikipedia.org/wiki/Riemann_integral)
13) *multiply Monom*, receives Monom and multiply it by another Polynom by Polynomial arithmetic.

#### this project was written by @Matan_Greenberg (206094815) and @Or_Mendel (315524389).

