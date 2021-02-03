package binery.tree;
/**
 *
 * @author Farzad
 */

//Add nodes tree
class BTNode {

    BTNode left, right;
    String data;
    String lable;
    /* Constructor */
    public BTNode() {
        left = null;
        right = null;
        data = "";
        lable="";
    }

    /* Constructor */
    public BTNode(String la,String data) {
        left = null;
        right = null;
        lable=la;
        this.data=data;
    }

    /* Function to set left node */
    public void setLeft(BTNode n) {
        left = n;
    }

    /* Function to set right node */
    public void setRight(BTNode n) {
        right = n;
    }

    /* Function to get left node */
    public BTNode getLeft() {
        return left;
    }

    /* Function to get right node */
    public BTNode getRight() {
        return right;
    }

}
