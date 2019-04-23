package customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingInt;

class CustomerServiceTest {
    private static final Customer[] people = new Customer[]{
            new Customer("Anna", "Nowak   ", 33, "1200"),
            new Customer("Beata", "Kowalska", 22, "1200"),
            new Customer("Marek", " Nowak", 25, "1250"),
            new Customer("Adam", "Twardowski", 33, "4100"),
            new Customer("Monika  ", "Kos", 25, "2500"),
            new Customer("Adam ", "Rudy", 45, "3333"),
            new Customer("Marek", "Rudy", 15, 2210),
            new Customer("Adam", "Madej", 15, 3333)
    };

    private CustomerService customerService = new CustomerService();

    @Test
    void eachCustomerShouldHaveAutoincrementedIdStartingFromOne() {
        Assertions.assertEquals(5, people[4].getId());
    }

    @Test
    void convertToListShouldHaveTheSameSize() {
        List<Customer> customers = customerService.convertToList(people);
        Assertions.assertEquals(8, customers.size());
    }

    @Test
    void convertToListShouldPreserveOrder() {
        List<Customer> customers = customerService.convertToList(people);
        Assertions.assertEquals(people[1], customers.get(1));
        Assertions.assertEquals(people[3], customers.get(3));
        Assertions.assertEquals(people[7], customers.get(7));
    }

    @Test
    void convertToListShouldReturnEmptyListOnNullArray() {
        List<Customer> customers = customerService.convertToList(null);
        Assertions.assertEquals(0, customers.size());
    }

    @Test
    void convertToListOfNamesReturnsListOfConcatenatedData() {
        List<String> listOfNames = customerService.convertToListOfNames(people);
        Assertions.assertEquals("Monika Kos", listOfNames.get(4));
    }

    @Test
    void convertToMapShouldReturnMapWithIdAsKey() {
        Map<Integer, Customer> customerMap = customerService.convertToMap(people);
        Assertions.assertEquals(8, customerMap.size());
        Assertions.assertEquals(people[4], customerMap.get(5));
    }

    //Napisz metodę, która zwróci mapę
    // osób według zarobków <zarobki,osoby_z_zarobkami>
    public Map<BigDecimal, List<String>> salariesMap() {
        Map<BigDecimal, List<String>> resultMap = new HashMap<>();
        for (Customer customer : CustomerServiceTest.people) {
            if (resultMap.containsKey(customer.getSalary())) {
                List<String> innerList = resultMap.get(customer.getSalary());
                innerList.add(customer.getId() + ", " + customer.getFirstName() + ", " + customer.getLastName());
            } else {
                List<String> innerList = new ArrayList<>();//zasieg zmiennej tylko w tym bloku
                innerList.add(customer.getId() + ", " + customer.getFirstName() + ", " + customer.getLastName());
                resultMap.put(customer.getSalary(), innerList);
            }
        }
        return resultMap;
    }

    private Map<BigDecimal, List<Customer>> salariesMapWithStream() {
        return customerService.convertToList(people).stream()
                .collect(Collectors.groupingBy(e -> e.getSalary()));
    }

    //Napisz metodê, która zwróci statystykê ile jest osób z danymi zarobkami <zarobki,liczba_osób> Map<Double,Integer>

    public Map<BigDecimal, Integer> statistics() {
        Map<BigDecimal, Integer> resultMap = new HashMap<>();
        for (Customer customer : people) {
            if (resultMap.containsKey(customer.getSalary())) {
                resultMap.put(customer.getSalary(), resultMap.get(customer.getSalary()) + 1);
            } else {
                resultMap.put(customer.getSalary(), 1);
            }
        }
        return resultMap;
    }

    //6. Napisz metodę, która zwróci mapę map <imię,<zarobki, liczba_osób_z_takimi_zarobkami>>
    public Map<String, Map<BigDecimal, Integer>> mapOfMap() {
        Map<String, Map<BigDecimal, Integer>> resultMap = new HashMap<>();

        for (Customer customer : people) {

            if (resultMap.containsKey(customer.getFirstName().trim())) {
                Map<BigDecimal, Integer> innerMap = resultMap.get(customer.getFirstName().trim());

                if (innerMap.containsKey(customer.getSalary())) {
                    innerMap.put(customer.getSalary(), innerMap.get(customer.getSalary()) + 1);

                } else {
                    innerMap.put(customer.getSalary(), 1);
                }

            } else {
                Map<BigDecimal, Integer> innerMap = new HashMap<>();
                innerMap.put(customer.getSalary(), 1);
                resultMap.put(customer.getFirstName().trim(), innerMap);
            }
        }
        return resultMap;
    }

    //7. Napisz metodę, która zwróci mapę <imię,<suma_zarobków_osób_o_taki_imien

    public Map<String, Integer> mapForNames() {
        return customerService.convertToList(people).stream()
//                .collect(Collectors.toMap(Customer::getFirstName, customer -> customer.getSalary().intValue()));
                .collect(Collectors.groupingBy(Customer::getFirstName, summingInt(c -> c.getSalary().intValue())));
//        Map<String, Integer> resultMap = new HashMap<>();
//        List<Customer> customerList = customerService.convertToList(people);
//        for (Customer customer : customerList) {
//            if (resultMap.containsKey(customer.getFirstName())) {
//                resultMap.put(customer.getFirstName(), resultMap.get(customer.getFirstName()) + customer.getSalary().intValue());
//            } else {
//                resultMap.put(customer.getFirstName(), customer.getSalary().intValue());
//            }
//        }
//        return resultMap;
    }

    //8. Napisz metodę, która zwróci kolekcję unikalnych imion posortowanych alfabetycznie, ale malejącej kolejności
    public List<String> distinctNamesSorted() {
        return customerService.convertToList(people).stream()
                .map(c -> c.getFirstName().trim())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

}