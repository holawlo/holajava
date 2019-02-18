package customers;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.math.BigDecimal.*;

public class CustomerService {

    public List<Customer> convertToList(Customer[] customers) {
        if (customers == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(customers);
    }

    public List<String> convertToListOfNames(Customer[] customers) {
        List<String> namesList = new ArrayList<>();
        for (Customer customer : customers) {
            namesList.add(customer.getFirstName().trim()
                    + " " + customer.getLastName().trim());
        }
        return namesList;
    }

    public List<String> convertToListOfNamesWithStream(Customer[] customers) {
        return Arrays.stream(customers)
                .map(c -> c.getFirstName().trim() + " " + c.getLastName().trim())
                .collect(Collectors.toList());
    }
        public Map<Integer, Customer> convertToMap(Customer[] customers) {
        return Optional.ofNullable(customers) //?? ofNullable?
                .map(ca -> Arrays.stream(ca)
                        .collect(Collectors.toMap(Customer::getId, Function.identity()))) //Function.identity()?
                .orElse(Collections.emptyMap());
    }

    public BigDecimal computeAllWishItemsCost(Customer customer, Map<String, BigDecimal> carOptionNamePriceMap) {
        return customer.getWishList().stream()
                .map(wishItem -> valueOf(wishItem.getQuantity())
                        .multiply(carOptionNamePriceMap.get(wishItem.getName())))
                .reduce(ZERO, (p1, p2) -> p1.add(p2));
    }

    public List<WishItem> getAffordableItemsIncludingOrder(Customer customer, Map<String, BigDecimal> carOptionNamePriceMap) {
        return null; //todo
    }

    public List<WishItem> getAffordableItemsMaxQuantity(Customer customer, Map<String, BigDecimal> carOptionNamePriceMap) {
        return null; //todo
    }

    private BigDecimal getWishItemFinalPrice(Map<String, BigDecimal> carOptionNamePriceMap, WishItem wishItem) {
        return new BigDecimal(0); //todo
    }
}
