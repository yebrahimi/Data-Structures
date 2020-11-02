import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lecture11 {
  // use main method to test code
    public static void main(String[] args) {
      AList<Integer> AL = new AList<>();
      AL.addLast(5);
      AL.addLast(10);
      AL.addLast(15);

      List<Integer> L = new ArrayList<>();
      L.add(5);
      L.add(10);
      L.add(15);
      System.out.println(L);
      // [5, 10, 15]

      Set<String> S = new HashSet<>();
      S.add("Tokyo");
      S.add("Beijing");
      S.add("Lagos");
      S.add("Sao Paulo");
      System.out.println(S.contains("Tokyo"));
      // true
      System.out.println(S);
      S.add("Tokyo");
      System.out.println(S);
      // does not add repeated items
    }
}
