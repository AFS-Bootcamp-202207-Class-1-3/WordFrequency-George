import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class WordFrequencyGame {
    private static final String SPACE_SPLIT = "\\s+";
    public String calculateWordFrequency(String input){
        if (input.split(SPACE_SPLIT).length==1) {
            return input + " 1";
        }
        try {
            Map<String, Long> sortedWordCountMap = generateSortWordCountMap(generateWordCountMap(input));
            StringJoiner resultJoiner = new StringJoiner("\n");
            sortedWordCountMap.forEach((key, value) -> resultJoiner.add(key + " " + value));
            return resultJoiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private Map<String, Long> generateSortWordCountMap(Map<String, Long> wordCountMap) {
        return wordCountMap
                .entrySet()
                .stream()
                .sorted((Map.Entry.<String, Long>comparingByValue().reversed()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
    }

    private Map<String, Long> generateWordCountMap(String input) {
        return Arrays.stream(input.split(SPACE_SPLIT))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

}
