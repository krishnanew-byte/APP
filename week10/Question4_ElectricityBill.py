"""
Question 4: Smart Electricity Meter Consumption using Functional Programming
=============================================================================
A smart electricity meter records the daily electricity consumption of a household.
a) Use filter() to identify days when consumption exceeded 10 units.
b) Use map() to calculate the electricity cost for each day at ₹6 per unit.
c) Use reduce() to determine the total electricity consumption.
d) Develop a pure function to calculate the electricity bill without changing the original
   consumption data.
"""

import sys
from functools import reduce
from typing import List, Dict, Any

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8")

RATE_PER_UNIT = 6.0  # Rs. 6 per unit


# -------------------------------------------------------------------------
# Part (d): Pure function to calculate the electricity bill
# -------------------------------------------------------------------------
def calculate_electricity_bill(
    consumption_data: List[float], rate: float = RATE_PER_UNIT
) -> Dict[str, Any]:
    """
    Pure Function to compute the complete electricity bill from daily consumption data.

    Properties:
      - Does NOT modify the incoming `consumption_data` list.
      - Has NO external side-effects (no I/O, no mutation of global variables).
      - Returns a newly created dictionary containing bill analytics.
    """
    total_units = reduce(lambda acc, u: acc + u, consumption_data, 0.0)
    total_bill = round(total_units * rate, 2)
    daily_costs = list(map(lambda u: round(u * rate, 2), consumption_data))
    high_consumption_readings = list(filter(lambda u: u > 10.0, consumption_data))
    avg_daily_units = round(total_units / len(consumption_data), 2) if consumption_data else 0.0

    return {
        "rate_per_unit": rate,
        "days_recorded": len(consumption_data),
        "total_units_consumed": round(total_units, 2),
        "total_bill_amount": total_bill,
        "average_daily_units": avg_daily_units,
        "daily_breakdown_costs": daily_costs,
        "peak_days_readings": high_consumption_readings,
        "peak_days_count": len(high_consumption_readings),
    }


