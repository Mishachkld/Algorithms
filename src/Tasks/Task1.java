package Tasks;

import java.util.*;

public class Task1 {

    private static ArrayList<String> openLetter = new ArrayList<>(Arrays.asList("{", "[", "("));
    private static ArrayList<String> closeLetter = new ArrayList<>(Arrays.asList("}", "]", ")"));


    public static void main(String[] args) {  /// ()[({}())]
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        Task1 task = new Task1();
        List<String> expression = List.of(scanner.nextLine().split(""));

        if (task.checkExpression(expression))
            System.out.println("Все гуд, строка нормальная.");
        else
            System.out.println("Не повезло, строка неправильная :(");


    }

    public boolean checkOpen(String letter) {
        return openLetter.contains(letter);
    }

    public boolean checkClose(String letter) {
        return closeLetter.contains(letter);
    }


    public boolean checkExpression(List<String> expression){
        Stack<String> stack = new Stack<>();
        boolean result;
        for (String item : expression) {
            if (Task1.closeLetter.contains(item)) {
                if (item.equals("}") && stack.peek().equals("{"))
                    stack.pop();
                if (item.equals(")") && stack.peek().equals("("))
                    stack.pop();
                if (item.equals("]") && stack.peek().equals("["))
                    stack.pop();
            } else if (Task1.openLetter.contains(item)) {
                stack.push(item);
            } else {
                System.out.println("Ошибка :(");
                break;
            }
        }
        result = stack.empty();
        return result;
    }

}