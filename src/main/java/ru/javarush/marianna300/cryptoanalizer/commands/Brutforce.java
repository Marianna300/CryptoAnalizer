package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;


public class Brutforce implements Action {

    @Override
    public Result execute(String[] parameters) {
        final String inputFileName = parameters[0];
        final String outputFileName = parameters[1];



        return new Result("the text is decoded", ResultCode.OK);
    }
}
