package com.company.impls;

import com.company.abstracts.SlantwiseConverter;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class EBCDICConverter extends SlantwiseConverter {

    private EBCDICConverter()
    {
        defineMap();
    }
    private static EBCDICConverter singletonConverter;
   public static EBCDICConverter getInstance()
   {
       if (singletonConverter == null)
       {
           synchronized (EBCDICConverter.class) {
               if (singletonConverter == null) {
                   return new EBCDICConverter();
               }
           }

       }
       return singletonConverter;
   }
    @Override
    public void defineMap() {

        characterSet.add("\n");
        characterSet.add(" ");
        characterSet.add("¢");
        characterSet.add(".");
        characterSet.add("<");
        characterSet.add("(");
        characterSet.add("+");
        characterSet.add("|");
        characterSet.add("!");
        characterSet.add("$");
        characterSet.add("*");
        characterSet.add(")");
        characterSet.add(";");
        characterSet.add("¬");
        characterSet.add("-");
        characterSet.add("/");
        characterSet.add("¦");
        characterSet.add(",");
        characterSet.add("%");
        characterSet.add("_");
        characterSet.add(">");
        characterSet.add("?");
        characterSet.add("`");
        characterSet.add(":");
        characterSet.add("#");
        characterSet.add("@");
        characterSet.add("\'");
        characterSet.add("=");
        characterSet.add("\"");
        characterSet.add("a");
        characterSet.add("b");
        characterSet.add("c");
        characterSet.add("d");
        characterSet.add("e");
        characterSet.add("f");
        characterSet.add("g");
        characterSet.add("h");
        characterSet.add("i");
        characterSet.add("j");
        characterSet.add("k");
        characterSet.add("l");
        characterSet.add("m");
        characterSet.add("n");
        characterSet.add("o");
        characterSet.add("p");
        characterSet.add("q");
        characterSet.add("r");
        characterSet.add("~");
        characterSet.add("s");
        characterSet.add("t");
        characterSet.add("u");
        characterSet.add("v");
        characterSet.add("w");
        characterSet.add("x");
        characterSet.add("y");
        characterSet.add("z");
        characterSet.add("{");
        characterSet.add("A");
        characterSet.add("B");
        characterSet.add("C");
        characterSet.add("D");
        characterSet.add("E");
        characterSet.add("F");
        characterSet.add("G");
        characterSet.add("H");
        characterSet.add("I");
        characterSet.add("}");
        characterSet.add("J");
        characterSet.add("K");
        characterSet.add("L");
        characterSet.add("M");
        characterSet.add("N");
        characterSet.add("O");
        characterSet.add("P");
        characterSet.add("Q");
        characterSet.add("R");
        characterSet.add("\\");
        characterSet.add("S");
        characterSet.add("T");
        characterSet.add("U");
        characterSet.add("V");
        characterSet.add("W");
        characterSet.add("X");
        characterSet.add("Y");
        characterSet.add("Z");
        characterSet.add("0");
        characterSet.add("1");
        characterSet.add("2");
        characterSet.add("3");
        characterSet.add("4");
        characterSet.add("5");
        characterSet.add("6");
        characterSet.add("7");
        characterSet.add("8");
        characterSet.add("9");
        characterSet.add("&");
    }


    @Override
    public String defaultCharacter() {
        return "?";
    }

    //on load define offline map to match characters
    @Override
    public void defineReplaceMap() {

       replaceMap.put("ñ","n");
       replaceMap.put("ú","u");

    }

    @Override
    public void addReplacement(char c, char replacement) {
        replaceMap.put(c + "", replacement + "");
    }


    @Override
    public String sanitize(String input) {

       StringBuilder output = new StringBuilder();
        //replace all found non compliant characters with replacements
        for (int i = 0; i < input.length(); i++)
        {
            String c = input.substring(i,i+1) + "";
            if (!characterSet.contains(c)) {
                if (replaceMap.containsKey(c)) {
                    c = replaceMap.get(c);
                } else {
                    c = defaultCharacter();
                }
            }
            output.append(c);
        }

        return output.toString();
    }
}
