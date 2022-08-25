package com.company.interfaces;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public interface Converter {

    //Need to make singleton
    HashMap<String, Integer> characterMap = new HashMap<>();

    void defineMap();

    ArrayList<Integer> convert(String input);

    String toBinary(String binary);

    String decode(String input);

    String decode(ArrayList<Integer> input);


}
