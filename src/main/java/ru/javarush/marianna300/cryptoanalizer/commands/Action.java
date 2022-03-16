package ru.javarush.marianna300.cryptoanalizer.commands;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;

public interface Action {
    Result execute(String[] parameters);
}
