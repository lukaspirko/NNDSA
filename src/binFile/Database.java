package binFile;

import interfaces.IAbstractDatabase;
import interfaces.IPlace;

import java.io.*;
import java.util.*;

public class Database implements Serializable, IAbstractDatabase {
    private final int CONTROL_SIZE;
    private int DATA_SIZE;
    private final String PATH;
    private ControlBlock controlBlock;
    private DataBlock buffer;
    private List<String> visited;

    public Database(String filePath) {
        CONTROL_SIZE = 200;
        this.PATH = filePath;
    }

    @Override
    public void build(List<IPlace> listOfPlaces, int numberOfBlocks, int numberOfRecords) {

        File file = new File(PATH);
        file.delete();

        listOfPlaces.sort(Comparator.comparing(IPlace::getName));

        DATA_SIZE = (500 + (45 * numberOfRecords));
        controlBlock = new ControlBlock(numberOfBlocks, numberOfRecords, DATA_SIZE);
        writeToFile(convertToBytes(controlBlock), 0);

        for (int i = 0; i < numberOfBlocks; i++) {
            IPlace[] pole = new IPlace[numberOfRecords];
            buffer = new DataBlock(pole, "B" + i);

            while (buffer.isAbleToInsert() && !listOfPlaces.isEmpty()) {
                buffer.add(listOfPlaces.remove(0));
            }
            writeToFile(convertToBytes(buffer), countAddress(i));
        }
        buffer = null;
        controlBlock = null;
    }

    private double countDivideIndex(String key, String keyFirst, String keyLast) {
        double hashKey, hashFirst, hashLast;
        hashKey = hashFunction(key);
        hashFirst = hashFunction(keyFirst);
        hashLast = hashFunction(keyLast);

        return (hashKey - hashFirst) / (hashLast - hashFirst);
    }

    private long hashFunction(String key) {
        return key.hashCode();
    }

    private int countPositionOfBlock(int firstBlock, int lastBlock, double d) {
        return firstBlock + (int) ((lastBlock - firstBlock + 1) * d);
    }

    private int calculateDivideIndex(int indexOd, int indexDo) {
        int max = Math.max(indexOd, indexDo);
        int min = Math.min(indexOd, indexDo);
        int tmp = (max - min) / 2;
        return min + tmp;
    }

    private int countAddress(int i) {
        return CONTROL_SIZE + (i * DATA_SIZE);
    }

    private void writeToFile(byte[] data, int position) {
        RandomAccessFile file;
        try {
            file = new RandomAccessFile(PATH, "rw");
            file.seek(position);
            file.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] readFromFile(int position, int size) {
        byte[] bytes = null;
        try {
            RandomAccessFile file = new RandomAccessFile(PATH, "r");
            file.seek(position);
            bytes = new byte[size];
            file.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    private byte[] convertToBytes(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toByteArray();
    }

    private Object convertFromBytes(byte[] bytes) {
        ObjectInput in;
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            in = new ObjectInputStream(bis);
            obj = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public IPlace delete(String key) {
        try {
            IPlace data = interpolationSearch(key);
            if (data != null) {
                buffer.remove(data);
                writeToFile(convertToBytes(buffer), buffer.getPosition());
            } else {
                return null;
            }
            buffer = null;
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IPlace interpolationSearch(String key) {
        controlBlock = (ControlBlock) convertFromBytes(readFromFile(0, CONTROL_SIZE));
        int countBlock = controlBlock.getCountOfBlocks();

        DATA_SIZE = controlBlock.getSizeOfDataBlock();
        visited = new ArrayList<>();

        int indexFrom = 0;
        int indexTo = countBlock - 1;

        while (true) {
            buffer = (DataBlock) convertFromBytes(readFromFile(countAddress(indexFrom), DATA_SIZE));
            String firstKey = buffer.getMin().getName();
            buffer = (DataBlock) convertFromBytes(readFromFile(countAddress(indexTo), DATA_SIZE));
            String lastKey = buffer.getMax().getName();

            int numberOfSearchedBlock = countPositionOfBlock(indexFrom, indexTo, countDivideIndex(key, firstKey, lastKey));

            if (numberOfSearchedBlock == countBlock) {
                numberOfSearchedBlock--;
            }
            if (numberOfSearchedBlock < 0) {
                throw new NoSuchElementException("Element has not been found in any data block");
            }

            buffer = (DataBlock) convertFromBytes(readFromFile(countAddress(numberOfSearchedBlock), DATA_SIZE));
            visited.add(buffer.getNumberOfBlock());
            if (buffer.getMin().getName().compareTo(key) <= 0 && buffer.getMax().getName().compareTo(key) >= 0) {
                List<IPlace> tmpList = buffer.getList();
                Optional<IPlace> founded = tmpList.stream().filter(o -> o.getName().equals(key)).findFirst();

                if (founded.isPresent()) {
                    IPlace get = founded.get();
                    System.out.println(get.getName());
                    System.out.println(visited);
                    return founded.get();
                } else {
                    return null;
                }
            }

            if (buffer.getMin().getName().compareTo(key) > 0) {
                indexTo = numberOfSearchedBlock - 1;
            }

            if (buffer.getMax().getName().compareTo(key) < 0) {
                indexFrom = numberOfSearchedBlock + 1;
            }

            if (indexFrom >= countBlock || indexTo < 0 || indexFrom > indexTo) {
                System.out.println(false);
                return null;
            }
        }
    }

    @Override
    public IPlace binarySearch(String key) {
        controlBlock = (ControlBlock) convertFromBytes(readFromFile(0, CONTROL_SIZE));
        int countOfBlocks = controlBlock.getCountOfBlocks();

        DATA_SIZE = controlBlock.getSizeOfDataBlock();
        visited = new ArrayList<>();

        int indexFrom = 0;
        int indexTo = countOfBlocks - 1;
        int divideIndex = calculateDivideIndex(indexFrom, indexTo);

        while (true) {
            buffer = (DataBlock) convertFromBytes(readFromFile(countAddress(divideIndex), DATA_SIZE));
            if (buffer.getCountRecords() == 0) {
                return null;
            }
            visited.add(buffer.getNumberOfBlock());
            if (buffer.getMin().getName().compareTo(key) <= 0 && buffer.getMax().getName().compareTo(key) >= 0) {
                List<IPlace> tmpList = buffer.getList();
                Optional<IPlace> founded = tmpList.stream().filter(o -> o.getName().equals(key)).findFirst();
                if (founded.isPresent()) {
                    IPlace get = founded.get();
                    System.out.println(get.getName());
                    System.out.println(visited);
                    return get;
                } else {
                    System.out.println(false);
                    return null;
                }
            }

            if (buffer.getMin().getName().compareTo(key) > 0) {
                indexTo = divideIndex - 1;
                divideIndex = calculateDivideIndex(indexFrom, indexTo);
            }
            if (buffer.getMax().getName().compareTo(key) < 0) {
                indexFrom = divideIndex + 1;
                divideIndex = calculateDivideIndex(indexFrom, indexTo);
            }

            if (indexFrom >= countOfBlocks || indexTo < 0 || indexFrom > indexTo) {
                System.out.println(false);
                return null;
            }
        }
    }
}
