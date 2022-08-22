package com.company;

import java.util.ArrayList;
import java.util.List;

public class EBCDICConverter {

    private CharacterLibrary cl;

    EBCDICConverter() {
        //define character set
        this.cl = new CharacterLibrary();

        cl.addChar("\n", 15);
        cl.addChar(" ", 32);
        cl.addChar("¢", 74);
        cl.addChar(".", 75);
        cl.addChar("<", 76);
        cl.addChar("(", 77);
        cl.addChar("+", 78);
        cl.addChar("|", 79);
        cl.addChar("!", 90);
        cl.addChar("$", 91);
        cl.addChar("*", 92);
        cl.addChar(")", 93);
        cl.addChar(";", 94);
        cl.addChar("¬", 95);
        cl.addChar("-", 96);
        cl.addChar("/", 97);
        cl.addChar("¦", 106);
        cl.addChar(",", 107);
        cl.addChar("%", 108);
        cl.addChar("_", 109);
        cl.addChar(">", 110);
        cl.addChar("?", 111);
        cl.addChar("`", 121);
        cl.addChar(":", 122);
        cl.addChar("#", 123);
        cl.addChar("@", 124);
        cl.addChar("'", 125);
        cl.addChar("=", 126);
        cl.addChar("\"", 127);
        cl.addChar("a", 129);
        cl.addChar("b", 130);
        cl.addChar("c", 131);
        cl.addChar("d", 132);
        cl.addChar("e", 133);
        cl.addChar("f", 134);
        cl.addChar("g", 135);
        cl.addChar("h", 136);
        cl.addChar("i", 137);
        cl.addChar("j", 145);
        cl.addChar("k", 146);
        cl.addChar("l", 147);
        cl.addChar("m", 148);
        cl.addChar("n", 149);
        cl.addChar("o", 150);
        cl.addChar("p", 151);
        cl.addChar("q", 152);
        cl.addChar("r", 153);
        cl.addChar("~", 161);
        cl.addChar("s", 162);
        cl.addChar("t", 163);
        cl.addChar("u", 164);
        cl.addChar("v", 165);
        cl.addChar("w", 166);
        cl.addChar("x", 167);
        cl.addChar("y", 168);
        cl.addChar("z", 169);
        cl.addChar("{", 192);
        cl.addChar("A", 193);
        cl.addChar("B", 194);
        cl.addChar("C", 195);
        cl.addChar("D", 196);
        cl.addChar("E", 197);
        cl.addChar("F", 198);
        cl.addChar("G", 199);
        cl.addChar("H", 200);
        cl.addChar("I", 201);
        cl.addChar("}", 208);
        cl.addChar("J", 209);
        cl.addChar("K", 210);
        cl.addChar("L", 211);
        cl.addChar("M", 212);
        cl.addChar("N", 213);
        cl.addChar("O", 214);
        cl.addChar("P", 215);
        cl.addChar("Q", 216);
        cl.addChar("R", 217);
        cl.addChar("\\", 224);
        cl.addChar("S", 226);
        cl.addChar("T", 227);
        cl.addChar("U", 228);
        cl.addChar("V", 229);
        cl.addChar("W", 230);
        cl.addChar("X", 231);
        cl.addChar("Y", 232);
        cl.addChar("Z", 233);
        cl.addChar("0", 240);
        cl.addChar("1", 241);
        cl.addChar("2", 242);
        cl.addChar("3", 243);
        cl.addChar("4", 244);
        cl.addChar("5", 245);
        cl.addChar("6", 246);
        cl.addChar("7", 247);
        cl.addChar("8", 248);
        cl.addChar("9", 249);

        cl.setNull_char(0);
        cl.setNon_compliant_marker(254);
    }

    public List<Integer> convert(String s, boolean comply) {
        List<Integer> conversion = cl.convert(s, comply);
        return conversion;
    }

    public String toNonCompliantBinaryString(String s) {
        return null;
    }

    public String toCompliantBinaryString(String s, boolean comply) {
        ArrayList<Integer> list = (ArrayList<Integer>) convert(s, comply);
        String bin = "";
        for (Integer i : list) {
            String seg = Integer.toBinaryString(i);
            //normalize seg length
            while (seg.length() != 8) {
                seg = "0" + seg;
            }
            bin += seg;
        }
        return bin;
    }

    public String decode(ArrayList<Integer> ar)
    {
        return cl.decode(ar);
    }

    public String decode(String binary)
    {
        return cl.decode(binary);
    }

    public String decodePlain(ArrayList<Integer> ar)
    {

        return cl.decodePlain(ar);
    }

}
