package dataStructures;

public class Map<K, V> {
    Node<K, V>[] nodes;

    public Map() {
        this(128);
    }
    
    public Map(int size) {
        this.nodes = new Node[size];
    }
    
    public void put(K key, V value) {
        Node<K, V> node = new Node<>(key, value, null);
        
        int place = key.hashCode() % nodes.length;
        
        Node<K, V> temp = nodes[place];
        if (temp == null) {
            nodes[place] = node;
        } else {
            while (temp.next != null) {
                if (temp.key.equals(key)) {
                    temp.value = value;
                    return;
                }
            }
            
            if (temp.key.equals(key)) {
                temp.value = value;
            } else {
                temp.next = node;
            }
        }
    }
    
    public V get(K key) {
        Node<K, V> node = nodes[key.hashCode() % nodes.length];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }
    
    public void clear() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = null;
        }
    }
    
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
    public IntStack keys() {
        IntStack stack = new IntStack();
        Node node;
        for (int i = 0; i < nodes.length; i++) {
            node = nodes[i];
            while (node != null) {
                stack.push((Integer) node.key);
                node = node.next;
            }
        }
        return stack;
    }

    public int size() {
        return nodes.length;
    }
    
    
}
