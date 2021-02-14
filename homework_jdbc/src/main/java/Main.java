import java.util.List;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        List<Integer> listFib = calc.fibonachi(19);
        listFib.forEach(System.out::println);
    }
}
