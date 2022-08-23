package com.company;

import com.company.impls.EBCDICConverter;

public class Main {

    public static void main(String[] args) {
	 EBCDICConverter ebcdicConverter = new EBCDICConverter();

     System.out.println(ebcdicConverter.convert("This is a test input"));
     System.out.println(ebcdicConverter.toBinary("This is a double test input"));



    }
}
