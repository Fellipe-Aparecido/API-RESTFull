package br.com.rest.api.service;

public interface Calculator {
    Double sum(String numberOne, String numberTwo);
    Double subtraction(String numberOne, String numberTwo);
    Double multiplication(String numberOne, String numberTwo);
    Double division(String numberOne, String numberTwo);
    Double mean(String numberOne, String numberTwo);
    Double squareRoot(String number);
}
