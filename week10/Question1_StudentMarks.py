"""
Question 1: Student Marks Processing using Functional Programming
=================================================================
A college maintains the marks obtained by students in a programming examination.
Develop a Python program using functional programming concepts to:
a) Identify students who scored 50 marks or above using filter().
b) Award 5 grace marks to every student using map(), ensuring that the maximum
   mark does not exceed 100.
c) Calculate the total marks using reduce().
d) Explain how lambda functions simplify the implementation.
"""

import sys
from functools import reduce
from typing import List, Dict, Any

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8")


def run_question_1():
    print("=" * 70)
    print("QUESTION 1: STUDENT MARKS PROCESSING (FUNCTIONAL PROGRAMMING)")
    print("=" * 70)

    # Initial Dataset: Students and their examination marks
    students: List[Dict[str, Any]] = [
        {"name": "Arun", "marks": 45},
        {"name": "Bala", "marks": 78},
        {"name": "Charan", "marks": 50},
        {"name": "Divya", "marks": 98},
        {"name": "Eshwar", "marks": 35},
        {"name": "Farhan", "marks": 96},
        {"name": "Geeta", "marks": 52},
        {"name": "Hari", "marks": 89},
    ]

    # Simple list of marks for pure numerical demonstration
    marks_list: List[int] = [s["marks"] for s in students]

    print("\n[Original Student Records]:")
    for s in students:
        print(f"  - {s['name']:<10} : {s['marks']} marks")
    print(f"  Raw marks list: {marks_list}")

    # -------------------------------------------------------------------------
    # Part (a): Identify students who scored 50 marks or above using filter()
    # -------------------------------------------------------------------------
    # Using lambda with filter() to extract students scoring >= 50 marks
    passed_students = list(filter(lambda s: s["marks"] >= 50, students))
    passed_marks_only = list(filter(lambda m: m >= 50, marks_list))

    print("\n" + "-" * 70)
    print("Part (a): Students who scored 50 marks or above using filter()")
    print("-" * 70)
    print(f"  Criteria: mark >= 50")
    print(f"  Eligible count: {len(passed_students)}/{len(students)}")
    for s in passed_students:
        print(f"  [PASS] {s['name']:<10} : {s['marks']} marks")
    print(f"  Filtered marks list: {passed_marks_only}")

    # -------------------------------------------------------------------------
    # Part (b): Award 5 grace marks using map(), capping at 100
    # -------------------------------------------------------------------------
    # Using lambda with map() to add 5 grace marks, min(100, marks + 5)
    # Functional programming creates new dictionaries to maintain immutability.
    students_with_grace = list(
        map(
            lambda s: {
                "name": s["name"],
                "original_marks": s["marks"],
                "marks": min(100, s["marks"] + 5),
            },
            students,
        )
    )
    grace_marks_only = list(map(lambda m: min(100, m + 5), marks_list))

    print("\n" + "-" * 70)
    print("Part (b): Award 5 grace marks to every student using map() [Cap: 100]")
    print("-" * 70)
    print(f"  Transformation: min(100, marks + 5)")
    for s in students_with_grace:
        capped_note = " (CAPPED AT 100)" if s["original_marks"] + 5 > 100 else ""
        print(
            f"  * {s['name']:<10} : {s['original_marks']} + 5 -> {s['marks']} marks{capped_note}"
        )
    print(f"  Grace marks list: {grace_marks_only}")

    # -------------------------------------------------------------------------
    # Part (c): Calculate total marks using reduce()
    # -------------------------------------------------------------------------
    total_original_marks = reduce(lambda acc, m: acc + m, marks_list)
    total_grace_marks = reduce(lambda acc, m: acc + m, grace_marks_only)
    total_marks_from_dict = reduce(
        lambda acc, s: acc + s["marks"], students_with_grace, 0
    )

    print("\n" + "-" * 70)
    print("Part (c): Calculate total marks using reduce()")
    print("-" * 70)
    print(f"  Total marks (Original)    : {total_original_marks}")
    print(f"  Total marks (After Grace) : {total_grace_marks}")
    print(f"  Total marks (From Dicts)  : {total_marks_from_dict}")
    print(f"  Net grace marks awarded   : {total_grace_marks - total_original_marks}")

    # -------------------------------------------------------------------------
    # Part (d): Explanation of how lambda functions simplify implementation
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (d): Explanation - How Lambda Functions Simplify Implementation")
    print("-" * 70)
    explanation = """
1. Elimination of Boilerplate:
   In standard Python, every operation would require defining named functions using
   'def', such as 'def is_pass(s): return s >= 50' or 'def add_grace(m): return min(100, m+5)'.
   Lambda functions allow inline, anonymous definitions (e.g., 'lambda m: min(100, m + 5)'),
   eliminating clutter and temporary function names from the namespace.

2. First-Class Functions and Higher-Order Interoperability:
   Higher-order functions like filter(), map(), and reduce() expect a callable as their
   first argument. Lambda functions can be written directly at the call site, keeping
   the transformation logic adjacent to where it is used (locality of reference).

3. Enforced Functional Purity:
   Python lambdas are restricted to single expressions. They cannot contain statements,
   in-place variable assignments, or side-effects, naturally promoting immutable,
   purely functional transformations.

4. Syntactic Conciseness and Readability:
   Complex pipelines can be expressed cleanly in fewer lines of code without sacrificing
   clarity. For example:
     reduce(lambda acc, m: acc + m, map(lambda m: min(100, m + 5), marks))
"""
    print(explanation.strip())
    print("=" * 70 + "\n")


if __name__ == "__main__":
    run_question_1()
