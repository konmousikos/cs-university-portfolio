# Distributed Systems

This directory contains coursework developed for the **Distributed Systems** university course.

At the current stage, it includes a single comprehensive project that focuses on the design and implementation of a distributed backend system using a master–worker architecture.

---

## Included Project

### Distributed Activity Tracker

A distributed system for processing activity tracking data (GPX files), inspired by real-world platforms such as Strava.

The system follows a **MapReduce-like master–worker model** and supports parallel data processing using TCP-based communication and multithreading.

Key characteristics:
- Master–Worker architecture
- Parallel processing of GPX activity data
- TCP socket communication
- Explicit synchronization using `synchronized` and `wait/notify`
- In-memory aggregation of activity statistics

Project directory:  [`distributed-activity-tracker/`](distributed-activity-tracker/)

---

## Learning Focus

This project was designed to explore core distributed systems concepts, including:

- Task decomposition and parallel execution
- Coordination between distributed components
- Network communication using sockets
- Concurrency and synchronization
- Trade-offs between performance and system simplicity

---

## Academic Context

The project was developed as part of a mandatory university assignment for the *Distributed Systems* course.
