package Testing;
import Generators.RNG;
public class RNGtester {

    public static void main(String arg[]){

        RNG rand = new RNG();

        System.out.println("should print out a number from 0 to 49");
        for(int i = 0; i <= 100; i++)  {
            System.out.println(rand.RNG(50));
        }
        System.out.println(" ");
        System.out.println("should print out a number from 20 to 39");
        for(int i = 0; i <= 100; i++)  {
            System.out.println(rand.RNG(20,40));
        }
        System.out.println(" ");
        System.out.println("should print out a number from 0 to 1");
        for(int i = 0; i <= 100; i++)  {
            System.out.println(rand.fRNG());
        }


    }
}
