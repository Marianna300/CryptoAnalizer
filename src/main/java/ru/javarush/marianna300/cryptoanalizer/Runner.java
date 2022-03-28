package ru.javarush.marianna300.cryptoanalizer;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;

import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {
        Application application = new Application();
        args = getArgs(args);
        Result result = application.run(args);
        System.out.println(result);
    }
    public static final String[][][] QUESTIONS = new String[][][] {
            {
                    {"Encrypt"},
                    {"File to encrypt"},
                    {"Choose the directory to save the file"},
                    {"Enter the key"},
            },
            {
                    {"Decrypt"},
                    {"File to decrypt"},
                    {"Choose the directory to save the file"},
                    {"Enter the key"},
            },
            {
                    {"Bruteforce"},
                    {"File to bruteforce"},
                    {"Choose the directory to save the file"},
                    {"Dictionary file"}
            },
            {
                    {"Analyzer"},
                    {"File to analyzer"},
                    {"Choose the directory to save the file"}
            },
    };
    public static final String MODE = """
            What are you gonna do?
            1. Encrypt
            2. Decipher the text
            3. Hack with bruteforce
            4. Hack with statistical analysis
            """;

    public static final String INCORRECT = "incorrect data entered";

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
