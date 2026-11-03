"""
Question 3: Mathematical Modeling of Production Costs using SymPy
A manufacturing company needs to simplify mathematical expressions used
in calculating production costs.

This program uses the SymPy library to:
1. Define symbolic variables representing production quantities and cost parameters.
2. Expand algebraic cost expressions.
3. Factorize polynomial cost expressions.
4. Simplify rational and composite production expressions.
5. Solve a quadratic equation to determine the unknown production quantity (q).
"""

import sympy as sp


def production_cost_analysis():
    print("=" * 80)
    print("      MANUFACTURING PRODUCTION COST ANALYSIS USING SYMPY      ")
    print("=" * 80)

    # ---------------------------------------------------------
    # 1. Define Symbolic Variables
    # ---------------------------------------------------------
    print("\n--- [STEP 1] Defining Symbolic Variables ---")
    q = sp.symbols('q', real=True, nonnegative=True)  # Production quantity (units >= 0)
    c, F = sp.symbols('c F', real=True, positive=True) # Variable cost per unit, Fixed overhead
    x = sp.symbols('x', real=True)                     # General quantity symbol

    print(f"Symbolic Quantity Variable (q) : {q} (Assumed real, nonnegative)")
    print(f"Cost Parameter Variables (c, F): {c}, {F} (Unit cost, Fixed overhead)")
    print(f"General Algebraic Variable (x) : {x}")

    # ---------------------------------------------------------
    # 2. Expand Algebraic Expressions
    # ---------------------------------------------------------
    print("\n--- [STEP 2] Expanding Algebraic Expressions ---")
    # A manufacturing company calculates total assembly cost from two interdependent stages:
    # Stage 1 cost factor: (2q + 15)
    # Stage 2 cost factor: (3q + 10)
    # Fixed tooling adjustment: + 50
    cost_expr_factored = (2 * q + 15) * (3 * q + 10) + 50
    cost_expr_expanded = sp.expand(cost_expr_factored)

    print("Unexpanded Production Cost Expression:")
    print(f"  C_raw(q)      = {cost_expr_factored}")
    print("Expanded Polynomial Production Cost Expression (sp.expand):")
    print(f"  C_expanded(q) = {cost_expr_expanded}")

    # Another example: Higher-order production batch expansion
    batch_expr = (q + 5)**3
    batch_expanded = sp.expand(batch_expr)
    print(f"\nBatch Scaling Factor : (q + 5)^3")
    print(f"Expanded Form        : {batch_expanded}")

    # ---------------------------------------------------------
    # 3. Factorize Algebraic Expressions
    # ---------------------------------------------------------
    print("\n--- [STEP 3] Factorizing Algebraic Expressions ---")
    # Given an expanded quadratic cost variance expression: 2q^2 + 11q + 12
    poly_cost = 2 * q**2 + 11 * q + 12
    factored_poly = sp.factor(poly_cost)

    print(f"Expanded Polynomial : {poly_cost}")
    print(f"Factored Expression (sp.factor) : {factored_poly}")

    # Second example: Factorizing a cost difference polynomial
    variance_expr = 6 * q**2 + 15 * q - 300
    factored_variance = sp.factor(variance_expr)
    print(f"\nCost Variance Polynomial : {variance_expr}")
    print(f"Factored Variance        : {factored_variance}")

    # ---------------------------------------------------------
    # 4. Simplify Resulting Expressions
    # ---------------------------------------------------------
    print("\n--- [STEP 4] Simplifying Algebraic Expressions ---")
    # Simplifying rational cost functions (e.g., Average Cost / Economy of Scale model)
    rational_expr = (q**3 - 8) / (q - 2)
    simplified_rational = sp.cancel(rational_expr)
    print("Rational Unit Cost Function: (q^3 - 8) / (q - 2)")
    print(f"Simplified Expression (sp.cancel / sp.simplify): {simplified_rational}")

    # Average cost simplification: [ (2q^2 + 8q) / (2q) ]
    avg_cost_expr = (2 * q**2 + 8 * q) / (2 * q)
    simplified_avg = sp.simplify(avg_cost_expr)
    print(f"\nAverage Overhead Expression: (2q^2 + 8q) / (2q)")
    print(f"Simplified Average Overhead: {simplified_avg}")

    # Algebraic difference simplification: [ (q + 10)^2 - (q - 10)^2 ] / (4q)
    diff_expr = ((q + 10)**2 - (q - 10)**2) / (4 * q)
    simplified_diff = sp.simplify(diff_expr)
    print(f"\nDifferential Scaling Expression: [ (q + 10)^2 - (q - 10)^2 ] / (4q)")
    print(f"Simplified Differential Scaling: {simplified_diff}")

    # ---------------------------------------------------------
    # 5. Solve Quadratic Equation for Unknown Production Quantity
    # ---------------------------------------------------------
    print("\n--- [STEP 5] Solving Quadratic Equation for Production Quantity ---")
    # Manufacturing problem scenario:
    # Total production cost function: C(q) = 2q^2 + 20q + 100
    # The company has an allocated budget of $1,300.
    # What production quantity 'q' can be manufactured within this budget?
    # Equation: 2q^2 + 20q + 100 = 1300  ==>  2q^2 + 20q - 1200 = 0  ==> q^2 + 10q - 600 = 0
    q_general = sp.symbols('q', real=True) # Unconstrained real symbol to reveal all algebraic roots
    total_cost_function = 2 * q_general**2 + 20 * q_general + 100
    budget_limit = 1300
    cost_equation = sp.Eq(total_cost_function, budget_limit)

    print(f"Production Cost Model : C(q) = {total_cost_function}")
    print(f"Allocated Budget      : B    = ${budget_limit}")
    print(f"Quadratic Equation    : {cost_equation}")

    # Solve algebraically
    all_roots = sp.solve(cost_equation, q_general)
    print(f"All Algebraic Roots for q : {all_roots}")

    print("\nPhysical Interpretation of Solutions:")
    valid_quantities = []
    for root in all_roots:
        if root >= 0:
            print(f"  -> q = {root} units : VALID (Feasible positive production quantity)")
            valid_quantities.append(root)
        else:
            print(f"  -> q = {root} units : INVALID (Discarded, negative quantity is physically impossible)")

    optimal_q = valid_quantities[0]
    print(f"\nDetermined Production Quantity: q = {optimal_q} units")

    # Verification:
    substituted_cost = total_cost_function.subs(q_general, optimal_q)
    print(f"Verification: C({optimal_q}) = 2*({optimal_q})^2 + 20*({optimal_q}) + 100 = ${substituted_cost}")
    assert substituted_cost == budget_limit, "Verification failed!"
    print("Verification Successful: Computed cost matches allocated budget exactly!")

    # Additional scenario: Break-even profit equation
    print("\n--- [Bonus Scenario] Break-Even Quantity Analysis ---")
    # Profit equation: P(q) = -q^2 + 60q - 500 = 0
    profit_func = -q**2 + 60 * q - 500
    break_even_eq = sp.Eq(profit_func, 0)
    be_solutions = sp.solve(break_even_eq, q)
    print(f"Profit Function     : P(q) = {profit_func}")
    print(f"Break-Even Equation : {break_even_eq}")
    print(f"Break-Even Points   : q = {be_solutions[0]} units (lower threshold) and q = {be_solutions[1]} units (upper threshold)")

    print("=" * 80)
    print("             PRODUCTION COST ANALYSIS COMPLETED               ")
    print("=" * 80)


if __name__ == "__main__":
    production_cost_analysis()
