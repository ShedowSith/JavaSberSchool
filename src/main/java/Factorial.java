import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        File file = new File ("numbers.txt");
        try(Scanner sc = new Scanner(file)) {
            while (sc.hasNext()){
                int number = sc.nextInt();
                new Thread(new CalculateFactorial(number)).start();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class CalculateFactorial implements Runnable {
    private int number;

    public CalculateFactorial(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(String.format("Факториал числа %d = %d", number, factorial(number)));
    }
    private BigInteger factorial(int number){
        if (number == 0) return BigInteger.valueOf(1);
        return BigInteger.valueOf(number).multiply(factorial(--number));
    }
}
