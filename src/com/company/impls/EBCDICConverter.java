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
        defineOfflineMap();
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
        characterMap.put("\n", 15);
        characterMap.put(" ", 32);
        characterMap.put("¢", 74);
        characterMap.put(".", 75);
        characterMap.put("<", 76);
        characterMap.put("(", 77);
        characterMap.put("+", 78);
        characterMap.put("|", 79);
        characterMap.put("!", 90);
        characterMap.put("$", 91);
        characterMap.put("*", 92);
        characterMap.put(")", 93);
        characterMap.put(";", 94);
        characterMap.put("¬", 95);
        characterMap.put("-", 96);
        characterMap.put("/", 97);
        characterMap.put("¦", 106);
        characterMap.put(",", 107);
        characterMap.put("%", 108);
        characterMap.put("_", 109);
        characterMap.put(">", 110);
        characterMap.put("?", 111);
        characterMap.put("`", 121);
        characterMap.put(":", 122);
        characterMap.put("#", 123);
        characterMap.put("@", 124);
        characterMap.put("'", 125);
        characterMap.put("=", 126);
        characterMap.put("\"", 127);
        characterMap.put("a", 129);
        characterMap.put("b", 130);
        characterMap.put("c", 131);
        characterMap.put("d", 132);
        characterMap.put("e", 133);
        characterMap.put("f", 134);
        characterMap.put("g", 135);
        characterMap.put("h", 136);
        characterMap.put("i", 137);
        characterMap.put("j", 145);
        characterMap.put("k", 146);
        characterMap.put("l", 147);
        characterMap.put("m", 148);
        characterMap.put("n", 149);
        characterMap.put("o", 150);
        characterMap.put("p", 151);
        characterMap.put("q", 152);
        characterMap.put("r", 153);
        characterMap.put("~", 161);
        characterMap.put("s", 162);
        characterMap.put("t", 163);
        characterMap.put("u", 164);
        characterMap.put("v", 165);
        characterMap.put("w", 166);
        characterMap.put("x", 167);
        characterMap.put("y", 168);
        characterMap.put("z", 169);
        characterMap.put("{", 192);
        characterMap.put("A", 193);
        characterMap.put("B", 194);
        characterMap.put("C", 195);
        characterMap.put("D", 196);
        characterMap.put("E", 197);
        characterMap.put("F", 198);
        characterMap.put("G", 199);
        characterMap.put("H", 200);
        characterMap.put("I", 201);
        characterMap.put("}", 208);
        characterMap.put("J", 209);
        characterMap.put("K", 210);
        characterMap.put("L", 211);
        characterMap.put("M", 212);
        characterMap.put("N", 213);
        characterMap.put("O", 214);
        characterMap.put("P", 215);
        characterMap.put("Q", 216);
        characterMap.put("R", 217);
        characterMap.put("\\", 224);
        characterMap.put("S", 226);
        characterMap.put("T", 227);
        characterMap.put("U", 228);
        characterMap.put("V", 229);
        characterMap.put("W", 230);
        characterMap.put("X", 231);
        characterMap.put("Y", 232);
        characterMap.put("Z", 233);
        characterMap.put("0", 240);
        characterMap.put("1", 241);
        characterMap.put("2", 242);
        characterMap.put("3", 243);
        characterMap.put("4", 244);
        characterMap.put("5", 245);
        characterMap.put("6", 246);
        characterMap.put("7", 247);
        characterMap.put("8", 248);
        characterMap.put("9", 249);
    }

    @Override
    public ArrayList<Integer> convert(String input) {
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i < input.length(); i++)
        {
            char ch = input.charAt(i);
            Integer adder = characterMap.get("" + ch);
            if (adder == null)
            {
                ArrayList<Integer> san = sanitize(ch);
                //add arbitrary length string to conversion
                for (Integer j : san)
                {
                    intList.add(j);
                }
            } else {
                intList.add(adder);
            }
        }
        return intList;
    }

    @Override
    public String toBinary(String input) {
        ArrayList<Integer> list = convert(input);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length(); i ++)
        {
            String s = Integer.toBinaryString(list.get(i));
            while (s.length() < 8)
            {
                s = "0" + s;
            }
            str.append(s);
        }
        return str.toString();
    }

    @Override
    public String decode(String input) {
       ArrayList<Integer> decoded = new ArrayList<>();
       for (int i = 0; i < input.length(); i += 8)
       {
           Integer num = Integer.parseInt(input.substring(i, i+8),2);
           decoded.add(num);
       }
        return decode(decoded);
    }

    @Override
    public String decode(ArrayList<Integer> input) {
        String decoded = "";
        HashMap<Integer, String> backwards = new HashMap<>();
        //convert backwards
        Set<Map.Entry<String,Integer>> entrySet = characterMap.entrySet();
        for (Map.Entry e : entrySet)
        {
            backwards.put((Integer)e.getValue(), (String)e.getKey());
        }
        for (Integer in : input)
        {
            decoded += backwards.get(in);
        }
        return decoded;
    }

    //on load define offline map to match characters
    @Override
    public void defineOfflineMap() {

    }

    @Override
    public String endpoint() {
        return "jdbc:mysql://localhost:3306/EBCDICtable";
    }

    @Override
    public String driver() {
        return "org.gjt.mm.mysql.Driver";
    }

    @Override
    public Connection getConnection() {
        try
        {
            Class.forName(driver());
        } catch (ClassNotFoundException e)
        {
            System.out.println("Driver not found for SQL connection");
            return null;
        }

        try {
            return DriverManager.getConnection(endpoint(), "root", "password");
        } catch (SQLException e)
        {
            System.out.println("Unable to connect to database");
            return null;
        }
    }

    @Override
    public ArrayList<Integer> sanitize(char input) {

        //start call to offline map
        String index = offlineMap.get(input + "");
        //if map is empty, reach out to database
        if (index == null)
        {

        }

        //if database returns null prompt response from user and save to database
        if (index == null)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please input conversion string for character \"" + input + "\"");

            //space to log character that was missing
            String slant = sc.nextLine();
            offlineMap.put(input + "",slant);
            index = slant;
        }
        return convert(index);
    }

    @Override
    public String commit(String key, String replacement) {
        return null;
    }

    @Override
    public String retrieve(String key) {
        return null;
    }
}
