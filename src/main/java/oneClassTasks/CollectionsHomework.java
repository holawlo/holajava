package oneClassTasks;

import java.util.*;

public class CollectionsHomework {

    private static String[] animals = new String[]{"cat", "dog ", "cat", "mouse", "rat ", "pig", "rabbit", "hamster", " ", "parrot", "cat", "", "dog", "cat", "  pig", "dog", null};

    public static void main(String[] args) {
        System.out.println(noWhite(animals));
        noWhiteWithStream(animals);
        System.out.println(mapOfElements(animals));
    }

    private static List<String> noWhite(String[] tab) {
        List<String> animalsList = new ArrayList<>();
        //System.out.println(Arrays.toString(tab));

        for (String s : tab) {
            if (s == null) {
                continue;
            } else if (s.length() < 1) {
                continue;
            } else {
                animalsList.add(s.trim());
            }
        }

        return animalsList;
    }

    private static List<String> noWhiteWithStream(String[] tab) {
        List<String> animalsList = new ArrayList<>();

        Arrays.stream(tab)
                .filter(e -> e != null)
                .map(e -> e.trim())
                .filter(e -> !e.isEmpty())
                .forEach(e -> System.out.println(e));

        return animalsList;
    }

    private static Map<String, Integer> mapOfElements(String[] tab) {
        Map<String, Integer> mapOfElems = new HashMap<>();


        for (String s : tab) {
            if (s != null) {
                if (mapOfElems.containsKey(s.trim()) && !s.trim().isEmpty()) {
                    mapOfElems.put(s.trim(), (mapOfElems.get(s.trim()) + 1));
                } else if (s == null) {
                    continue;
                } else {
                    mapOfElems.put(s.trim(), 1);
                }
            } else {
                continue;
            }
        }

        return mapOfElems;
    }

}
