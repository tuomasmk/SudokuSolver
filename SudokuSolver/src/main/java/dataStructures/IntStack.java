package dataStructures;

public class IntStack {
    int[] array;
    int current;

    public IntStack(int size) {
        array = new int[size];
        current = -1;
    }

    public IntStack() {
        this(10);
    }
    
    public int pop() {
        if (current > -1) {
            return array[current--];
        }
        throw new ArrayIndexOutOfBoundsException(current);
    }
    
    public void push(int x) {
        if (current == array.length - 1) {
            int[] temp = new int[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
        array[++current] = x;
    }
    
    public  boolean isEmpty() {
        return current == -1;
    }
    
    public int size() {
        return current + 1;
    }
}
