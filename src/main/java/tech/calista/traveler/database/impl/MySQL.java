package tech.calista.traveler.database.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import tech.calista.traveler.Traveler;
import tech.calista.traveler.database.SQLManager;
import tech.calista.traveler.database.credentials.Credentials;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    @Override
    public void createTable() {
        update("CREATE TABLE IF NOT EXISTS traveler_users (id INT NOT NULL AUTO_INCREMENT, uuid CHAR(36), PRIMARY KEY (id))");
        update("CREATE TABLE IF NOT EXISTS traveler_discoveries (id INT, discovery VARCHAR(255), PRIMARY KEY (id, discovery))");
    }

    @Override
    public ResultSet query(String sql, Object... params) {
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(String sql, Object... params) {
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
