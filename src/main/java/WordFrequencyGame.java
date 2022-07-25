import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    private static final String SPACE_SPLIT = "\\s+";
    public String calculateWordFrequency(String input){
        if (input.split(SPACE_SPLIT).length==1) {
            return input + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<Word> words = new ArrayList<>();
                for (String word : input.split(SPACE_SPLIT)) {
                    words.add(new Word(word, 1));
                }


                //get the map for the next step of sizing the same word
                Map<String, List<Word>> map =getListMap(words);

                List<Word> list = new ArrayList<>();
                for (Map.Entry<String, List<Word>> entry : map.entrySet()){
                    Word word = new Word(entry.getKey(), entry.getValue().size());
                    list.add(word);
                }
                words = list;

                words.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Word w : words) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<Word>> getListMap(List<Word> wordList) {
        Map<String, List<Word>> map = new HashMap<>();
        for (Word word : wordList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
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
