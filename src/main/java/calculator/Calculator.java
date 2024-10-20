package calculator;

import java.util.List;

public class Calculator {

    public Calculator() {
    }

    public int calculate(Expression expression) {
        List<Integer> numbers = expression.getNumbers();
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
