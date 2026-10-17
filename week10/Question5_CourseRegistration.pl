% =============================================================================
% QUESTION 5: ADVANCED PROGRAMMING COURSE REGISTRATION ELIGIBILITY
% Language: Prolog (Standard ISO / SWI-Prolog)
% =============================================================================
% Problem Description:
% A university permits students to register for an advanced programming course
% only if they have passed Python Programming and Data Structures.
%
% Student   Python   Data Structures
% Arun      Pass     Pass
% Bala      Pass     Fail
% Charan    Pass     Pass
% Divya     Fail     Pass
% =============================================================================

% -----------------------------------------------------------------------------
% Part (a): Represent the student results using logical facts.
% -----------------------------------------------------------------------------
% Representation 1: Ternary predicate student_result(Student, Course, Status).
student_result(arun, python, pass).
student_result(arun, data_structures, pass).

student_result(bala, python, pass).
student_result(bala, data_structures, fail).

student_result(charan, python, pass).
student_result(charan, data_structures, pass).

student_result(divya, python, fail).
student_result(divya, data_structures, pass).

% Representation 2: Binary relation passed(Student, Course).
% Under the Closed World Assumption (CWA), only passing facts are asserted:
passed(arun, python).
passed(arun, data_structures).
passed(bala, python).
passed(charan, python).
passed(charan, data_structures).
passed(divya, data_structures).


% -----------------------------------------------------------------------------
% Part (b): Define a rule to determine eligibility for advanced programming.
% -----------------------------------------------------------------------------
% A student is eligible if and only if they passed 'python' AND 'data_structures'.
% Rule using ternary predicate:
eligible_for_advanced(Student) :-
    student_result(Student, python, pass),
    student_result(Student, data_structures, pass).

% Rule using binary passed/2 predicate:
eligible_student(Student) :-
    passed(Student, python),
    passed(Student, data_structures).


% -----------------------------------------------------------------------------
% Part (c): Queries to identify all eligible students.
% -----------------------------------------------------------------------------
% Query 1: Find all eligible students:
% ?- eligible_for_advanced(Student).
%
% Expected Prolog execution and output:
%   Student = arun ;
%   Student = charan ;
%   false.
%
% Query 2: Test individual student eligibility:
% ?- eligible_for_advanced(arun).
%   true.
% ?- eligible_for_advanced(bala).
%   false.
%
% Query 3: Find all eligible students using findall/3 into a list:
% ?- findall(S, eligible_for_advanced(S), EligibleList).
%   EligibleList = [arun, charan].
