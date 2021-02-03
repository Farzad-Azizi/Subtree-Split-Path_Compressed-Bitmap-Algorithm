package binery.tree;

/**
 *
 * @author Farzad
 */
public class BinaryTree extends Tree {

    public BinaryTree() {
        root = createNode(2);
    }

    public BinaryTree(Node r) {
        root = r;
    }

    @Override
    protected Node createNode(int childs) {
        Node n = null;
        if (childs != 2) {
            System.out.println("ERROR: BinaryTree nodes must have 2 childs![createNode(2)]");
        } else {
            n = new Node();
            n.addChild(null);           // Left Child
            n.addChild(null);           // Right Child
        }
        return n;
    }

    @Override
    public void insertIP(String ip, int subnetMask, String port) {
        Node ptr = root;
        char[] ipChar = ip.toCharArray();
        boolean isOutput = false;
        if (subnetMask == 0) {
            ptr.setOutput(port);
        }
        for (int i = 0; i < subnetMask; i++) {
            if (ipChar[i] == '0') {
                if (ptr.getChild(0) == null) {
                    insertNode(createNode(2), ptr, 0);
                }
                ptr = ptr.getChild(0);
            } else {
                if (ptr.getChild(1) == null) {
                    insertNode(createNode(2), ptr, 1);
                }
                ptr = ptr.getChild(1);
            }

            if (i == subnetMask - 1) {
                ptr.setOutput(port);
            }
        }
    }

    //1
    public TreeBitmap getTreeBitmap() {
        SuperNode superNode = toSuperNode(this.root);
        TreeBitmap bitmapTree = new TreeBitmap(superNode);
        return bitmapTree;
    }

    //2
    private SuperNode toSuperNode(Node n) {
        SuperNode superNode = new SuperNode();
        BinaryTree t = new BinaryTree(n);
        int cIndex = 0, lvl = 0, idx = 1;
        for (int i = 0; i < 7; i++) {
            Node node = t.getNodeAt(lvl, idx);
            if (node != null && node.isOutput()) {
                superNode.resultBitmap[i] = 1;
                superNode.result[i] = node.getOutputPort();
            }
            if (i > 2 && !isLastSuperNode(n)) {
                if (node == null) {
                    cIndex += 2;
                } else {
                    if (node.getChild(0) != null) {
                        superNode.childBitmap[cIndex] = 1;
                        superNode.child[cIndex] = toSuperNode(node.getChild(0));
                    }
                    cIndex++;
                    if (node.getChild(1) != null) {
                        superNode.childBitmap[cIndex] = 1;
                        superNode.child[cIndex] = toSuperNode(node.getChild(1));
                    }
                    cIndex++;
                }
            }
            if (i == 0 || i == 2) {
                lvl++;
                idx = 1;
            } else {
                idx++;
            }
        }
       System.out.print("\nResult ptr:");
        for (int j = 0; j <superNode.resultBitmap.length;j++) {
           System.out.print(superNode.resultBitmap[j]); 
        }
         System.out.print("\nChild ptr:");
        for (int i = 0; i <superNode.childBitmap.length;i++) {
           System.out.print(superNode.childBitmap[i]); 
        }
        System.out.println("\n..........");
        return superNode;
    }
    //3

    private boolean isLastSuperNode(Node n) {
        boolean isLast = false;
        int lvl = 0;
        BinaryTree t = new BinaryTree(n);
        TreePointer tp = new TreePointer(t);
        for (int i = 0; i < 3; i++) {
            if (tp.goToNextLevel() == 1) {
                lvl++;
            }
        }
        if (lvl < 3) {
            isLast = true;
        }
        return isLast;
    }

}
