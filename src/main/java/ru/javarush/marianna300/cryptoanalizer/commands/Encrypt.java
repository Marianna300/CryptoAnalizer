package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static ru.javarush.marianna300.cryptoanalizer.constans.Constans.ALPHABET;
//На входе - адрес файла
// адрес куда писать зашифрованный файл,
// сдвиг по алфавиту который использовался при шифровании (ключ).

// Для нее нужно знать сдвиг (ключ) и алфавит.
//  Для каждого символа оригинального текста нужно -
//  - проверить что он есть в вашем алфавите. Если его нет, пропускаем этот символ.
//   - найти его позицию в алфавите. Подумайте, какую структуру данных нужно использовать чтобы ускорить этот процесс
//   (раз в 15), ведь необязательно же сканировать всю библиотеку в поисках книги на букву Ы (Ладно, П).
//   - найти символ на позиции смещенной на заданный сдвиг. И помним что в примере с игрушкой Y стала А (и не улетела в космос).
//   Как это гарантировать? (можно сделать (позиция буквы + сдвиг) %( размер алфавита). Процент - оператор получения остатка от деления).
//   - заменить оригинальный символ на зашифрованный
//   Сохранить результат в файл (чтобы избежать плохого пользователя который попробует зафигачить тебе .bash_profile или hosts
//   валидируй имя файла вывода!)

public class Encrypt implements Action{

    @Override
    public Result execute(String[] parameters) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            ArrayList<String> data = new ArrayList<>();

            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                char[] chars = string.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int index = ALPHABET.indexOf(Character.toLowerCase(chars[i]));
                    if (index == -1) {
                        continue;
                    }
                    int shift = (index + key) % ALPHABET.length();
                    if (shift < 0) shift = shift + ALPHABET.length();
                    chars[i] = ALPHABET.charAt(shift);
                }
                data.add(new String(chars));
            }

            for (String string : data) {
                bufferedWriter.write(string + "\n");
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return new Result("текст зашифрован", ResultCode.OK);
    }
}
