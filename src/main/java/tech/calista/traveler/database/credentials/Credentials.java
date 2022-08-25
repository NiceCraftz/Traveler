package tech.calista.traveler.database.credentials;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;

@RequiredArgsConstructor
@Getter
public class Credentials {
    private final String username;
    private final String password;

    private final String host;
    private final int port;

    private final String database;


    public static Credentials fromConfig(FileConfiguration fileConfiguration) {
        return new Credentials(
                fileConfiguration.getString("database.username"),
                fileConfiguration.getString("database.password"),
                fileConfiguration.getString("database.host"),
                fileConfiguration.getInt("database.port"),
                fileConfiguration.getString("database.database")
        );
    }
}
