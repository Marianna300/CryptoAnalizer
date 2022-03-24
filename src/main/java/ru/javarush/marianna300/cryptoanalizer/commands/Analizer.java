package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

//  На входе - адрес зашифрованного файла
//             адрес файла с текстом который является примером текста что был зашифрован (или частота использования букв)
//List <Character> statList = new ArrayList (ArrayList.asList('о','е','а','и','н','т','с','р','в','л','к','м','д','п','у','я','ы','ь',
//        'г','з','б','ч','й','х','ж','ш','ю','ц','щ','э','ф','ъ','ё'));
//             адрес файла который должен содержать расшифрованный текст.

//Используйте пример текста (репрезентативный текст автора или в том же стиле) и составьте статистику букв (например, как часто
// встречается на каждые 1000 символов); (Кстати. Легко взломать шифр и без такого файла и анализа - попробуйте угадать пробел -
// это наверняка наиболее часто встречающийся символ в обычном тексте.)
//Далее составьте такую же статистику для зашифрованного текста. Учтите, просто считать символы не достаточно так как тексты могут
// быть разной длины!
//Далее посчитайте отклонение для каждого возможного сдвига зашифрованной статистики относительно репрезентативной (можно для этого
// использовать сумму квадратов отклонения или дот-произведение векторов). Найдите сдвиг дающий минимальное отклонение и расшифруйте
// используя этот сдвиг.

public class Analizer implements Action{

    @Override
    public Result execute(String[] parameters) {
        final String inputFileName = parameters[0];
        final String outputFileName = parameters[1];
        final String inputStatFileName = parameters[2];

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFileName))){

        List<String> inputData = Files.readAllLines(Path.of(inputFileName));
        List<String> statData = Files.readAllLines(Path.of(inputStatFileName));
        ArrayList<String> outputData = new ArrayList<>();

        HashMap<Character, Integer> inputChars = new HashMap<>();
        HashMap<Character, Integer> statChars = new HashMap<>();

        for (String string : inputData) {
            char[] chars = string.toLowerCase().toCharArray();
            for (char ch : chars) {
                if (inputChars.get(ch) == null) {
                    inputChars.put(ch, 1);
                } else inputChars.put(ch, inputChars.get(ch) + 1);

            }
        }

        for (String string : statData) {
            char[] chars = string.toLowerCase().toCharArray();
            for (char ch : chars) {
                if (statChars.get(ch) == null) {
                    statChars.put(ch, 1);
                } else statChars.put(ch, statChars.get(ch) + 1);
            }
        }

        ArrayList<Map.Entry<Character, Integer>> inputList = new ArrayList(inputChars.entrySet());
        inputList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (int) (o2.getValue() - o1.getValue());
            }
        });
        System.out.println(inputList);

        ArrayList<Map.Entry<Character, Integer>> statList = new ArrayList<>(statChars.entrySet());
        statList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (int) (o2.getValue() - o1.getValue());
            }
        });
        System.out.println("ДАННЫЕ СТАТИСТИЧЕСКОГО АНАЛИЗА" + statList);

        HashMap<Character, Character> totalMap = new HashMap<>();

        for (int i = 0; i < inputList.size(); i++) {
            totalMap.put(inputList.get(i).getKey(), statList.get(i).getKey());
        }

        for (String string : inputData) {
            char[] chars = string.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (totalMap.containsKey(chars[i])) {
                    chars[i] = totalMap.get(chars[i]);
                }
            }
            outputData.add(new String(chars));
        }

        for (String string : outputData) {
            writer.write(string + "\n");
        }
    } catch (IOException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
    }


        return new Result("текст расшифрован", ResultCode.OK);
    }
}
