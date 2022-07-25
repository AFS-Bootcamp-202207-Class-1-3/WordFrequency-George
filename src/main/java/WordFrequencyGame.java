import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class WordFrequencyGame {
    private static final String SPACE_SPLIT = "\\s+";

    public String calculateWordFrequency(String input) {
        if (input.split(SPACE_SPLIT).length == 1) {
            return input + " 1";
        }
        try {
            Map<String, Long> sortedWordCountMap = generateSortWordCountMap(input);
            StringJoiner resultJoiner = new StringJoiner("\n");
            sortedWordCountMap.forEach((key, value) -> resultJoiner.add(key + " " + value));
            return resultJoiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private Map<String, Long> generateSortWordCountMap(String input) {
        return Arrays.stream(input.split(SPACE_SPLIT))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((Map.Entry.<String, Long>comparingByValue().reversed()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (word1, word2) -> word2, LinkedHashMap::new));
    }

}
