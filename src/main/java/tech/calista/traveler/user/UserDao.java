package tech.calista.traveler.user;

import lombok.RequiredArgsConstructor;
import tech.calista.traveler.dao.Dao;
import tech.calista.traveler.database.SQLManager;

import java.util.UUID;

@RequiredArgsConstructor
public class UserDao implements Dao<UUID, User> {
    private final SQLManager sqlManager;

    @Override
    public void create(UUID key, User value) {
        String sql = "INSERT INTO traveler_users (uuid) VALUES (?)";
        sqlManager.updateAsync(sql, key.toString());
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
