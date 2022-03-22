package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

import java.util.ArrayList;

import static ru.javarush.marianna300.cryptoanalizer.constans.Constans.ALPHABET;
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

    @Override
    public Result execute(String[] parameters) {
        return new Result("текст расшифрован", ResultCode.OK);
    }
}
