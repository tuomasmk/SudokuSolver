package dataStructures;


public class IntList {
    int[] array;
    int current;

    public IntList(int length) {
        array = new int[length];
        current = -1;
    }
    
    public void add(int x) {
        array[++current] = x;
    }
    
    public int get(int index) {
        if (index >= 0 && index <= current) {
            return array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
    
    public int size() {
        return current + 1;
    }
}
