package Tasks;

import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

public class Task3 {
    // 3^k*5^l*7^m = Xi
    public static void main(String[] args) {
        System.out.println("Введите число: ");
        /*Scanner scanner = new Scanner(System.in);
       int x = Integer.parseInt(scanner.nextLine());*/
        long x = 10_000;
        int counter = 1;
        for (int k = 0; k < Math.pow(x, (double) 1/3) + 1; k++)  /// идем до корня
            for (int l = 0; l < Math.pow(x, (double) 1/5) + 1; l++)
                for (int m = 0; m < Math.pow(x, (double) 1/7) + 1; m++) {
                    double expression = Math.pow(3, k) * Math.pow(5, l) * Math.pow(7, m);
                    if (expression <= x)
                        System.out.println(counter++ + ". " + k + " " + l + " " + m + " : " + expression);
                }

    }
}
