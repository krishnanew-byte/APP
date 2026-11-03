"""
Question 4: Vehicle Kinematics and Motion Analysis using SymPy
An automobile company analyzes the motion of a vehicle using its
displacement function:
    s(t) = t^3 - 6t^2 + 9t + 5

This program uses the SymPy library to:
1. Define the displacement function s(t).
2. Determine velocity v(t) through first-order symbolic differentiation: v(t) = ds/dt.
3. Determine acceleration a(t) through second-order symbolic differentiation: a(t) = dv/dt = d^2s/dt^2.
4. Identify the time points at which the vehicle is momentarily at rest (v(t) = 0).
5. Calculate vehicle displacement over specified time intervals using definite integration:
   integral_{t1}^{t2} v(t) dt = s(t2) - s(t1).
"""

import sympy as sp
import sys


def vehicle_motion_analysis(t_start: float = 0.0, t_end: float = 4.0):
    print("=" * 80)
    print("         AUTOMOBILE KINEMATICS & MOTION ANALYSIS USING SYMPY          ")
    print("=" * 80)

    # ---------------------------------------------------------
    # 1. Define Symbolic Variables and Displacement Function
    # ---------------------------------------------------------
    print("\n--- [STEP 1] Defining Symbolic Time Variable and Displacement Function ---")
    t = sp.symbols('t', real=True, nonnegative=True)
    s = t**3 - 6 * t**2 + 9 * t + 5

    print(f"Time Variable (t)               : {t} (seconds, t >= 0)")
    print(f"Given Displacement Function s(t): s(t) = {s} (meters)")

    # ---------------------------------------------------------
    # 2. Determine Velocity via Symbolic Differentiation
    # ---------------------------------------------------------
    print("\n--- [STEP 2] Determining Velocity through Symbolic Differentiation ---")
    # v(t) = ds/dt
    v = sp.diff(s, t)
    v_factored = sp.factor(v)
    print(f"Velocity v(t) = ds/dt           : v(t) = {v} (m/s)")
    print(f"Factored Velocity Form          : v(t) = {v_factored}")

    # ---------------------------------------------------------
    # 3. Determine Acceleration via Symbolic Differentiation
    # ---------------------------------------------------------
    print("\n--- [STEP 3] Determining Acceleration through Symbolic Differentiation ---")
    # a(t) = dv/dt = d^2s/dt^2
    a = sp.diff(v, t)
    print(f"Acceleration a(t) = dv/dt       : a(t) = {a} (m/s^2)")

    # ---------------------------------------------------------
    # 4. Identify Times at Which the Vehicle is Momentarily at Rest
    # ---------------------------------------------------------
    print("\n--- [STEP 4] Finding Times When Vehicle is Momentarily at Rest ---")
    print("Physical Condition for Rest: Velocity v(t) = 0")
    rest_equation = sp.Eq(v, 0)
    print(f"Rest Equation                   : {rest_equation}")

    rest_times = sp.solve(rest_equation, t)
    print(f"Solved Rest Times (roots of v)  : t = {rest_times} seconds")

    print("\nState of Vehicle at Momentary Rest Points:")
    print("-" * 75)
    print(f"{'Time (s)':<10} | {'Velocity v(t)':<15} | {'Displacement s(t)':<20} | {'Acceleration a(t)':<20}")
    print("-" * 75)
    for rt in rest_times:
        s_val = s.subs(t, rt)
        v_val = v.subs(t, rt)
        a_val = a.subs(t, rt)
        motion_state = "Decelerating / Local Max" if a_val < 0 else "Accelerating / Local Min"
        print(f"t = {float(rt):<6.2f} s | {float(v_val):<6.2f} m/s      | s = {float(s_val):<6.2f} m        | a = {float(a_val):<6.2f} m/s^2 ({motion_state})")
    print("-" * 75)

    # ---------------------------------------------------------
    # 5. Calculate Displacement using Definite Integration
    # ---------------------------------------------------------
    print("\n--- [STEP 5] Calculating Displacement via Definite Integration ---")
    print("By the Fundamental Theorem of Calculus:")
    print("  Displacement Delta_s = Integral from t1 to t2 of v(t) dt = s(t2) - s(t1)")

    test_intervals = [
        (0, 4),
        (0, 2),
        (1, 3)
    ]

    # Include user-requested interval if distinct
    custom_interval = (t_start, t_end)
    if custom_interval not in test_intervals:
        test_intervals.insert(0, custom_interval)

    print(f"\n{'Interval [t1, t2]':<20} | {'Definite Integral of v(t)':<30} | {'s(t2) - s(t1)':<20} | {'Match'}")
    print("-" * 80)

    for (t1, t2) in test_intervals:
        # Compute definite integral of velocity
        disp_integral = sp.integrate(v, (t, t1, t2))

        # Compute displacement difference directly
        s_t1 = s.subs(t, t1)
        s_t2 = s.subs(t, t2)
        disp_diff = s_t2 - s_t1

        matched = "VERIFIED (OK)" if disp_integral == disp_diff else "MISMATCH"
        print(f"[{t1}, {t2}] seconds       | Delta_s = {str(disp_integral):<20} m | {s_t2} - {s_t1} = {str(disp_diff):<6} m | {matched}")

    print("\nDetailed Working for Specified Interval [t_start = {0}, t_end = {1}]:".format(t_start, t_end))
    indefinite_integral = sp.integrate(v, t)
    print(f"  Indefinite Integral Integral(v(t) dt) = {indefinite_integral} + C")
    val_upper = indefinite_integral.subs(t, t_end)
    val_lower = indefinite_integral.subs(t, t_start)
    final_disp = val_upper - val_lower
    print(f"  Upper Limit Evaluation at t = {t_end} : {val_upper}")
    print(f"  Lower Limit Evaluation at t = {t_start} : {val_lower}")
    print(f"  Net Displacement Delta_s                : {val_upper} - {val_lower} = {final_disp} meters")

    print("=" * 80)
    print("             VEHICLE MOTION ANALYSIS COMPLETED                ")
    print("=" * 80)


if __name__ == "__main__":
    t_a = float(sys.argv[1]) if len(sys.argv) > 1 and sys.argv[1].replace('.', '', 1).isdigit() else 0.0
    t_b = float(sys.argv[2]) if len(sys.argv) > 2 and sys.argv[2].replace('.', '', 1).isdigit() else 4.0
    vehicle_motion_analysis(t_start=t_a, t_end=t_b)
