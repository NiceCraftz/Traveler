package tech.calista.traveler.database.impl;

import tech.calista.traveler.Traveler;
import tech.calista.traveler.database.SQLManager;
import tech.calista.traveler.database.credentials.Credentials;

public class MySQL extends SQLManager {
    public MySQL(Traveler traveler, Credentials credentials) {
        super(traveler);

        setUsername(credentials.getUsername());
        setPassword(credentials.getPassword());
        setHost(credentials.getHost());
        setPort(credentials.getPort());
        setDatabase(credentials.getDatabase());
    }
}
