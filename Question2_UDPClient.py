"""
Question 2: UDP Client - Multi-Sensor Weather Transmitter
Simulates distributed weather sensors transmitting real-time temperature
readings to the central monitoring server via UDP datagrams.

Also displays the justification of why UDP is preferred over TCP for sensor networks.
"""

import socket
import json
import time
import random
from datetime import datetime
import sys

# Predefined weather monitoring sensors
SENSORS = [
    {"sensor_id": "WS-NORTH-01", "location": "North Wing Rooftop", "base_temp": 22.5, "base_hum": 55.0},
    {"sensor_id": "WS-GREEN-02", "location": "Greenhouse Alpha",    "base_temp": 28.2, "base_hum": 78.0},
    {"sensor_id": "WS-FIELD-03", "location": "Agricultural Field",  "base_temp": 31.0, "base_hum": 48.0},
    {"sensor_id": "WS-SVR-04",   "location": "Server Room Data Ctr","base_temp": 19.5, "base_hum": 42.0},
    {"sensor_id": "WS-EAST-05",  "location": "East Gate Weather Stn","base_temp": 24.8, "base_hum": 60.0}
]

UDP_JUSTIFICATION_SUMMARY = """
----------------------------------------------------------------------------------------
WHY UDP IS SUITED FOR WEATHER SENSOR MONITORING OVER TCP:
1. Low Overhead: Fixed 8-byte UDP header vs 20-60 byte TCP header saves bandwidth.
2. Connectionless: No 3-way handshake (SYN/ACK) needed, preserving battery on sleep-wake cycles.
3. Fresh Data Over Retransmission: Weather data is continuous; a delayed packet is obsolete.
4. No Head-of-Line Blocking: Dropped packets do not delay subsequent real-time readings.
5. Multicast Support: Enables broadcasting sensor feeds to multiple telemetry systems.
----------------------------------------------------------------------------------------
"""


def generate_sensor_reading(sensor_info: dict) -> dict:
    """Generates a realistic temperature reading with minor jitter."""
    temp_jitter = round(random.uniform(-0.8, 0.8), 2)
    hum_jitter = round(random.uniform(-1.5, 1.5), 1)

    return {
        "sensor_id": sensor_info["sensor_id"],
        "location": sensor_info["location"],
        "temperature_c": round(sensor_info["base_temp"] + temp_jitter, 2),
        "humidity_percent": round(sensor_info["base_hum"] + hum_jitter, 1),
        "timestamp": datetime.now().strftime("%H:%M:%S")
    }


def send_reading(reading: dict, host: str = "127.0.0.1", port: int = 54322) -> bool:
    """Sends a single reading over UDP socket."""
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        payload = json.dumps(reading).encode("utf-8")
        client_socket.sendto(payload, (host, port))
        return True
    except Exception as e:
        print(f"[CLIENT ERROR] Failed to send reading: {e}")
        return False
    finally:
        client_socket.close()


def run_sensor_simulation(host: str = "127.0.0.1", port: int = 54322, rounds: int = 3, interval: float = 0.5):
    """Simulates multiple sensor stations broadcasting temperature readings."""
    print("=" * 75)
    print("       WEATHER MONITORING SYSTEM - UDP SENSOR TRANSMITTER       ")
    print("=" * 75)
    print(f"Target Central Server: {host}:{port}")
    print(f"Active Sensor Count  : {len(SENSORS)}")
    print(f"Transmission Rounds  : {rounds}")
    print("=" * 75)
    print(UDP_JUSTIFICATION_SUMMARY)

    for r in range(1, rounds + 1):
        print(f"\n--- [Round {r}/{rounds}] Transmitting Readings from All Sensors ---")
        for sensor in SENSORS:
            reading = generate_sensor_reading(sensor)
            success = send_reading(reading, host=host, port=port)
            status = "SENT" if success else "FAILED"
            print(f"  [{status}] {reading['sensor_id']:<14} ({reading['location']:<22}) -> Temp: {reading['temperature_c']}°C, Humidity: {reading['humidity_percent']}%")
            time.sleep(interval)

    print("\n[CLIENT] Sensor transmission simulation completed successfully.")


if __name__ == "__main__":
    host_target = "127.0.0.1"
    port_target = 54322
    rounds_target = 3

    if len(sys.argv) > 1 and sys.argv[1].isdigit():
        rounds_target = int(sys.argv[1])

    run_sensor_simulation(host=host_target, port=port_target, rounds=rounds_target, interval=0.2)
