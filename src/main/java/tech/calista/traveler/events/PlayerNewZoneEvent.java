package tech.calista.traveler.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import tech.calista.traveler.user.User;

@Getter
public class PlayerNewZoneEvent extends PlayerEvent {
    private final User user;
    private final String discoveredZone;
    private static final HandlerList HANDLERS = new HandlerList();


    public PlayerNewZoneEvent(@NotNull Player who, User user, String discoveredZone) {
        super(who);
        this.user = user;
        this.discoveredZone = discoveredZone;

        user.getDiscoveries().add(discoveredZone);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
