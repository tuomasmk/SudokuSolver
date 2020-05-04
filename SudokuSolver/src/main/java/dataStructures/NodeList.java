package dataStructures;


public class NodeList {
    ColumnNode[] array;
    int current;

    public NodeList(int length) {
        array = new ColumnNode[length];
        current = -1;
    }
    
    public void add(ColumnNode node) {
        array[++current] = node;
    }
    
    public ColumnNode get(int index) {
        if (index >= 0 && index <= current) {
            return array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
}
