# Client

The Client component simulates a frontend application that submits activity data to the system.

In the context of this project, it acts as a **dummy frontend** used to test and demonstrate the backend functionality.

---

## Responsibilities

- Select and load a GPX file
- Establish a TCP connection with the Master node
- Send the GPX file asynchronously
- Receive and display the final activity statistics

---

## Communication Model

- Uses a persistent TCP socket connection
- Operates asynchronously to avoid blocking execution
- Waits for the Master to complete processing before receiving results
