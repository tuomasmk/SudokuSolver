package dataStructures;


public class NodeStack {
    DancingNode[] array;
    int current;
    
    public NodeStack(int size) {
        array = new DancingNode[size];
        current = -1;
    }

    public NodeStack() {
        this(10);
    }
    
    public DancingNode pop() {
        if (current > -1) {
            return array[current--];
        }
        throw new ArrayIndexOutOfBoundsException(current);
    }
    
    public void push(DancingNode node) {
        if (current == array.length - 1) {
            DancingNode[] temp = new DancingNode[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
        array[++current] = node;
    }
    
    public  boolean isEmpty() {
        return current == -1;
    }
    
    public int size() {
        return current + 1;
    }
    
    public NodeStack copy() {
        NodeStack to = new NodeStack(array.length);
        for (int i = 0; i <= current; i++) {
            to.push(array[i]);
        }
        return to;
    }
}
