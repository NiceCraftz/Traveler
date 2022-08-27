package tech.calista.traveler.user;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import tech.calista.traveler.dao.Dao;
import tech.calista.traveler.database.SQLManager;

import java.util.UUID;

@RequiredArgsConstructor
public class UserDao implements Dao<UUID, User> {

    private final SQLManager sqlManager;

    @Override
    public void create(UUID key, User value) {
        if (value.getDiscoveries().isEmpty()) return;

        String sql = "INSERT INTO traveler_data (uuid, discovery) VALUES (?, ?) ON DUPLICATE KEY UPDATE discovery = discovery, uuid = uuid";
        for (String discovery : value.getDiscoveries()) {
            sqlManager.updateAsync(sql, key.toString(), discovery);
        }
    }

    @Override
    @SneakyThrows
    public User read(UUID key) {
        String sql = "SELECT * FROM traveler_data WHERE uuid = ?";
        return new User(key); // TODO: Implement
    }

    @Override
    public void update(User value) {
    }

    @Override
    public void delete(UUID key) {
    }
}
