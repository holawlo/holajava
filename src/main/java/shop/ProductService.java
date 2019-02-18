package shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public void showListOfProducts(){
        products.stream()
                .map(Product::view)
                .forEach(System.out::println);
    }

    public Optional<Product> getById(Long id){
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findAny();
    }

    public void addAll(Product ... products){
        this.products.addAll(Arrays.asList(products));
    }
}
