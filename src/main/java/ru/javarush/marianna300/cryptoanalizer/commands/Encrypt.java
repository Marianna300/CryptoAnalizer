package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.CaesarCipher;
import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

import java.io.*;
import java.util.ArrayList;

public class Encrypt implements Action {

    @Override
    public Result execute(String[] parameters) {
        final String inputFileName = parameters[0].trim();
        final String outputFileName = parameters[1].trim();
        final int key = Integer.parseInt(parameters[2].trim());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            final ArrayList<String> data = new ArrayList<>();
            final CaesarCipher cipher = new CaesarCipher();
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                data.add(cipher.cipher(string, key));
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
