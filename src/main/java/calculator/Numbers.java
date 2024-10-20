package calculator;

import java.util.Arrays;
import java.util.List;

public class Numbers {

    private static final String CUSTOM_OPERATOR_PREFIX = "//";
    private static final String CUSTOM_OPERATOR_SUFFIX = "\\n";
    private static final int CUSTOM_OPERATOR_LENGTH = 1;

    private final List<Integer> numbers;

    public Numbers(String input, Operators operators) {
        this.numbers = parse(input, operators);
    }

    private List<Integer> parse(String input, Operators operators) {
        if (operators.containsCustom()) {
            input = input.substring(
                    CUSTOM_OPERATOR_PREFIX.length() + CUSTOM_OPERATOR_SUFFIX.length() + CUSTOM_OPERATOR_LENGTH);
        }

        String[] rawNumbers = input.split(operators.getAsRegex());

        return Arrays.stream(rawNumbers)
                .map(this::parseInt)
                .toList();
    }

    private int parseInt(String rawNumber) {
        try {
            int number = Integer.parseInt(rawNumber);
            validatePositive(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("지정하지 않은 구분자를 사용할 수 없습니다.");
        }
    }

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수를 입력할 수 없습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
