package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	 EBCDICConverter ebcdicConverter = new EBCDICConverter();

     System.out.println("Testing ASCII table");
     System.out.println(ebcdicConverter.convert("", true));
     System.out.println("Test UTF-8 table");

     String UTF = "This is a test String! UTF-8 characters should not convert over ^";
     String binary = ebcdicConverter.toCompliantBinaryString(UTF, false);
     List<Integer> ar = ebcdicConverter.convert(UTF, false);
     System.out.println(binary);
     System.out.println(ar);
     System.out.println(ebcdicConverter.decode(binary));
     System.out.println(ebcdicConverter.decode((ArrayList<Integer>) ar));
     System.out.println(ebcdicConverter.decodePlain((ArrayList<Integer>) ar));


    }
}
