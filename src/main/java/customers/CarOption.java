package customers;

import lombok.Getter;

@Getter
public class CarOption {
    private final int id;
    private final String name;
    private final int price;
    private static int counter = 1;

    {
        id = counter++;
    }

    public CarOption(String name, int price) {
        this.name = name;
        this.price = price;
    }

}
