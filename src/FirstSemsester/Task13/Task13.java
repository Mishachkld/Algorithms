package FirstSemsester.Task13;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Task13 {
    private static final String IN_PATH = "src/FirstSemsester.Task13/input.txt";
    public static final String OUT_PATH = "src/FirstSemsester.Task13/out.txt";
    private HashMap<Integer, String> table = new HashMap<>();
   private List<String> values = new ArrayList<>();
    //private List<Integer> keysTable1 = new ArrayList<>();*/

    public static void main(String[] args) throws IOException {
        List<String> expression = readFile();
        Task13 task = new Task13(expression.size());
        task.calculateHash(expression);
        writeInFile(task.values);
    }

    public Task13(int size){
        for (int i = 0; i < size; i++){
            values.add(null);
        }

    }

    public void calculateHash(List<String> expression) {  /// считаем хэш-сумму для всех элементов
        for (String item : expression) {
            int sum = createHashSum(item) ; // считаем хэш-сумму для элемента
            while (!this.isPutInTable(item, sum, expression.size()))
                sum++;
        }

    }


    public static Integer createHashSum(String item) { // создаем хэш-сумму для элемента
        int sum = 0;
        for (Character letter : item.toCharArray())
            sum += letter;
        return sum;
    }
    

    private boolean isPutInTable(String item, int sum, int size) { // проверяем на коллизии
        boolean result = false;
        if (!table.containsKey(sum) && (values.get(sum % size) == null)) { //|| !keysTable1.contains(sum))
            table.put(sum, item);
            result = true;

            values.set(sum % size, item);
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
    public static void writeInFile(List<String> values) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_PATH));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            builder.append(i).append(" - ").append(values.get(i)).append("\n");
        }

        writer.write(builder.toString());
        writer.close();
        System.out.println("Вывод в файл " + OUT_PATH + " выполнен!");
    }
}
