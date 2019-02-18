package shop;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> registeredUsers = new ArrayList<>();

    public boolean registration(String name, String password) {
        boolean notExist = registeredUsers.stream()
                .map(User::getName)
                .noneMatch(name::equals);
        if (notExist) {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setId(getNextId());
            registeredUsers.add(user);
        }
        return notExist;
    }

    private Long getNextId() {
        Long id = registeredUsers.stream()
                .map(User::getId)
                .max(Long::compareTo)
                .orElse(0L);
        return ++id;
    }

    public boolean login(String name, String password){
        User user = registeredUsers.stream()
                .filter(u -> u.getName().equals(name) && u.getPassword().equals(password))
                .findAny()
                .orElse(null);
        MainShop.user = user;
        return user != null;
    }
}