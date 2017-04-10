package ru.apetrov.user;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 10.04.2017.
 */
public class Main {

    /**
     * main.
     * @param args args.
     */
    public static void main(String[] args) {

        User user1 = new User("Tom", 2, new GregorianCalendar(1988, 05, 10));
        User user2 = new User("Tom", 2, new GregorianCalendar(1988, 05, 10));

        Map<User, Object> map = new HashMap<>();

        map.put(user1, "first");
        map.put(user2, "second");

        System.out.println(map.toString());
        System.out.printf("%s %s", "equals = ", user1.equals(user2));
        System.out.printf("%n%s %s", "user1 = ", user1.hashCode());
        System.out.printf("%n%s %s", "user2 = ", user2.hashCode());
    }
}