def run_question_4():
    print("=" * 70)
    print("QUESTION 4: SMART ELECTRICITY METER (FUNCTIONAL PROGRAMMING)")
    print("=" * 70)

    # Initial Dataset: Daily electricity consumption recorded by smart meter
    daily_readings: List[Dict[str, Any]] = [
        {"day": "Day 1", "units": 8.5},
        {"day": "Day 2", "units": 12.4},
        {"day": "Day 3", "units": 15.0},
        {"day": "Day 4", "units": 9.2},
        {"day": "Day 5", "units": 14.8},
        {"day": "Day 6", "units": 6.0},
        {"day": "Day 7", "units": 11.5},
        {"day": "Day 8", "units": 18.2},
        {"day": "Day 9", "units": 7.4},
        {"day": "Day 10", "units": 10.2},
    ]

    # Numeric list of consumption readings
    consumption_units: List[float] = [d["units"] for d in daily_readings]

    print("\n[Recorded Daily Electricity Consumption]:")
    print(f"  {'Day':<10} {'Units Consumed':>18}")
    print("  " + "-" * 30)
    for d in daily_readings:
        print(f"  {d['day']:<10} {d['units']:>14.1f} units")
    print(f"\n  Units list: {consumption_units}")

    # -------------------------------------------------------------------------
    # Part (a): Use filter() to identify days when consumption exceeded 10 units
    # -------------------------------------------------------------------------
    THRESHOLD = 10.0
    high_consumption_days = list(
        filter(lambda d: d["units"] > THRESHOLD, daily_readings)
    )
    high_units_only = list(filter(lambda u: u > THRESHOLD, consumption_units))

    print("\n" + "-" * 70)
    print("Part (a): Days when consumption exceeded 10 units using filter()")
    print("-" * 70)
    print(f"  Threshold: > {THRESHOLD} units")
    print(f"  High consumption days count: {len(high_consumption_days)} of {len(daily_readings)}")
    for d in high_consumption_days:
        print(f"  * [PEAK] {d['day']:<10} : {d['units']:.1f} units")
    print(f"  Filtered high consumption list: {high_units_only}")

    # -------------------------------------------------------------------------
    # Part (b): Use map() to calculate electricity cost at ₹6 per unit
    # -------------------------------------------------------------------------
    # Using lambda with map() to multiply each unit by RATE_PER_UNIT (Rs. 6)
    daily_costs = list(map(lambda u: round(u * RATE_PER_UNIT, 2), consumption_units))

    daily_costs_detailed = list(
        map(
            lambda d: {
                "day": d["day"],
                "units": d["units"],
                "cost": round(d["units"] * RATE_PER_UNIT, 2),
                "is_peak": d["units"] > THRESHOLD,
            },
            daily_readings,
        )
    )

    print("\n" + "-" * 70)
    print(f"Part (b): Electricity cost for each day at Rs. {RATE_PER_UNIT:.2f}/unit using map()")
    print("-" * 70)
    print(f"  Formula: cost = units * Rs. {RATE_PER_UNIT:.2f}")
    print(f"  {'Day':<10} {'Units':>10} {'Cost (INR)':>17} {'Classification'}")
    print("  " + "-" * 57)
    for item in daily_costs_detailed:
        badge = "Peak (>10 units)" if item["is_peak"] else "Normal"
        print(f"  {item['day']:<10} {item['units']:>8.1f}   Rs. {item['cost']:>10,.2f}   {badge}")
    print(f"\n  Daily costs list: {daily_costs}")

    # -------------------------------------------------------------------------
    # Part (c): Use reduce() to determine the total electricity consumption
    # -------------------------------------------------------------------------
    total_units_consumed = reduce(
        lambda acc, u: acc + u, consumption_units, 0.0
    )
    total_cost_from_reduce = reduce(
        lambda acc, c: acc + c, daily_costs, 0.0
    )

    print("\n" + "-" * 70)
    print("Part (c): Total electricity consumption using reduce()")
    print("-" * 70)
    print(f"  Total Electricity Consumed : {total_units_consumed:.2f} units")
    print(f"  Total Cost (reduce sum)    : Rs. {total_cost_from_reduce:,.2f}")
    print(f"  Average Daily Consumption  : {total_units_consumed / len(consumption_units):.2f} units/day")

    # -------------------------------------------------------------------------
    # Part (d): Pure function to calculate bill without changing original data
    # -------------------------------------------------------------------------
    print("\n" + "-" * 70)
    print("Part (d): Pure Function Execution & Immutability Proof")
    print("-" * 70)

    # Snapshot of consumption units before passing into pure function
    before_call_snapshot = list(consumption_units)

    # Invoke pure function
    bill_summary = calculate_electricity_bill(consumption_units, rate=6.0)

    # Snapshot after call
    after_call_snapshot = list(consumption_units)
    is_unmodified = (before_call_snapshot == after_call_snapshot == consumption_units)

    print("  [Pure Function Output Summary]:")
    print(f"  - Total Units Consumed      : {bill_summary['total_units_consumed']} units")
    print(f"  - Tariff Rate               : Rs. {bill_summary['rate_per_unit']:.2f} / unit")
    print(f"  - Total Bill Payable        : Rs. {bill_summary['total_bill_amount']:,.2f}")
    print(f"  - Average Consumption       : {bill_summary['average_daily_units']} units/day")
    print(f"  - Days Monitored            : {bill_summary['days_recorded']} days")
    print(f"  - Peak Days (>10 units)     : {bill_summary['peak_days_count']} days")

    print(f"\n  [Immutability Verification]:")
    print(f"  - Data before function call : {before_call_snapshot}")
    print(f"  - Data after function call  : {after_call_snapshot}")
    print(f"  - Was original data preserved 100% without modification? -> {is_unmodified}")

    print("=" * 70 + "\n")


if __name__ == "__main__":
    run_question_4()
