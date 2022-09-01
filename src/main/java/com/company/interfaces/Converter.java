package com.company.interfaces;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface Converter {

    //Need to make singleton
    HashSet<String> characterSet = new HashSet<>();

    void defineMap();

}
