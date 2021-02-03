package binery.tree;

/**
 *
 * @author Farzad
 */
public class BT {

    private BTNode root;
    BTNode root1;

    /* Start Functions to insert data */
    public void insert(String data, String la) {
        root = root1;
        if (root == null) {
            root = new BTNode("_", "");
            root1 = root;
        }
        for (int i = 0; i < data.length(); i++) {

            if (data.charAt(i) == '1') {

                if (root.right == null) {
                    if (i == data.length() - 1) {
                        root.right = new BTNode(la, root.data + "1");
                        root = root.right;
                    } else {
                        root.right = new BTNode("_", root.data + "1");
                        root = root.right;
                    }

                } else {
                    if (i == data.length() - 1) {
                        root.right.lable = la;
                        root = root.right;
                    } else {
                        root = root.right;
                    }
                }
            }

            if (data.charAt(i) == '0') {
                if (root.left == null) {
                    if (i == data.length() - 1) {
                        root.left = new BTNode(la, root.data + "0");
                        root = root.left;
                    } else {
                        root.left = new BTNode("_", root.data + "0");
                        root = root.left;
                    }
                } else {
                    if (i == data.length() - 1) {
                        root.left.lable = la;
                        root = root.left;
                    } else {
                        root = root.left;
                    }
                }

            }

        }
    }

    /* End Functions to insert data */

    public int countNodes() {
        return countNodes(root1);
    }

    /*  Functions to count node tree */
    public int countNodes(BTNode r) {

        if (r == null) {
            return 0;
        } else {
            System.out.println(r.lable + "------>" + r.data);
            int count = 1;
            count += countNodes(r.getLeft());
            count += countNodes(r.getRight());
            return count;
        }
    }
    /* End Functions to count Nodes tree */

    private String Search;

    /* Start Functions to search_binery_tree data */
    public String search_binery_tree(String val) {
        System.out.println("\n--------------------------------Start Algoritem_Binery_tree--------------------------------");
        return search_binery_tree(root1, val);
    }
//برای جستجو

    public String search_binery_tree(BTNode temp, String val) {
        if (temp.getLeft() == null & temp.getRight() == null) {
            Search = "درختی ساخته نشده است";
        } else {
            for (int i = 0; i < val.length(); i++) {
                if (val.charAt(i) == '1') {
                    if (temp.right != null) {
                        temp = temp.right;

                        if (!"_".equals(temp.lable)) {
                            Search = temp.lable;
                        }
                    } else {
                        break;
                    }
                }
                if (val.charAt(i) == '0') {
                    if (temp.left != null) {
                        temp = temp.left;

                        if (!"_".equals(temp.lable)) {
                            Search = temp.lable;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return Search;
    }
    /* End Functions to search_binery_tree data */

    private BTNode tem;
    private BTNode temp_root;

    /*  Start sub-tree */
    void sub_te(String IP) {
        sub s = new sub();
        s.insert(root1);
        s.search_Subtree_Split(IP);
    }
    /*  End sub-tree */

    /* Start Path_Compressed  */
    public String le_te(String IP) {
        Le_tr l = new Le_tr();
        l.insert(root1);
        l.One_bit_trie();
        String research = l.Path_Compressed(IP);
        return research;
    }
    /* End Path_Compressed  */

    /* Start Bitmap  */
    public String Bit_map(String IP,BinaryTree tree) {
        System.out.println("------------------Start Algoritem Bitmap Tree--------------------");
           TreeBitmap t = tree.getTreeBitmap();
        System.out.println("------------------End Algoritem Bitmap Tree--------------------");
          return t.searchIP(IP);
    }
    /* End Bitmap  */
}
