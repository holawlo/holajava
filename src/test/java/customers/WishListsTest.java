package customers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WishListsTest {
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

    private static final CarOption[] items = new CarOption[]{
            new CarOption("Klima", 1500), //todo
            new CarOption("Radyjko", 1200),
            new CarOption("Wycieraczki", 100),
            new CarOption("Dywaniki", 150)
    };

    private static Map<Integer, Customer> customerMap;
    private static CustomerService customerService = null;
    private static CarOptionService carOptionService = null;

    @BeforeAll
    public static void prepareWishLists() {
        customerMap = customerService.convertToMap(people);
        prepareWishListForCustomer(1, "Klima", "Radyjko", "Wycieraczki", "Dywaniki:4");
        prepareWishListForCustomer(2, "Radyjko", "Klima", "Wycieraczki", "Dywaniki:2");
        prepareWishListForCustomer(3, "Radyjko", "Wycieraczki:3", "Dywaniki", "Klima");
        prepareWishListForCustomer(4, "Wycieraczki", "Klima", "Radyjko", "Dywaniki");
        prepareWishListForCustomer(5, "Wycieraczki", "Dywaniki", "Klima", "Radyjko");
        prepareWishListForCustomer(6, "Dywaniki", "Radyjko", "Klima", "Wycieraczki");
        prepareWishListForCustomer(7, "Klima", "Radyjko", "Wycieraczki:2", "Dywaniki");
        prepareWishListForCustomer(8, "Radyjko", "Wycieraczki", "Klima", "Dywaniki");
    }

    private static void prepareWishListForCustomer(Integer id, String... items) {
        Customer customer = customerMap.get(id);
        Arrays.stream(items)
                .map(s -> parseWishItem(s))
                .forEach(wi -> customer.addToWishList(wi));
    }

    private static WishItem parseWishItem(String s) {
        String[] split = s.split(":");
        return new WishItem(split[0],split.length > 1 ? Integer.valueOf(split[1]) : 1);
    }

    @Test
    public void wishItemsOverBudget() {
        Map<String, BigDecimal> carOptionNamePriceMap = carOptionService.convertToNamePriceMap(items);
        Map<Customer, BigDecimal> customerWishListCostMap = new HashMap<>(); //todo

        customerWishListCostMap.entrySet().stream()
                .filter(entry -> entry.getKey().getSalary().compareTo(entry.getValue()) <= 0)
                .forEach(e -> System.out.println(e.getKey() + " :: " + e.getValue().subtract(e.getKey().getSalary())));

    }

    @Test
    public void whatCustomersCanAffordIncludingOrder() {
        Map<String, BigDecimal> carOptionNamePriceMap = carOptionService.convertToNamePriceMap(items);
        Map<Customer, List<WishItem>> customerAffordableItemsMap = new HashMap<>(); //todo

        System.out.println("Car options with prices:");
        carOptionNamePriceMap.forEach((name, price) -> System.out.println("\t" + name + ": " + price));
        customerAffordableItemsMap.forEach((c, items) -> {
            System.out.println("Customer: " + c);
            System.out.println("\tWish list (" + c.getWishList().size() + "): " + c.getWishList());
            System.out.println("\tAffordable items with order (" + items.size() + "): " + items);
        });
    }

    @Test
    public void whatCustomersCanAffordMaxItems() {
        Map<String, BigDecimal> carOptionNamePriceMap = carOptionService.convertToNamePriceMap(items);
        Map<Customer, List<WishItem>> customerAffordableItemsMap = new HashMap<>(); //todo

        System.out.println("Car options with prices:");
        carOptionNamePriceMap.forEach((name, price) -> System.out.println("\t" + name + ": " + price));
        customerAffordableItemsMap.forEach((c, items) -> {
            System.out.println("Customer: " + c);
            System.out.println("\tWish list (" + c.getWishList().size() + "): " + c.getWishList());
            System.out.println("\tAffordable items max quantity (" + items.size() + "): " + items);
        });
    }

    @Test
    public void customerWithMaximumMoneyLeftAfterBuingAllWishList() {
        Map<String, BigDecimal> carOptionNamePriceMap = carOptionService.convertToNamePriceMap(items);
        Map<Customer, BigDecimal> customerWishListCostMap = new HashMap<>(); //todo

        customerWishListCostMap.forEach((c, p) -> System.out.println(c + " :: " + p + " -> " + c.getSalary().subtract(p)));

        Optional<Map.Entry<Customer, BigDecimal>> customerWithMostMoneyLeft = customerWishListCostMap.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().subtract(entry.getKey().getSalary())))
                .findFirst();

        System.out.println();
        customerWithMostMoneyLeft.ifPresent(customerEntry ->
                System.out.println("Customer with most money left: " + customerEntry.getKey()
                        + ", items cost: " + customerEntry.getValue()
                        + ", left: " + customerEntry.getKey().getSalary().subtract(customerEntry.getValue()))
        );
    }
}