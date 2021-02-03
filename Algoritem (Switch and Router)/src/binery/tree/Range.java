package binery.tree;

/**
 *
 * @author Farzad
 */

public class Range {
    
    public static boolean isInRange(int a, int[] x){
        boolean inRange = false;
        if(x[0] > x[1])   System.out.println("ERROR: 2nd parameter must be greater or equal than the 1st parameter in array! [isInRange(int, int[]) in Tools class]");
        else{
            if(a >= x[0] && a <= x[1])    inRange = true;
        }
        return inRange;
    }
    
    public static int[] getLowerHalfRange(int[] x){
        int[] result = new int[2];
        if(x[0] == x[1]){
            result[0] = x[0];
            result[1] = x[1];
        }
        else if(x[0] > x[1])    System.out.println("ERROR: 2nd parameter must be greater or equal than the 1st parameter in array! [getLowerHalfRange(int[]) in Tools class]");
        else{
            int counter = (x[1] - x[0] + 1) / 2;
            result[0] = x[0];
            result[1] = x[0] + counter - 1;
        }
        return result;
    }
    
    public static int[] getHigherHalfRange(int[] x){
        int[] result = new int[2];
        if(x[0] == x[1]){
            result[0] = x[0];
            result[1] = x[1];
        }
        else if(x[0] > x[1])    System.out.println("ERROR: 2nd parameter must be greater or equal than the 1st parameter in array! [getHigherHalfRange(int[]) in Tools class]");
        else{
            int counter = ((x[1] - x[0] + 1) / 2) + 1; 
            result[0] = counter;
            result[1] = x[1];
        }
        return result;
    }
    
    // Returns reverse of the input string as a string
    public static String reverseString(String s){
        String res;
        char[] c = s.toCharArray();
        char tmp;
        int max = c.length / 2;
        for(int i=0;i<max;i++){
            tmp = c[i];
            c[i] = c[c.length - 1 - i];
            c[c.length - 1 - i] = tmp;
        }
        res = new String(c);
        return res;
    }
    
    public static String getLargerBinary(String bin, int size){
        if(bin.length() > size) System.out.println("");
        else{
            String extension = "";
            for(int i=bin.length();i<size;i++)  extension += "0";
            return extension + bin;
        }
        return null;
    }
    
}

