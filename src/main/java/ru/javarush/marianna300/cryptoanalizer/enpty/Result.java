package ru.javarush.marianna300.cryptoanalizer.enpty;

public class Result {
    private final String message;
    private final ResultCode resultCode;

    public Result(String message, ResultCode resultCode) {
        this.message = message;
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", resultCode=" + resultCode +
                '}';
    }
}

