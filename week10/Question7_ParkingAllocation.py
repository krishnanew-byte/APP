"""
Question 7: Rule-Based Parking Allocation System (Logic Programming)
====================================================================
A shopping mall uses a rule-based parking allocation system.
The following parking spaces are available:

Slot   Vehicle Type   Availability
S1     Car            Available
S2     Bike           Occupied
S3     Car            Available
S4     Bike           Available
S5     Car            Occupied

a) Represent the parking slot details using facts.
b) Define a rule to identify available parking slots for a particular vehicle type.
c) Write a query to retrieve available car parking slots.
"""

import sys
from typing import List, Tuple, Optional

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8")


class ParkingAllocationKnowledgeBase:
    """Rule-based inference engine for mall parking slot allocation."""

    def __init__(self):
        # Part (a): Represent parking slot details using facts
        # Schema: (slot_id, vehicle_type, availability_status)
        self.facts: List[Tuple[str, str, str]] = [
            ("S1", "Car", "Available"),
            ("S2", "Bike", "Occupied"),
            ("S3", "Car", "Available"),
            ("S4", "Bike", "Available"),
            ("S5", "Car", "Occupied"),
        ]

    # -------------------------------------------------------------------------
    # Part (b): Logical Rule Definition
    # -------------------------------------------------------------------------
    # In Prolog:
    #   available_slot_for(SlotID, VehicleType) :-
    #       parking_slot(SlotID, VehicleType, available).
    def is_slot_available_for(self, slot_id: str, vehicle_type: str) -> bool:
        """
        Rule: A slot is eligible for allocation IF:
          1. The registered vehicle type of the slot matches `vehicle_type` (case-insensitive), AND
          2. The slot status is 'Available'.
        """
        for s_id, v_type, status in self.facts:
            if s_id.upper() == slot_id.upper():
                return (v_type.lower() == vehicle_type.lower()) and (status.lower() == "available")
        return False

    # -------------------------------------------------------------------------
    # Part (c): Query Execution
    # -------------------------------------------------------------------------
    # In Prolog: ?- available_slot_for(Slot, car).
    def query_available_slots(self, target_vehicle_type: str) -> List[str]:
        """
        Query: Unify variable 'Slot' with all parking slot IDs matching vehicle type and 'Available'.
        """
        return [
            slot_id
            for slot_id, v_type, status in self.facts
            if v_type.lower() == target_vehicle_type.lower() and status.lower() == "available"
        ]

    def allocate_slot(self, vehicle_type: str) -> Optional[str]:
        """Simulate allocation of the first available slot."""
        available = self.query_available_slots(vehicle_type)
        return available[0] if available else None


def run_question_7():
    print("=" * 70)
    print("QUESTION 7: RULE-BASED PARKING ALLOCATION SYSTEM")
    print("=" * 70)

    kb = ParkingAllocationKnowledgeBase()

    # -------------------------------------------------------------------------
    # Part (a): Representing Parking Slot Details using Facts
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (a): Parking Slot Details as Logical Facts")
    print("-" * 70)
    print("1. Standard Prolog Facts:")
    print("   parking_slot(s1, car, available).")
    print("   parking_slot(s2, bike, occupied).")
    print("   parking_slot(s3, car, available).")
    print("   parking_slot(s4, bike, available).")
    print("   parking_slot(s5, car, occupied).")

    print("\n2. First-Order Logic Knowledge Base Facts:")
    for slot, v_type, status in kb.facts:
        print(f"   FACT: parking_slot(Slot='{slot}', VehicleType='{v_type}', Status='{status}')")

    # -------------------------------------------------------------------------
    # Part (b): Defining Rule to Identify Available Slots
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (b): Logical Rule Definition for Parking Slot Allocation")
    print("-" * 70)
    print("1. Prolog Rule:")
    print("   available_slot_for(SlotID, VehicleType) :-")
    print("       parking_slot(SlotID, VehicleType, available).")

    print("\n2. Formal First-Order Logic Horn Clause:")
    print("   FOR ALL Slot, Type [")
    print("       parking_slot(Slot, Type, 'Available')")
    print("       ===> available_slot_for(Slot, Type)")
    print("   ]")

    # -------------------------------------------------------------------------
    # Part (c): Query to Retrieve Available Car Parking Slots
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (c): Query Execution - Retrieve Available Car Parking Slots")
    print("-" * 70)
    print("1. Prolog Query:")
    print("   ?- available_slot_for(Slot, car).")

    print("\n2. Slot-by-Slot Evaluation Table:")
    print(f"   {'Slot':<8} {'Vehicle Type':<15} {'Status':<12} {'Available for Car?'}")
    print("   " + "-" * 55)
    for slot, v_type, status in kb.facts:
        match_car = "[YES] Match" if kb.is_slot_available_for(slot, "car") else "[NO]"
        reason = ""
        if v_type.lower() != "car":
            reason = f"Type mismatch ({v_type})"
        elif status.lower() != "available":
            reason = "Currently Occupied"
        else:
            reason = "Free to assign"

        print(f"   {slot:<8} {v_type:<15} {status:<12} {match_car:<18} ({reason})")

    # Execute queries
    available_car_slots = kb.query_available_slots("car")
    available_bike_slots = kb.query_available_slots("bike")

    print("\n3. Query Results:")
    print(f"   Available Car Slots  : {available_car_slots}")
    print(f"   Prolog Output        : Slot = s1 ; Slot = s3.")
    print(f"\n   [Additional Query - Bike Slots]:")
    print(f"   Available Bike Slots : {available_bike_slots}")
    print(f"   Prolog Output        : Slot = s4.")

    allocated_car = kb.allocate_slot("car")
    print(f"\n4. Allocation Simulator:")
    print(f"   Next incoming Car assigned to Slot -> [{allocated_car}]")
    print("=" * 70 + "\n")


if __name__ == "__main__":
    run_question_7()
