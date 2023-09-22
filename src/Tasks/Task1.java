package Tasks;

import java.util.*;

public class Task1 {

    private static final ArrayList<String> OPEN_LETTERS = new ArrayList<>(Arrays.asList("{", "[", "("));
    private static final ArrayList<String> CLOSE_LETTERS = new ArrayList<>(Arrays.asList("}", "]", ")"));


    public static void main(String[] args) {  /// ()[({}())]
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expression = List.of(scanner.nextLine().split(""));
        if (checkExpressionForStaples(expression))
            System.out.println("Все гуд, строка нормальная.");
        else
            System.out.println("Строка неправильная :(");
    }

    public static boolean checkExpressionForStaples(List<String> expression){
        Stack<String> stack = new Stack<>();
        boolean result;
        for (String item : expression) {
            if (Task1.CLOSE_LETTERS.contains(item)) {
                if (item.equals("}") && stack.peek().equals("{"))
                    stack.pop();
                if (item.equals(")") && stack.peek().equals("("))
                    stack.pop();
                if (item.equals("]") && stack.peek().equals("["))
                    stack.pop();
            } else if (Task1.OPEN_LETTERS.contains(item)) {
                stack.push(item);
            }
        }
        result = stack.empty();
        return result;
    }

}