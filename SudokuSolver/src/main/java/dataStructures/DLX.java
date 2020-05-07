package dataStructures;

public class DLX {

    private ColumnNode header;
    private NodeStack answer;
    public NodeStack result;

    public DLX(int[][] cover) {
        header = createDLXList(cover);
        answer = new NodeStack();
    }

    private ColumnNode createDLXList(int[][] grid) {
        final int nbColumns = grid[0].length;
        ColumnNode headerNode = new ColumnNode("header");
        NodeList columnNodes = new NodeList(nbColumns);

        for (int i = 0; i < nbColumns; i++) {
            ColumnNode n = new ColumnNode(i + "");
            columnNodes.add(n);
            headerNode = (ColumnNode) headerNode.linkRight(n);
        }

        headerNode = headerNode.right.column;

        for (int[] aGrid : grid) {
            DancingNode prev = null;

            for (int j = 0; j < nbColumns; j++) {
                if (aGrid[j] == 1) {
                    ColumnNode col = columnNodes.get(j);
                    DancingNode newNode = new DancingNode(col);

                    if (prev == null) {
                        prev = newNode;
                    }

                    col.top.linkDown(newNode);
                    prev = prev.linkRight(newNode);
                    col.size++;
                }
            }
        }

        headerNode.size = nbColumns;

        return headerNode;
    }

    private ColumnNode selectColumnNodeHeuristic() {
        int min = Integer.MAX_VALUE;
        ColumnNode ret = null;
        for (ColumnNode c = (ColumnNode) header.right;
                c != header;
                c = (ColumnNode) c.right) {
            if (c.size < min) {
                min = c.size;
                ret = c;
            }
        }
        return ret;
    }

    private void process(int k) {
        if (header.right == header) {
            // End of Algorithm X
            // Result is copied in a result list
            result = answer.copy();
        } else {
            // we choose column c
            ColumnNode c = selectColumnNodeHeuristic();
            c.cover();

            for (DancingNode r = c.bottom; r != c; r = r.bottom) {
                // We add r line to partial solution
                answer.push(r);

                // We cover columns
                for (DancingNode j = r.right; j != r; j = j.right) {
                    j.column.cover();
                }

                // recursive call to leverl k + 1
                process(k + 1);

                // We go back
                r = answer.pop();
                c = r.column;

                // We uncover columns
                for (DancingNode j = r.left; j != r; j = j.left) {
                    j.column.uncover();
                }
            }

            c.uncover();
        }
    }

    public void solve() {
        process(0);
    }
}
