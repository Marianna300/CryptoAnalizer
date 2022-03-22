package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

import java.util.ArrayList;

import static ru.javarush.marianna300.cryptoanalizer.constans.Constans.ALPHABET;

//  На входе - адрес зашифрованного файла
//             адрес файла с текстом который является примером текста что был зашифрован
//             адрес файла который должен содержать расшифрованный текст.

//Вы можете использовать код который написали для расшифровки при известном ключе, подставляя все возможные значения ключа.
//Но как понять получилось ли расшифровать? Используйте пример текста (репрезентативный текст автора или в том же стиле); можете
// составить словарь слов и составить метрику основанную на том сколько слов совпало и какой они длины; или иную метрику которая
// изучает длину слов и предложений, или посмотреть какие буквы чаще всего предшествуют каким буквам или словарь наиболее частых
// начал слова (3 буквы), можно вообще не использовать никаких репрезентативных файлов и проверить правильность пунктуации и
// пробелов; Вариант с наилучшими результатами сохраните в файл вывода.

public class Brutforce implements Action {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
        ArrayList<String> inputData = new ArrayList<>();
        ArrayList<String> outputData = new ArrayList<>();

        while (bufferedReader.ready()) {
            inputData.add(bufferedReader.readLine());
        }

        for (int key = 1; key < ALPHABET.length(); key++) {
            for (String string : inputData) {
                char[] chars = string.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int index = ALPHABET.indexOf(Character.toLowerCase(chars[i]));
                    if (index == -1) {
                        continue;
                    }

                    int shift = (index - key) % ALPHABET.length();
                    if (shift < 0) shift = shift + ALPHABET.length();
                    chars[i] = ALPHABET.charAt(shift);
                }
                outputData.add(new String(chars));
            }

            boolean isCorrectLength = true;
            boolean isCorrectPunt = true;
            int notCorrectPunch = 0;
            int countWords = 0;

            for (String string : outputData) {
                if (string.matches("(.*)[a-zA-Z](.*)")) {
                    //if (string.matches("(.*)[а-яА-Я](.*)")) {  не работает
                    continue;
                }

                String[] stringsLength = string.split(" ");
                for (String s : stringsLength) {
                    if (s.length() > 25) {
                        isCorrectLength = false;
                        break;
                    }
                }

                String[] stringsPunt = string.split("[?!.]");
                for (String s : stringsPunt) {
                    if (stringsPunt.length == 1 | s.length() == 1 | s.isEmpty()) {
                        break;
                    }
                    if (!s.startsWith(" ")) {
                        notCorrectPunch++;
                    }
                }
            }

            for (String string : outputData) {
                String[] words = string.split(" ");
                countWords += words.length;
            }

            isCorrectPunt = notCorrectPunch <= countWords / 10;
            //isCorrectPunt = notCorrectPunch > countWords / 10 ? false : true;

            if (isCorrectLength & isCorrectPunt) {
                System.out.println("ПОДОБРАННЫЙ КЛЮЧ >>" + key);
                break;
            }
            outputData.clear();
        }

        for (String string : outputData) {
            bufferedWriter.write(string + "\n");
        }
    } catch (IOException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
    }
    @Override
    public Result execute(String[] parameters) {
        return new Result("текст расшифрован", ResultCode.OK);
    }
}
