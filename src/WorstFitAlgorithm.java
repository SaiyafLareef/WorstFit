import java.util.List;

class WorstFitAlgorithm {
    public void allocateMemory(List<MemoryBlock> memoryBlocks, List<Process> processes) {
        for (Process process : processes) {
            // Skip the process if it is already allocated
            if (process.isAllocated()) continue;

            int worstFitIndex = -1;

            // Find the worst-fit memory block for this process
            for (int i = 0; i < memoryBlocks.size(); i++) {
                MemoryBlock block = memoryBlocks.get(i);

                if (block.getRemainingSize() >= process.getSize()) {
                    if (worstFitIndex == -1 || block.getRemainingSize() > memoryBlocks.get(worstFitIndex).getRemainingSize()) {
                        worstFitIndex = i;
                    }
                }
            }

            // Allocate the process to the memory block if a suitable block is found
            if (worstFitIndex != -1) {
                MemoryBlock allocatedBlock = memoryBlocks.get(worstFitIndex);
                allocatedBlock.allocate(process.getId(), process.getSize());
                process.allocateToBlock(worstFitIndex + 1);
            }
        }
    }
}
