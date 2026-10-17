"""
Question 5: University Course Registration (Logic Programming)
============================================================
A university permits students to register for an advanced programming course only if they
have passed Python Programming and Data Structures.
The following facts are available:

Student   Python  Data Structures
Arun      Pass    Pass
Bala      Pass    Fail
Charan    Pass    Pass
Divya     Fail    Pass

a) Represent the student results using logical facts.
b) Define a rule to determine eligibility for the advanced programming course.
c) Write a query to identify all eligible students.
"""

import sys
from typing import List, Tuple, Dict, Set, Any

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8")


class CourseRegistrationKnowledgeBase:
    """
    In-memory First-Order Logic Knowledge Base representing student results,
    inference rules, and query resolution.
    """

    def __init__(self):
        # Part (a): Logical Facts Knowledge Base
        # Stored as First-Order Predicates: student_result(Student, Course, Status)
        self.facts: Set[Tuple[str, str, str]] = {
            ("Arun", "Python", "Pass"),
            ("Arun", "Data Structures", "Pass"),
            ("Bala", "Python", "Pass"),
            ("Bala", "Data Structures", "Fail"),
            ("Charan", "Python", "Pass"),
            ("Charan", "Data Structures", "Pass"),
            ("Divya", "Python", "Fail"),
            ("Divya", "Data Structures", "Pass"),
        }

    # -------------------------------------------------------------------------
    # Part (b): Logical Rule Definition
    # -------------------------------------------------------------------------
    # In Prolog:
    #   eligible(Student) :-
    #       student_result(Student, 'Python', 'Pass'),
    #       student_result(Student, 'Data Structures', 'Pass').
    def is_eligible(self, student: str) -> bool:
        """
        Rule: A student is eligible IF AND ONLY IF:
          1. student_result(Student, 'Python', 'Pass') is TRUE, AND
          2. student_result(Student, 'Data Structures', 'Pass') is TRUE.
        """
        passed_python = (student, "Python", "Pass") in self.facts
        passed_ds = (student, "Data Structures", "Pass") in self.facts
        return passed_python and passed_ds

    # -------------------------------------------------------------------------
    # Part (c): Query Resolution Engine
    # -------------------------------------------------------------------------
    # In Prolog: ?- eligible(Student).
    def query_eligible_students(self) -> List[str]:
        """
        Query: Unify variable 'Student' with all individuals satisfying the rule.
        """
        all_students = sorted(list({fact[0] for fact in self.facts}))
        return [s for s in all_students if self.is_eligible(s)]

    def query_student_status(self, student: str) -> Dict[str, Any]:
        """Query individual student status and eligibility."""
        python_status = next(
            (status for s, c, status in self.facts if s == student and c == "Python"),
            "Unknown",
        )
        ds_status = next(
            (status for s, c, status in self.facts if s == student and c == "Data Structures"),
            "Unknown",
        )
        eligible = self.is_eligible(student)
        return {
            "student": student,
            "python": python_status,
            "data_structures": ds_status,
            "eligible": eligible,
        }


def run_question_5():
    print("=" * 70)
    print("QUESTION 5: LOGIC PROGRAMMING - COURSE REGISTRATION ELIGIBILITY")
    print("=" * 70)

    kb = CourseRegistrationKnowledgeBase()

    # -------------------------------------------------------------------------
    # Part (a): Representing Student Results using Logical Facts
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (a): Logical Facts Representation")
    print("-" * 70)
    print("1. Standard Prolog Syntax:")
    print("   student_result(arun, python, pass).")
    print("   student_result(arun, data_structures, pass).")
    print("   student_result(bala, python, pass).")
    print("   student_result(bala, data_structures, fail).")
    print("   student_result(charan, python, pass).")
    print("   student_result(charan, data_structures, pass).")
    print("   student_result(divya, python, fail).")
    print("   student_result(divya, data_structures, pass).")
    print("\n2. First-Order Logic Predicate Representation:")
    for student, course, status in sorted(kb.facts):
        print(f"   FACT: student_result({student}, {course}, {status})")

    # -------------------------------------------------------------------------
    # Part (b): Logical Rule Definition
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (b): Logical Rule Definition")
    print("-" * 70)
    print("1. Prolog Rule:")
    print("   eligible_for_advanced(Student) :-")
    print("       student_result(Student, python, pass),")
    print("       student_result(Student, data_structures, pass).")
    print("\n2. First-Order Logic Formal Horn Clause:")
    print("   FOR ALL Student [ Passed(Student, Python) AND Passed(Student, DataStructures)")
    print("                     ===> EligibleForAdvancedCourse(Student) ]")

    # -------------------------------------------------------------------------
    # Part (c): Query Execution and Evaluation
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (c): Query to Identify All Eligible Students")
    print("-" * 70)
    print("1. Prolog Query:")
    print("   ?- eligible_for_advanced(Student).")

    print("\n2. Evaluation against Knowledge Base:")
    all_students = sorted(list({fact[0] for fact in kb.facts}))
    for s in all_students:
        info = kb.query_student_status(s)
        reason = ""
        if info["eligible"]:
            reason = "[PASS] Passed both Python and Data Structures"
        elif info["python"] != "Pass":
            reason = "[FAIL] Failed Python"
        elif info["data_structures"] != "Pass":
            reason = "[FAIL] Failed Data Structures"

        status_str = "ELIGIBLE" if info["eligible"] else "NOT ELIGIBLE"
        print(
            f"   {info['student']:<10} | Python: {info['python']:<4} | "
            f"DS: {info['data_structures']:<4} | -> [{status_str:<12}] ({reason})"
        )

    eligible_students = kb.query_eligible_students()
    print("\n3. Query Result:")
    print(f"   Eligible Students: {eligible_students}")
    print(f"   Prolog Result: Student = arun ; Student = charan.")
    print("=" * 70 + "\n")


if __name__ == "__main__":
    run_question_5()
