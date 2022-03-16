package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

public class Encoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        return new Result("encode all rigth", ResultCode.OK);
    }
}
