package tech.calista.traveler;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.codemc.worldguardwrapper.WorldGuardWrapper;
import org.codemc.worldguardwrapper.flag.IWrappedFlag;
import tech.calista.traveler.database.SQLManager;
import tech.calista.traveler.database.credentials.Credentials;
import tech.calista.traveler.database.impl.MySQL;
import tech.calista.traveler.database.impl.SQLite;
import tech.calista.traveler.listeners.ConnectionListener;
import tech.calista.traveler.listeners.DiscoveryListener;
import tech.calista.traveler.listeners.WalkListener;
import tech.calista.traveler.user.UserDao;
import tech.calista.traveler.user.UserRepository;

@Getter
public final class Traveler extends JavaPlugin {

    private WorldGuardWrapper worldGuardWrapper;
    private IWrappedFlag<String> flag;

    private UserRepository userRepository;
    private UserDao userDao;

    private SQLManager sqlManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        hookWorldGuard();

        loadDatabase();
        loadInstances();

        getServer().getPluginManager().registerEvents(new WalkListener(this), this);
        getServer().getPluginManager().registerEvents(new ConnectionListener(this), this);
        getServer().getPluginManager().registerEvents(new DiscoveryListener(), this);

    }

    private void loadDatabase() {
        String databaseType = getConfig().getString("database.type");

        if (databaseType == null || databaseType.isEmpty() || databaseType.equalsIgnoreCase("sqlite")) {
            sqlManager = new SQLite(this);
            return;
        }

        if (databaseType.equalsIgnoreCase("mysql")) {
            sqlManager = new MySQL(this, new Credentials(
                    getConfig().getString("database.username"),
                    getConfig().getString("database.password"),
                    getConfig().getString("database.host"),
                    getConfig().getInt("database.port"),
                    getConfig().getString("database.database")
            ));
        }
    }

    private void loadInstances() {
        userDao = new UserDao(sqlManager);
        userRepository = new UserRepository();
    }

    private void hookWorldGuard() {
        worldGuardWrapper = WorldGuardWrapper.getInstance();
        flag = worldGuardWrapper.registerFlag("zone", String.class, "Unknown").orElse(null);
    }


    @Override
    public void onDisable() {
        sqlManager.disconnect();
    }
}
