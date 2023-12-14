package Task12;

import Tasks.Task1;
import Tasks.Task11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task12 {
    private static final String PATH = "src/Task12";
    private static final int COUNT_OF_FILES = 2;

    public static void main(String[] args) throws IOException { // 15 61616 4185 41841664 84 8 48 9
        // List<Integer> expression = Task1.scanConsole();
        multiphaseSort("/input.txt", 5);
    }

    private static void createFiles(int count) throws IOException {
        for (int i = 1; i < count + 1; i++) {
            File file = new File(PATH + "/" + i + ".txt");
            file.createNewFile();
        }
    }

    public static void multiphaseSort(String inputPath, int size) throws IOException {
        new File(PATH + "/" + 0 + ".txt").createNewFile();
        List<String> strArray;
        List<Integer> result = new ArrayList<>();
        String path = PATH + "/" + "ntf" + ".txt";
       /* StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(PATH + inputPath));
                size = strArray.size() / size;
*/
        int counter = 0;
        BufferedReader reader = new BufferedReader(new FileReader(PATH + inputPath));
        strArray = new ArrayList<>(List.of(reader.readLine().split(" ")));  /// прочитали с файла числа
        System.out.println(strArray);
        reader.close();
        //createFiles(size);
        List<Integer> array = new ArrayList<>();
        for (String item : strArray)
            result.add(Integer.valueOf(item));
        for (int i = 0; i < result.size(); i++) {
            path = PATH + "/" + counter + ".txt";
            if (array.size() >= size) {
                new File(path).createNewFile();
                StringBuilder builder = new StringBuilder();
                array.add(result.get(i));
                array = Task11.quickSort(array);
                for (Integer item : array) {
                    builder.append(item).append(" ");
                }
                System.out.println(builder);
                array.clear();
                counter++;
                writeInFile(path, builder.toString());
            } else {
                array.add(result.get(i));
            }
        }
        if (!array.isEmpty()) {
            path = PATH + "/" + counter + ".txt";
            new File(path).createNewFile();
            StringBuilder builder = new StringBuilder();
            array = Task11.quickSort(array);
            for (Integer item : array) {
                builder.append(item).append(" ");
            }
            System.out.println(builder);
            writeInFile(path, builder.toString());
        }

       /* strArray = new ArrayList<>(List.of(reader.readLine().split(" ")));  /// прочитали с файла числа
        reader.close();
        String path = PATH + "/" + 1 + ".txt";
        for (String item : strArray)
            result.add(Integer.valueOf(item));

        for (Integer item : Task11.quickSort(new ArrayList<>(result.subList(0, strArray.size() / 2)))) {
            builder.append(item).append(" ");
        }
        writeInFile(path, builder.toString());
        result.subList(0, strArray.size() / 2).clear();
        builder = new StringBuilder();
        for (Integer item : Task11.quickSort(result)) {
            builder.append(item).append(" ");
        }
        path = PATH + "/" + 2 + ".txt";
        writeInFile(path, builder.toString());*/

        boolean flag = true;
        int minItem = Integer.MAX_VALUE;
        int firstItem;
        String pathRemoveFile = PATH + "/" + "notFoundTXT" + ".txt";
        BufferedWriter outWriter = new BufferedWriter(new FileWriter(PATH + "/output.txt"));
        while (flag) {
            flag = false;
            List<String> tempArray = new ArrayList<>();
            for (int i = 0; i < counter + 1; i++) {
                path = PATH + "/" + (i) + ".txt";
                reader = new BufferedReader(new FileReader(path));
                String item;
                if ((item = reader.readLine()) != null)
                    strArray = new ArrayList<>(List.of(item.split(" ")));
                else continue;
                reader.close();
                firstItem = Integer.parseInt(strArray.get(0));
                if (firstItem <= minItem) {
                    flag = true;
                    minItem = firstItem;
                    pathRemoveFile = path;
                    tempArray = new ArrayList<>(strArray);
                }
            }
            if (minItem != Integer.MAX_VALUE) {
                outWriter.write(String.valueOf(minItem));
                outWriter.write(" ");
                outWriter.flush();
            }
            if (!tempArray.isEmpty()) {
                tempArray.remove(0);
                System.out.println(tempArray);
                writeInFile(pathRemoveFile, createString(tempArray));
            }
            minItem = Integer.MAX_VALUE;

        }
        outWriter.close();
    }


    private static void writeInFile(String path, String expression) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(expression);
        writer.close();
    }

    private static String createString(List<String> expressions) {
        StringBuilder builder = new StringBuilder();
        for (String item : expressions)
            builder.append(item).append(" ");
        return builder.toString();
    }
}
