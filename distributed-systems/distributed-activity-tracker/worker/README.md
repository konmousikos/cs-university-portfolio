# Worker Node

Worker nodes are responsible for the **parallel processing** of GPX data chunks.

Each Worker receives a subset of waypoints from the Master and computes intermediate results.

---

## Responsibilities

- Receive GPX chunks from the Master
- Compute:
  - Partial distance
  - Average speed
  - Elevation gain
  - Time duration
- Return intermediate results to the Master

---

## Parallel Execution

- Workers are multithreaded
- Multiple chunks can be processed concurrently
- Designed to scale with the number of Workers

---

## Notes

Workers do not communicate with each other directly.
All coordination is handled by the Master node.
