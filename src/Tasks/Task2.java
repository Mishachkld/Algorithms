package Tasks;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Task2 {

    public static void main(String[] args) {  /// 2+7*(3/9)-5=
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expression = List.of(scanner.nextLine().split(""));

    }

    private void calculateExpression(List<String> expression) {
        Stack<String> stack = new Stack<>();
        for (String item : expression) {

        }
    }

}