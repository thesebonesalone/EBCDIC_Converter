package com.company.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface Converter {

    HashMap<String, Integer> characterMap = new HashMap<>();

    void defineMap();

    ArrayList<Integer> convert(String input);

    String toBinary(String binary);


}
