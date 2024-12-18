package com.example.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double calculate(double num1, double num2, String operator) {
        //計算ロジックを実装
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> {
                if (num2 == 0) {
                    throw new ArithmeticException(("Division by zero"));
                }
                yield num1 / num2;
            }
            default -> throw new IllegalArgumentException("Invalid number format");
        };
    }
}
