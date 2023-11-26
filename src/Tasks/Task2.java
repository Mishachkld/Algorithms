package Tasks;

import java.util.*;

import static Tasks.Task1.CLOSE_LETTERS;
import static Tasks.Task1.OPEN_LETTERS;

public class Task2 {

    public static final List<String> NUMBERS = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    public static final List<String> CALCULUS = new ArrayList<>(Arrays.asList("+", "-", "*", "/", ")", "("));
    public static HashMap<String, Integer> PRIORITY = new HashMap<>();
    private static final int HIGH_PRIORITY = 2;
    private static final int LOW_PRIORITY = 1;


    public static void main(String[] args) throws Exception {  /// 256+79898*(345/49)-15+5=
        // 5+5+(3/9)+5=
        // 2+7*(3/9)-5=
        new Task2().setPRIORITY();
        System.out.print("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        List<String> expression = List.of(scanner.nextLine().split(""));
        if (Task1.checkExpressionForStaples(expression)) {
            System.out.println(calculatePolska(calculateExpression(expression)));
        }

    }

    private static Stack<String> calculateExpression(List<String> expression) throws Exception {
        Stack<String> polskaKurwa = new Stack<>();
        Stack<String> calculus = new Stack<>();
        StringBuilder numberBuilder = new StringBuilder();
        for (String item : expression) {
            if (CALCULUS.contains(item)) {
                if (!numberBuilder.toString().isEmpty()) {
                    polskaKurwa.add(numberBuilder.toString());
                    numberBuilder = new StringBuilder();
                }
                if (item.equals("*") || item.equals("/")) {
                    if (calculus.isEmpty() || calculus.peek().equals("+") || calculus.peek().equals("-")
                            || calculus.peek().equals("("))
                        calculus.push(item);
                    else {
                        while (!calculus.isEmpty() && (calculus.peek().equals("*") || calculus.peek().equals("/")))
                            polskaKurwa.add(calculus.pop());
                        calculus.add(item);
                    }
                } else if (item.equals("+") || item.equals("-")) {
                    if (item.equals("-") && calculus.peek().equals("("))
                        polskaKurwa.add("0");
                    if (calculus.isEmpty())
                        calculus.add(item);
                    else {
                        while (!calculus.isEmpty() && PRIORITY.containsKey(calculus.peek())) {
                            polskaKurwa.add(calculus.pop());
                        }
                        calculus.add(item);
                    }
                } else if (OPEN_LETTERS.contains(item))
                    calculus.add(item);
                else if (CLOSE_LETTERS.contains(item)) {
                    while (!calculus.isEmpty() && !calculus.peek().equals("("))
                        polskaKurwa.add(calculus.pop());
                    calculus.pop();
                } else if (item.equals("=")) {
                    while (!calculus.isEmpty()) {
                        polskaKurwa.add(calculus.pop());
                    }
                }
            } else if (NUMBERS.contains(item)) {
                numberBuilder.append(item);
            } else if (item.equals("=")) {
                if (!numberBuilder.toString().isEmpty()) {
                    polskaKurwa.add(numberBuilder.toString());
                }
                while (!calculus.isEmpty()) {
                    polskaKurwa.add(calculus.pop());
                }
                break;
            } else throw new Exception("Symbol not found");

        }
        System.out.println(polskaKurwa);
        return polskaKurwa;
    }


    public static String calculatePolska(Stack<String> expression) throws Exception {
        Stack<Double> nums = new Stack<>();
        double item;
        for (String sItem : expression) {
            if (NUMBERS.contains(String.valueOf(sItem.toCharArray()[0]))) {
                item = Double.parseDouble(sItem);
                nums.add(item);
            } else {
                System.out.println(expression);
                System.out.println(nums);
                item = Double.parseDouble(makeSmthWithNumber(nums.pop(), nums.pop(), sItem));  /// (-5)+5=
                nums.add(item);
            }

        }
        return nums.pop().toString();
    }


    private static String makeSmthWithNumber(Double Ssecond, Double Sfirst, String move) throws Exception {
        double first = Sfirst;
        double second = Ssecond;
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
                else {
                    throw new Exception("Деление на 0!!1!!!1!");
                }
                break;
            default:
                result = first * second;
                break;
        }

        return String.valueOf(result);
    }

    //2+7*(3/9)-5=
    //n 2,7,3,9/*5-+
    //o
    private void setPRIORITY() {
        PRIORITY.put("+", 1);
        PRIORITY.put("-", 1);
        PRIORITY.put("*", 2);
        PRIORITY.put("/", 2);
    }
}