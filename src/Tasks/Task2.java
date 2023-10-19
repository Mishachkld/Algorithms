package Tasks;

import java.util.*;

public class Task2 {

    public static final List<String> NUMBERS = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    public static final List<String> CALCULUS = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
    public static HashMap<String, Integer> PRIORITY = new HashMap<>();


    public static void main(String[] args) {  /// 2+7*(3/9)-5=   256+79898*(345/49)-15+5=
        new Task2().setPRIORITY();
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expression = List.of(scanner.nextLine().split(""));
        if (Task1.checkExpressionForStaples(expression)) {
            try {
                calculateExpression(expression);
            } catch (Exception e) {
                System.out.println("Делоение на 0 или еще какая-то проблема :(");
            }
        }

    }

    private static void calculateExpression(List<String> expression) throws Exception {
        Stack<String> numbers = new Stack<>();
        Stack<String> calculus = new Stack<>();
        for (String item : expression) {
            if (!item.equals("=")) {
                if (PRIORITY.containsKey(item)) {
                    if (!calculus.isEmpty() && ( PRIORITY.get(item) >= PRIORITY.get(calculus.peek()))) {
                        calculus.push(makeSmthWithnumber(Double.parseDouble(numbers.pop()), Double.parseDouble(numbers.pop()), item));
                    } else
                        calculus.push(item);
                } else if (NUMBERS.contains(item)) {
                    numbers.push(item);
                } else if (Task1.CLOSE_LETTERS.contains(item)) {
                    numbers.push(makeSmthWithnumber(Double.parseDouble(numbers.pop()), Double.parseDouble(numbers.pop()), calculus.pop()));
                } else if (Task1.OPEN_LETTERS.contains(item)) {
                    calculus.push(item);
                }

            }
        }

        System.out.println(numbers.peek());
    }

    private static String makeSmthWithnumber(double second, double first, String move) throws Exception {
        double result;
        switch (move) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "/":
                if (second != 0)
                    result = first / second;
                else{
                    throw new Exception("Деление на 0!!1!!!1!");
                }
                break;
            default:
                result = first * second;
                break;
        }

        return String.valueOf(result);
    }

    private void setPRIORITY() {
        PRIORITY.put("+", 1);
        PRIORITY.put("-", 1);
        PRIORITY.put("*", 2);
        PRIORITY.put("/", 2);
    }

    /*if (!item.equals("="))
            if (NUMBERS.contains(item))
            stringNumber.append(item);
                else {
        if (!stringNumber.toString().equals(""))
            expressionNormal.add(stringNumber.toString());
        if (CALCULUS.contains(item))
            move.add(item);
        stringNumber = new StringBuilder();
    }
            else {
        if (!stringNumber.toString().equals(""))
            expressionNormal.add(stringNumber.toString());
        break; /// ну или считаем
    }*/
}