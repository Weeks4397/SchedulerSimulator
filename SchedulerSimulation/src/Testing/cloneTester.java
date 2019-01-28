package Testing;
import Processes.*;
public class cloneTester {
    public static void main(String arg[]){
        ProcessI PI = new ProcessI();
        ProcessII PII = new ProcessII();
        ProcessIII PIII = new ProcessIII();
        ProcessIV PIV = new ProcessIV();
        ProcessI PIClone = PI.cloneProcess();
        ProcessII PIIClone = PII.cloneProcess();
        ProcessIII PIIIClone = PIII.cloneProcess();
        ProcessIV PIVClone = PIV.cloneProcess();

        if(PI.getBlockRecord() != null){
            System.out.println("This is the Polled Clone of PI: " + PIClone.BlockRecord.poll());
            System.out.println("This is the Peeked of PI: " + PI.BlockRecord.peek());
        }
        PIIClone.updateNextBlockResource("hi");
        System.out.println("this is the clone String: " + PIClone.getNextBlockResource());
        System.out.println("This is PI String: " + PI.getNextBlockResource());

        System.out.println("This is the Polled Clone of PIII: " + PIIIClone.BlockRecord.poll());
        System.out.println("This is the Peeked of PIII: " + PIII.BlockRecord.peek());

        System.out.println("This is the Polled Clone of PIV: " + PIVClone.BlockRecord.poll());
        System.out.println("This is the Peeked of PIV: " + PIV.BlockRecord.peek());
    }


}
