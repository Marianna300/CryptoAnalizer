package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

import java.io.*;
import java.util.ArrayList;

import static ru.javarush.marianna300.cryptoanalizer.constants.Constants.ALPHABET;
import static ru.javarush.marianna300.cryptoanalizer.constants.Constants.ALPHABET_MAP;
//На входе - адрес зашифрованного файла
// адрес куда писать расшифрованный файл,
// сдвиг по алфавиту который использовался при шифровании (ключ).

//Для нее нужно знать сдвиг (ключ) и алфавит.
//Для каждого символа зашифрованного текста нужно
//- проверить что он есть в вашем алфавите. Если нет - вас ломают хакеры. Паникуйте (ну или верните ошибку).
//- найти его позицию в алфавите.
//- найти символ на позиции смещенной на заданный сдвиг (но только помните - вы не пытаетесь еще раз зашифровать шифр - поэтому
// сдвигаем в другую сторону).
//- заменить зашифрованный символ на расшифрованный//
//Вы будете этот код использовать в следующий под-задачах, поэтому выводить результат в поток.//
//Сохранить результат в файл.

public class Decrypt implements Action {

    @Override
    public Result execute(String[] parameters) {
        final String inputFileName = parameters[0];
        final String outputFileName = parameters[1];
        final int key = Integer.parseInt(parameters[2]);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            ArrayList<String> data = new ArrayList<>();
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                char[] chars = string.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    final Character tmp = Character.toUpperCase(chars[i]);
                    final Integer index = ALPHABET_MAP.get(tmp);
                    if (index == null) {
                        continue;
                    }
                    int shift;
                    if (key > 0) {
                        shift = (index - key) % ALPHABET.length();
                    } else shift = (index + key) % ALPHABET.length();
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
        return new Result("текст расшифрован", ResultCode.OK);
    }
}
