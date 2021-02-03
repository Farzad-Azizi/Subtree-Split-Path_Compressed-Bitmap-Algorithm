package binery.tree;

/**
 *
 * @author Farzad
 */

public abstract class Tree {

    public Node root;

    public int getDepth() {
        TreePointer p = new TreePointer(this);
        while (!p.isLastLevel()) {
            p.goToNextLevel();
        }
        return (p.level);
    }

    protected abstract Node createNode(int childs);

    public void insertNode(Node n, Node p, int index) {
        if (contains(p)) {
            n.parent = p;
            p.setChild(n, index);
        } else {
            System.out.println("ERROR: Attempting to insert a node not associated with the tree! [insertNode(Node, Node, int) in Tree class]");
        }
    }
    // Detects if a given node exists in the tree
    public boolean contains(Node n) {
        TreePointer p = new TreePointer(this);
        boolean contains = false;
        int depth = getDepth();
        while (true) {
            if (p.ptr.equals(n)) {
                contains = true;
                break;
            } else {
                if (p.getPtr() != p.getLastNode()) {
                    p.goToNextNode();
                } else {
                    if (p.isLastLevel()) {
                        break;
                    }
                    p.goToNextLevel();
                }
            }
        }
        return contains;
    }

    protected int convertStride(int str) {
        int value = 1;
        for (int i = 0; i < str; i++) {
            value *= 2;
        }
        return value;
    }

    public abstract void insertIP(String ip, int subnetMask, String port);

    public String searchIP(String ip) {
        String ipSeg, port = "";
        int index, idx = 0;
        Node ptr = root;

        while (true) {
            if (ptr.skip > 0) {
                ipSeg = ip.substring(idx, idx + ptr.skip);
                if (ipSeg.equals(ptr.segment)) {
                    idx += ptr.skip;
                } else {
                    break;
                }
            }
            if (ptr.isOutput()) {
                port = ptr.getOutputPort();
            }
            if (ptr.isLeaf()) {
                break;
            }
            ipSeg = ip.substring(idx, idx + ptr.stride);
            index = Integer.parseInt(ipSeg, 2);
            if (ptr.getChild(index) == null) {
                break;
            }
            ptr = ptr.getChild(index);
            idx += ptr.stride;
        }

        return port;
    }

    public Node getNodeAt(int depth, int column) {
        Node n;
        n = this.root;
        int lvl = 0;
        int cl = 1;
        for (int i = 0; i < depth; i++) {
            cl *= 2;
        }
        int[] tmp = new int[2];
        tmp[0] = 1;
        tmp[1] = cl;
        int[] x, y;
        while (lvl != depth) {
            x = Range.getLowerHalfRange(tmp);       // Lower Half Range
            y = Range.getHigherHalfRange(tmp);      // Higher Half Range
            if (Range.isInRange(column, x)) {
                if (n.getChild(0) == null) {
                    n = null;
                    break;
                } else {
                    n = n.getChild(0);
                    tmp[0] = x[0];
                    tmp[1] = x[1];
                }
            } else {
                if (n.getChild(1) == null) {
                    n = null;
                    break;
                } else {
                    n = n.getChild(1);
                    tmp[0] = y[0];
                    tmp[1] = y[1];
                }
            }
            lvl++;
        }
        return n;
    }

    // --> ERROR: shows one unit more ???????
    public int getStorageUsage() {
        int counter = 1;
        TreePointer p = new TreePointer(this);
        int lastLvl = p.getLastLevel();
        while (true) {
            while (true) {
                if (p.getPtr() == p.getLastNode()) {
                    break;
                } else {
                    p.goToNextNode();
                    counter++;
                }
            }
            if (p.getCurrentLevel() == lastLvl) {
                break;
            }
            p.goToNextLevel();
            counter++;
        }
        return counter;
    }

    public String getNodePosition(Node n) {
        String position;
        TreePointer tp = new TreePointer(this);
        tp.setPtr(n);
        tp.setPtr(tp.firstNode);
        int cntr = 1;
        while (!tp.getPtr().equals(n)) {
            tp.goToNextNode();
            cntr++;
        }
        position = "Row:" + Integer.toString(tp.getCurrentLevel()) + " " + "Column:" + cntr;
        return position;
    }

    protected class TreePointer {

        private final Tree tree;

        private Node firstNode;
        private Node lastNode;
        private Node ptr;

        private int level;

        public TreePointer(Tree t) {
            tree = t;
            firstNode = t.root;
            lastNode = t.root;
            ptr = firstNode;
            level = 0;
        }

        public Node getPtr() {
            return this.ptr;
        }

        public Node getFirstNode() {
            return this.firstNode;
        }

        public Node getLastNode() {
            return this.lastNode;
        }

        public void setPtr(Node n) {
            if (tree.contains(n)) {
                ptr = n;
                level = getLevel();
                lastNode = getLastNodeInCurrentLevel();
                firstNode = getFirstNodeInCurrentLevel();
            } else {
                System.out.println("TreePointer only works with the given tree, so it's not allowed to set the pointer to a node not exists in the tree!");
            }
        }

        public int getCurrentLevel() {
            return level;
        }

