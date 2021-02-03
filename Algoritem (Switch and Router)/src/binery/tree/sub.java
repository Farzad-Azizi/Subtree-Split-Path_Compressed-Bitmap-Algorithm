package binery.tree;

/**
 *
 * @author Farzad
 */
public class sub {

    BTNode root1;
    BT scan = new BT();

    public void insert(BTNode root) {
        this.root1 = root;
    }
    private int count_node = 0;
    private BTNode tem;
    private BTNode temp_root;
    private int flag = 2;//برای تشخیص اینکه ایا سمت راست هست یا چپ درخت
    private String R = "";//right
    private String L = "";//left
    private String total = "";

    /* Start count node search_Subtree_Split */
    private int count_Subtree_Split(BTNode r) {//این تابع برای تشخیص ااینکه تعداد نود کمتر یا مساوی اون تعداد مشخص شده هست یا نه؟
        if (r == null) {
            return 0;
        } else {
            if (!"_".equals(r.lable)) {
                total += "Port:" + r.lable + "---Bucket Prefixes--->" + r.data + "*\n";
                count_node += 1;
            }
            count_Subtree_Split(r.getLeft());
            count_Subtree_Split(r.getRight());
        }
        return count_node;
    }
    /* End count node search_Subtree_Split*/

 /* Start search_Subtree_Split         */
    String IP;
    BTNode tempp;

    public void search_Subtree_Split(String IP) {
        this.IP = IP;
        System.out.println("\n--------------------------------Start Algoritem_Subtree_Spli--------------------------------");
        tem = root1;
        search_Subtree_Split(tem);
        System.out.println("--------------------------------End Algoritem_Subtree_Spli----------------------------------\n");
        System.exit(0);
    }

    private String search_Subtree_Split(BTNode temp) {

        count_node = 0;
        count_Subtree_Split(temp);//تعداد جداسازی  
        scan.search_binery_tree(temp, IP);   // ای پی را در زیر درخت جستجو میشود 
        if (count_node <= 4 & count_node > 0) {
            System.out.println("|-----------******-----------|");
            System.out.println("Node found Algoritem:" + scan.search_binery_tree(temp, IP));   // ای پی را در زیر درخت جستجو میشود 
            System.out.println("|-----------******-----------|");
            if (flag == 0) {
                System.out.println(total);
                System.out.println("Index:" + L + "*");
                System.out.println("Bucket Size:" + count_node);
                L = "";
                temp_root.left = null;
                flag = 2;
                total = "";
            } else if (flag == 1) {
                System.out.println(total);
                System.out.println("Index:" + R + "*");
                System.out.println("Bucket Size:" + count_node);
                R = "";
                temp_root.right = null;
                flag = 2;
                total = "";
            } else {
                System.out.println(total);
                System.out.println("Index:" + "*");
                System.out.println("Bucket Size:" + count_node);
                tem = null;
            }
            System.out.println("******Nodes Removed from Algoritem*******");
        } else if (count_node > 4) {

            total = "";
            if (temp.getLeft() != null) {
                flag = 0;
                temp_root = temp;

                L += temp.left.data;
                R = L;
                search_Subtree_Split(temp.left);
                if (null != temp_root & tem != null) {
                    System.out.println("Start Algoritem");
                    L = "";
                    R = "";
                    search_Subtree_Split(tem);
                }
            } else if (temp.getRight() != null) {
                flag = 1;
                temp_root = temp;
                R += temp.right.data;
                L = R;
                search_Subtree_Split(temp.right);
                if (null != temp_root & tem != null) {
                    R = "";
                    L = "";
                    System.out.println("Start Algoritem1");
                    search_Subtree_Split(tem);
                }
            }
        }
        return "";
    }
    /* End Algoritem_Subtree_Split  */

}
