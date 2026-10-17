"""
Question 6: Employee Promotion Eligibility (Rule-Based System)
=============================================================
An organization considers employees for promotion based on the following conditions:
- Minimum five years of experience.
- Completion of mandatory training.
- Performance rating of at least 4.

Employee  Experience (Years)  Training       Rating
Ravi      6                   Completed      5
Priya     3                   Completed      4
Kumar     8                   Not Completed  5
Meena     7                   Completed      4

a) Represent the employee information as logical facts.
b) Define a logical rule for promotion eligibility.
c) Write a query to retrieve employees eligible for promotion.
"""

import sys
from typing import List, Dict, Any, NamedTuple

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8")


class EmployeeFact(NamedTuple):
    name: str
    experience: int
    training: str
    rating: int


class PromotionKnowledgeBase:
    """Rule-based inference engine for employee promotion evaluation."""

    def __init__(self):
        # Part (a): Represent employee information as logical facts
        # Predicate: employee(Name, Experience, Training, Rating)
        self.facts: List[EmployeeFact] = [
            EmployeeFact("Ravi", 6, "Completed", 5),
            EmployeeFact("Priya", 3, "Completed", 4),
            EmployeeFact("Kumar", 8, "Not Completed", 5),
            EmployeeFact("Meena", 7, "Completed", 4),
        ]

    # -------------------------------------------------------------------------
    # Part (b): Logical Rule Definition
    # -------------------------------------------------------------------------
    # In Prolog:
    #   eligible_for_promotion(Employee) :-
    #       employee(Employee, Experience, completed, Rating),
    #       Experience >= 5,
    #       Rating >= 4.
    def evaluate_rule(self, emp: EmployeeFact) -> Dict[str, Any]:
        """
        Evaluates the logical rule:
          Rule = (Experience >= 5) AND (Training == 'Completed') AND (Rating >= 4)
        """
        exp_ok = emp.experience >= 5
        training_ok = emp.training.lower() == "completed"
        rating_ok = emp.rating >= 4
        is_eligible = exp_ok and training_ok and rating_ok

        failures = []
        if not exp_ok:
            failures.append(f"Experience {emp.experience} yrs < 5 yrs required")
        if not training_ok:
            failures.append(f"Training status '{emp.training}' (Mandatory training incomplete)")
        if not rating_ok:
            failures.append(f"Rating {emp.rating} < 4 required")

        return {
            "name": emp.name,
            "experience": emp.experience,
            "training": emp.training,
            "rating": emp.rating,
            "eligible": is_eligible,
            "reasons": failures if not is_eligible else ["All promotion conditions satisfied"],
        }

    # -------------------------------------------------------------------------
    # Part (c): Query Execution
    # -------------------------------------------------------------------------
    # In Prolog: ?- eligible_for_promotion(Employee).
    def query_eligible_employees(self) -> List[str]:
        """Query to retrieve all employees who satisfy promotion eligibility."""
        return [
            emp.name
            for emp in self.facts
            if self.evaluate_rule(emp)["eligible"]
        ]


def run_question_6():
    print("=" * 70)
    print("QUESTION 6: RULE-BASED SYSTEM - EMPLOYEE PROMOTION ELIGIBILITY")
    print("=" * 70)

    kb = PromotionKnowledgeBase()

    # -------------------------------------------------------------------------
    # Part (a): Representing Employee Information as Logical Facts
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (a): Logical Facts Representation")
    print("-" * 70)
    print("1. Standard Prolog Representation:")
    print("   employee(ravi, 6, completed, 5).")
    print("   employee(priya, 3, completed, 4).")
    print("   employee(kumar, 8, not_completed, 5).")
    print("   employee(meena, 7, completed, 4).")

    print("\n2. Structured First-Order Logic Knowledge Base Facts:")
    for f in kb.facts:
        print(f"   FACT: employee(Name='{f.name}', Experience={f.experience}, Training='{f.training}', Rating={f.rating})")

    # -------------------------------------------------------------------------
    # Part (b): Logical Rule Definition
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (b): Logical Rule for Promotion Eligibility")
    print("-" * 70)
    print("1. Prolog Rule:")
    print("   eligible_for_promotion(Employee) :-")
    print("       employee(Employee, Experience, completed, Rating),")
    print("       Experience >= 5,")
    print("       Rating >= 4.")

    print("\n2. Formal First-Order Logic Horn Clause:")
    print("   FOR ALL E, Exp, Tr, Rt [")
    print("       Employee(E, Exp, Tr, Rt) AND (Exp >= 5) AND (Tr = 'Completed') AND (Rt >= 4)")
    print("       ===> EligibleForPromotion(E)")
    print("   ]")

    # -------------------------------------------------------------------------
    # Part (c): Query Execution and Verification
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (c): Query to Retrieve Employees Eligible for Promotion")
    print("-" * 70)
    print("1. Prolog Query:")
    print("   ?- eligible_for_promotion(Employee).")

    print("\n2. Query Resolution and Breakdown for Each Employee:")
    print(f"   {'Employee':<10} {'Exp':<5} {'Training':<16} {'Rating':<8} {'Status':<14} {'Justification'}")
    print("   " + "-" * 80)

    for emp in kb.facts:
        eval_res = kb.evaluate_rule(emp)
        status_label = "PROMOTABLE" if eval_res["eligible"] else "NOT ELIGIBLE"
        justification = ", ".join(eval_res["reasons"])
        print(
            f"   {emp.name:<10} {emp.experience:<5} {emp.training:<16} "
            f"{emp.rating:<8} [{status_label:<12}] {justification}"
        )

    eligible_list = kb.query_eligible_employees()
    print("\n3. Query Result:")
    print(f"   Eligible Employees: {eligible_list}")
    print(f"   Prolog Query Output: Employee = ravi ; Employee = meena.")
    print("=" * 70 + "\n")


if __name__ == "__main__":
    run_question_6()
