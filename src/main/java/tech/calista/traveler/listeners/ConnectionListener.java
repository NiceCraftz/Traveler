package tech.calista.traveler.listeners;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tech.calista.traveler.Traveler;
import tech.calista.traveler.user.User;

@RequiredArgsConstructor
public class ConnectionListener implements Listener {
    private final Traveler traveler;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        User user = traveler.getUserDao().read(player.getUniqueId());

        if (user == null) {
            user = new User(player.getUniqueId());
            traveler.getUserDao().create(user.getUuid(), user);
        }

        traveler.getUserRepository().create(user.getUuid(), user);
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        traveler.getUserRepository().delete(player.getUniqueId());
    }
}
