package Task13;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task13 {
    private static final String IN_PATH = "src/Task13/input.txt";
    private static final String OUT_PATH = "src/Task13/out.txt";
    private HashMap<Integer, String> table = new HashMap<>();

    public static void main(String[] args) throws IOException {
        List<String> expression = readFile();
        Task13 task = new Task13();
        task.calculateHash(expression);
        writeInFile(task.table);
    }

    public void calculateHash(List<String> expression) {  /// считаем хэш-сумму для всех элементов
        for (String item : expression) {
            int sum = createHashSum(item); // считаем хэш-сумму для элемента
            while (!this.isPutInTable(item, sum)) {
                sum++;
            }
        }

    }

    private Integer createHashSum(String item) { // создаем хэш-сумму для элемента
        int sum = 0;
        for (Character letter : item.toCharArray())
            sum += letter;
        return sum;
    }

    private boolean isPutInTable(String item, int sum) { // проверяем на коллизии
        boolean result = false;
        if (!table.containsKey(sum)) {
            table.put(sum, item);
            result = true;
        }
        return result;
    }

    public static List<String> readFile() throws IOException {
        return new ArrayList<>(List.of(new BufferedReader(new FileReader(IN_PATH)).readLine().split(" ")));
    }

    public static void writeInFile(HashMap<Integer, String> expression) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_PATH));
        StringBuilder builder = new StringBuilder();
        for (Integer item : expression.keySet())
            builder.append(item).append(" - ").append(expression.get(item)).append("\n");

        writer.write(builder.toString());
        writer.close();
        System.out.println("Вывод в файл " + Task13.OUT_PATH + " выполнен!");
    }
}
