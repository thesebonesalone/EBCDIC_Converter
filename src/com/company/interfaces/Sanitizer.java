package com.company.interfaces;

import java.sql.Connection;
import java.util.HashMap;

public interface Sanitizer {

    //hashmap to store offline conversions
    HashMap<String, String> offlineMap = new HashMap<>();

    //define api url
    String endpoint();

    //define driver
    String driver();

    //define connection
    Connection getConnection();

    public String sanitize(char input);

    public String commit(String key, String replacement);

    public String retrieve(String key);

}
