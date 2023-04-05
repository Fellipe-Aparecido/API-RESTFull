package br.com.rest.api.service.impl;

import br.com.rest.api.exceptions.UnsupportedMathOperationException;
import br.com.rest.api.service.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorImpl implements Calculator {

    @Override
    public Double sum(String numberOne, String numberTwo) {
        isNumericnumber(numberOne);
        isNumericnumber(numberTwo);
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @Override
    public Double subtraction(String numberOne, String numberTwo) {
        isNumericnumber(numberOne);
        isNumericnumber(numberTwo);
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @Override
    public Double multiplication(String numberOne, String numberTwo) {
        isNumericnumber(numberOne);
        isNumericnumber(numberTwo);
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @Override
    public Double division(String numberOne, String numberTwo) {
        isNumericnumber(numberOne);
        isNumericnumber(numberTwo);
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @Override
    public Double mean(String numberOne, String numberTwo) {
        isNumericnumber(numberOne);
        isNumericnumber(numberTwo);
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @Override
    public Double squareRoot(String number) {
        isNumericnumber(number);
        return Math.sqrt(convertToDouble(number));
    }

    private Double convertToDouble(String strNumber){
        if(strNumber == null) return 0D;
        String number = strNumber.replace(",",".");
        isNumericnumber(number);
        return Double.parseDouble(number);
    }

    private void isNumericnumber(String strNumber){
        String regex = "[-+]?[0-9]*\\.?[0-9]+";
        String number = strNumber.replace(",",".");
        if(strNumber == null || !number.matches(regex))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
    }
}
