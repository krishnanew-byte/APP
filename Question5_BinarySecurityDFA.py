"""
Question 5: Digital Security System - DFA for Binary Access Codes
Validates binary access codes before granting access to users.
The system accepts ONLY binary strings over alphabet {0, 1} that end with "01".

This program constructs the Deterministic Finite Automaton (DFA) using the
Automata library (automata.fa.dfa.DFA), prints its formal specification and
transition table, traces execution step-by-step, and tests multiple access codes.
"""

from automata.fa.dfa import DFA
from automata.base.exceptions import RejectionException
import sys


def construct_access_code_dfa() -> DFA:
    """
    Constructs and returns the DFA recognizing language L = { w in {0, 1}* | w ends with '01' }.

    Formal 5-Tuple Definition:
      M = (Q, Sigma, delta, q0, F)
      - Q = {'q0', 'q1', 'q2'}
      - Sigma = {'0', '1'}
      - q0: Initial state (empty string, or last seen char was '1' not preceded by '0')
      - q1: State representing last seen character was '0'
      - q2: Final / Accepting state (last two characters seen were '01')
      - F = {'q2'}
    """
    dfa = DFA(
        states={'q0', 'q1', 'q2'},
        input_symbols={'0', '1'},
        transitions={
            'q0': {'0': 'q1', '1': 'q0'},
            'q1': {'0': 'q1', '1': 'q2'},
            'q2': {'0': 'q1', '1': 'q0'}
        },
        initial_state='q0',
        final_states={'q2'}
    )
    return dfa


def display_dfa_specification():
    """Prints the mathematical 5-tuple and state transition table of the DFA."""
    print("=" * 80)
    print("      DIGITAL SECURITY SYSTEM: BINARY ACCESS CODE VALIDATOR (DFA)     ")
    print("=" * 80)
    print("Language Specification:")
    print("  L = { w in {0, 1}* | string w ends with the suffix '01' }")
    print("\nFormal 5-Tuple Definition M = (Q, Sigma, delta, q0, F):")
    print("  * Set of States (Q)         : {q0, q1, q2}")
    print("  * Alphabet (Sigma)          : {0, 1}")
    print("  * Initial Start State (q0)  : q0 (No suffix matched / ends with '1')")
    print("  * Accepting State Set (F)   : {q2} (Suffix '01' successfully matched)")
    print("\nState Semantics:")
    print("  - q0 : Suffix does not match '0' or '01' (start state, or after '1' not following '0')")
    print("  - q1 : Last character seen is '0' (potential start of target suffix '01')")
    print("  - q2 : Last two characters seen are '01' [ACCEPT STATE - ACCESS GRANTED]")
    print("\nState Transition Table (delta):")
    print("-" * 55)
    print(f"| {'Current State':<18} | {'Input 0':<14} | {'Input 1':<14} |")
    print("-" * 55)
    print(f"| {'-> q0 (Start)':<18} | {'q1':<14} | {'q0':<14} |")
    print(f"| {'   q1':<18} | {'q1':<14} | {'*q2 (Accept)':<14} |")
    print(f"| {'*  q2 (Accept)':<18} | {'q1':<14} | {'q0':<14} |")
    print("-" * 55)
    print("Legend: '->' indicates Initial State, '*' indicates Final/Accept State\n")


def test_dfa_trace(dfa: DFA, test_strings: list):
    """Evaluates test strings and displays step-by-step state transitions."""
    print("=" * 80)
    print("                    AUTOMATED TEST SUITE EXECUTION                    ")
    print("=" * 80)
    print(f"{'Access Code':<15} | {'State Path Sequence':<40} | {'Result':<10} | {'Access Decision'}")
    print("-" * 80)

    for code in test_strings:
        path = []
        is_accepted = False
        try:
            for st in dfa.read_input_stepwise(code):
                path.append(st)
            is_accepted = dfa.accepts_input(code)
        except RejectionException:
            is_accepted = False
        except Exception as e:
            path = [f"Error: {e}"]
            is_accepted = False

        path_str = " -> ".join(path) if path else "N/A"
        result_str = "ACCEPTED" if is_accepted else "REJECTED"
        decision_str = "ACCESS GRANTED" if is_accepted else "ACCESS DENIED"

        display_code = f"\"{code}\"" if code != "" else "\"\" (empty)"
        print(f"{display_code:<15} | {path_str:<40} | {result_str:<10} | {decision_str}")

    print("-" * 80)


def interactive_validator(dfa: DFA):
    """Allows user to enter arbitrary binary strings to test DFA."""
    print("\n--- Interactive Security Validator Mode ---")
    print("Enter binary access codes to validate (type 'exit' to quit):")
    while True:
        try:
            user_code = input("\nEnter Binary Access Code: ").strip()
            if user_code.lower() in ["exit", "quit"]:
                print("Exiting validator.")
                break

            # Validate input characters
            if not all(ch in "01" for ch in user_code):
                print("[ERROR] Invalid input! Access codes must contain only binary digits (0 and 1).")
                continue

            path = []
            is_accepted = False
            try:
                for st in dfa.read_input_stepwise(user_code):
                    path.append(st)
                is_accepted = dfa.accepts_input(user_code)
            except RejectionException:
                is_accepted = False

            print(f"  Transition Path: {' -> '.join(path)}")
            print(f"  Final State    : {path[-1] if path else 'N/A'}")
            if is_accepted:
                print(f"  Status         : [ACCEPTED] - Ends with '01'. ACCESS GRANTED!")
            else:
                print(f"  Status         : [REJECTED] - Does not end with '01'. ACCESS DENIED!")
        except (KeyboardInterrupt, EOFError):
            print("\nExiting validator.")
            break


if __name__ == "__main__":
    access_dfa = construct_access_code_dfa()
    display_dfa_specification()

    # Comprehensive test suite of binary strings
    test_cases = [
        # Expected ACCEPTED (ends with "01")
        "01",
        "001",
        "101",
        "1101",
        "00001",
        "10101",
        "0101",
        "111001",
        # Expected REJECTED (does NOT end with "01")
        "",
        "0",
        "1",
        "00",
        "10",
        "11",
        "100",
        "010",
        "011",
        "11010"
    ]

    test_dfa_trace(access_dfa, test_cases)

    if "--interactive" in sys.argv:
        interactive_validator(access_dfa)
