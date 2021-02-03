package binery.tree;

/**
 *
 * @author Farzad
 */
public class Le_tr {

    BTNode root1;
    BT scan = new BT();

    public void insert(BTNode root) {
        this.root1 = root;
    }

     /* Start One_bit_trie  */
    private String lab = "";
    private int i = 0;
    private int fal = 0;
    private int le;
    private BTNode tem;
    
    public void One_bit_trie() {
         System.out.println("\n-------------Algoritem implementation details (Level Compression Trie)---------------------\n");
        
        System.out.println("\n--------------------------------Start Algoritem_One_bit_trie--------------------------------");
        tem = root1;
        One_bit_trie(tem);// فرلخوانی قسمت اول الگوریتم
        scan.countNodes(tem);
        System.out.println("\n--------------------------------End Algoritem_One_bit_trie----------------------------------");
    }

    private String One_bit_trie(BTNode temp) {// قسمت اول a 
        le = 0;
        if (temp == null) {
            return "";
        } else {
            if (!"_".equals(temp.lable) & (temp.left != null || temp.right != null)) {
                lab = temp.lable;
                temp.lable = "_";
                fal = 1;
                i = 0;
            }
            if (temp.left == null & temp.right != null & fal == 1) {
                temp.left = new BTNode(lab + i, temp.data + 0);
                le = 1;
                i++;
            }
            if (temp.right == null & temp.left != null & fal == 1) {
                temp.right = new BTNode(lab + i, temp.data + 1);
                i++;
            }
            if (le == 0) {
                One_bit_trie(temp.getLeft());
            }

            One_bit_trie(temp.getRight());
        }
        tem = temp;
        return "";
    }
    /* End One_bit_trie  */

    /* Start Path_Compressed  */
    public String Path_Compressed(String IP) {// قسمت b
        System.out.println("\n--------------------------------Start Algoritem Path_Compressed ----------------------------");
        Path_Compressed(tem);// فراخواتی فشرده سازی 
        scan.countNodes(tem);
        String s=scan.search_binery_tree(tem, IP);// جستجو بعد از عملیات
        System.out.println("\n--------------------------------End Algoritem Path_Compressed   ----------------------------");
        return s;
    }

    private String Path_Compressed(BTNode temp) {
        if (temp == null) {
            return "";
        } else {

            if (temp.left != null & temp.right != null) {//چپ زیر درخت خالی نیست و راست زیر درخت خالی استکه با فشرده سازی انجام شود
                Path_Compressed(temp.left);
                Path_Compressed(temp.right);
            } else if (temp.left != null & temp.right == null) {

                Path_Compressed(temp.left);
                temp.lable = temp.left.lable;

                if (temp.left.right == null & temp.left.left == null) {
                    temp.left = null;
                } else if (temp.left.left != null & temp.left.right != null) {
                    if (!"_".equals(temp.left.left.lable) & !"_".equals(temp.left.right.lable)) {
                        String m = temp.left.left.lable;
                        String n = temp.left.right.lable;
                        temp.left = null;
                        temp.left = new BTNode(m, temp.data + "0");//copmres in 0
                        temp.right = new BTNode(n, temp.data + "1");//compress in 1

                    }
                }
            } else if (temp.left == null & temp.right != null) {//چپ زیر درخت خال و راست زیر درخت خالی نیست
                Path_Compressed(temp.right);
                temp.lable = temp.right.lable;
                if (temp.right.right == null & temp.right.left == null) {
                    temp.right = null;
                } else if (temp.right.left != null & temp.right.right != null) {
                    if (!"_".equals(temp.right.left.lable) & !"_".equals(temp.right.right.lable)) {
                        String m = temp.right.right.lable;
                        System.out.println("" + m);
                        String n = temp.right.left.lable;
                        temp.right = null;
                        temp.left = new BTNode(n, temp.data + "0");
                        temp.right = new BTNode(m, temp.data + "1");

                    }
                }
            }
        }
        tem = temp;
        return temp.lable;

    }
    /* End Path_Compressed  */
}
