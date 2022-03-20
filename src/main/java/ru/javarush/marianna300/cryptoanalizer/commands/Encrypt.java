package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;
import ru.javarush.marianna300.cryptoanalizer.enpty.ResultCode;

import java.io.File;
import java.util.HashMap;

public class Encrypt implements Action{

    @Override
    public Result execute(String[] parameters) {


        return new Result("текст зашифрован", ResultCode.OK);
    }
}
