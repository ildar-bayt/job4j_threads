package ru.job4j.io;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();

    public synchronized boolean add(User user) {
        return storage.putIfAbsent(user.getId(), user) != user;
    }

    public synchronized boolean update(User user) {
        User oldUser = storage.get(user.getId());
        if (oldUser == null) {
            return false;
        }
        oldUser.setAmount(user.getAmount());
        return true;
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user.getId()) != user;
    }

    public synchronized boolean transfer(int fromId, int told, int amount) {
        User fromUser = storage.get(fromId);
        User toUser = storage.get(told);

        if (fromUser == null || toUser == null || fromUser.getAmount() < amount) {
            return false;
        }

        fromUser.setAmount(fromUser.getAmount() - amount);
        toUser.setAmount(toUser.getAmount() + amount);

        return true;
    }
}
