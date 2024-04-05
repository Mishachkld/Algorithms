package ReadAndWriteFuncrions;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Helper {

    public static void writeInFile(HashMap<Integer, List<String>> expression, String Path ) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Path));
        StringBuilder builder = new StringBuilder();
        for (Integer key : expression.keySet()) {
            builder.append(key).append(" - ");
            for (String item : expression.get(key))
                builder.append(item).append(" ");
            builder.append("\n");
        }

        writer.write(builder.toString());
        writer.close();
        System.out.println("Вывод в файл " + Path + " выполнен!");

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
        return new ArrayList<>(List.of(new Scanner(System.in).nextLine().split("")));
    }

    public static List<String> readFile(String path) throws IOException {
        return new ArrayList<>(List.of(new BufferedReader(new FileReader(path)).readLine().split(" ")));
    }
}
