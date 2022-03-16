package ru.javarush.marianna300.cryptoanalizer.constans;

public class Constans {
    private static final String rus = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String eng = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String cypher = "0123456789";
    private static final String z = "!?{}[]()%$@*";
    public static final String ALPHABET = rus + eng + rus.toLowerCase() + eng.toLowerCase() + cypher + z;
}
