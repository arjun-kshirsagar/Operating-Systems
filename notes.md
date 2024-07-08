
### Concurrency: 
Multiple tasks are under progress(depends on the schduling algorithm), but only one of them is being executed at a particular time stamp.

Theses feels like parallel, bcoz of its speeds, but thats not the case, one pasuses for a moment while other works

### Parallelism
Parallelism, on the other hand, is the simultaneous execution of multiple tasks. It requires multiple processing units (e.g., multi-core CPUs or multiple machines) to run tasks at the same time.


#
- Critical Section:
    Part of a code  that works on shared data.
- Race Condition:
    When multiple threads are tyring to access the critical section, it is called as Race Condition

- Pre emtive OS

### Solution to prevent race condition
    1. Mutual Exclusion(Mutex) of Code
    2. Progress:
        The program should be making some progress at any point of time.
    3. Bounded Wait:
        You should not ask the other threads to wait for eternity, every thread should be allowed to access the cricital section
        in their lifeline.
    4. No Busy Waiting:
        Busy Waiting: Amabanis' maraige and washroom occupany.
        But the CPU should not be given a task to check the resources, this is a waste of CPU resources.
