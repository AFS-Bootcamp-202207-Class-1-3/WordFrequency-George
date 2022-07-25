import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String SPACE_SPLIT = "\\s+";
    public String calculateWordFrequency(String input){
        if (input.split(SPACE_SPLIT).length==1) {
            return input + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<Word> frequencyWords = generateWords(input);
                //get the map for the next step of sizing the same word
                Map<String, List<Word>> map =getListMap(frequencyWords);

                List<Word> list = new ArrayList<>();
                for (Map.Entry<String, List<Word>> entry : map.entrySet()){
                    Word word = new Word(entry.getKey(), entry.getValue().size());
                    list.add(word);
                }
                frequencyWords = list;

                frequencyWords.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Word w : frequencyWords) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<Word> generateWords(String input) {
        return Arrays.stream(input.split(SPACE_SPLIT))
                .map(word -> new Word(word, 1))
                .collect(Collectors.toList());
    }


    private Map<String,List<Word>> getListMap(List<Word> wordList) {
        Map<String, List<Word>> map = new HashMap<>();
        for (Word word : wordList){
            if (!map.containsKey(word.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(word);
                map.put(word.getValue(), arr);
            }

            else {
                map.get(word.getValue()).add(word);
            }
        }


        return map;
    }


}
