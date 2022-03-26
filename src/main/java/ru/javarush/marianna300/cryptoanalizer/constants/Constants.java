package ru.javarush.marianna300.cryptoanalizer.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    public static final Map<Character, Integer> ALPHABET_MAP = new HashMap<>() {{
        put('А', 0);
        put('Б', 1);
        put('В', 2);
        put('Г', 3);
        put('Д', 4);
        put('Е', 5);
        put('Ё', 6);
        put('Ж', 7);
        put('З', 8);
        put('И', 9);
        put('Й', 10);
        put('К', 11);
        put('Л', 12);
        put('М', 13);
        put('Н', 14);
        put('О', 15);
        put('П', 16);
        put('Р', 17);
        put('С', 18);
        put('Т', 19);
        put('У', 20);
        put('Ф', 21);
        put('Х', 22);
        put('Ц', 23);
        put('Ч', 24);
        put('Ш', 25);
        put('Щ', 26);
        put('Ъ', 27);
        put('Ы', 28);
        put('Ь', 29);
        put('Э', 30);
        put('Ю', 31);
        put('Я', 32);
    }};
    public static final Map<Character, Double> ALPHABET_MAP_ = new HashMap<>() {{
        put('А', 8.01);
        put('Б', 1.59);
        put('В', 4.54);
        put('Г', 1.70);
        put('Д', 2.98);
        put('Е', 8.45);
        put('Ё', 0.04);
        put('Ж', 0.94);
        put('З', 1.65);
        put('И', 7.35);
        put('Й', 1.21);
        put('К', 3.49);
        put('Л', 4.40);
        put('М', 3.21);
        put('Н', 6.70);
        put('О', 10.97);
        put('П', 2.81);
        put('Р', 4.73);
        put('С', 5.47);
        put('Т', 6.26);
        put('У', 2.62);
        put('Ф', 0.26);
        put('Х', 0.97);
        put('Ц', 0.48);
        put('Ч', 1.44);
        put('Ш', 0.73);
        put('Щ', 0.36);
        put('Ъ', 0.04);
        put('Ы', 1.90);
        put('Ь', 1.74);
        put('Э', 0.32);
        put('Ю', 0.64);
        put('Я', 2.01);
    }};

    public static final double[] ALPHABET_ = {0.0801, 0.0159, 0.0454, 0.0170, 0.0298, 0.0845, 0.0004, 0.0094, 0.0165,
            0.0735, 0.0121, 0.0349, 0.0440, 0.0321, 0.0670, 0.1097, 0.0281, 0.0473, 0.0547, 0.0626, 0.0262, 0.0026,
            0.0097, 0.0048, 0.0144, 0.0073, 0.0036, 0.0004, 0.0190, 0.0174, 0.0032, 0.0064, 0.0201};
}
