package tech.calista.traveler.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import tech.calista.traveler.Traveler;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public abstract class SQLManager {
    private final Traveler traveler;

    // MySQL
    private String username;
    private String password;
    private String host;
    private int port;
    private String database;

    // SQLite
    private boolean isSQLite = false;
    private File databaseFile;

    // Database
    private HikariDataSource hikariDataSource;

    protected SQLManager(Traveler traveler) {
        this.traveler = traveler;
    }


    public void connect() {
        HikariConfig hikariConfig = new HikariConfig();

        if (isSQLite) {
            hikariConfig.setJdbcUrl("jdbc:sqlite:" + databaseFile.getAbsolutePath());
            hikariConfig.setPoolName("Traveler SQLite Pool");
        } else {
            hikariConfig.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);
            hikariConfig.setPoolName("Traveler MySQL Pool");
        }

        hikariDataSource = new HikariDataSource(hikariConfig);
    }


    public void disconnect() {
        if (hikariDataSource != null) {
            hikariDataSource.close();
        }
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS traveler_data (uuid CHAR(36), VARCHAR(255) discovery, PRIMARY KEY (uuid, discovery))";
        updateAsync(sql);
    }

    @SneakyThrows
    private PreparedStatement initStatement(String query, Object... objects) {
        try (PreparedStatement preparedStatement = hikariDataSource.getConnection().prepareStatement(query)) {
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }

            return preparedStatement;
        }
    }

    public ResultSet query(String query, Object... objects) {
        try (PreparedStatement preparedStatement = initStatement(query, objects)) {
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(String query, Object... objects) {
        try (PreparedStatement preparedStatement = initStatement(query, objects)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAsync(String query, Object... objects) {
        traveler.getServer().getScheduler().runTaskAsynchronously(traveler, () -> update(query, objects));
    }

    public CompletableFuture<ResultSet> queryAsync(String query, Object... objects) {
        return CompletableFuture.supplyAsync(() -> query(query, objects));
    }
}
