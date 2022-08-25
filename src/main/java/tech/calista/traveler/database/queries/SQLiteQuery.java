package tech.calista.traveler.database.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SQLiteQuery {
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS traveler_users (uuid CHAR(36), discovery VARCHAR(255), PRIMARY KEY(uuid, discovery))"),
    ;


    private final String sql;
}
