# Week 10: Functional Programming and Logic Programming

This repository contains complete solutions for **Week 10**, covering **Functional Programming concepts in Python** (`filter()`, `map()`, `reduce()`, pure functions, lambda functions, immutability) and **Logic Programming / Rule-Based Systems** (Facts, Rules, Queries in both Prolog and Python).

---

## Table of Contents
1. [Question 1: Student Marks Processing](#question-1-student-marks-processing)
2. [Question 2: Employee Salary Revision](#question-2-employee-salary-revision)
3. [Question 3: E-Commerce Product Discount](#question-3-e-commerce-product-discount)
4. [Question 4: Smart Electricity Meter](#question-4-smart-electricity-meter)
5. [Question 5: University Course Registration](#question-5-university-course-registration)
6. [Question 6: Employee Promotion Eligibility](#question-6-employee-promotion-eligibility)
7. [Question 7: Shopping Mall Parking Allocation System](#question-7-shopping-mall-parking-allocation-system)
8. [File Structure](#file-structure)
9. [How to Run](#how-to-run)

---

## Question 1: Student Marks Processing
A college maintains the marks obtained by students in a programming examination. Develop a Python program using functional programming concepts to:
- **a) Identify students who scored 50 marks or above using `filter()`.**
- **b) Award 5 grace marks to every student using `map()`, ensuring that the maximum mark does not exceed 100.**
- **c) Calculate the total marks using `reduce()`.**
- **d) Explain how lambda functions simplify the implementation.**

### Key Implementation Snippets:
```python
from functools import reduce

# a) Identify students who scored 50 or above
passed = list(filter(lambda s: s["marks"] >= 50, students))

# b) Award 5 grace marks, capped at 100
grace_students = list(map(
    lambda s: {**s, "marks": min(100, s["marks"] + 5)},
    students
))

# c) Calculate total marks using reduce()
total_marks = reduce(lambda acc, s: acc + s["marks"], grace_students, 0)
```

### Part (d) Explanation: How Lambda Functions Simplify the Implementation
1. **Elimination of Function Definition Boilerplate:** Without lambda functions, developers must declare standalone functions using `def` for each small operation (e.g., `def is_pass(s): return s['marks'] >= 50`). Lambdas provide concise, single-expression anonymous functions directly where needed.
2. **First-Class Functions in Higher-Order Functions:** Functions like `filter()`, `map()`, and `reduce()` take functions as their first argument. Lambdas allow defining these transformation and filtering predicates inline.
3. **Immutability and Purity:** Because Python lambdas only accept expressions (not statements or variable re-assignments), they promote pure functional pipelines without unexpected side effects.
4. **Locality of Reference:** The business logic (e.g., `min(100, mark + 5)`) is defined exactly at the call-site, making the code much easier to read and maintain.

---

## Question 2: Employee Salary Revision
A company maintains monthly salaries of its employees. The company provides a 10% salary increment to employees earning below ₹40,000.
- **a) Develop a program using `map()` and lambda functions to revise the salaries.**
- **b) Use `filter()` to identify employees eligible for the increment.**
- **c) Calculate the total salary expenditure after revision.**
- **d) Explain why functional programming is suitable for processing salary records.**

### Key Implementation Snippets:
```python
from functools import reduce

# b) Identify eligible employees (< 40,000)
eligible = list(filter(lambda emp: emp["salary"] < 40000.0, employees))

# a) Revise salaries with 10% hike using map() and lambda
revised = list(map(
    lambda emp: {
        **emp,
        "salary": round(emp["salary"] * 1.10, 2) if emp["salary"] < 40000.0 else emp["salary"]
    },
    employees
))

# c) Total expenditure using reduce()
total_expenditure = reduce(lambda acc, emp: acc + emp["salary"], revised, 0.0)
```

### Part (d) Explanation: Why Functional Programming is Suitable for Salary Records
1. **Data Immutability & Financial Auditability:** Payroll systems demand strict audit compliance. Mutating in-memory salary records in-place destroys historical states and risks corrupt data. Functional programming preserves the original record and returns new transformed versions.
2. **Pure Functions & Determinism:** Increment formulas and tax computations written as pure functions yield the exact same output for the same input without side effects, preventing discrepancies in banking and tax reporting.
3. **Declarative Pipelines (`Filter -> Map -> Reduce`):** Salary management naturally follows clear stages: filter eligible staff, map revisions, and reduce to total budget expenditures.
4. **Concurrency and Thread Safety:** Because data is immutable and functions have no shared mutable state, batch processing of thousands of employee records can be executed in parallel across threads or clusters without locks or race conditions.

---

## Question 3: E-Commerce Product Discount
An e-commerce platform maintains a list of product prices. During a festival sale, the company offers a 20% discount on products costing more than ₹1,000.
- **a) Write a pure function to calculate the discounted price.**
- **b) Use `map()` to apply the discount to eligible products.**
- **c) Use `filter()` to identify products costing more than ₹1,000.**
- **d) Explain how the pure function avoids modifying the original product list.**

