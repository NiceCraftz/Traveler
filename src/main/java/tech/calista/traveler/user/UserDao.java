package tech.calista.traveler.user;

import tech.calista.traveler.dao.Dao;

import java.util.UUID;

public class UserDao implements Dao<UUID, User> {

    @Override
    public void create(UUID key, User value) {

    }

    @Override
    public User read(UUID key) {
        return null;
    }

    @Override
    public void update(UUID key, User value) {

    }

    @Override
    public void delete(UUID key) {

    }
}
