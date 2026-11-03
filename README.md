# Advanced Python Programming (APP) - Week 12 Laboratory Solutions

This repository contains Python implementations for the **Week 12 Laboratory Exercises**, covering:
1. **Network Socket Programming**: TCP Client-Server and UDP Client-Server architectures.
2. **Symbolic Mathematics with SymPy**: Polynomial operations, calculus, kinematics, and quadratic equation solving.
3. **Formal Language & Automata Theory with Automata-lib**: Deterministic Finite Automata (DFA) and Non-Deterministic Finite Automata (NFA).

---

## Repository Contents

| File | Question / Topic | Description |
| :--- | :--- | :--- |
| [`Question1_TCPServer.py`](file:///Question1_TCPServer.py) | **Q1: TCP Server** | Multithreaded TCP server serving student examination results based on register number. |
| [`Question1_TCPClient.py`](file:///Question1_TCPClient.py) | **Q1: TCP Client** | TCP client requesting examination results and displaying formatted report cards. |
| [`Question2_UDPServer.py`](file:///Question2_UDPServer.py) | **Q2: UDP Server** | Central weather monitoring station collecting real-time temperature telemetry. |
| [`Question2_UDPClient.py`](file:///Question2_UDPClient.py) | **Q2: UDP Client** | Weather sensor simulator broadcasting sensor readings via UDP datagrams. |
| [`Question3_ProductionCost_SymPy.py`](file:///Question3_ProductionCost_SymPy.py) | **Q3: SymPy Algebra** | Production cost modeling: symbol definition, expansion, factorization, simplification, and quadratic equation solution. |
| [`Question4_VehicleMotion_SymPy.py`](file:///Question4_VehicleMotion_SymPy.py) | **Q4: SymPy Kinematics** | Vehicle motion analysis: differentiation for velocity and acceleration, rest times, and definite integration for displacement. |
| [`Question5_BinarySecurityDFA.py`](file:///Question5_BinarySecurityDFA.py) | **Q5: Automata DFA** | DFA validating binary access codes ending with suffix `"01"`. |
| [`Question6_SubstringNFA.py`](file:///Question6_SubstringNFA.py) | **Q6: Automata NFA** | NFA recognizing strings over alphabet `$\Sigma = \{a, b\}$` containing substring `"ab"`. |
| [`run_all.py`](file:///run_all.py) | **Master Verification Runner** | Executes and validates all 6 programs in an automated test suite. |

---

## Detailed Problem Solutions

### Question 1: TCP Client–Server Student Information System

#### Problem Description
A college wants to develop a student information system where students can request their examination results from a central server. Develop a Python-based TCP client–server application in which the client sends a student's register number to the server, and the server retrieves and sends the corresponding result to the client.

#### Implementation Highlights
- **Transport Protocol**: Transmission Control Protocol (TCP) via `socket.SOCK_STREAM`.
- **Server Features**:
  - Sets `SO_REUSEADDR` to prevent socket binding conflicts.
  - Multi-threaded connection handling allows multiple students to query simultaneously.
  - Formats tabular student transcripts containing student name, department, semester, subject grades, GPA, and final pass/fail status.
  - Handles non-existent register numbers gracefully with descriptive error feedback.
- **Client Features**:
  - Interactive mode for manual queries.
  - Automated demo mode via `--demo` flag.

```
[Client] --- (TCP Connect: Handshake) ---> [Server]
[Client] --- Register No: "REG1001" -----> [Server: Database Lookup]
[Client] <-- Formatted Examination Card -- [Server]
[Client] --- (Connection Close) ---------> [Server]
```

---

### Question 2: UDP Client–Server Weather Monitoring Station & Justification

#### Problem Description
A weather monitoring station collects temperature readings from multiple sensors and transmits them to a central monitoring server. Develop a Python-based UDP client–server application in which the client sends temperature readings to the server, and the server receives and displays the readings. Justify the suitability of UDP for this application compared with TCP.

#### Justification: Suitability of UDP Compared with TCP

| Criterion | User Datagram Protocol (UDP) | Transmission Control Protocol (TCP) | Advantage for Weather Monitoring |
| :--- | :--- | :--- | :--- |
| **Connection Overhead** | **Connectionless**: Zero handshake overhead. | **Connection-oriented**: Requires a 3-way handshake (`SYN` $\to$ `SYN-ACK` $\to$ `ACK`) and 4-way teardown (`FIN` $\to$ `ACK`). | **UDP Wins**: Battery-powered IoT sensors can wake from deep sleep, send an immediate datagram, and return to sleep instantly. |
| **Data Freshness & Retransmission** | **No Retransmissions**: Lost datagrams are simply discarded. | **Guaranteed Delivery**: Retransmits unacknowledged packets. | **UDP Wins**: Weather data is continuous real-time telemetry. If a packet is lost, retransmitting stale data is useless because a fresher reading arrives shortly after. |
| **Head-of-Line Blocking** | **None**: Packets are delivered immediately upon receipt. | **Strict Ordering**: If packet $N$ is dropped, packets $N+1, N+2$ are held in buffers until $N$ is retransmitted. | **UDP Wins**: Eliminates artificial latency; fresh readings are never delayed behind missing historical packets. |
| **Header Overhead** | **8 Bytes** fixed header. | **20 to 60 Bytes** variable header. | **UDP Wins**: Reduces wireless bandwidth and battery consumption on constrained links (LoRaWAN, Zigbee, NB-IoT). |
| **Broadcast / Multicast** | **Natively supported**. | **Unicast only** (point-to-point). | **UDP Wins**: Allows sensors or repeaters to broadcast telemetry to multiple monitoring consoles simultaneously. |

---

### Question 3: Production Cost Analysis using SymPy

#### Problem Description
A manufacturing company needs to simplify mathematical expressions used in calculating production costs. Develop a Python program using the SymPy library to define symbolic variables, expand and factorize algebraic expressions, simplify the resulting expressions, and solve a quadratic equation to determine the unknown production quantity.

#### Mathematical Formulation & Steps
1. **Symbolic Variables**:
   $$\text{Production quantity: } q \ge 0, \quad \text{Cost parameters: } c, F > 0$$
2. **Expansion (`sp.expand`)**:
   $$C_{\text{raw}}(q) = (2q + 15)(3q + 10) + 50 \implies C_{\text{expanded}}(q) = 6q^2 + 65q + 200$$
3. **Factorization (`sp.factor`)**:
   $$2q^2 + 11q + 12 \implies (q + 4)(2q + 3)$$
   $$6q^2 + 15q - 300 \implies 3(2q^2 + 5q - 100)$$
4. **Simplification (`sp.cancel` / `sp.simplify`)**:
   $$\frac{q^3 - 8}{q - 2} \implies q^2 + 2q + 4$$
   $$\frac{2q^2 + 8q}{2q} \implies q + 4$$
   $$\frac{(q + 10)^2 - (q - 10)^2}{4q} \implies 10$$
5. **Quadratic Equation Solving**:
   - Total Cost: $C(q) = 2q^2 + 20q + 100$
   - Budget: $B = 1300$
   - Equation:
     $$2q^2 + 20q + 100 = 1300 \iff 2q^2 + 20q - 1200 = 0 \iff q^2 + 10q - 600 = 0$$
   - Factored: $(q + 30)(q - 20) = 0$
   - Roots: $q = -30$ and $q = 20$.
   - **Physical Constraint**: Production quantity must be non-negative ($q \ge 0$).
   - **Final Solution**: **$q = 20\text{ units}$**.

---

### Question 4: Automobile Kinematics using SymPy

#### Problem Description
An automobile company wants to analyze the motion of a vehicle using its displacement function $s(t) = t^3 - 6t^2 + 9t + 5$, where $t$ represents time. Develop a Python program using the SymPy library to determine the velocity and acceleration through symbolic differentiation, identify the time at which the vehicle is momentarily at rest, and calculate the displacement over a specified time interval using definite integration.

#### Mathematical Derivations
1. **Displacement Function**:
   $$s(t) = t^3 - 6t^2 + 9t + 5$$
2. **Velocity Function (First Derivative)**:
   $$v(t) = \frac{ds(t)}{dt} = 3t^2 - 12t + 9 = 3(t - 1)(t - 3)$$
3. **Acceleration Function (Second Derivative)**:
   $$a(t) = \frac{dv(t)}{dt} = \frac{d^2s(t)}{dt^2} = 6t - 12$$
4. **Times Momentarily at Rest ($v(t) = 0$)**:
   $$3(t - 1)(t - 3) = 0 \implies t = 1\text{ s} \quad \text{and} \quad t = 3\text{ s}$$
   - **At $t = 1\text{ s}$**:
     $$s(1) = 9\text{ m}, \quad a(1) = 6(1) - 12 = -6\text{ m/s}^2 \quad (\text{Decelerating / Local maximum position})$$
   - **At $t = 3\text{ s}$**:
     $$s(3) = 5\text{ m}, \quad a(3) = 6(3) - 12 = +6\text{ m/s}^2 \quad (\text{Accelerating / Local minimum position})$$
5. **Displacement via Definite Integration**:
   $$\Delta s = \int_{t_1}^{t_2} v(t) \, dt = [t^3 - 6t^2 + 9t]_{t_1}^{t_2} = s(t_2) - s(t_1)$$
   - For $[0, 4]\text{ s}$: $\int_0^4 (3t^2 - 12t + 9)\,dt = 4\text{ m}$ (Verified: $s(4) - s(0) = 9 - 5 = 4\text{ m}$)
   - For $[0, 2]\text{ s}$: $\int_0^2 (3t^2 - 12t + 9)\,dt = 2\text{ m}$ (Verified: $s(2) - s(0) = 7 - 5 = 2\text{ m}$)
   - For $[1, 3]\text{ s}$: $\int_1^3 (3t^2 - 12t + 9)\,dt = -4\text{ m}$ (Verified: $s(3) - s(1) = 5 - 9 = -4\text{ m}$)

---

### Question 5: Digital Security System - DFA for Binary Access Codes Ending with "01"

#### Problem Description
A digital security system validates binary access codes before granting access to users. The system accepts only binary strings that end with `"01"`. Construct a DFA to recognize valid access codes and develop a Python program using the Automata library to implement the DFA. Test the automaton with different binary strings and display whether each string is accepted or rejected.

#### Formal 5-Tuple Definition
$$M = (Q, \Sigma, \delta, q_0, F)$$
- $Q = \{q_0, q_1, q_2\}$
- $\Sigma = \{0, 1\}$
- Initial State: $q_0$
- Final / Accept States: $F = \{q_2\}$

#### State Transition Table ($\delta$)
| Current State | Input `0` | Input `1` | Semantic Meaning |
| :---: | :---: | :---: | :--- |
| $\to q_0$ | $q_1$ | $q_0$ | Start state / Suffix not ending in `0` or `01` |
| $q_1$ | $q_1$ | $*q_2$ | Last symbol read was `0` (prefix of target `01`) |
| $*q_2$ | $q_1$ | $q_0$ | Last two symbols read were `01` **(ACCEPT STATE)** |

#### Test Results Summary
- **Accepted**: `"01"`, `"001"`, `"101"`, `"1101"`, `"00001"`, `"10101"`, `"0101"`, `"111001"`
- **Rejected**: `""` (empty), `"0"`, `"1"`, `"00"`, `"10"`, `"11"`, `"100"`, `"010"`, `"011"`, `"11010"`

---

### Question 6: Text Processing System - NFA for Substrings Containing "ab"

#### Problem Description
A text processing system is designed to identify strings over the alphabet that contain the substring `"ab"`. Construct an NFA to recognize such strings and develop a Python program using the Automata library to implement the NFA. Test the automaton with different input strings and display whether each string is accepted or rejected.

#### Formal 5-Tuple Definition
$$M = (Q, \Sigma, \delta, q_0, F)$$
- $Q = \{q_0, q_1, q_2\}$
- $\Sigma = \{a, b\}$
- Initial State: $q_0$
- Final / Accept States: $F = \{q_2\}$

#### State Transition Table ($\delta: Q \times \Sigma \to \mathcal{P}(Q)$)
| Current State | Input `a` | Input `b` | Non-deterministic Action |
| :---: | :---: | :---: | :--- |
| $\to q_0$ | $\{q_0, q_1\}$ | $\{q_0\}$ | On `'a'`, nondeterministically branches to $q_1$ while staying in $q_0$ |
| $q_1$ | $\emptyset$ | $\{*q_2\}$ | On `'b'`, successfully transitions to accept state $q_2$ |
| $*q_2$ | $\{*q_2\}$ | $\{*q_2\}$ | Absorbing accept state: loops on both `'a'` and `'b'` |

#### Test Results Summary
- **Accepted (Contains `"ab"`)**: `"ab"`, `"aab"`, `"bab"`, `"aba"`, `"bbab"`, `"aabb"`, `"bbaab"`, `"ababa"`, `"baab"`, `"bbababb"`
- **Rejected (Does NOT Contain `"ab"`)**: `""` (empty), `"a"`, `"b"`, `"aa"`, `"bb"`, `"ba"`, `"bba"`, `"bbbaa"`, `"baaaa"`

---

## Installation & Running Instructions

### 1. Prerequisites
Ensure Python 3.8+ is installed. Install the required dependencies:
```bash
pip install sympy automata-lib
```

### 2. Run All Exercises Simultaneously
Execute the master test suite runner:
```bash
python run_all.py
```

### 3. Run Individual Programs

#### Question 1: TCP Student Information System
In terminal 1 (start server):
```bash
python Question1_TCPServer.py
```
In terminal 2 (run client):
```bash
python Question1_TCPClient.py
# Or run with automated demo:
python Question1_TCPClient.py --demo
```

#### Question 2: UDP Weather Monitoring Station
In terminal 1 (start server):
```bash
python Question2_UDPServer.py
```
In terminal 2 (transmit readings):
```bash
python Question2_UDPClient.py
# View justification:
python Question2_UDPServer.py --justification
```

#### Question 3: Production Cost Analysis (SymPy)
```bash
python Question3_ProductionCost_SymPy.py
```

#### Question 4: Automobile Motion Analysis (SymPy)
```bash
python Question4_VehicleMotion_SymPy.py
# Or specify custom time interval [t_start, t_end]:
python Question4_VehicleMotion_SymPy.py 0 5
```

#### Question 5: Binary Security Access Code DFA
```bash
python Question5_BinarySecurityDFA.py
# Or launch interactive mode:
python Question5_BinarySecurityDFA.py --interactive
```

#### Question 6: Substring "ab" NFA
```bash
python Question6_SubstringNFA.py
# Or launch interactive mode:
python Question6_SubstringNFA.py --interactive
```
