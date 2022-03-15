package ru.javarush.marianna300.cryptoanalizer;

import java.util.Locale;

public class Runner {
    public static void main(String[] args) {
        String rus = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String eng = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cypher = "0123456789";
        String z = "!?{}[]()%$@*";
        String alphabet = rus + eng + rus.toLowerCase() + eng.toLowerCase() + cypher + z;

        int key = 3;
        String text = "Привет";
        String result = ".......";
        Application application = new Application(args);
    }
}
