package oneClassTasks;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GenericsHomework {
    public static void main(String[] args) {
        name();

        //Utwórz dwa obiekty Pair i ustaw im wartości String, Integer a następnie porównaj // nie rozumiem zadania

//        Pair<String> pair1 = new Pair();
//        Pair<Integer> pair2 = new Pair();

    }

    //    Napisz generyczną metodę która przyjmuje Listę jakichkolwiek elementów i wypisuje wszystkie elementy tej listy,
    //    ale z zachowaniem kolejności przekazanej w parametrze (Comparator)
    static void name() {
        List<String> names = Lists.newArrayList("Adam", "1Ewa", "Ola", "Marek");
        System.out.println(sortSomething(names, Comparator.naturalOrder()));

        List<Object> list = Lists.newArrayList("aa", "4", "1", 2, "3");
        Comparator<Object> numberComparator = (e1, e2) -> e1.toString().compareTo(e2.toString());
        List<Object> x = sortSomething(list, numberComparator);
        System.out.println(x);


    }

    public static <T> List<T> sortSomething(List<T> somethings, Comparator<T> comparator) {
        return somethings.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    //Napisz generyczną metodę która przyjmuje Listę elementów będących jakimikolwiek numerami i zwraca ich sumę jako double

    public static <T extends Number> Double sum(List<T> numberList) {
        return numberList.stream()
                .mapToDouble(n -> n.doubleValue())
                .sum();
    }

    //Napisz generyczną metodę która przyjmuje Listę elementów będących jakimikolwiek numerami i zwraca ich sumę jako double,
    // ale fitruje elementy większe od dodatkowo przekazanego parametru

    public static <T extends Number> Double sumWithFilter(List<T> numberList, T threshold){
        Double thresholdDouble = threshold.doubleValue();
        return numberList.stream()
                .mapToDouble(n -> n.doubleValue())
                .filter(n -> n > thresholdDouble)
                .sum();
    }

}

