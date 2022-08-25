package tech.calista.traveler.user;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import tech.calista.traveler.dao.Dao;
import tech.calista.traveler.database.SQLManager;
import tech.calista.traveler.database.queries.Query;

import java.sql.ResultSet;
import java.util.UUID;

@RequiredArgsConstructor
public class UserDao implements Dao<UUID, User> {
    private final SQLManager sqlManager;

    @Override
    public void create(UUID key, User value) {
        sqlManager.updateAsync(Query.INSERT_USER.getQuery(), key.toString());
    }

    @Override
    @SneakyThrows
    public User read(UUID key) {
        try (ResultSet resultSet = sqlManager.query(Query.SELECT_USER.getQuery(), key.toString())) {
            int id = resultSet.getInt("id");
            User user = new User(id, key);

            while (resultSet.next()) {
                user.addDiscovery(resultSet.getString("discovery"));
            }
        }


        return null;
    }

    @Override
    public void update(UUID key, User value) {

    }

    @Override
    public void delete(UUID key) {

    }
}
