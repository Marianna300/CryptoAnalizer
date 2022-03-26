package ru.javarush.marianna300.cryptoanalizer.controllers;

import ru.javarush.marianna300.cryptoanalizer.commands.Action;
import ru.javarush.marianna300.cryptoanalizer.enpty.Result;

public class MainController {
    public Result doAction(String actionName, String[] parameters){
        Action action = Actions.find(actionName);
        return ((Action) action).execute(parameters);
    }
}
