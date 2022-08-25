package tech.calista.traveler.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tech.calista.traveler.events.PlayerNewZoneEvent;

public class DiscoveryListener implements Listener {

    @EventHandler
    public void onDiscovery(PlayerNewZoneEvent e) {
        Player player = e.getPlayer();

    }
}
