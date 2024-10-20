package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        System.out.println("계산할 문자열을 입력하세요.");
        String input = Console.readLine();

        Calculator calculator = new Calculator();
        Expression expression = new Expression(input);

        System.out.println("결과 : " + calculator.calculate(expression));
    }
}
