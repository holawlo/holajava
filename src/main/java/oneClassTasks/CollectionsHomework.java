package oneClassTasks;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionsHomework {

    private static String[] animals = new String[]{"cat", "dog ", "cat", "mouse", "rat ", "pig", "rabbit", "hamster", " ", "parrot", "cat", "", "dog", "cat", "  pig", "dog", null};

    public static void main(String[] args) {
        System.out.println(noWhite(animals));
        noWhiteWithStream(animals);
        System.out.println(mapOfElements(animals));
        System.out.println(naturalOrderForStringAndNoDuplicates());
        System.out.println(howManyElements());
        System.out.println(method(animals));
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
                .forEach(e -> animalsList.add(e));

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

    private static List<String> naturalOrderForStringAndNoDuplicates(){
        List<String> orderList= noWhiteWithStream(animals);

        orderList = orderList.stream()
                .sorted(Comparator.naturalOrder())
                .distinct()
                .collect(Collectors.toList());

        return orderList;
    }

    private static Map<String, Integer> howManyElements(){
        List<String> orderList= noWhiteWithStream(animals);
        Map<String,Integer> howMany = new HashMap<>();

        for (String s : orderList) {
            if (howMany.containsKey(s)){
                howMany.replace(s,howMany.get(s) + 1);
            }else{
                howMany.put(s,1);
            }
        }
        return howMany;
    }

    private static Map<Boolean,List<String>> method(String[] tab){
        Map<Boolean,List<String>> map = new HashMap<>();

        List<String> notEmptyStrings = Arrays.stream(tab)
                .filter(a -> a != null)
                .map(String::trim)
                .filter(a -> !a.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        map.put(true,notEmptyStrings);
        map.put(false,new ArrayList<>());

        return map;
    }


}
