package ru.javarush.marianna300.cryptoanalizer;

import ru.javarush.marianna300.cryptoanalizer.enpty.Result;

public class Runner {
    public static void main(String[] args) {

        Application application = new Application();
        Result result = application.run(args);
        System.out.println(result);
    }
}
   // int key = 3;
   // String text = "Привет";
   // String result = ".......";