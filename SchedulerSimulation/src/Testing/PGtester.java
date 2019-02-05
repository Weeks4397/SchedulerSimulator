package Testing;

import Generators.*;

public class PGtester {
    public static void main(String arg[]){

        for (int i = 0;  i <= 1000; i++) {

            ProccessIGenerator PG1 = new ProccessIGenerator();
            ProccessIIGenerator PG2 = new ProccessIIGenerator();
            ProccessIIIGenerator PG3 = new ProccessIIIGenerator();
            ProccessIVGenerator PG4 = new ProccessIVGenerator();

            if (PG1.getprocessRunTime() > 75 || PG1.getprocessRunTime() <25){
                System.out.println("should print out a number runtime for 25 to 75 not ");
                System.out.println("RunTime: " + PG1.getprocessRunTime());
                System.out.println(" ");
            }
            if(PG2.getprocessRunTime() > 600 || PG2.getprocessRunTime() < 200){
                System.out.println("should print out a number runtime for 200 to 600 not ");
                System.out.println("RunTime: " + PG2.getprocessRunTime());
                System.out.println(" ");
            }
            if(PG3.getprocessRunTime() > 500 || PG3.getprocessRunTime() < 150){
                System.out.println("should print out a number runtime for 150 to 500");
                System.out.println("RunTime: " + PG2.getprocessRunTime());
                System.out.println(" ");
            }
            if(PG4.getprocessRunTime() > 1000 || PG4.getprocessRunTime() < 400){
                System.out.println("should print out a number runtime for 400 to 1000");
                System.out.println("RunTime: " + PG2.getprocessRunTime());
                System.out.println(" ");
            }

            if (PG1.getBlock().getR() == "A" || PG3.getBlock().getR() == "A"){
                if (PG1.getBlock().getBT() > 100|| PG1.getBlock().getBT() <60 || PG3.getBlock().getBT() > 100|| PG3.getBlock().getBT() < 60){
                    System.out.println("should print out a number Block runtime form 60 to 100 not");
                    System.out.println("Block RunTime: " + PG1.getBlock().getBT()+  "   " + PG3.getBlock().getBT());
                    System.out.println(" ");
                }
            }
            if(PG1.getBlock().getR() == "B" || PG3.getBlock().getR() == "B"|| PG4.getBlock().getR() == "B"){
                if (PG1.getBlock().getBT() > 125|| PG1.getBlock().getBT() <75 || PG4.getBlock().getBT() > 125|| PG4.getBlock().getBT() < 75|| PG3.getBlock().getBT() > 125|| PG3.getBlock().getBT() < 75){
                    System.out.println("should print out a number Block runtime form 60 to 100 not");
                    System.out.println("Block RunTime: " + PG1.getBlock().getBT() +  "   " + PG3.getBlock().getBT() + "   " + PG4.getBlock().getBT() );
                    System.out.println(" ");
                }
            }
            if(PG3.getBlock().getR() == "C" || PG4.getBlock().getR() == "C"){
                if (PG3.getBlock().getBT() > 400|| PG3.getBlock().getBT() <100 || PG4.getBlock().getBT() > 400|| PG4.getBlock().getBT() < 100){
                    System.out.println("should print out a number Block runtime form 60 to 100 not");
                    System.out.println("Block RunTime: " +PG3.getBlock().getBT() + "   " + PG4.getBlock().getBT());
                    System.out.println(" ");
                }
            }
            if (PG2.getBlock().getR() != null){
                System.out.println("Process 2 should not have a block");
                System.out.println("blockType: " + PG2.getBlock().getR());
                System.out.println(" ");
            }
            if (PG1.getBlock().getR() == "C" || PG4.getBlock().getR() == "A"){
                System.out.println("Process 1 should not have RC and process 4 should not have RA");
                System.out.println(" ");
            }



        }
        System.out.println(" all done testing process Generator ");



    }
}
