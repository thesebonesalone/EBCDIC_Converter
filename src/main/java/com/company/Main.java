package com.company;

import com.company.impls.EBCDICConverter;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String name = "Darwin Núñez";

        EBCDICConverter converter = EBCDICConverter.getInstance();
        System.out.println(name);
        System.out.println(converter.sanitize(name));
    }
}
