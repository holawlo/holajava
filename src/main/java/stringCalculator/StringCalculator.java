package stringCalculator;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {


    public static Integer adding(String text) {
        if (StringUtils.isBlank(text)) {
            return 0;
        }

        if (text.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.*)\n(.*)");
            Matcher matcher = pattern.matcher(text);
            matcher.matches();
            String delimiters = matcher.group(1);
            delimiters = delimiters
                    .replaceAll("[\\[|\\]]+", " ")
                    .trim()
                    .replaceAll("\\s", "|");
            String[] splitted = text.split("\n");
            return tokenizeAndSum(splitted[1], delimiters);
        }
        return tokenizeAndSum(text, ",|\n");
    }

    private static Integer tokenizeAndSum(String text, String regex) {
        List<Integer> integerList = Arrays.stream(text.split(regex))
                .map(e -> Integer.valueOf(e.trim()))
                .collect(Collectors.toList());

        List<Integer> negativeValues = integerList.stream()
                .filter(i -> i < 0)
                .collect(Collectors.toList());

        if (!negativeValues.isEmpty()) {
            throw new NegativeNumberFoundException("Tak nie można! " + negativeValues);
        }

        return integerList.stream()
                .filter(a -> a <= 1000)
                .reduce((a, b) -> a + b)
                .orElseGet(() -> superHardLongAndSourcesNeedingMethod());
    }

    private static Integer superHardLongAndSourcesNeedingMethod() {
        System.out.println("NIE OK");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}

/*
        if (text.startsWith("//")) {
            if (text.startsWith("//[")) {
                List<String> delimiterList = new ArrayList<>();
                // wszystkie ponizsze pomysly dzialaja tu: https://regex101.com/
//                Pattern pattern = Pattern.compile("[(.+)]"); // milion matchow
//                Pattern pattern = Pattern.compile("\\[(.*?)\\]"); // matchuje razem z nawiasami []
//                Pattern pattern = Pattern.compile("[(.*?)]"); // matchuje glupoty razem z "]"
//                Pattern pattern = Pattern.compile("(?<=\\[)([^]]+)(?=\\])"); // lookahead lookbehind, dziala pieknie w przegladarce, tu wyłapuje w debugu "]" np "sss]"
//                Pattern pattern = Pattern.compile("(?<=\\[)([^]]+)(?=\\\\])"); // to tez wylapuje "]"
//                Pattern pattern = Pattern.compile("(?<=\\[)(.+[^\\]])(?=])"); // to tez wylapuje "]"
                Pattern pattern = Pattern.compile("\\[([^\\[\\]]*)\\]");
                Matcher matcher = pattern.matcher(text);
                //matcher.matches();

                while (matcher.find()) {
                    delimiterList.add(matcher.group(1));
                }
                String[] splitted = text.split("\n");

                for (String delimiter : delimiterList) {
                    splitted[1] = splitted[1].replaceAll(delimiter, ",");
                }

                return tokenizeAndSum(splitted[1], ",");

            } else {
                Pattern pattern = Pattern.compile("//(.*)\\n(.*)");
                Matcher matcher = pattern.matcher(text);
                matcher.matches();
                String delimiter = matcher.group(1);
                String[] splitted = text.split("\n");

                return tokenizeAndSum(splitted[1], String.valueOf(delimiter));
            }
        }*/
