import java.util.HashMap;
import java.util.Map;

public class JitMain {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for(int i = 0; i< 100_000; i++){
            map.put(i, "value"+i);
        }
    }
}
