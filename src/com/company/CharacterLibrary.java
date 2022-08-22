package com.company;

import com.sun.jdi.request.InvalidRequestStateException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class CharacterLibrary {

    CharacterLibrary() {
        character_hash = new HashMap<>();
    }

    private Integer null_char;

    public Integer getNon_compliant_marker() {
        return non_compliant_marker;
    }

    public void setNon_compliant_marker(Integer non_compliant_marker) {
        this.non_compliant_marker = non_compliant_marker;
    }

    private Integer non_compliant_marker;

    private HashMap<String, Integer> character_hash;

    //getters and setters

    public Integer getNull_char() {
        return null_char;
    }

    public void setNull_char(Integer null_char) {
        this.null_char = null_char;
    }

    public HashMap<String, Integer> getCharacter_hash() {
        return character_hash;
    }

    public void setCharacter_hash(HashMap<String, Integer> character_hash) {
        this.character_hash = character_hash;
    }

    //general functions

    public void addChar(String s, Integer i)
    {
        character_hash.put(s,i);
    }

    public List<Integer> convert(String input, boolean comply) {

        ArrayList<Integer> splitter = new ArrayList<>();
        for (String c : input.split(""))
        {
            //check against character map for character
            Integer i = this.character_hash.get(c);
            //check if character exists in set
            if (i != null)
            {
                //if character exists add it to the list to convert to binary
                splitter.add(i);
            } else {
                //convert non compliant to either null character or to non complaint marker for decode on return
                System.out.println("Non compliant character is: \"" + c + "\"");
                if (comply)
                {
                    splitter.add(null_char);
                } else {
                    //add marker for decode
                    splitter.add(non_compliant_marker);
                    //encode non compliant character as integer representation of UTF-8 binary
                    ArrayList<Integer> non = (ArrayList<Integer>)encodeNonCompliant(c);
                    for (Integer nums : non)
                    {
                        splitter.add(nums);
                    }
                    //add closing marker for decode
                    splitter.add(non_compliant_marker);
                }
            }
        }
        return splitter;
    }

    public List<Integer> encodeNonCompliant(String c)
    {
        char non = c.toCharArray()[0];

        //encode character as binary
        String binary = Integer.toBinaryString(non);
        //encode binary as integer string
        String i = Integer.parseInt(binary, 2) + "";
        return convert(i, false);
    }

    public String decodePlain(ArrayList<Integer> list)
    {
        String ret = "";
        for(int i = 0; i<list.size(); i++)
        {
            Integer ch = list.get(i);
            if (ch != non_compliant_marker)
            {
                ret += decodeCharacter(ch);
            } else {
                ret += " ";
            }
        }
        return ret;
    }

    public String decode(ArrayList<Integer> list) {
        String ret = "";
        for (int i = 0; i < list.size(); i ++)
        {
            Integer ch = list.get(i);
            if (ch != non_compliant_marker)
            {
                ret += decodeCharacter(ch);
            } else {
                i += 1;
                String non = "";
                while (list.get(i) != non_compliant_marker)
                {
                    non += decodeCharacter(list.get(i));
                    i ++;
                }
                System.out.println(non);
                //convert int to binary string
                String bin = Integer.toBinaryString(i);
                //convert binary string to unicode character
                char c = (char)Integer.parseInt(bin,2);
                ret += c;
            }
        }
        return ret;
    }

    public String decodeCharacter(Integer i)
    {
        Set<Map.Entry<String, Integer>> entries = character_hash.entrySet();
        for (Map.Entry<String, Integer> entry : entries)
        {
            if (entry.getValue() == i)
            {
                return entry.getKey();
            }
        }

        if (i == null_char)
        {
            return " ";
        }
        return null;
    }

    public String decode(String binary)
    {
        ArrayList<Integer> ar = new ArrayList<>();
        for (int i = 0; i < binary.length(); i += 8)
        {
            String sub = binary.substring(i,i+8);
            Integer j = Integer.parseInt(sub,2);
            ar.add(j);
        }
        System.out.println(ar);
        return decode(ar);
    }
}
