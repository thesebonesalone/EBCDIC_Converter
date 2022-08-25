package com.company.interfaces;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public interface Sanitizer {

    //hashmap to store offline conversions
    //Need to make singleton
    HashMap<String, String> offlineMap = new HashMap<>();

    void defineOfflineMap();

    //define api url
    String endpoint();

    //define driver
    String driver();

    //define connection
    Connection getConnection();

    public ArrayList<Integer> sanitize(char input);

    public String commit(String key, String replacement);

    public String retrieve(String key);

}
