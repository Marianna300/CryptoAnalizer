package ru.javarush.marianna300.cryptoanalizer.controllers;

import ru.javarush.marianna300.cryptoanalizer.AppException;
import ru.javarush.marianna300.cryptoanalizer.commands.Action;
import ru.javarush.marianna300.cryptoanalizer.commands.Decoder;
import ru.javarush.marianna300.cryptoanalizer.commands.Encoder;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String actionName){
        try {
            Actions value = Actions.valueOf(actionName.toUpperCase());
            return value.action;
        } catch (IllegalArgumentException e){
            throw new AppException("not found " + actionName, e);
        }
    }
}
