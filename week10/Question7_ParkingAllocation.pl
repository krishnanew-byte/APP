% =============================================================================
% QUESTION 7: RULE-BASED PARKING ALLOCATION SYSTEM
% Language: Prolog (Standard ISO / SWI-Prolog)
% =============================================================================
% Problem Description:
% A shopping mall uses a rule-based parking allocation system.
% The following parking spaces are available:
%
% Slot   Vehicle Type   Availability
% S1     Car            Available
% S2     Bike           Occupied
% S3     Car            Available
% S4     Bike           Available
% S5     Car            Occupied
% =============================================================================

% -----------------------------------------------------------------------------
% Part (a): Represent the parking slot details using facts.
% -----------------------------------------------------------------------------
% Predicate schema: parking_slot(SlotID, VehicleType, AvailabilityStatus).
parking_slot(s1, car, available).
parking_slot(s2, bike, occupied).
parking_slot(s3, car, available).
parking_slot(s4, bike, available).
parking_slot(s5, car, occupied).


% -----------------------------------------------------------------------------
% Part (b): Define a rule to identify available parking slots for a vehicle type.
% -----------------------------------------------------------------------------
% Rule: A slot is available for VehicleType if the slot matches the vehicle type
% and its current status is 'available'.
available_slot_for(SlotID, VehicleType) :-
    parking_slot(SlotID, VehicleType, available).

% Generalized rule to check slot availability:
is_slot_free(SlotID) :-
    parking_slot(SlotID, _, available).


% -----------------------------------------------------------------------------
% Part (c): Query to retrieve available car parking slots.
% -----------------------------------------------------------------------------
% Query 1: Retrieve available slots for 'car':
% ?- available_slot_for(Slot, car).
%
% Expected Prolog execution and output:
%   Slot = s1 ;
%   Slot = s3 ;
%   false.
%
% Query 2: Retrieve all available car slots into a list using findall/3:
% ?- findall(Slot, available_slot_for(Slot, car), AvailableCarSlots).
%   AvailableCarSlots = [s1, s3].
%
% Query 3: Retrieve available bike slots (for comparison):
% ?- available_slot_for(Slot, bike).
%   Slot = s4.
