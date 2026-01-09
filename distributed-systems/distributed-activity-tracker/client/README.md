# Client

The Client component simulates a frontend application that submits activity data to the distributed system.

In the context of this project, it acts as a **dummy frontend**, as required by the assignment specification, and is used to test and demonstrate the backend functionality.

---

## Responsibilities

- Load GPX activity files from local resources
- Establish a TCP connection with the Master node
- Send GPX data asynchronously for processing
- Receive and display the final activity statistics

---

## GPX Resources

GPX files used by the Client are stored in the `resources/` directory.

These files represent recorded physical activities and are used as input data for the system during testing and demonstration.

