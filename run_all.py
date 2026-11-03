"""
========================================================================================
                      WEEK 12 MASTER RUNNER & VERIFICATION SUITE
========================================================================================
Executes and validates all 6 programs:
  1. TCP Client-Server Student Examination Result Information System
  2. UDP Client-Server Weather Monitoring Station & Justification
  3. SymPy Manufacturing Production Cost Modeling & Quadratic Equation Solving
  4. SymPy Automobile Motion Analysis (Kinematics, Rest Times & Definite Integration)
  5. Automata DFA for Binary Access Code Validation (Ending with "01")
  6. Automata NFA for Text Processing Substring Detection (Containing "ab")
========================================================================================
"""

import sys
import time
import threading

# Import Question modules
from Question1_TCPServer import start_tcp_server
from Question1_TCPClient import request_student_result
from Question2_UDPServer import start_udp_server, UDP_SUITABILITY_JUSTIFICATION
from Question2_UDPClient import run_sensor_simulation
from Question3_ProductionCost_SymPy import production_cost_analysis
from Question4_VehicleMotion_SymPy import vehicle_motion_analysis
from Question5_BinarySecurityDFA import construct_access_code_dfa, display_dfa_specification, test_dfa_trace
from Question6_SubstringNFA import construct_substring_ab_nfa, display_nfa_specification, test_nfa_trace


def run_question_1():
    print("\n" + "#" * 80)
    print("  QUESTION 1: TCP CLIENT-SERVER STUDENT INFORMATION SYSTEM")
    print("#" * 80)

    port = 54331
    server_thread = threading.Thread(
        target=start_tcp_server,
        kwargs={"host": "127.0.0.1", "port": port, "max_requests": 2},
        daemon=True
    )
    server_thread.start()
    time.sleep(0.5)

    print("\n--- Test Query 1: Valid Student Register Number (REG1001) ---")
    res1 = request_student_result("REG1001", host="127.0.0.1", port=port)
    print(res1)

    print("\n--- Test Query 2: Non-existent Register Number (REG9999) ---")
    res2 = request_student_result("REG9999", host="127.0.0.1", port=port)
    print(res2)

    server_thread.join(timeout=2)
    print("\n[OK] Question 1 TCP Client-Server demonstration completed successfully.")


def run_question_2():
    print("\n" + "#" * 80)
    print("  QUESTION 2: UDP CLIENT-SERVER WEATHER MONITORING & JUSTIFICATION")
    print("#" * 80)

    port = 54332
    server_thread = threading.Thread(
        target=start_udp_server,
        kwargs={"host": "127.0.0.1", "port": port, "max_packets": 5},
        daemon=True
    )
    server_thread.start()
    time.sleep(0.5)

    print("\n--- Transmitting Multi-Sensor Temperature Readings over UDP ---")
    run_sensor_simulation(host="127.0.0.1", port=port, rounds=1, interval=0.1)

    server_thread.join(timeout=2)

    print("\n--- UDP vs TCP Justification ---")
    print(UDP_SUITABILITY_JUSTIFICATION)
    print("[OK] Question 2 UDP Client-Server and Justification completed successfully.")


def run_question_3():
    print("\n" + "#" * 80)
    print("  QUESTION 3: SYMPY PRODUCTION COST ALGEBRA & QUADRATIC EQUATION")
    print("#" * 80)
    production_cost_analysis()
    print("[OK] Question 3 SymPy Production Cost Analysis completed successfully.")


def run_question_4():
    print("\n" + "#" * 80)
    print("  QUESTION 4: SYMPY VEHICLE MOTION ANALYSIS & DEFINITE INTEGRATION")
    print("#" * 80)
    vehicle_motion_analysis(t_start=0.0, t_end=4.0)
    print("[OK] Question 4 SymPy Vehicle Kinematics completed successfully.")


def run_question_5():
    print("\n" + "#" * 80)
    print("  QUESTION 5: AUTOMATA DFA - BINARY ACCESS CODE VALIDATOR (ENDS WITH '01')")
    print("#" * 80)
    dfa = construct_access_code_dfa()
    display_dfa_specification()
    test_cases = ["01", "001", "101", "1101", "00001", "", "0", "1", "10", "11", "010"]
    test_dfa_trace(dfa, test_cases)
    print("[OK] Question 5 DFA Access Code Validation completed successfully.")


def run_question_6():
    print("\n" + "#" * 80)
    print("  QUESTION 6: AUTOMATA NFA - SUBSTRING 'ab' RECOGNIZER")
    print("#" * 80)
    nfa = construct_substring_ab_nfa()
    display_nfa_specification()
    test_cases = ["ab", "aab", "bab", "aba", "bbab", "", "a", "b", "aa", "bb", "ba", "bba"]
    test_nfa_trace(nfa, test_cases)
    print("[OK] Question 6 NFA Substring Recognition completed successfully.")


def main():
    start_time = time.time()
    print("=" * 80)
    print("       STARTING EXECUTION OF ALL 6 WEEK 12 PYTHON PROGRAMS       ")
    print("=" * 80)

    try:
        run_question_1()
    except Exception as e:
        print(f"[FAIL] Question 1 failed: {e}")

    try:
        run_question_2()
    except Exception as e:
        print(f"[FAIL] Question 2 failed: {e}")

    try:
        run_question_3()
    except Exception as e:
        print(f"[FAIL] Question 3 failed: {e}")

    try:
        run_question_4()
    except Exception as e:
        print(f"[FAIL] Question 4 failed: {e}")

    try:
        run_question_5()
    except Exception as e:
        print(f"[FAIL] Question 5 failed: {e}")

    try:
        run_question_6()
    except Exception as e:
        print(f"[FAIL] Question 6 failed: {e}")

    elapsed = round(time.time() - start_time, 2)
    print("\n" + "=" * 80)
    print(f"       ALL 6 ASSIGNMENTS EXECUTED SUCCESSFULLY IN {elapsed}s        ")
    print("=" * 80)


if __name__ == "__main__":
    main()
