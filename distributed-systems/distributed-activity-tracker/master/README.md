# Master Node

The Master node is the **central coordinator** of the distributed system.

It receives GPX activity files from clients, distributes processing tasks to workers, and aggregates the intermediate results.

---

## Responsibilities

- Accept incoming TCP connections from clients and workers
- Split GPX files into chunks of waypoints
- Distribute chunks to workers using round-robin scheduling
- Collect intermediate results from workers
- Perform the reduce phase and compute final activity statistics
- Maintain per-user and global statistics in memory

---

## Concurrency Model

- Each client connection is handled in a separate thread
- Worker communication is handled concurrently
- Shared data structures are synchronized using:
  - `synchronized`
  - `wait()` / `notify()`

---

## Notes

The Master node also acts as the **Reducer** in the MapReduce process.
