% =============================================================================
% QUESTION 6: EMPLOYEE PROMOTION ELIGIBILITY (RULE-BASED SYSTEM)
% Language: Prolog (Standard ISO / SWI-Prolog)
% =============================================================================
% Promotion Criteria:
%   1. Minimum 5 years of experience (Experience >= 5).
%   2. Completion of mandatory training (Training == completed).
%   3. Performance rating of at least 4 (Rating >= 4).
%
% Employee Information Table:
% Employee   Experience (Years)   Training        Rating
% Ravi       6                    Completed       5
% Priya      3                    Completed       4
% Kumar      8                    Not Completed   5
% Meena      7                    Completed       4
% =============================================================================

% -----------------------------------------------------------------------------
% Part (a): Represent employee information as logical facts.
% -----------------------------------------------------------------------------
% Predicate schema: employee(Name, ExperienceInYears, TrainingStatus, PerformanceRating).
employee(ravi, 6, completed, 5).
employee(priya, 3, completed, 4).
employee(kumar, 8, not_completed, 5).
employee(meena, 7, completed, 4).


% -----------------------------------------------------------------------------
% Part (b): Define a logical rule for promotion eligibility.
% -----------------------------------------------------------------------------
% A candidate is eligible for promotion if they satisfy all three constraints:
eligible_for_promotion(Employee) :-
    employee(Employee, Experience, completed, Rating),
    Experience >= 5,
    Rating >= 4.

% Detailed rule capturing individual criteria explanations:
promotion_criteria_check(Employee, Exp, Training, Rating, Status) :-
    employee(Employee, Exp, Training, Rating),
    (
        (Exp >= 5, Training == completed, Rating >= 4) ->
            Status = eligible
        ;
            Status = not_eligible
    ).


% -----------------------------------------------------------------------------
% Part (c): Query to retrieve employees eligible for promotion.
% -----------------------------------------------------------------------------
% Query 1: Find all eligible employees:
% ?- eligible_for_promotion(Employee).
%
% Expected Prolog execution and output:
%   Employee = ravi ;
%   Employee = meena ;
%   false.
%
% Query 2: Retrieve all eligible employees as a unified list:
% ?- findall(E, eligible_for_promotion(E), PromotionList).
%   PromotionList = [ravi, meena].
%
% Query 3: Verify single candidate:
% ?- eligible_for_promotion(ravi).
%   true.
% ?- eligible_for_promotion(priya).
%   false. (Fails because Experience = 3 < 5)
% ?- eligible_for_promotion(kumar).
%   false. (Fails because Training = not_completed)
