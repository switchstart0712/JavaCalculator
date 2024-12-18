package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//Spring Bootアプリケーションのメインクラスに付与するアノテーション
@SpringBootApplication
// メソッドの戻り値を直接HTTPレスポンスのボディにする。テンプレートエンジンを使用する場合は @RestController ではなく @Controller を使用
@RestController
@RequestMapping("/api")
public class CalculatorApplication {

	//CalculatorServiceのインスタンスを自動的に注入,依存関係を自動的に解決
	@Autowired
	private CalculatorService calculatorService;

	@PostMapping("/calculate")
	public Map<String, Object> calculate(
			@RequestParam(value = "num1", defaultValue = "0") String num1Str,
			@RequestParam(value = "num2", defaultValue = "0") String num2Str,
			@RequestParam(value = "operator") String operator
	) {
		Map<String, Object> response = new HashMap<>();
        System.out.println("Received num1: " + num1Str + ", num2: " + num2Str + ", operator: " + operator);
		try {
            // 数値のバリデーション
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);

			double result = calculatorService.calculate(num1, num2, operator);
			response.put("result", result);
			response.put("status", "success");
		} catch (IllegalArgumentException | ArithmeticException e) {
			response.put("status", "error");
			response.put("message", e.getMessage());
		}
		return response;
	}

	//エントリーポイント
	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}
}
