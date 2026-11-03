"""
Question 1: TCP Server - Student Information System
A college central examination results server using Python TCP sockets.
Listens for client connections, receives student register numbers,
retrieves examination records, and sends formatted results back to clients.
"""

import socket
import threading
import json
import sys

# Central database of student examination results
STUDENT_DATABASE = {
    "REG1001": {
        "register_no": "REG1001",
        "name": "Aarav Sharma",
        "department": "Computer Science & Engineering",
        "semester": "VI",
        "subjects": {
            "Computer Networks": {"marks": 92, "grade": "O"},
            "Theory of Computation": {"marks": 88, "grade": "A+"},
            "Python Programming": {"marks": 95, "grade": "O"},
            "Database Systems": {"marks": 85, "grade": "A+"}
        },
        "gpa": 9.4,
        "status": "PASS"
    },
    "REG1002": {
        "register_no": "REG1002",
        "name": "Diya Patel",
        "department": "Information Technology",
        "semester": "VI",
        "subjects": {
            "Computer Networks": {"marks": 78, "grade": "A"},
            "Theory of Computation": {"marks": 74, "grade": "B+"},
            "Python Programming": {"marks": 82, "grade": "A"},
            "Database Systems": {"marks": 80, "grade": "A"}
        },
        "gpa": 8.1,
        "status": "PASS"
    },
    "REG1003": {
        "register_no": "REG1003",
        "name": "Rohan Verma",
        "department": "Electronics & Communication",
        "semester": "VI",
        "subjects": {
            "Computer Networks": {"marks": 65, "grade": "B"},
            "Theory of Computation": {"marks": 58, "grade": "C"},
            "Python Programming": {"marks": 70, "grade": "B+"},
            "Database Systems": {"marks": 62, "grade": "B"}
        },
        "gpa": 6.8,
        "status": "PASS"
    },
    "REG1004": {
        "register_no": "REG1004",
        "name": "Ananya Reddy",
        "department": "Computer Science & Engineering",
        "semester": "VI",
        "subjects": {
            "Computer Networks": {"marks": 98, "grade": "O"},
            "Theory of Computation": {"marks": 96, "grade": "O"},
            "Python Programming": {"marks": 99, "grade": "O"},
            "Database Systems": {"marks": 94, "grade": "O"}
        },
        "gpa": 9.9,
        "status": "PASS"
    },
    "REG1005": {
        "register_no": "REG1005",
        "name": "Vikram Singh",
        "department": "Mechanical Engineering",
        "semester": "VI",
        "subjects": {
            "Computer Networks": {"marks": 42, "grade": "U"},
            "Theory of Computation": {"marks": 38, "grade": "U"},
            "Python Programming": {"marks": 55, "grade": "C"},
            "Database Systems": {"marks": 49, "grade": "U"}
        },
        "gpa": 4.5,
        "status": "FAIL (Arrears in 3 subjects)"
    }
}


def format_student_result(record: dict) -> str:
    """Formats student record into a clean, human-readable report card."""
    lines = [
        "=" * 60,
        "        CENTRAL UNIVERSITY EXAMINATION RESULT PORTAL        ",
        "=" * 60,
        f" Register Number : {record['register_no']}",
        f" Student Name    : {record['name']}",
        f" Department      : {record['department']}",
        f" Semester        : {record['semester']}",
        "-" * 60,
        f"{'Subject':<30} {'Marks':<10} {'Grade':<10}",
        "-" * 60
    ]
    for subject, details in record["subjects"].items():
        lines.append(f"{subject:<30} {details['marks']:<10} {details['grade']:<10}")
    lines.append("-" * 60)
    lines.append(f" Semester GPA    : {record['gpa']}")
    lines.append(f" Final Status    : {record['status']}")
    lines.append("=" * 60)
    return "\n".join(lines)


def handle_client(client_socket: socket.socket, client_address: tuple):
    """Handles an individual client TCP connection."""
    print(f"[SERVER] Connected by client at {client_address}")
    try:
        while True:
            data = client_socket.recv(1024)
            if not data:
                break

            reg_no = data.decode("utf-8").strip().upper()
            print(f"[SERVER] Received request for Register Number: '{reg_no}' from {client_address}")

            if reg_no in ["EXIT", "QUIT"]:
                response = "Connection terminated by client request."
                client_socket.sendall(response.encode("utf-8"))
                break

            if reg_no in STUDENT_DATABASE:
                result_text = format_student_result(STUDENT_DATABASE[reg_no])
            else:
                available = ", ".join(STUDENT_DATABASE.keys())
                result_text = (
                    f"ERROR: Register Number '{reg_no}' NOT FOUND in the examination database.\n"
                    f"Available register numbers for testing: {available}"
                )

            client_socket.sendall(result_text.encode("utf-8"))
    except ConnectionResetError:
        print(f"[SERVER] Client {client_address} disconnected abruptly.")
    finally:
        client_socket.close()
        print(f"[SERVER] Connection closed for {client_address}")


def start_tcp_server(host: str = "127.0.0.1", port: int = 65432, max_requests: int = None):
    """
    Starts the TCP server on the given host and port.
    If max_requests is provided, stops after servicing that many client connections.
    """
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    try:
        server_socket.bind((host, port))
        server_socket.listen(5)
        print("=" * 60)
        print(f"[SERVER] TCP Student Information Server running on {host}:{port}")
        print(f"[SERVER] Loaded records for {len(STUDENT_DATABASE)} students.")
        print("[SERVER] Waiting for student result requests... (Press Ctrl+C to stop)")
        print("=" * 60)

        connections_served = 0
        while True:
            client_socket, client_address = server_socket.accept()
            # Handle client in a separate thread so multiple students can query concurrently
            client_thread = threading.Thread(
                target=handle_client,
                args=(client_socket, client_address),
                daemon=True
            )
            client_thread.start()
            connections_served += 1
            if max_requests and connections_served >= max_requests:
                client_thread.join(timeout=3)
                break
    except KeyboardInterrupt:
        print("\n[SERVER] Shutting down TCP Server gracefully.")
    finally:
        server_socket.close()
        print("[SERVER] Socket closed.")


if __name__ == "__main__":
    port_arg = int(sys.argv[1]) if len(sys.argv) > 1 else 65432
    start_tcp_server(port=port_arg)
