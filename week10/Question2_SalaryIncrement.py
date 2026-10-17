"""
Question 2: Employee Salary Revision using Functional Programming
================================================================
A company maintains the monthly salaries of its employees.
The company decides to provide a 10% salary increment to employees earning below ₹40,000.
a) Develop a program using map() and lambda functions to revise the salaries.
b) Use filter() to identify employees eligible for the increment.
c) Calculate the total salary expenditure after revision.
d) Explain why functional programming is suitable for processing salary records.
"""

import sys
from functools import reduce
from typing import List, Dict, Any

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8")


def run_question_2():
    print("=" * 70)
    print("QUESTION 2: EMPLOYEE SALARY REVISION (FUNCTIONAL PROGRAMMING)")
    print("=" * 70)

    # Initial Dataset: Employee records with monthly salaries
    employees: List[Dict[str, Any]] = [
        {"id": "EMP101", "name": "Ravi", "department": "Operations", "salary": 32000.0},
        {"id": "EMP102", "name": "Priya", "department": "Engineering", "salary": 48000.0},
        {"id": "EMP103", "name": "Kumar", "department": "Logistics", "salary": 28500.0},
        {"id": "EMP104", "name": "Meena", "department": "Finance", "salary": 55000.0},
        {"id": "EMP105", "name": "Anand", "department": "Support", "salary": 39500.0},
        {"id": "EMP106", "name": "Kavita", "department": "Marketing", "salary": 40000.0},
        {"id": "EMP107", "name": "Suresh", "department": "IT Support", "salary": 36000.0},
    ]

    print("\n[Initial Employee Salary Records]:")
    print(f"  {'ID':<8} {'Name':<10} {'Department':<15} {'Monthly Salary (INR)':>22}")
    print("  " + "-" * 59)
    for emp in employees:
        print(f"  {emp['id']:<8} {emp['name']:<10} {emp['department']:<15} Rs. {emp['salary']:>16,.2f}")

    # -------------------------------------------------------------------------
    # Part (b): Identify employees eligible for increment using filter()
    # (Identified first to clearly show the condition: salary < 40,000)
    # -------------------------------------------------------------------------
    THRESHOLD = 40000.0
    INCREMENT_RATE = 0.10  # 10%

    eligible_employees = list(filter(lambda emp: emp["salary"] < THRESHOLD, employees))

    print("\n" + "-" * 70)
    print("Part (b): Employees eligible for 10% increment (< Rs. 40,000) using filter()")
    print("-" * 70)
    print(f"  Condition: salary < Rs. {THRESHOLD:,.2f}")
    print(f"  Eligible count: {len(eligible_employees)} of {len(employees)}")
    print(f"  {'ID':<8} {'Name':<10} {'Current Salary':>18} {'Status':<15}")
    print("  " + "-" * 55)
    for emp in eligible_employees:
        print(f"  {emp['id']:<8} {emp['name']:<10} Rs. {emp['salary']:>12,.2f}   Eligible")

    # -------------------------------------------------------------------------
    # Part (a): Revise salaries using map() and lambda functions
    # -------------------------------------------------------------------------
    # If salary < 40,000: new_salary = salary * 1.10 (10% increment)
    # Otherwise: unchanged
    # Immutability preserved: produces new dictionaries
    revised_employees = list(
        map(
            lambda emp: {
                "id": emp["id"],
                "name": emp["name"],
                "department": emp["department"],
                "old_salary": emp["salary"],
                "salary": round(emp["salary"] * (1.0 + INCREMENT_RATE), 2)
                if emp["salary"] < THRESHOLD
                else emp["salary"],
                "is_incremented": emp["salary"] < THRESHOLD,
            },
            employees,
        )
    )

    print("\n" + "-" * 70)
    print("Part (a): Revised salaries using map() and lambda")
    print("-" * 70)
    print(f"  {'ID':<8} {'Name':<10} {'Old Salary':>16} {'New Salary':>16} {'Hike':>14} {'Status'}")
    print("  " + "-" * 75)
    for emp in revised_employees:
        hike = emp["salary"] - emp["old_salary"]
        status = "+10% Applied" if emp["is_incremented"] else "No Change (>= 40k)"
        print(
            f"  {emp['id']:<8} {emp['name']:<10} Rs. {emp['old_salary']:>10,.2f} "
            f"Rs. {emp['salary']:>10,.2f} Rs. {hike:>8,.2f}  {status}"
        )

    # -------------------------------------------------------------------------
    # Part (c): Calculate total salary expenditure after revision
    # -------------------------------------------------------------------------
    # Using reduce() to aggregate total expenditure before and after
    total_initial_expenditure = reduce(
        lambda acc, emp: acc + emp["salary"], employees, 0.0
    )
    total_revised_expenditure = reduce(
        lambda acc, emp: acc + emp["salary"], revised_employees, 0.0
    )
    net_additional_expenditure = total_revised_expenditure - total_initial_expenditure

    print("\n" + "-" * 70)
    print("Part (c): Total salary expenditure calculation using reduce()")
    print("-" * 70)
    print(f"  Total initial monthly expenditure : Rs. {total_initial_expenditure:>14,.2f}")
    print(f"  Total revised monthly expenditure : Rs. {total_revised_expenditure:>14,.2f}")
    print(f"  Net monthly expenditure increase  : Rs. {net_additional_expenditure:>14,.2f}")
    print(f"  Percentage increase in payroll    : {(net_additional_expenditure / total_initial_expenditure) * 100:.2f}%")

    # -------------------------------------------------------------------------
    # Part (d): Why functional programming is suitable for processing salary records
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (d): Why Functional Programming is Suitable for Salary Records")
    print("-" * 70)
    explanation = """
1. Data Immutability and Audit Integrity:
   In financial systems and payroll management, modifying records in-place (mutation)
   risks state corruption and audit failure. Functional programming guarantees that
   original salary data remains strictly unaltered, producing new transformed versions.
   This maintains an auditable trail of pre-revision and post-revision states.

2. Pure Functions and Deterministic Compensation:
   Calculating percentages, tax deductions, bonuses, and hikes with pure functions ensures
   that the exact same inputs always generate identical financial outputs without side effects
   such as accidental database writes or unintended variable overrides.

3. Pipeline Composability (filter -> map -> reduce):
   Payroll processing is naturally sequential:
     Step 1 (Filter): Select employees based on policy criteria (< Rs. 40,000).
     Step 2 (Map)   : Compute and apply individual percentage revisions.
     Step 3 (Reduce): Aggregate corporate-level budgetary expenditure.
   Functional programming expresses this declarative pipeline clearly and concisely.

4. Thread Safety and Parallel Scalability:
   Because functional transformations rely on immutable inputs and pure functions with no
   shared mutable state, processing payroll for millions of employees across multiple threads
   or distributed clusters (e.g., Apache Spark) requires no locking primitives or mutexes.

5. Testability and Maintenance:
   Rules like eligibility and percentage calculation can be isolated and verified through
   rigorous automated unit tests without needing complex mocks or database fixtures.
"""
    print(explanation.strip())
    print("=" * 70 + "\n")


if __name__ == "__main__":
    run_question_2()
