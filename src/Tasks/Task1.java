package Tasks;

import java.util.*;

public class Task1 {

    public static final ArrayList<String> OPEN_LETTERS = new ArrayList<>(Arrays.asList("{", "[", "("));
    public static final ArrayList<String> CLOSE_LETTERS = new ArrayList<>(Arrays.asList("}", "]", ")"));


    public static void main(String[] args) {  /// ()[({}())]
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expression = List.of(scanner.nextLine().split(" "));
        if (checkExpressionForStaples(expression))
            System.out.println("Все гуд, строка нормальная.");
        else
            System.out.println("Строка неправильная :(");
    }

    public static List<Integer> scanConsole() {
        List<Integer> expression = new ArrayList<>();
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expressionString = new ArrayList<>(List.of(scanner.nextLine().split(" ")));
        for (String item : expressionString) {
            expression.add(Integer.valueOf(item));
        }
        return expression;
    }
    public static List<String> scanConsoleString() {
        System.out.print("Введите строку: ");
        return new ArrayList<>(List.of(new Scanner(System.in).nextLine().split(" ")));
    }

    public static boolean checkExpressionForStaples(List<String> expression) {
        Stack<String> stack = new Stack<>();
        boolean result;
        for (String item : expression) {
            if (Task1.CLOSE_LETTERS.contains(item)) {
                if (item.equals("}") && stack.peek().equals("{"))
                    stack.pop();
                else if (item.equals(")") && stack.peek().equals("("))
                    stack.pop();
                else if (item.equals("]") && stack.peek().equals("["))
                    stack.pop();
                else
                    return false;
            } else if (Task1.OPEN_LETTERS.contains(item)) {
                stack.push(item);
            }
        }
        result = stack.empty();
        return result;
    }

}