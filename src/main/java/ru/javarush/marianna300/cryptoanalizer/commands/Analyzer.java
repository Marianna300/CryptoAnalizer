package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.CaesarCipher;
import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

import java.io.*;


public class Analyzer implements Action {

    @Override
    public Result execute(String[] parameters) {
        final String inputFileName = parameters[0].trim();
        final String outputFileName = parameters[1].trim();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            final CaesarCipher cipher = new CaesarCipher();
            final StringBuilder sb = new StringBuilder();
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                sb.append(string);
            }
            final String message = sb.toString();
            final int key = cipher.breakCipher(message);
            final String result = cipher.decipher(message, key);
            bufferedWriter.write(result + "\n");
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return new Result("the text is decoded", ResultCode.OK);
    }
}
