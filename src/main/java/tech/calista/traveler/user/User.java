package tech.calista.traveler.user;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class User {
    private final UUID uuid;
    private final Set<String> discoveries;

    public User(UUID uuid) {
        this(uuid, null);
    }

    public User(UUID uuid, Collection<String> discoveries) {
        this.uuid = uuid;
        this.discoveries = Sets.newHashSet();

        if (discoveries != null) {
            this.discoveries.addAll(discoveries);
        }
    }
}
