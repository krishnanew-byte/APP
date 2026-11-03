"""
Question 1: TCP Client - Student Information System
Connects to the college central examination server via TCP socket,
sends a student's register number, receives and displays the examination result.
"""

import socket
import sys


def request_student_result(reg_no: str, host: str = "127.0.0.1", port: int = 65432) -> str:
    """
    Connects to the TCP server, sends the student register number,
    and returns the server's response.
    """
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        client_socket.connect((host, port))
        client_socket.sendall(reg_no.strip().encode("utf-8"))
        response = client_socket.recv(4096).decode("utf-8")
        return response
    except ConnectionRefusedError:
        return f"[CLIENT ERROR] Could not connect to server at {host}:{port}. Is Question1_TCPServer running?"
    except Exception as e:
        return f"[CLIENT ERROR] An error occurred: {e}"
    finally:
        client_socket.close()


def run_client_interactive(host: str = "127.0.0.1", port: int = 65432):
    """Interactive console prompt for students to check results."""
    print("=" * 60)
    print("      STUDENT RESULT PORTAL - TCP CLIENT APPLICATION       ")
    print("=" * 60)
    print(f"Connecting to Central Server at {host}:{port}")
    print("Type 'exit' to quit.\n")

    while True:
        try:
            reg_no = input("Enter Student Register Number (e.g., REG1001, REG1004): ").strip()
            if not reg_no:
                continue
            if reg_no.lower() in ["exit", "quit"]:
                print("Exiting Student Result Portal. Goodbye!")
                break

            print(f"\n[CLIENT] Sending Register Number: '{reg_no}' to server...")
            result = request_student_result(reg_no, host=host, port=port)
            print("\n[CLIENT] Received Response from Server:\n")
            print(result)
            print("\n" + "-" * 60 + "\n")
        except (KeyboardInterrupt, EOFError):
            print("\nExiting Student Result Portal. Goodbye!")
            break


def run_demo(host: str = "127.0.0.1", port: int = 65432):
    """Demonstrates client requests with multiple sample register numbers."""
    test_registers = ["REG1001", "REG1004", "REG1005", "REG9999"]
    print("=" * 60)
    print("          AUTOMATED DEMONSTRATION OF TCP CLIENT             ")
    print("=" * 60)
    for reg_no in test_registers:
        print(f"\n>>> Querying Examination Result for: '{reg_no}'")
        res = request_student_result(reg_no, host=host, port=port)
        print(res)
        print("-" * 60)


if __name__ == "__main__":
    host_target = "127.0.0.1"
    port_target = 65432

    if len(sys.argv) > 1:
        arg = sys.argv[1].strip()
        if arg == "--demo":
            run_demo(host=host_target, port=port_target)
        else:
            # Single register number argument provided
            print(f"Requesting result for: {arg}")
            print(request_student_result(arg, host=host_target, port=port_target))
    else:
        run_client_interactive(host=host_target, port=port_target)
