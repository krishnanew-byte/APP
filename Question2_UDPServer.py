"""
Question 2: UDP Server - Weather Monitoring Station
A central monitoring station receiving temperature and atmospheric
readings from multiple distributed weather sensors via UDP sockets.

Includes justification of why UDP is ideally suited for real-time
weather sensor data collection compared to TCP.
"""

import socket
import json
import sys
from datetime import datetime

UDP_SUITABILITY_JUSTIFICATION = """
========================================================================================
           JUSTIFICATION: SUITABILITY OF UDP COMPARED WITH TCP FOR WEATHER MONITORING
========================================================================================
1. Connectionless & Zero Handshake Overhead:
   - TCP requires a 3-way handshake (SYN, SYN-ACK, ACK) and connection termination (FIN, ACK).
   - Weather sensors are often low-power embedded IoT devices (battery or solar powered).
   - With UDP, sensors wake up from deep sleep, instantly transmit their datagram, and return
     to low-power sleep without waiting for connection negotiation.

2. Real-Time Streaming & Temporal Relevance:
   - Sensor readings are continuous time-series data.
   - The *latest* temperature reading is always the most valuable.
   - If a temperature packet is lost, retransmitting it 500ms later is wasteful because
     a newer, fresher temperature reading is already being sampled and dispatched.

3. Absence of Head-of-Line Blocking:
   - TCP guarantees strict in-order delivery. If packet #10 is dropped, packets #11, #12,
     and #13 are buffered and blocked until #10 is retransmitted and acknowledged.
   - UDP delivers packets immediately without blocking, preventing artificial latency.

4. Minimal Protocol Header Overhead:
   - UDP has a fixed, compact 8-byte header compared to TCP's 20-60 byte header.
   - For tiny sensor payloads (e.g., 20–50 bytes of sensor metrics), TCP doubles the bandwidth
     and transmission energy consumption over constrained wireless links (LoRaWAN, Zigbee, NB-IoT).

5. Native Broadcast and Multicast Support:
   - UDP enables a weather sensor or repeater to broadcast readings to multiple monitoring
     dashboards or logging servers simultaneously without maintaining separate point-to-point connections.
========================================================================================
"""


def start_udp_server(host: str = "127.0.0.1", port: int = 54322, max_packets: int = None):
    """
    Starts the UDP weather monitoring central server.
    Listens for incoming datagrams from weather sensors.
    """
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        server_socket.bind((host, port))
        print("=" * 80)
        print(f"[UDP SERVER] Central Weather Monitoring Station running on {host}:{port}")
        print("[UDP SERVER] Listening for sensor datagrams... (Press Ctrl+C to stop)")
        print("=" * 80)
        print(f"{'Time':<10} | {'Sensor ID':<12} | {'Location':<22} | {'Temp (°C)':<10} | {'Humidity':<10} | {'Client Address'}")
        print("-" * 80)

        packets_received = 0
        while True:
            data, client_address = server_socket.recvfrom(2048)
            raw_message = data.decode("utf-8").strip()

            try:
                # Expecting JSON or structured sensor reading
                payload = json.loads(raw_message)
                timestamp = payload.get("timestamp", datetime.now().strftime("%H:%M:%S"))
                sensor_id = payload.get("sensor_id", "UNKNOWN")
                location = payload.get("location", "N/A")
                temperature = payload.get("temperature_c", 0.0)
                humidity = payload.get("humidity_percent", 0.0)

                print(f"{timestamp:<10} | {sensor_id:<12} | {location:<22} | {temperature:>8.2f}°C | {humidity:>8.1f}% | {client_address[0]}:{client_address[1]}")
            except json.JSONDecodeError:
                # Plain text fallback
                now_str = datetime.now().strftime("%H:%M:%S")
                print(f"{now_str:<10} | RAW DATA     | {raw_message:<22} | {'-':<10} | {'-':<10} | {client_address[0]}:{client_address[1]}")

            packets_received += 1
            if max_packets and packets_received >= max_packets:
                break

    except KeyboardInterrupt:
        print("\n[UDP SERVER] Monitoring station shutting down.")
    finally:
        server_socket.close()
        print("[UDP SERVER] Socket closed.")


if __name__ == "__main__":
    if "--justification" in sys.argv:
        print(UDP_SUITABILITY_JUSTIFICATION)
        sys.exit(0)

    port_arg = int(sys.argv[1]) if len(sys.argv) > 1 and sys.argv[1].isdigit() else 54322
    start_udp_server(port=port_arg)
