import java.util.ArrayList;
import java.util.List;

// Class for Memory Block

class MemoryBlock {
    private final int totalSize;
    private int remainingSize;
    private final List<Integer> assignedProcesses;

//  Memory Block constructor with size
    public MemoryBlock(int size) {
        this.totalSize = size;
        this.remainingSize = size;
        this.assignedProcesses = new ArrayList<>();
    }

//  Method for getting remaining memory block size
    public int getRemainingSize() {
        return remainingSize;
    }

//  Method for getting total memory block size
    public int getTotalSize() {
        return totalSize;
    }

//  Method for allocating process to a block
    public void allocate(int processId, int processSize) {
        remainingSize -= processSize;
        assignedProcesses.add(processId);
    }

//  Method for deallocating process from a block
    public void deallocate(int processId, int processSize) {
        remainingSize += processSize;
        assignedProcesses.remove((Integer) processId);
    }

//   Method for getting all the assigned process of a memory block
    public List<Integer> getAssignedProcesses() {
        return new ArrayList<>(assignedProcesses);
    }
}
