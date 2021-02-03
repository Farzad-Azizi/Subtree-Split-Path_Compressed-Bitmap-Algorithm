package binery.tree;

/**
 *
 * @author Farzad
 */
public class TreeBitmap extends Tree {

    SuperNode root;

    public TreeBitmap() {
        root = new SuperNode();
    }

    public TreeBitmap(SuperNode r) {
        root = r;
    }

    public String searchIP(String ip) {
        String ipSeg_1, ipSeg_2;
        int index;
        SuperNode ptr = root;
        String port = null;
        boolean fin = false;
        SuperNode tmp = ptr;
        for (int i = 0; i < 32; i++) {
            if (i == 30) {
                ipSeg_1 = "0" + ip.substring(i, i + 2);      // 3-bits
            } else {
                ipSeg_1 = ip.substring(i, i + 3);      // 3-bits
            }
            ipSeg_2 = ip.substring(i, i + 2);      // 2-bits
            index = Integer.parseInt(ipSeg_1, 2);
            if (ptr.childBitmap[index] == 0) {
                fin = true;
            } else {
                tmp = ptr.child[index];
            }

            index = 3 + Integer.parseInt(ipSeg_2, 2);
           
           
            if (ptr.resultBitmap[index] == 1) {
                port = ptr.result[index];
            } else {
                index = 1 + Integer.parseInt(ip.substring(i, i + 1), 2);
                if (ptr.resultBitmap[index] == 1) {
                    port = ptr.result[index];
                } else if (ptr.resultBitmap[0] == 1) {
                    port = ptr.result[0];
                }
            }

            if (fin) {
                break;
            } else {
                ptr = tmp;
                i += 2;
            }
        }
        return port;
    }

    protected Node createNode(int childs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insertIP(String ip, int subnetMask, String port) {
    }

}
