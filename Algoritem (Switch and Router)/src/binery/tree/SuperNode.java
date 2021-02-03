package binery.tree;

/**
 *
 * @author Farzad
 */

public class SuperNode {
    
    protected int[] resultBitmap;
    protected String[] result;
    protected int[] childBitmap;
    protected SuperNode[] child;
    
    public SuperNode(){
        resultBitmap = new int[7];
        result = new String[7];
        childBitmap = new int[8];
        child = new SuperNode[8];
        
        for(int i=0;i<7;i++){
            resultBitmap[i] = 0;
            result[i] = "";
        }
        for(int i=0;i<8;i++){
            childBitmap[i] = 0;
            child[i] = null;
        }
    }
    
    public SuperNode(int[] resBitmap, String[] res, int[] cBitmap, SuperNode[] c){
        this();
        resultBitmap = resBitmap;
        result = res;
        childBitmap = cBitmap;
        child = c;
    }
    
    public boolean isLeaf(){
        boolean is = true;
        for(int i=0;i<8;i++){
            if(childBitmap[i] == 1) is = false; 
        }
        return is;
    }
    
}

