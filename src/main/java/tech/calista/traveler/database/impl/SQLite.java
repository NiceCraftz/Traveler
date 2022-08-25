package tech.calista.traveler.database.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import tech.calista.traveler.Traveler;
import tech.calista.traveler.database.SQLManager;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLite extends SQLManager {

    public SQLite(Traveler traveler) {
        super(traveler);
    }

    @Override
    @SneakyThrows
    public void connect() {
        File databaseFile = new File(getTraveler().getDataFolder(), "database.db");
        if (!databaseFile.exists()) {
            databaseFile.getParentFile().mkdir();
            databaseFile.createNewFile();
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:sqlite:" + databaseFile.getAbsolutePath());
        hikariConfig.setPoolName("Traveler SQLite Pool");

        setDataSource(new HikariDataSource(hikariConfig));
    }

    @Override
    public void createTable() {
        update("CREATE TABLE IF NOT EXISTS traveler_users (id INTEGER PRIMARY KEY AUTOINCREMENT, uuid CHAR(36))");
        update("CREATE TABLE IF NOT EXISTS traveler_discoveries (id INTEGER, discovery VARCHAR(255), PRIMARY KEY (id, discovery))");
    }

    @Override
    public ResultSet query(String sql, Object... params) {
        return null;
    }

    @Override
    public void update(String sql, Object... params) {
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
