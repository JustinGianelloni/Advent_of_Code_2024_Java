package aoc.day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

public class Part1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String file = "day7.txt";
        Path path = Path.of(ClassLoader.getSystemResource(file).toURI());
        String[] lines = readInput(path);
        Long answer = parseLines(lines);
        System.out.println("The answer is: " + answer);
    }

    private static String[] readInput(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        return lines.toArray(new String[0]);
    }

    private static Long parseLines(String[] lines) {
        long answer = 0;
        for (String line : lines) {
            answer += parseLine(line);
        }
        return answer;
    }

    private static Long parseLine(String line) {
        String[] split = line.split(": ");
        long testValue = Long.parseLong(split[0]);
        String[] n = split[1].split(" ");
        int[] numbers = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            numbers[i] = Integer.parseInt(n[i]);
        }
        return (calibrate(testValue, numbers)) ? testValue : 0;
    }

    private static boolean calibrate(long testValue, int[] numbers) {
        String binaryString = "0".repeat(numbers.length - 1);
        int decimalValue = Integer.parseInt(binaryString, 2);
        int halt = Integer.parseInt("1".repeat(numbers.length - 1), 2);
        do {
            StringBuilder expressionString = new StringBuilder(Integer.toString(numbers[0]));
            for (int i = 0; i < numbers.length-1; i++) {
                expressionString.append(getOperator(binaryString.charAt(i)));
                expressionString.append(numbers[i+1]);
            }
            Expression expression = getExpression(expressionString.toString());
            double result = expression.evaluate();
            if (result == testValue) {
                return true;
            }
            decimalValue++;
            binaryString = padLeft(Integer.toBinaryString(decimalValue), numbers.length - 1);
        } while (decimalValue <= halt);
        return false;
    }

    private static String getOperator(char character) {
        return switch (character) {
            case '0' -> "+";
            case '1' -> "*";
            default -> null;
        };
    }

    private static String padLeft(String binary, int length) {
        if (binary.length() >= length) {
            return binary;
        }
        int charsToPad = length - binary.length();
        return "0".repeat(charsToPad) + binary;
    }

    private static Expression getExpression(String expressionString) {
        Operator add = new Operator("+", 2, true, Operator.PRECEDENCE_ADDITION) {
            @Override
            public double apply(double[] args) {
                return args[0] + args[1];
            }
        };
        Operator mul = new Operator("*", 2, true, Operator.PRECEDENCE_ADDITION) {
            @Override
            public double apply(double[] args) {
                return args[0] * args[1];
            }
        };
        return new ExpressionBuilder(expressionString)
                .operator(add, mul)
                .build();
    }
}