package tech.calista.traveler.database.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Query {
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS traveler_data (uuid CHAR(36), VARCHAR(255) discovery, PRIMARY KEY (uuid, discovery))"),
    SELECT_USER("SELECT * FROM traveler_data WHERE uuid = ?"),
    INSERT_USER("INSERT INTO traveler_data (uuid, dicovery) VALUES (?, ?)"),
    DELETE_USER("DELETE FROM traveler_data WHERE uuid = ?");
    ;


    private final String query;
}
