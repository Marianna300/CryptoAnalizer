package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

public class Decrypt implements Action {

    @Override
    public Result execute(String[] parameters) {
        return new Result("decode all rigth", ResultCode.OK);
    }
}
