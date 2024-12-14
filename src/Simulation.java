import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Simulation {
    private static Simulation instance;
    private final Scanner scanner;
    private final List<MemoryBlock> memoryBlocks;
    private final List<Process> processes;
    private final WorstFitAlgorithm worstFitAlgorithm;

    private Simulation() {
        scanner = new Scanner(System.in);
        memoryBlocks = new ArrayList<>();
        processes = new ArrayList<>();
        worstFitAlgorithm = new WorstFitAlgorithm();
    }

    public static Simulation getInstance() {
        // Singleton pattern to ensure only one instance of Simulation exists
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    // Starts the simulation by initializing memory blocks, processes, and running the main loop
    public void start() {
        initializeMemoryBlocks();
        initializeProcesses();
        runSimulation();
    }

    // Initializes memory blocks by reading the number and size of each block from the user
    private void initializeMemoryBlocks() {
        System.out.print("Enter the number of memory blocks: ");
        int numberOfBlocks = readValidInteger();
        System.out.println("Enter the sizes of memory blocks:");

        for (int i = 0; i < numberOfBlocks; i++) {
            System.out.print("Enter size of block " + (i + 1) + ": ");
            int size = readValidInteger();
            memoryBlocks.add(new MemoryBlock(size));
        }
    }

    // Initializes processes by reading the number and size of each process from the user
    private void initializeProcesses() {
        System.out.print("Enter the number of processes: ");
        int numberOfProcesses = readValidInteger();
        System.out.println("Enter the sizes of processes:");

        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("Enter size of process " + (i + 1) + ": ");
            int size = readValidInteger();
            processes.add(new Process(i + 1, size));
        }
    }

    // Main simulation loop to allocate memory, display details, and handle user inputs for adding or terminating processes
    private void runSimulation() {
        while (true) {
            worstFitAlgorithm.allocateMemory(memoryBlocks, processes);
            displayMemoryBlocks();
            displayProcesses();

            // Ask to add more processes
            System.out.print("\nDo you want to add more processes? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();

            if (response.equals("yes")) {
                addNewProcesses();
            } else if (response.equals("no")) {
                // Ask to terminate processes if not adding more
                System.out.print("Would you like to terminate existing processes? (yes/no): ");
                response = scanner.next().trim().toLowerCase();

                if (response.equals("yes")) {
                    terminateProcesses();
                } else if (response.equals("no")) {
                    System.out.println("Simulation terminated.");
                    break; // Exit the loop and terminate the program
                } else {
                    System.out.println("Invalid input! Please enter 'yes' or 'no'.");
                }
            } else {
                System.out.println("Invalid input! Please enter 'yes' or 'no'.");
            }
        }
        scanner.close();
    }

    // Adds new processes to the simulation by reading their sizes from the user
    private void addNewProcesses() {
        System.out.print("Enter the number of additional processes: ");
        int additionalProcesses = readValidInteger();
        System.out.println("Enter the sizes of additional processes:");

        for (int i = 0; i < additionalProcesses; i++) {
            System.out.print("Enter size of additional process " + (processes.size() + 1) + ": ");
            int size = readValidInteger();
            processes.add(new Process(processes.size() + 1, size));
        }
    }

    // Terminates specified processes, deallocating their memory and removing them from the list
    private void terminateProcesses() {
        System.out.print("Enter the number of processes to terminate: ");
        int terminateCount = readValidInteger();

        if (terminateCount <= 0) {
            System.out.println("Invalid number of processes to terminate.");
            return;
        }

        System.out.println("Enter the process IDs to terminate:");

        for (int i = 0; i < terminateCount; i++) {
            System.out.print("Enter process ID: ");
            int processId = readValidInteger();
            boolean processFound = false;

            for (int j = 0; j < processes.size(); j++) {
                Process process = processes.get(j);
                if (process.getId() == processId) {
                    processFound = true;

                    if (process.isAllocated()) {
                        int blockIndex = process.getAssignedBlock() - 1;
                        memoryBlocks.get(blockIndex).deallocate(process.getId(), process.getSize());
                    }

                    processes.remove(j);
                    System.out.println("Process " + processId + " terminated and removed.");
                    break;
                }
            }

            if (!processFound) {
                System.out.println("Process " + processId + " not found.");
            }
        }
    }

    // Displays the current state of all memory blocks
    private void displayMemoryBlocks() {
        System.out.println("\nMemory Block Details:");
        System.out.println("Block No.\tTotal Size\tRemaining Space\tAssigned Processes");

        for (int i = 0; i < memoryBlocks.size(); i++) {
            MemoryBlock block = memoryBlocks.get(i);
            System.out.print("\t" + (i + 1) + "\t\t\t" + block.getTotalSize() + "\t\t\t" + block.getRemainingSize() + "\t\t\t\t");
            System.out.println(block.getAssignedProcesses());
        }
    }

    // Displays the current state of all processes
    private void displayProcesses() {
        System.out.println("\nProcess Details:");
        System.out.println("Process No.\tProcess Size\tStatus\t\tAssigned Block");

        for (Process process : processes) {
            System.out.print("\t" + process.getId() + "\t\t\t" + process.getSize() + "\t");
            System.out.println(process.isAllocated() ? "\t\tAllocated\t\t\t" + process.getAssignedBlock() : "\t\tNot Allocated");
        }
    }

    // Reads a valid integer input from the user, handling invalid inputs
    private int readValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }
}
