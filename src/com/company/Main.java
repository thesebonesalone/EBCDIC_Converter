package com.company;

import com.company.impls.EBCDICConverter;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	 EBCDICConverter ebcdicConverter = EBCDICConverter.getInstance();

     ArrayList<Integer> conversion1 = ebcdicConverter.convert("This is a test input ^^");
     String conversion2 = ebcdicConverter.toBinary("This is a double test input... €");

     System.out.println(conversion1);
     System.out.println(conversion2);

     System.out.println(ebcdicConverter.decode(conversion1));
     System.out.println(ebcdicConverter.decode(conversion2));



    }
}
