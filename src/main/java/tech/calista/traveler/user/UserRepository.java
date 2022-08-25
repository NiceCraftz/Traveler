package tech.calista.traveler.user;

import com.google.common.collect.Maps;
import tech.calista.traveler.repository.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserRepository implements Repository<UUID, User> {
    private final Map<UUID, User> userMap = Maps.newHashMap();

    @Override
    public void create(UUID key, User value) {
        userMap.put(key, value);
    }

    @Override
    public Optional<User> read(UUID key) {
        return Optional.ofNullable(userMap.get(key));
    }

    @Override
    public void update(UUID key, User value) {
        userMap.replace(key, value);
    }

    @Override
    public User delete(UUID key) {
        return userMap.remove(key);
    }
}
