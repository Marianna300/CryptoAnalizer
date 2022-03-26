package ru.javarush.marianna300.cryptoanalizer;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;

import java.util.Scanner;

//Не забудьте проделать проверку того что а) файл оригинала по заданному адресу существует,
// и б) ключ от 0 и до (размер алфавита - 1) (или можете взять остаток от деления на размер алфавита).
//акже нужно, чтобы программа завершала работу по желанию пользователя (например, вводом слова “exit”).

public class Runner {
    public static void main(String[] args) {
        Application application = new Application();
        args = getArgs(args);
        Result result = application.run(args);
        System.out.println(result);
    }
    public static final String[][][] QUESTIONS = new String[][][] {
            {
                    {"ENCRYPT"},
                    {"Исходный файл (полный путь или имя файла)"},
                    {"Куда сохранить результат(полный путь или имя файла)"},
                    {"Веведите ключ"},
            },
            {
                    {"DECRYPT"},
                    {"Исходный файл (полный путь или имя файла)"},
                    {"Куда сохранить результат(полный путь или имя файла"},
                    {"Веведите ключ"},
            },
            {
                    {"BRUTFORCE"},
                    {"Исходный файл (полный путь или имя файла)"},
                    {"Куда сохранить результат(полный путь или имя файла"},
                    {"Файл-словарь (полный путь или имя файла)"}
            },
            {
                    {"ANALIZER"},
                    {"Исходный файл (полный путь или имя файла)"},
                    {"Куда сохранить результат(полный путь или имя файла"},
                    {"Файл-словарь (полный путь или имя файла)"}
            },
    };
    public static final String MODE = """
            Что вы хотите сделать?
            1. Зашифровать
            2. Расшифровать
            3. Брутфорс
            4. Статистический анализ
            """;

    public static final String INCORRECT = "Неправильный выбор";

    private static String[] getArgs(String[] args){
        if (args.length == 0){
            Scanner scanner = new Scanner(System.in);
            int mode = getMode(scanner);

            mode--;
            args = new String[QUESTIONS[mode].length];
            args[0] = QUESTIONS[mode][0][0];
            for (int i = 1; i < args.length ; i++) {
                String quest = QUESTIONS[mode][i][0];
                System.out.println(quest);
                String answer = scanner.nextLine();
                args[i] = answer.isEmpty() ? QUESTIONS[mode][i][1] : answer;

            }
        }
        return args;
    }


    private static int getMode(Scanner scanner){
        int mode;
        do {
            System.out.println(MODE);
            String input = scanner.nextLine();
            mode = switch (input){
                case "1" -> 1;
                case "2" -> 2;
                case "3" -> 3;
                case "4" -> 4;
                default -> {
                    System.out.println(INCORRECT);
                    yield -1;
                }
            };

        }while (mode < 0);
        return mode;
    }
}
