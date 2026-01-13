# Stack and Queue Abstract Data Types

This project implements fundamental abstract data types,
including stacks and FIFO queues, using singly linked lists.

The project also includes an application that uses a stack
to solve a maze traversal problem via backtracking.

---

## Implemented Components

- Stack ADT with O(1) operations
- Queue ADT with O(1) operations
- Alternative queue implementation using a circular list
  and a single pointer
- Maze traversal application using stack-based backtracking

---

## Notes

This project was developed as part of academic coursework.
The implementation focuses on correctness and algorithmic
design rather than full production-level completeness.

## How to Run

### Prerequisites
- Java JDK 8 or newer
- Command line / terminal access

---

### Compile

From the project directory:

```bash
cd src
javac *.java
```
---

### Run the Maze Traversal Program

The maze traversal program reads a maze description from a text file and
searches for an exit using a stack-based backtracking approach.

From the src directory:
java Thiseas ../data/maze.txt

---

### Input Format

The input file describes the maze as a grid of characters:

1 : wall

0 : free path

E : maze entrance

Example:  
9 7  
0 3  
1 1 1 E 1 1 1  
1 1 1 0 1 1 1  
1 0 0 0 1 0 1  
1 0 1 0 1 0 0  
1 1 1 0 1 1 1  
1 0 0 0 0 0 1  
1 0 1 1 1 0 1  
1 0 1 1 0 0 1  
0 1 1 1 0 1 1  

---

### Output

- If an exit is found, the program prints the coordinates of the exit.

- If no exit exists, an appropriate message is displayed.

---

### Notes

- The traversal allows only horizontal and vertical movement.

- Backtracking is implemented using a stack.


