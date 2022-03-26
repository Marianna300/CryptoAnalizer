package ru.javarush.marianna300.cryptoanalizer;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CaesarCipher {

    private static final char FIRST_LETTER = 'а';
    private static final char LAST_LETTER = 'я';
    private static final int ALPHABET_SIZE = LAST_LETTER - FIRST_LETTER + 1;
    public static final double[] RUSSIAN_LETTERS_PROBABILITIES = {
            0.0801, 0.0159, 0.0454, 0.0170, 0.0298, 0.0845, 0.0094, 0.0165, 0.0735,
            0.0121, 0.0349, 0.0440, 0.0321, 0.0670, 0.1097, 0.0281, 0.0473, 0.0547, 0.0626, 0.0262, 0.0026,
            0.0097, 0.0048, 0.0144, 0.0073, 0.0036, 0.0004, 0.0190, 0.0174, 0.0032, 0.0064, 0.0201
    };

    public String cipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                int originalAlphabetPosition = character - FIRST_LETTER;
                int newAlphabetPosition = (originalAlphabetPosition + offset) % ALPHABET_SIZE;
                char newCharacter = (char) (FIRST_LETTER + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public String decipher(String message, int offset) {
        return cipher(message, ALPHABET_SIZE - (offset % ALPHABET_SIZE));
    }

    public int breakCipher(String message) {
        return probableOffset(chiSquares(message));
    }

    private double[] chiSquares(String message) {
        double[] expectedLettersFrequencies = expectedLettersFrequencies(message.length());

        double[] chiSquares = new double[ALPHABET_SIZE];

        for (int offset = 0; offset < chiSquares.length; offset++) {
            String decipheredMessage = decipher(message, offset);
            long[] lettersFrequencies = observedLettersFrequencies(decipheredMessage);
            double chiSquare = new ChiSquareTest().chiSquare(expectedLettersFrequencies, lettersFrequencies);
            chiSquares[offset] = chiSquare;
        }

        return chiSquares;
    }

    private long[] observedLettersFrequencies(String message) {
        return IntStream.rangeClosed(FIRST_LETTER, LAST_LETTER)
                .mapToLong(letter -> countLetter((char) letter, message))
                .toArray();
    }

    private long countLetter(char letter, String message) {
        System.out.println("letter = " + letter);
        return message.chars()
                .filter(character -> character == letter)
                .count();
    }

    private double[] expectedLettersFrequencies(int messageLength) {
        return Arrays.stream(RUSSIAN_LETTERS_PROBABILITIES)
                .map(probability -> probability * messageLength)
                .toArray();
    }

    private int probableOffset(double[] chiSquares) {
        int probableOffset = 0;
        for (int offset = 0; offset < chiSquares.length; offset++) {
            double tmp = chiSquares[offset];
            System.out.println(String.format("Chi-Square for offset %d: %.2f", offset, tmp));
            if (chiSquares[offset] < chiSquares[probableOffset]) {
                probableOffset = offset;
            }
        }
        return probableOffset;
    }
}
