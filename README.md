---

# **Memory Allocation Simulation - Worst Fit Algorithm**

This program simulates memory allocation using the **Worst Fit** algorithm. Processes are allocated to memory blocks such that the block with the largest remaining space that can fit the process is selected. Users can add processes dynamically and terminate existing processes.

---

## **Features**

- Simulates memory allocation using the Worst Fit strategy.
- Allows user input for:
  - Number of memory blocks and their sizes.
  - Number of processes and their sizes.
- Enables dynamic process addition and termination.
- Displays memory and process status after each operation.

---

## **Prerequisites**

Ensure the following software is installed on your system:

- **Java Development Kit (JDK)** version 8 or later.
- A text editor (e.g., VS Code, IntelliJ IDEA, Eclipse) or terminal for running the program.

---

## **Program Structure**

The program consists of the following files:

1. **`WorstFitSimulation.java`** - Main entry point to start the program.
2. **`Simulation.java`** - Handles the simulation's flow (memory and process management).
3. **`WorstFitAlgorithm.java`** - Contains the logic for the Worst Fit memory allocation.
4. **`MemoryBlock.java`** - Represents a memory block with its total and remaining sizes.
5. **`Process.java`** - Represents a process with its size and allocation details.

---

## **How to Run the Program**

Follow these steps to compile and run the program:

1. **Clone the Repository**  
   Download or clone this repository to your local system:  
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. **Compile the Program**  
   Open a terminal and compile all `.java` files:  
   ```bash
   javac WorstFitSimulation.java Simulation.java WorstFitAlgorithm.java MemoryBlock.java Process.java
   ```

3. **Run the Program**  
   Execute the `WorstFitSimulation` class:  
   ```bash
   java WorstFitSimulation
   ```

4. **Follow User Prompts**  
   - Enter the number of memory blocks and their sizes.  
   - Enter the number of processes and their sizes.  
   - Add or terminate processes dynamically during the simulation.  