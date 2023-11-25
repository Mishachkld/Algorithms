package Task14;

import Task13.Task13;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import static Task13.Task13.OUT_PATH;
import static Task13.Task13.createHashSum;

public class Task14 {

    private HashMap<Integer, List<String>> table = new HashMap<>();


    public static void main(String[] args) throws IOException {
        Task14 task = new Task14();
        task.calculateHash(Task13.readFile());
        writeInFile(task.table);
    }


    public void calculateHash(List<String> expression) {  /// считаем хэш-сумму для всех элементов
        for (String item : expression) {
            int sum = createHashSum(item) % expression.size() ; // считаем хэш-сумму для элемента
            addElement(sum, item);
        }

    }

    public void addElement(int hashKey, String value){
        if (this.table.containsKey(hashKey))
            table.get(hashKey).add(value);
        else{
            table.put(hashKey, new ArrayList<>());
            table.get(hashKey).add(value);
        }


    }

    public static void writeInFile(HashMap<Integer, List<String>> expression ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_PATH));
        StringBuilder builder = new StringBuilder();
        for (Integer key : expression.keySet()) {
            builder.append(key).append(" - ");
            for (String item : expression.get(key))
                builder.append(item).append(" ");
            builder.append("\n");
        }

        writer.write(builder.toString());
        writer.close();
        System.out.println("Вывод в файл " + OUT_PATH + " выполнен!");

    }

}
