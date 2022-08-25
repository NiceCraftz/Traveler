package tech.calista.traveler.listeners;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.codemc.worldguardwrapper.WorldGuardWrapper;
import org.codemc.worldguardwrapper.region.IWrappedRegion;
import tech.calista.traveler.Traveler;
import tech.calista.traveler.events.PlayerNewZoneEvent;
import tech.calista.traveler.user.User;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class WalkListener implements Listener {
    private final Traveler traveler;

    @EventHandler
    public void onWalk(PlayerMoveEvent e) {
        if (e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) {
            return;
        }

        Player player = e.getPlayer();
        WorldGuardWrapper worldGuardWrapper = traveler.getWorldGuardWrapper();
        Set<IWrappedRegion> iWrappedRegions = worldGuardWrapper.getRegions(player.getLocation());

        if (iWrappedRegions.isEmpty()) {
            return;
        }

        Optional<User> userOptional = traveler.getUserRepository().read(player.getUniqueId());

        if (userOptional.isEmpty()) {
            return;
        }

        User user = userOptional.get();

        for (IWrappedRegion iWrappedRegion : iWrappedRegions) {
            Optional<String> zoneOptional = iWrappedRegion.getFlag(traveler.getFlag());

            if (zoneOptional.isEmpty()) {
                continue;
            }

            String zone = zoneOptional.get();

            if (user.getDiscoveries().contains(zone)) {
                continue;
            }

            Bukkit.getPluginManager().callEvent(new PlayerNewZoneEvent(player, user, zone));
        }

    }
}
