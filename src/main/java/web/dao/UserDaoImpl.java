package web.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import web.model.User;

@Component
public class UserDaoImpl implements UserDao{

    private List<User> users;
    private static Long USER_COUNT = 0L;

    {
        users = new ArrayList<>();

        users.add(new User(++USER_COUNT, "Vladimir", "Putin", (byte) 70, "putin@gov.ru"));
        users.add(new User(++USER_COUNT, "Sergey", "Lavrov", (byte) 72, "lavrov@gov.ru"));
        users.add(new User(++USER_COUNT, "Sergey", "Shoigu", (byte) 67, "shoigu@gov.ru"));
        users.add(new User(++USER_COUNT, "Sergey", "Narishkin", (byte) 68, "narishkin@gov.ru"));
        users.add(new User(++USER_COUNT, "Igor", "Shuvalov", (byte) 56, "shuvalov@gov.ru"));
    }

    @Override
    public List<User> listUsers() {
        return users;
    }

    @Override
    public User showUser(Long id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

    @Override
    public void removeUser(Long id) {
        users.removeIf(user -> user.getId() == id);
    }

    @Override
    public void updateUser(Long id, User user) {
        User updateUser = showUser(id);
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setAge(user.getAge());
        updateUser.setEmail(user.getEmail());
    }
}
