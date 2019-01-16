package Processes;

/** A block is two ints for block instance and block length
 * and a string for the type of resource needed
 */

public class Block {

    /**
     * is the block instance
     */
    public int BI;
    /**
     *is the recourse type
     */
    public String R;
    /**
     *is the block time
     */
    public int BT;

    /**
     * sets all block info
     * @param bi is the block instance
     * @param r is the recourse type
     * @param bt is the block time
     */
    public Block(int bi, String r, int bt) {
        this.BI = bi;
        this.R = r;
        this.BT = bt;
    }

    /**
     * gets block instance
     * @return BI is block instance
     */
    public int getBI() {
        return this.BI;
    }

    /**
     * gets the recourse type
     * @return R is the recourse type
     */
    public String getR() {
        return this.R;
    }

    /**
     * gets the block time
     * @return BT is block time
     */
    public int getBT() {
        return this.BT;
    }
}
