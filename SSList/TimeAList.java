import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // Initialize Lists
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        TimeAList a = new TimeAList();

        for (int i = 0; i < 8; i++) {
            AList<Integer> list = new AList<>();
            Stopwatch sw = new Stopwatch();
            Ns.add((int) (1000 * Math.pow(2, i)));

            // addLast Operation
            int j = 0;
            while(j < Ns.get(i)) {
                list.addLast(j);
                j++;
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
            opCounts.add(j);
        }
        a.printTimingTable(Ns, times, opCounts);
    }
}
