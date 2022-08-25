package tech.calista.traveler.user;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private final int id;
    private final UUID uuid;
    private final Set<String> discoveries = Sets.newHashSet();

    private boolean update = false;

    public void addDiscovery(String discovery) {
        discoveries.add(discovery);
        update = true;
    }

    public boolean hasDiscovery(String discovery) {
        return discoveries.contains(discovery);
    }
}
