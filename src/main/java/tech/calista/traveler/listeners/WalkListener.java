package tech.calista.traveler.listeners;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.codemc.worldguardwrapper.WorldGuardWrapper;
import org.codemc.worldguardwrapper.region.IWrappedRegion;
import tech.calista.traveler.Traveler;

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

        for(IWrappedRegion iWrappedRegion : iWrappedRegions) {
            Optional<String> zone = iWrappedRegion.getFlag(traveler.getFlag());
        }

    }
}
