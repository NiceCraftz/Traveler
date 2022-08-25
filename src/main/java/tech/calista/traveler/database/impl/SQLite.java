package tech.calista.traveler.database.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import tech.calista.traveler.Traveler;
import tech.calista.traveler.database.SQLManager;

import java.io.File;

public class SQLite extends SQLManager {

    public SQLite(Traveler traveler) {
        super(traveler);
    }

    @Override
    @SneakyThrows
    public void connect() {
        File databaseFile = new File(getTraveler().getDataFolder(), "database.db");
        if(!databaseFile.exists()) {
            databaseFile.getParentFile().mkdir();
            databaseFile.createNewFile();
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlite:" + databaseFile.getAbsolutePath());
        hikariConfig.setPoolName("Traveler SQLite Pool");

        setDataSource(new HikariDataSource(hikariConfig));
    }
}
