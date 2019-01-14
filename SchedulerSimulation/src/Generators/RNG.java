package Generators;
import java.util.Random;

/**
 The RNG class is used for random number generation in the ProcessGenerator and WorkSetGenerator.
 */

public class RNG {
    //RNG Methods

    static Random rand = new Random();
    /**
     * Random number from 0 to max exclusive of max
     *
     * @param  max   the maximum random int
     * @return       a random int
     */
    public static int RNG(int max)  {
        int num = rand.nextInt(max);
        return num;
    }

    /**
     * Random number from min to max inclusive
     *
     * @param  min   the minimum random int
     * @param  max   the maximum random int
     * @return       a random int
     */
    public static int RNG (int min, int max) {
        int num = rand.nextInt((max+1) - min)+min;
        return num;
    }

    /**
     * Random number from 0 to 1 exclusive of 1
     *
     * @return       a random int
     */
    public static float fRNG() {
        float num = rand.nextFloat();
        return num;
    }

}
