package tech.calista.traveler.database.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import tech.calista.traveler.Traveler;
import tech.calista.traveler.database.credentials.Credentials;
import tech.calista.traveler.database.SQLManager;

@Setter
public class MySQL extends SQLManager {
    private final Credentials credentials;

    public MySQL(Traveler traveler, Credentials credentials) {
        super(traveler);
        this.credentials = credentials;
    }

    @Override
    public void connect() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mysql://" + credentials.getHost() + ":" + credentials.getPort() + "/" + credentials.getDatabase());
        hikariConfig.setUsername(credentials.getUsername());
        hikariConfig.setPassword(credentials.getPassword());
        hikariConfig.setPoolName("Traveler MySQL Pool");

        setDataSource(new HikariDataSource(hikariConfig));
    }
}
