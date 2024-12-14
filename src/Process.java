// Class representing a process in the memory allocation
class Process {
    private final int id;
    private final int size;
    private boolean isAllocated;
    private int assignedBlock;

    //  Process constructor with an ID and size
    public Process(int id, int size) {
        this.id = id;
        this.size = size;
        this.isAllocated = false;
        this.assignedBlock = -1;        // Default state -1 means not assigned to any block
    }

    // Getter for the process ID
    public int getId() {
        return id;
    }

    // Getter for the process size
    public int getSize() {
        return size;
    }

    // Getter to check if the process is allocated
    public boolean isAllocated() {
        return isAllocated;
    }

    // Method to allocate the process to a memory block
    public void allocateToBlock(int blockNumber) {
        this.isAllocated = true;
        this.assignedBlock = blockNumber;
    }

    // Method to deallocate the process from memory block
    public void deallocate() {
        this.isAllocated = false;
        this.assignedBlock = -1;
    }

    // Retrieve the assigned block number
    public int getAssignedBlock() {
        return assignedBlock;
    }
}
