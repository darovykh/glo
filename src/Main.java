import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<String> getWords(String content){
        String [] lines = content.split(System.lineSeparator());
        List<String> words = new ArrayList<>();
        for(int i = 0; i < lines.length; i++) {
            String [] wordsInLine = lines[i].split(" ");
            words.addAll(Arrays.asList(wordsInLine));
        }
        return words;
    }

    public static List<Integer> getOccurrencesNumber(List<String> collection, List<String> distincts) {
        List<Integer> occurrences = new ArrayList<>();
        for(int i = 0; i < distincts.size(); i++) {
            occurrences.add(Collections.frequency(collection, distincts.get(i)));
        }
        return occurrences;
    }

    public static List<String> getProperNames(List<String> collection){
        List<String> properNames = new ArrayList<>();
        String regEx = "^([A-Z][a-z]+)$";
        for(int i = 0; i < collection.size(); i++){
            if(collection.get(i).matches(regEx)){
                properNames.add(collection.get(i));
            }
        }
        return properNames;
    }

    public static void main(String [] args) throws IOException {
        List<String> words = getWords(new String(Files.readAllBytes(Paths.get("harry.txt"))));
        List<String> distincts = words.stream().distinct().collect(Collectors.toList());
        List<Integer> occurences = getOccurrencesNumber(words, distincts);
        List<String> properNames = getProperNames(distincts);
        Files.write(Paths.get("test.txt"), properNames);
    }
}
