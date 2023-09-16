package Tasks;

import java.util.*;

public class Task2 {

    public static final List<String> NUMBERS = new ArrayList<>(List.of(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
    public static final List<String> CALCULUS = new ArrayList<>(List.of(new String[]{"+", "-", "*", "/"}));

    public static void main(String[] args) {  /// 2+7*(3/9)-5=
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expression = List.of(scanner.nextLine().split(""));
        if (Task1.checkExpressionForStaples(expression))  /// смотри Task1
            calculateExpression(expression);

    }

    private static void calculateExpression(List<String> expression) {
        List<String> expressionNormal = new ArrayList<>();
        Stack<String> move = new Stack<>();
        StringBuilder stringNumber = new StringBuilder();
        for (String item : expression) {
            if (NUMBERS.contains(item))
                stringNumber.append(item);
            else {
                if (!stringNumber.toString().equals(""))
                    expressionNormal.add(stringNumber.toString());
                stringNumber = new StringBuilder();
                move.add(item);
            }
        }
        System.out.println(expressionNormal + move.toString());
    }

}