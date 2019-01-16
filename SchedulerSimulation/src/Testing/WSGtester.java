package Testing;
import Generators.WorksetGenerator;
import Schedulers.Report;

public class WSGtester {

    public static void main(String arg[]){
        WorksetGenerator Test = new WorksetGenerator();
        Report.ReportWorkSet(Test);
    }
}