### Pure Function Implementation:
```python
def calculate_discounted_price(price: float) -> float:
    """Pure function: returns discounted price if price > 1000, else original."""
    if price > 1000.0:
        return round(price * 0.80, 2)
    return round(price, 2)

# c) Filter products > ₹1,000
eligible_prices = list(filter(lambda p: p > 1000.0, prices))

# b) Apply discount with map()
discounted_prices = list(map(calculate_discounted_price, eligible_prices))
```

### Part (d) Explanation: How Pure Functions Avoid Modifying the Original List
1. **Value Immutability:** In Python, numeric types (`float`, `int`) are immutable. Passing a price passes a numeric value; any mathematical operation (`price * 0.80`) creates a brand-new number.
2. **No Mutating Operations:** The function never interacts with the enclosing list and contains no in-place mutation methods (e.g., `list[i] = ...`, `list.append()`, or `list.pop()`).
3. **`map()` Produces a New Sequence:** The `map()` function creates an independent generator that transforms elements one by one, allocating an entirely new list object in heap memory when consumed by `list()`. The original list retains its exact identity and values.

---

## Question 4: Smart Electricity Meter
A smart electricity meter records daily electricity consumption of a household.
- **a) Use `filter()` to identify days when consumption exceeded 10 units.**
- **b) Use `map()` to calculate the electricity cost for each day at ₹6 per unit.**
- **c) Use reduce() to determine the total electricity consumption.**
- **d) Develop a pure function to calculate the electricity bill without changing the original consumption data.**

### Pure Function Implementation:
```python
from functools import reduce

def calculate_electricity_bill(readings: list[float], rate: float = 6.0) -> dict:
    total_units = reduce(lambda acc, u: acc + u, readings, 0.0)
    total_bill = round(total_units * rate, 2)
    daily_costs = list(map(lambda u: round(u * rate, 2), readings))
    peak_readings = list(filter(lambda u: u > 10.0, readings))
    return {
        "total_units": round(total_units, 2),
        "total_bill": total_bill,
        "daily_costs": daily_costs,
        "peak_days": peak_readings
    }
```

---

## Question 5: University Course Registration
Permits students to register for an advanced programming course only if they have passed Python Programming and Data Structures.

| Student | Python | Data Structures |
| :--- | :--- | :--- |
| Arun | Pass | Pass |
| Bala | Pass | Fail |
| Charan | Pass | Pass |
| Divya | Fail | Pass |

### a) Logical Facts:
```prolog
student_result(arun, python, pass).
student_result(arun, data_structures, pass).
student_result(bala, python, pass).
student_result(bala, data_structures, fail).
student_result(charan, python, pass).
student_result(charan, data_structures, pass).
student_result(divya, python, fail).
student_result(divya, data_structures, pass).
```

