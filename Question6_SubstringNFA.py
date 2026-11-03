"""
Question 6: Text Processing System - NFA for Substring "ab"
Recognizes strings over the alphabet {a, b} that contain the substring "ab".

This program constructs the Non-Deterministic Finite Automaton (NFA) using
the Automata library (automata.fa.nfa.NFA), prints its formal 5-tuple specification
and transition table, traces active state subsets step-by-step, and tests multiple input strings.
"""

from automata.fa.nfa import NFA
from automata.base.exceptions import RejectionException
import sys


def construct_substring_ab_nfa() -> NFA:
    """
    Constructs and returns the NFA recognizing language L = { w in {a, b}* | w contains substring 'ab' }.

    Formal 5-Tuple Definition:
      M = (Q, Sigma, delta, q0, F)
      - Q = {'q0', 'q1', 'q2'}
      - Sigma = {'a', 'b'}
      - q0: Initial state (loops on 'a', 'b' and nondeterministically branches to 'q1' on 'a')
      - q1: State reached when an 'a' is seen that could begin the target substring 'ab'
      - q2: Final / Accepting state reached when 'b' is seen following 'a' in 'q1'
      - F = {'q2'}
    """
    nfa = NFA(
        states={'q0', 'q1', 'q2'},
        input_symbols={'a', 'b'},
        transitions={
            'q0': {'a': {'q0', 'q1'}, 'b': {'q0'}},
            'q1': {'b': {'q2'}},
            'q2': {'a': {'q2'}, 'b': {'q2'}}
        },
        initial_state='q0',
        final_states={'q2'}
    )
    return nfa


def display_nfa_specification():
    """Prints the mathematical 5-tuple and state transition table of the NFA."""
    print("=" * 80)
    print("      TEXT PROCESSING SYSTEM: SUBSTRING 'ab' DETECTOR (NFA)           ")
    print("=" * 80)
    print("Language Specification:")
    print("  L = { w in {a, b}* | string w contains the substring 'ab' }")
    print("\nFormal 5-Tuple Definition M = (Q, Sigma, delta, q0, F):")
    print("  * Set of States (Q)         : {q0, q1, q2}")
    print("  * Alphabet (Sigma)          : {a, b}")
    print("  * Initial Start State (q0)  : q0")
    print("  * Accepting State Set (F)   : {q2} (Target substring 'ab' has occurred)")
    print("\nState Semantics:")
    print("  - q0 : Searching for substring 'ab'. Loops on 'a' and 'b'.")
    print("         Non-deterministically forks to q1 on 'a' (guessing that 'a' starts 'ab').")
    print("  - q1 : Candidate 'a' detected. Awaiting symbol 'b' to complete 'ab'.")
    print("  - q2 : Substring 'ab' confirmed! Absorbing state (loops on 'a' and 'b' forever).")
    print("\nNFA State Transition Table (delta: Q x Sigma -> P(Q)):")
    print("-" * 55)
    print(f"| {'Current State':<18} | {'Input a':<14} | {'Input b':<14} |")
    print("-" * 55)
    print(f"| {'-> q0 (Start)':<18} | {'{q0, q1}':<14} | {'{q0}':<14} |")
    print(f"| {'   q1':<18} | {'Empty / None':<14} | {'{*q2}':<14} |")
    print(f"| {'*  q2 (Accept)':<18} | {'{*q2}':<14} | {'{*q2}':<14} |")
    print("-" * 55)
    print("Legend: '->' indicates Initial State, '*' indicates Final/Accept State")
    print("Note: On input 'a' in state q0, the NFA branches into both q0 and q1 simultaneously.\n")


def test_nfa_trace(nfa: NFA, test_strings: list):
    """Evaluates test strings and displays active state subsets at each step."""
    print("=" * 80)
    print("                    AUTOMATED TEST SUITE EXECUTION                    ")
    print("=" * 80)
    print(f"{'Input String':<15} | {'Active State Sets Over Time':<42} | {'Result':<10} | {'Status'}")
    print("-" * 80)

    for s in test_strings:
        active_sets = []
        is_accepted = False
        try:
            for state_subset in nfa.read_input_stepwise(s):
                sorted_subset = "{" + ",".join(sorted(list(state_subset))) + "}"
                active_sets.append(sorted_subset)
            is_accepted = nfa.accepts_input(s)
        except RejectionException:
            is_accepted = False
        except Exception as e:
            active_sets = [f"Error: {e}"]
            is_accepted = False

        trace_str = " -> ".join(active_sets) if active_sets else "N/A"
        result_str = "ACCEPTED" if is_accepted else "REJECTED"
        contains_str = "Contains 'ab'" if is_accepted else "Does NOT contain 'ab'"

        display_s = f"\"{s}\"" if s != "" else "\"\" (empty)"
        print(f"{display_s:<15} | {trace_str:<42} | {result_str:<10} | {contains_str}")

    print("-" * 80)


def interactive_tester(nfa: NFA):
    """Allows user to enter arbitrary strings over {a, b} to test the NFA."""
    print("\n--- Interactive Substring Tester Mode ---")
    print("Enter strings over alphabet {a, b} (type 'exit' to quit):")
    while True:
        try:
            user_str = input("\nEnter String (over {a, b}): ").strip()
            if user_str.lower() in ["exit", "quit"]:
                print("Exiting tester.")
                break

            # Validate alphabet
            if not all(ch in "ab" for ch in user_str):
                print("[ERROR] Invalid character! Alphabet is strictly Sigma = {a, b}.")
                continue

            active_sets = []
            is_accepted = False
            try:
                for st in nfa.read_input_stepwise(user_str):
                    active_sets.append("{" + ",".join(sorted(list(st))) + "}")
                is_accepted = nfa.accepts_input(user_str)
            except RejectionException:
                is_accepted = False

            print(f"  Stepwise Subsets : {' -> '.join(active_sets)}")
            print(f"  Final Active Set : {active_sets[-1] if active_sets else 'N/A'}")
            if is_accepted:
                print(f"  Status           : [ACCEPTED] - String contains substring 'ab'!")
            else:
                print(f"  Status           : [REJECTED] - String does NOT contain substring 'ab'.")
        except (KeyboardInterrupt, EOFError):
            print("\nExiting tester.")
            break


if __name__ == "__main__":
    substring_nfa = construct_substring_ab_nfa()
    display_nfa_specification()

    # Comprehensive test suite of strings over alphabet {a, b}
    test_cases = [
        # Expected ACCEPTED (contains "ab")
        "ab",
        "aab",
        "bab",
        "aba",
        "bbab",
        "aabb",
        "bbaab",
        "ababa",
        "baab",
        "bbababb",
        # Expected REJECTED (does NOT contain "ab")
        "",
        "a",
        "b",
        "aa",
        "bb",
        "ba",
        "bba",
        "bbbaa",
        "baaaa"
    ]

    test_nfa_trace(substring_nfa, test_cases)

    if "--interactive" in sys.argv:
        interactive_tester(substring_nfa)
