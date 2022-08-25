package tech.calista.traveler;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.codemc.worldguardwrapper.WorldGuardWrapper;
import org.codemc.worldguardwrapper.flag.IWrappedFlag;
import tech.calista.traveler.listeners.WalkListener;

@Getter
public final class Traveler extends JavaPlugin {

    private WorldGuardWrapper worldGuardWrapper;
    private IWrappedFlag<String> flag;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        hookWorldGuard();

        getServer().getPluginManager().registerEvents(new WalkListener(this), this);
    }

    private void hookWorldGuard() {
        worldGuardWrapper = WorldGuardWrapper.getInstance();
        flag = worldGuardWrapper.registerFlag("zone", String.class, "Unknown").orElse(null);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
