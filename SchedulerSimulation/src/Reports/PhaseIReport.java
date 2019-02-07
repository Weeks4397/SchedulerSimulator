package Reports;
import Generators.WorksetGenerator;
import Reports.WorksetReport;

public class PhaseIReport {

    public static void main(String arg[]){
        WorksetGenerator Test = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test);

        WorksetGenerator Test2 = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test2);
    }
}
