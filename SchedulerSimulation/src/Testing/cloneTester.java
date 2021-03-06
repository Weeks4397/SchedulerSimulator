package Testing;
import Processes.*;
public class cloneTester {
    public static void main(String arg[]){
        process PI = new ProcessI();
        process PII = new ProcessII();
        process PIII = new ProcessIII();
        process PIV = new ProcessIV();
        process PIClone = PI.cloneProcess();
        process PIIClone = PII.cloneProcess();
        process PIIIClone = PIII.cloneProcess();
        process PIVClone = PIV.cloneProcess();

        if(PI.getBlockRecord() == null){
           System.out.println("it is null");

        }
        else{

            System.out.println("PI is not null ");

        }
        System.out.println("this is the clone String: " + PIIClone.getNextBlockResource());
        System.out.println("This is PII String: " + PII.getNextBlockResource());
        PIIClone.updateNextBlockResource("hi");
        System.out.println("this is the clone String: " + PIIClone.getNextBlockResource());
        System.out.println("This is PII String: " + PII.getNextBlockResource());

        System.out.println("This is the Polled Clone of PIII: " + PIIIClone.BlockRecord.poll().getR());
        System.out.println("This is the Peeked of PIII: " + PIII.BlockRecord.peek().getR());

        System.out.println("This is the Polled Clone of PIV: " + PIVClone.BlockRecord.poll().getR());
        System.out.println("This is the Peeked of PIV: " + PIV.BlockRecord.peek().getR());


        System.out.println("This is the block time of PI: " + PI.getNextBlockTime());
        System.out.println("This is the cloned block time Clone of PI: " + PIClone.getNextBlockTime());
        PIClone.updateNextBlockTime(3);
        System.out.println("This is the block time of PI: " + PI.getNextBlockTime());
        System.out.println("This is the cloned block time Clone of PI: " + PIClone.getNextBlockTime());
    }


}
