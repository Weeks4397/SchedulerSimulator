package Processes;

/** A block is two ints for block instance and block length
 * and a string for the type of resource needed
 */

public class Block {

    public int BI;
    public String R;
    public int BT;

    public Block(int bi, String r, int bt) {
        this.BI = bi;
        this.R = r;
        this.BT = bt;
    }

    public int getBI() {
        return this.BI;
    }

    public String getR() {
        return this.R;
    }

    public int getBT() {
        return this.BT;
    }
}
