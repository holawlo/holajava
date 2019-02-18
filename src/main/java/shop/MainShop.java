package shop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

//TODO delete(), order(), change quantity, rabat(), clear()

public class MainShop {
    public static User user;
    public static ProductService productService = new ProductService();
    private static final Scanner scanner = new Scanner(System.in);
    public static UserService userService = new UserService();
    public static CartService cartService = new CartService();


    public static void main(String[] args) {
        initProducts();
        start();
    }

    private static void start() {
        if (user == null) {
            initialMenu();
        } else {
            loggedMenu();
        }
    }

    private static void showCartProducts(){
        Set<CartProduct> cartProducts = cartService.getCartProducts();
        cartProducts.stream()
                .map(CartProduct::viewProduct)
                .forEach(System.out::println);
        start();
    }

    private static void initProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        product1.setId(1L);
        product2.setId(2L);
        product3.setId(3L);
        product4.setId(4L);
        product1.setName("chleb");
        product2.setName("śledź");
        product3.setName("paluszki");
        product4.setName("dziecko");
        product1.setPrice(new BigDecimal("2.04"));
        product2.setPrice(new BigDecimal("3"));
        product3.setPrice(new BigDecimal("4.6"));
        product4.setPrice(new BigDecimal("3.55"));
        productService.addAll(product1, product2, product3, product4);
    }

    private static void loggedMenu() {
        System.out.println("Wybierz opcję: ");
        System.out.println("1. Zakończ");
        System.out.println("2. Pokaż dostępne produkty");
        System.out.println("3. Dodaj do koszyka produkt");
        System.out.println("4. Pokaż zawartość koszyka");
        System.out.println("5. Usuń produkt z koszyka");

        Integer choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.exit(0);
            case 2:
                showProducts();
                break;
            case 3:
                addToCart();
                break;
            case 4:
                showCartProducts();
                break;
            case 5:
                removeFromCart();
                break;
            default:
                System.out.println("Wybrałeś nieprawidłową opcję");
        }
        start();
    }

    private static void addToCart() {
        productManagement("", cartService::addProduct);
    }

    private static void removeFromCart(){
        productManagement("", cartService::removeProduct);
    }

    private static void productManagement(String successMessage, Consumer<Product> manage){
        System.out.println("Podaj id produktu:");
        Long productId = scanner.nextLong();
        Optional<Product> product = productService.getById(productId);
        product.ifPresent(manage);

        if (!product.isPresent()) {
            System.out.println("Nie ma takiego produktu, podaj prawidłowe id.");
        }else{
            System.out.println(successMessage);
        }
        start();
    }


    private static void showProducts() {
        productService.showListOfProducts();
    }

    private static void initialMenu() {
        System.out.println("Wybierz opcję: ");
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
        System.out.println("3. Zakończ");

        Integer choice = scanner.nextInt();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Wybrałeś nieprawidłową opcję");
        }
        start();
    }

    private static void login() {
        System.out.println("Podaj login:");
        String login = scanner.next();
        System.out.println("Podaj hasło:");
        String password = scanner.next();
        boolean ifSuccess = userService.login(login, password);
        if (ifSuccess) {
            System.out.println("Udało się zalogować");
        } else {
            System.out.println("Nie udało się zalogować");
        }
    }

    private static void register() {
        System.out.println("Podaj login:");
        String login = scanner.next();
        System.out.println("Podaj hasło:");
        String password = scanner.next();
        boolean ifSuccess = userService.registration(login, password);
        if (ifSuccess) {
            System.out.println("Udało się zarejestrować");
        } else {
            System.out.println("Nie udało się zarejestrować");
        }
    }
}