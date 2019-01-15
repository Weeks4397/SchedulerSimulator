package Processes;
import Generators.ProccessGenator;

public class ProcessII extends Process{

    // processGenerator object for type 2 processes
    public ProccessGenator PG2 = new ProccessGenator(2);

    //Constructor for a type 2 process
    public ProcessII() {
        super();
        this.Type = 2;
        this.RunTime = PG2.getprocessRunTime();
        this.NextBlockInstant = ProccessGenator.MAXINT;
        this.NextBlockResource = null;
        this.NextBlockTime = 0;
        this.genBlockRecord();

    }

    //generate the block record, processes of type 2 never block
    public void genBlockRecord(){
            this.BlockRecord = null;
            this.CurrentListIndex = null;
            this.MaxListIndex = null;
    }
}
