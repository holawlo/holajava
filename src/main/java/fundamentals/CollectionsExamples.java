package fundamentals;

import fundamentals.oopAndInterfacesFundamentals.Pensioner;
import fundamentals.oopAndInterfacesFundamentals.Person;
import fundamentals.oopAndInterfacesFundamentals.Student;
import fundamentals.oopAndInterfacesFundamentals.Worker;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionsExamples {
    public static final Student student1 = new Student("student3", "Nowak", 3, 5, 43, BigDecimal.ZERO);
    public static final Student student2 = new Student("student5", "Nowak ", 5, 5, 43, BigDecimal.ZERO);
    public static final Worker worker1 = new Worker("worker6", "Kowalski", 6, 6, BigDecimal.ZERO);
    public static final Pensioner pensioner1 = new Pensioner("pensioner7", "Psikuta", 7, 5, BigDecimal.ZERO);
    public static final Pensioner pensioner2 = new Pensioner("pensioner7", null, 7, 5, BigDecimal.ZERO);
    //! jeśli pole jest big decimal to nie mozna wpisac samej liczby!!!

    public static void main(String[] args) {
        treeSetExample();
        listsExample();
        hashSetExample();
        hashMapExample();
        namesStatisticsMap();

        List<Person> personList = populationPeopleList();

        Map<String, Long> stringLongMap = personList.stream() //streamy automatycznie zamieniaja Integer na Long
                .filter(e -> e.getLastName() != null)
                .map(e -> e.getLastName().trim())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(stringLongMap);

    }

    private static void namesStatisticsMap() {
        List<Person> peopleList = populationPeopleList(); //ma liczyc ile jakich nazwisk występjuje
        Map<String, Integer> resultMap = new HashMap<>();

        for (Person x : peopleList) {
            String surname; //jesli najade na x.getLastName ctrl alt v to sam mi stworzy ta linijke ze zmienna lastName
            if (x.getLastName() == null){
                surname = null;
            }else{
                surname = x.getLastName().trim();
            }

            if (resultMap.containsKey(surname)) { //a zamiast surname bylo x.getLastName()
                int counter = resultMap.get(surname);
                resultMap.replace(surname, counter + 1);
            } else {
                resultMap.put(surname, 1);
            }
        }
        System.out.println(resultMap);
    }

    private static void hashMapExample() {
        Map<Integer, Person> personMap = new HashMap<>();
        personMap.put(student1.getIdentity(), student1);
        personMap.put(student2.getIdentity(), student2);
        personMap.put(worker1.getIdentity(), worker1);
        personMap.put(pensioner1.getIdentity(), pensioner1);
        personMap.put(pensioner2.getIdentity(), pensioner2);
        System.out.println(personMap);
    }

    private static void hashSetExample() {
        Set<Person> peopleSet = new HashSet<>(); //uwaga na nazwy na set i get, bo to zazwyczaj metody
        //Set<Person> peopleSet = new LinkedHashSet<>(); //w tym przypadku zostanie zachowana kolejnosc dodawania elem
/*        peopleSet.add(student1);
        peopleSet.add(worker1);
        peopleSet.add(pensioner1);
        peopleSet.add(pensioner2);*/
        System.out.println(peopleSet.add(pensioner1));
        System.out.println(peopleSet.add(pensioner2));
        System.out.println(peopleSet.add(worker1));
        System.out.println(peopleSet.add(student1));
        System.out.println(peopleSet.add(student2));
        System.out.println(peopleSet.size());
        System.out.println(peopleSet); //kolejnosc jest po hashcodzie, a overridowalismy go w person po identity, dlatego bedzie po identity sortowal
    }

    private static void listsExample() {

        List<Person> personList2 = new LinkedList<>();
        personList2.add(student1);
        personList2.add(student2);
        personList2.add(pensioner2);
        personList2.add(worker1);
        System.out.println(personList2);
    }

    private static List<Person> populationPeopleList() {
        List<Person> personList = new ArrayList<>();
        personList.add(student1);
        personList.add(student2);
        personList.add(pensioner2);
        personList.add(worker1);
        personList.add(pensioner1);
//        System.out.println(personList.contains(pensioner1));
//        System.out.println(personList);//w kolejnosci dodawania
        return personList;
    }

    private static void treeSetExample() {
        Set<Person> tSet = new TreeSet<>();
        tSet.add(student2);
        tSet.add(student1);
        System.out.println(tSet.toString());//kolejnosc posortowana wg overrida z person - po identity

        for (Person x : tSet) {
            System.out.println(x.getFirstName());
        }

        tSet.stream()
                .map(e -> e.getFirstName())
                .forEach(e -> System.out.println(e));
    }
}
