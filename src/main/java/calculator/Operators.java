package calculator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Operators {

    private static final Set<Character> ESCAPE = Set.of(
            '.', '^', '$', '*', '+', '?', '\\', '|', '[', ']', '(', ')', '{', '}', '-', '/');
    private static final Set<Character> INITIAL_OPERATORS = Set.of(',', ':');

    private static final String CUSTOM_OPERATOR_PREFIX = "//";
    private static final int CUSTOM_OPERATOR_PREFIX_INDEX = 0;

    private static final String CUSTOM_OPERATOR_SUFFIX = "\\n";
    private static final int CUSTOM_OPERATOR_SUFFIX_INDEX = 3;

    private final Set<Character> operators = new HashSet<>(INITIAL_OPERATORS);

    public Operators(String input) {
        if (input.contains(CUSTOM_OPERATOR_PREFIX) && input.contains(CUSTOM_OPERATOR_SUFFIX)) {
            operators.add(findCustomOperator(input));
        }
    }

    public boolean containsCustom() {
        return operators.size() > INITIAL_OPERATORS.size();
    }

    private char findCustomOperator(String input) {
        int prefixIndex = input.indexOf(CUSTOM_OPERATOR_PREFIX);
        int suffixIndex = input.indexOf(CUSTOM_OPERATOR_SUFFIX);

        validateIndices(prefixIndex, suffixIndex);
        char operator = input.charAt(suffixIndex - 1);
        validateNonDigit(operator);

        return operator;
    }

    private void validateIndices(int prefixIndex, int suffixIndex) {
        if (prefixIndex != CUSTOM_OPERATOR_PREFIX_INDEX) {
            throw new IllegalArgumentException("// 의 위치가 잘못되었습니다.");
        }

        if (suffixIndex != CUSTOM_OPERATOR_SUFFIX_INDEX) {
            throw new IllegalArgumentException("\\n 의 위치가 잘못되었습니다. 구분자는 한 글자만 허용합니다.");
        }
    }

    private void validateNonDigit(char operator) {
        if (Character.isDigit(operator)) {
            throw new IllegalArgumentException("숫자는 구분자로 입력할 수 없습니다.");
        }
    }

    public String getAsRegex() {
        return operators.stream()
                .map(this::escapeIfNecessary)
                .collect(Collectors.joining("|"));
    }

    private String escapeIfNecessary(char operator) {
        if (ESCAPE.contains(operator)) {
            return "\\" + operator;
        }

        return String.valueOf(operator);
    }
}
