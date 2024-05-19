package SecondSemester.Lab11;

public class Lab11 {
    private static final int SIZE_ALPHABET = 256; // Размер алфавита ASCII
    private static int[][] transitionTable;

    private static void calulateTransTable(String subString) {
        int sizeSubString = subString.length();
        transitionTable = new int[SIZE_ALPHABET + 1][SIZE_ALPHABET];  // Инициализация таблицы переходов
        transitionTable[0][subString.charAt(0)] = 1;                  // начальное состояния
        int state = 0;                                                // храним текущее состояние
        for (int i = 1; i <= sizeSubString; i++) {                    // Заполнение таблицы переходов
            for (int j = 0; j < SIZE_ALPHABET; j++) {
                transitionTable[i][j] = transitionTable[state][j];
            }
            if (i < sizeSubString) {
                transitionTable[i][subString.charAt(i)] = i + 1;
                state = transitionTable[state][subString.charAt(i)];
            }
        }
    }

    public static int search(String subString, String text) {
        calulateTransTable(subString);
        int positionOfSubstring = -1;
        int state = 0;
        for (int i = 0; i < text.length(); i++) {                           // Поиск с использованием конечного автомата
            state = transitionTable[state][text.charAt(i)];
            if (state == subString.length()) {
                positionOfSubstring = i - subString.length() + 1;           // Элемент найден
                break;
            }
        }
        return positionOfSubstring; // возвращается или позиция подстроки или -1 в случае, если мы не нашли подстроку
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String subString = "ABABCABAB";
        int position = search(subString, text);
        if (position == -1) {
            System.out.println("Образец не найден");
        } else {
            System.out.println("Образец найден в позиции " + position);
        }
    }
}