### b) Logical Rule:
```prolog
eligible_for_advanced(Student) :-
    student_result(Student, python, pass),
    student_result(Student, data_structures, pass).
```

### c) Query:
```prolog
?- eligible_for_advanced(Student).
% Output:
% Student = arun ;
% Student = charan.
```

---

## Question 6: Employee Promotion Eligibility
Conditions:
1. Minimum 5 years of experience.
2. Completion of mandatory training.
3. Performance rating of at least 4.

| Employee | Experience (Years) | Training | Rating |
| :--- | :--- | :--- | :--- |
| Ravi | 6 | Completed | 5 |
| Priya | 3 | Completed | 4 |
| Kumar | 8 | Not Completed | 5 |
| Meena | 7 | Completed | 4 |

### a) Logical Facts:
```prolog
employee(ravi, 6, completed, 5).
employee(priya, 3, completed, 4).
employee(kumar, 8, not_completed, 5).
employee(meena, 7, completed, 4).
```

### b) Logical Rule:
```prolog
eligible_for_promotion(Employee) :-
    employee(Employee, Experience, completed, Rating),
    Experience >= 5,
    Rating >= 4.
```

### c) Query:
```prolog
?- eligible_for_promotion(Employee).
% Output:
% Employee = ravi ;
% Employee = meena.
```

---

## Question 7: Shopping Mall Parking Allocation System
Available Spaces:

| Slot | Vehicle Type | Availability |
| :--- | :--- | :--- |
| S1 | Car | Available |
| S2 | Bike | Occupied |
| S3 | Car | Available |
| S4 | Bike | Available |
| S5 | Car | Occupied |

### a) Logical Facts:
```prolog
parking_slot(s1, car, available).
parking_slot(s2, bike, occupied).
parking_slot(s3, car, available).
parking_slot(s4, bike, available).
parking_slot(s5, car, occupied).
```

### b) Logical Rule:
```prolog
available_slot_for(SlotID, VehicleType) :-
    parking_slot(SlotID, VehicleType, available).
```

### c) Query:
```prolog
?- available_slot_for(Slot, car).
% Output:
% Slot = s1 ;
% Slot = s3.
```

---

## File Structure
```
week10/
├── Question1_StudentMarks.py         # Question 1: Functional programming (filter, map, reduce, lambda)
├── Question2_SalaryIncrement.py      # Question 2: Salary revision and functional suitability
├── Question3_ProductDiscount.py      # Question 3: E-commerce pure functions & immutability
├── Question4_ElectricityBill.py      # Question 4: Smart electricity meter & bill calculations
├── Question5_CourseRegistration.py   # Question 5: Python logic programming engine
├── Question5_CourseRegistration.pl   # Question 5: Prolog facts, rules, and queries
├── Question6_EmployeePromotion.py    # Question 6: Python rule-based system
├── Question6_EmployeePromotion.pl    # Question 6: Prolog facts, rules, and queries
├── Question7_ParkingAllocation.py    # Question 7: Python parking rule engine
├── Question7_ParkingAllocation.pl    # Question 7: Prolog facts, rules, and queries
├── run_all.py                        # Master script to execute all test cases
└── README.md                         # Detailed documentation and analysis
```

---

## How to Run

### Run All Solutions:
```bash
python run_all.py
```

### Run Individual Python Solutions:
```bash
python Question1_StudentMarks.py
python Question2_SalaryIncrement.py
python Question3_ProductDiscount.py
python Question4_ElectricityBill.py
python Question5_CourseRegistration.py
python Question6_EmployeePromotion.py
python Question7_ParkingAllocation.py
```

### Run Prolog Code (via SWI-Prolog if installed):
```bash
swipl -s Question5_CourseRegistration.pl
?- eligible_for_advanced(Student).

swipl -s Question6_EmployeePromotion.pl
?- eligible_for_promotion(Employee).

swipl -s Question7_ParkingAllocation.pl
?- available_slot_for(Slot, car).
```
