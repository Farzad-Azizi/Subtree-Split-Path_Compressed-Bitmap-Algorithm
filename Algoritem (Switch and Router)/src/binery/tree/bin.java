package binery.tree;

/**
 *
 * @author Farzad
 */
class bin {
     String b = "";

    void bin(int n) {

        if (n > 1) {
            bin(n / 2);
        }
        b += n % 2;
    }

    String g() {
        return b;
    }

    void Clear() {
        b = "";
    }  
}
