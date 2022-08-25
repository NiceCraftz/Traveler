package tech.calista.traveler.database;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import tech.calista.traveler.Traveler;

@Getter
@Setter
public abstract class SQLManager {
    private HikariDataSource dataSource;
    private final Traveler traveler;

    public SQLManager(Traveler traveler) {
        this.traveler = traveler;
    }

    public abstract void connect();

    public void disconnect() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    public void reconnect() {
        disconnect();
        connect();
    }


}
