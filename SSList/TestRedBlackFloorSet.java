import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        RedBlackFloorSet t = new RedBlackFloorSet();
        AListFloorSet a = new AListFloorSet();
        int i = 1000000;
        while (i > 0) {
            double rand = (Math.random() - 0.5) * 10000;
            t.add(rand);
            a.add(rand);
            i--;
        }
        int j = 100000;
        while (j > 0) {
            double key = Math.random() * 10000;
            assertEquals(a.floor(key), t.floor(key));
            j--;
        }
    }
}
