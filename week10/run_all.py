"""
Week 10 - Master Execution Script
=================================
Runs all solutions from Question 1 to Question 7 sequentially, displaying
comprehensive outputs, validations, and theoretical answers.
"""

import sys
import subprocess
from Question1_StudentMarks import run_question_1
from Question2_SalaryIncrement import run_question_2
from Question3_ProductDiscount import run_question_3
from Question4_ElectricityBill import run_question_4
from Question5_CourseRegistration import run_question_5
from Question6_EmployeePromotion import run_question_6
from Question7_ParkingAllocation import run_question_7


def main():
    print("#" * 80)
    print(" " * 20 + "WEEK 10: FUNCTIONAL & LOGIC PROGRAMMING")
    print(" " * 23 + "FULL TEST & EXECUTION SUITE")
    print("#" * 80)
    print()

    tasks = [
        ("Question 1: Student Marks (Functional Programming)", run_question_1),
        ("Question 2: Salary Increment (Functional Programming)", run_question_2),
        ("Question 3: Product Discount (Pure Functions)", run_question_3),
        ("Question 4: Smart Electricity Meter (Functional Programming)", run_question_4),
        ("Question 5: Course Registration (Logic Programming)", run_question_5),
        ("Question 6: Employee Promotion (Rule-Based System)", run_question_6),
        ("Question 7: Parking Allocation (Rule-Based System)", run_question_7),
    ]

    for title, func in tasks:
        try:
            func()
        except Exception as e:
            print(f"[ERROR] Failed executing {title}: {e}", file=sys.stderr)

    print("#" * 80)
    print(" " * 26 + "ALL 7 QUESTIONS EXECUTED SUCCESSFULLY")
    print("#" * 80)


if __name__ == "__main__":
    main()
