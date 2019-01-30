package Testing;
import Generators.WorksetGenerator;
import Reports.WorksetReport;

public class WSGtester {

    public static void main(String arg[]){
        WorksetGenerator Test = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test);
    }
}
