package calculator;

import java.util.List;

public class Expression {

    private final Numbers numbers;

    public Expression(String input) {
        Operators operators = new Operators(input);
        this.numbers = new Numbers(input, operators);
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }
}
