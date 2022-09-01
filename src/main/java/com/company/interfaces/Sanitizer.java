package com.company.interfaces;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public interface Sanitizer {

    //hashmap to store offline conversions
    //Need to make singleton
    HashMap<String, String> replaceMap = new HashMap<>();

    public String defaultCharacter();

    void defineReplaceMap();

    void addReplacement(char in, char replacement);

    public String sanitize(String input);


}