        // This method uses Breadth-First Search(BFS)
        public int getLevel() {
            TreePointer p = new TreePointer(tree);
            while (!p.ptr.equals(this.ptr)) {
                if (p.ptr != p.lastNode) {
                    p.goToNextNode();
                } else {
                    p.goToNextLevel();
                }
            }
            return p.level;
        }

        // If ptr points to the most right node in the level, the method would return 'null'
        public Node getNextNode() {
            Node n = null;
            if (!ptr.equals(lastNode)) {
                n = ptr;
                int lvl = level;

                // Upward move
                // While n is the most right child ...
                while (n.equals(n.parent.getMostRightChild())) {
                    n = n.parent;
                    lvl--;
                }
                int idx = n.getIndex() + 1;
                while (n.parent.getChild(idx) == null) {
                    idx++;
                }
                n = n.parent.getChild(idx);
                // Now we're in the next branch

                while (lvl != level) {

                    // Downward move
                    if (!n.isLeaf()) {
                        n = n.getMostLeftChild();
                        lvl++;
                    } // Upward move 
                    else {
                        while (n.equals(n.parent.getMostRightChild())) {
                            n = n.parent;
                            lvl--;
                        }
                        idx = n.getIndex() + 1;
                        while (n.parent.getChild(idx) == null) {
                            idx++;
                        }
                        n = n.parent.getChild(idx);
                    }
                }
            }
            return n;
        }

        // If ptr points to the most left node in the level, the method would return 'null'
        public Node getPreviousNode() {
            Node n = null;
            if (ptr != firstNode) {
                int lvl = level;
                n = ptr;

                // Upward Move
                // While n is the most left child ...
                while (n.equals(n.parent.getMostLeftChild())) {
                    n = n.parent;
                    lvl--;
                }
                int idx = n.getIndex() - 1;
                while (n.parent.getChild(idx) == null) {
                    idx--;
                }
                n = n.parent.getChild(idx);
                // Now we're in the previous branch

                while (lvl != level) {

                    // Upward move
                    if (n.isLeaf()) {
                        // While n is the most left child ...
                        while (n.equals(n.parent.getMostLeftChild())) {
                            n = n.parent;
                            lvl--;
                        }
                        idx = n.getIndex() - 1;
                        while (n.parent.getChild(idx) == null) {
                            idx--;
                        }
                        n = n.parent.getChild(idx);
                    } // Downward move
                    else {
                        n = n.getMostRightChild();
                        lvl++;
                    }
                }
            }
            return n;
        }

        public void goToNextNode() {
            ptr = getNextNode();
        }

        public void goToPreviousNode() {
            ptr = getPreviousNode();
        }

        // Pointer jumps to the next level. if current level is the last level, the method would return -1 otherwise 1 would be returned to represent successful process.
        public int goToNextLevel() {
            int successful = -1;
            if (!isLastLevel()) {
                ptr = firstNode;
                while (ptr.isLeaf()) {
                    goToNextNode();
                }
                firstNode = ptr.getMostLeftChild();
                ptr = firstNode;
                level++;
                lastNode = getLastNodeInCurrentLevel();
                successful = 1;
            }
            return successful;
        }

        protected boolean isLastLevel() {
            boolean last = true;
            Node n = ptr;
            ptr = firstNode;
            while (!ptr.equals(lastNode)) {
                if (!ptr.isLeaf()) {
                    last = false;
                    break;
                }
                goToNextNode();
            }

            // Check the last node in the level-x
            if (last != false) {
                if (!ptr.isLeaf()) {
                    last = false;
                }
            }
            ptr = n;
            return last;
        }

        public int getLastLevel() {
            int depth = tree.getDepth();
            return depth;
        }

        private Node getFirstNodeInCurrentLevel() {
            Node node = tree.root;
            if (level != 0) {
                int lvl = 0;
                while (lvl != level) {

                    // Downward move 
                    if (!node.isLeaf()) {
                        node = node.getMostLeftChild();
                        lvl++;
                    } // Upward move
                    else {
                        while (node.equals(node.parent.getMostRightChild())) {
                            node = node.parent;
                            lvl--;
                        }
                        int idx = node.getIndex() + 1;
                        while (node.parent.getChild(idx) == null) {
                            idx++;
                        }
                        node = node.parent.getChild(idx);
                    }

                }
            }
            return node;
        }

        /* This method is a Right-to-Left DFS; 
         * So it starts from the most right branch.
         */
        private Node getLastNodeInCurrentLevel() {
            Node node = tree.root;
            if (level != 0) {
                int lvl = 0;
                while (lvl != level) {

                    // Downward move 
                    if (!node.isLeaf()) {
                        node = node.getMostRightChild();
                        lvl++;
                    } // Upward move
                    else {
                        while (node.equals(node.parent.getMostLeftChild())) {
                            node = node.parent;
                            lvl--;
                        }
                        int idx = node.getIndex() - 1;
                        while (node.parent.getChild(idx) == null) {
                            idx--;
                        }
                        node = node.parent.getChild(idx);
                    }
                }
            }
            return node;
        }
    } // End of TreePointer class

} // End of Tree class

