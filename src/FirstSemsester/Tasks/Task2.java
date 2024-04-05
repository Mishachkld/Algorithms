package FirstSemsester.Tasks;

import java.util.*;

import static FirstSemsester.Tasks.Task1.CLOSE_LETTERS;
import static FirstSemsester.Tasks.Task1.OPEN_LETTERS;

public class Task2 {

    public static final List<String> NUMBERS = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    public static final List<String> CALCULUS = new ArrayList<>(Arrays.asList("+", "-", "*", "/", ")", "("));
    public static HashMap<String, Integer> PRIORITY = new HashMap<>();


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
        Stack<String> polskaNotation = new Stack<>();
        Stack<String> calculus = new Stack<>();
        StringBuilder numberBuilder = new StringBuilder();
        for (String item : expression) {
            if (CALCULUS.contains(item)) { /// если у нас попался какой-то из символов, содержащийся в CALCULUS
                if (!numberBuilder.toString().isEmpty()) {
                    polskaNotation.add(numberBuilder.toString());
                    numberBuilder = new StringBuilder();
                }
                if (item.equals("*") || item.equals("/")) { /// если это операции с повышенным приоритетом
                    if (calculus.isEmpty() || calculus.peek().equals("+") || calculus.peek().equals("-")
                            || calculus.peek().equals("("))
                        calculus.add(item);
                    else { // если оператор того же приоритета или ')', то выполняем все действия с оператором того же приоритета
                        while (!calculus.isEmpty() && (calculus.peek().equals("*") || calculus.peek().equals("/")))
                            polskaNotation.add(calculus.pop());
                        calculus.add(item);
                    }
                } else if (item.equals("+") || item.equals("-")) {/// если это операции с низким приоритетом
                    if (item.equals("-") && expression.get(expression.indexOf(item) - 1).equals("("))
                        polskaNotation.add("0"); /// добавляем ноль для чисел вида (-5)
                    if (calculus.isEmpty())
                        calculus.add(item); /// если массив пустой, то прост добавляем символ
                    else {
                        while (!calculus.isEmpty() && PRIORITY.containsKey(calculus.peek()))
                            polskaNotation.add(calculus.pop());
                        calculus.add(item);
                    }
                } else if (OPEN_LETTERS.contains(item)) // если это открывающияся скобка то просто кидаем ее в символы
                    calculus.add(item);
                else if (CLOSE_LETTERS.contains(item)) { // если это закрывающаяся скобка, то выполняем все действия до открывающеся и удаляем '('
                    while (!calculus.isEmpty() && !calculus.peek().equals("("))
                        polskaNotation.add(calculus.pop());
                    calculus.pop();
                } else if (item.equals("=")) {
                    while (!calculus.isEmpty()) {
                        polskaNotation.add(calculus.pop());
                    }
                }
            } else if (NUMBERS.contains(item))
                numberBuilder.append(item);
            else if (item.equals("=")) {
                if (!numberBuilder.toString().isEmpty())
                    polskaNotation.add(numberBuilder.toString());
                while (!calculus.isEmpty())
                    polskaNotation.add(calculus.pop());
                break;
            } else throw new Exception("Symbol not found");

        }
        return polskaNotation;
    }


    public static String calculatePolska(Stack<String> expression) throws Exception {
        Stack<Double> nums = new Stack<>();
        double item;
        for (String sItem : expression) {
            if (NUMBERS.contains(String.valueOf(sItem.toCharArray()[0]))) {
                item = Double.parseDouble(sItem);
                nums.add(item);
            } else {
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