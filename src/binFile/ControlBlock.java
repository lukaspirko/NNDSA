package binFile;

import java.io.Serializable;

public class ControlBlock implements Serializable {

    private int countOfBlocks;
    private int blockFactor;
    private int actualBlock;
    private int sizeOfDataBlock;

    public ControlBlock(int countOfBlocks, int blockFactor, int sizeOfDataBlock) {
        this.countOfBlocks = countOfBlocks;
        this.blockFactor = blockFactor;
        this.sizeOfDataBlock = sizeOfDataBlock;
        actualBlock = -1;
    }

    public int getCountOfBlocks() {
        return countOfBlocks;
    }

    public void setCountOfBlocks(int countOfBlocks) {
        this.countOfBlocks = countOfBlocks;
    }

    public int getBlockFactor() {
        return blockFactor;
    }

    public void setBlockFactor(int blockFactor) {
        this.blockFactor = blockFactor;
    }

    public int getActualBlock() {
        return actualBlock;
    }

    public void setActualBlock(int actualBlock) {
        this.actualBlock = actualBlock;
    }

    public int getSizeOfDataBlock() {
        return sizeOfDataBlock;
    }

    public void setSizeOfDataBlock(int sizeOfDataBlock) {
        this.sizeOfDataBlock = sizeOfDataBlock;
    }
}
