# Greedy Bin Packing with Priority Queues

This project explores efficient greedy approaches to the **Bin Packing problem**
using custom implementations of **priority queues** and **sorting algorithms**.

The goal is to pack a set of folders (items) into fixed-capacity disks
while minimizing the total number of disks used.

---

## Problem Overview

Each folder has a size between `0` and `1,000,000 MB`.
Each disk has a fixed capacity of **1 TB (1,000,000 MB)**.

Constraints:
- Each folder must be placed entirely on a single disk
- Disks cannot exceed their capacity

The Bin Packing problem is known to be NP-hard, so this project focuses on
**efficient approximation algorithms** rather than optimal solutions.

---

## Implemented Approaches

### Greedy Strategy
Folders are processed in their original order.
Each folder is placed in the disk with the **maximum remaining free space**.
If no disk can accommodate the folder, a new disk is created.

### Greedy Decreasing Strategy
Folder sizes are first sorted in **descending order**.
The greedy strategy is then applied to the sorted input.

This variant often produces better packing results in practice.

---

## Project Structure

src/  
├── adt/  
│ ├── IntQueue.java  
│ ├── IntQueueImpl.java  
│ └── Node.java  
│  
├── core/  
│ ├── Disk.java  
│ ├── MaxPQ.java  
│ ├── Sort.java  
│ └── Greedy.java  
│  
└── utils/  
└── ExperimentRunner.java  
  
data/  
├── sample_100.txt  
└── sample_500.txt  


---

## Design Highlights

- **Custom data structures**  
  All core data structures (queues, priority queues, sorting)
  are implemented from scratch without relying on Java collections.

- **Heap-based Priority Queue**  
  A max-heap is used to efficiently select the disk with the most free space.

- **Clear separation of concerns**  
  - `adt`: low-level data structures  
  - `core`: domain logic and algorithms  
  - `utils`: experimental and benchmarking tools  

---

## How to Build

From the `src` directory:

```bash
javac adt/*.java core/*.java utils/*.java
```
---

## How to Run
Run the Greedy Algorithms
```bash
java core.Greedy path/to/input.txt
```
Example:
```bash
java core.Greedy data/sample_100.txt
```
---

## Run Experimental Evaluation
```bash
java utils.ExperimentRunner data 1000
```
This will:

- generate random input datasets

- execute both greedy strategies multiple times

- report average disk usage

---

## Sample Data

The `data/` directory contains small example input files for demonstration.
Larger datasets are generated programmatically during experimental evaluation
and are intentionally excluded from the repository.

---

## Notes

- The project prioritizes clarity, correctness, and efficiency.

- No external libraries are used for core logic.

- The implementation is suitable for experimentation and further optimization.
