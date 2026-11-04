package com.dheeraj.url_shortener.generator;

import java.util.Arrays;

public class Base62Encoder {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();
    private static final int[] DECODE_MAP = new int[Character.MAX_VALUE + 1];
    static {
        Arrays.fill(DECODE_MAP, -1);
        for(int i=0;i<BASE;i++) { DECODE_MAP[ALPHABET.charAt(i)] = i; }
    }

    private Base62Encoder(){
        throw new UnsupportedOperationException("Utility class");
    }

    public static String encode(long value) {
        if(value < 0) {
            throw new IllegalArgumentException("value must be Positive");
        }
        if(value == 0L) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        long current = value;
        while(current > 0) {
            int reminder = (int)(current % BASE);
            sb.append(ALPHABET.charAt(reminder));
            current /= BASE;
        }
        return sb.toString();
    }
    public static long decode(String encoded) {
        if(encoded == null || encoded.isEmpty()) {
            throw new IllegalArgumentException("value cannot be null or empty");
        }
        long result = 0L;
        for(int i=0;i<encoded.length();i++){
            char ch = encoded.charAt(i);

            if(ch>=DECODE_MAP.length || DECODE_MAP[ch] == -1){
                throw new IllegalArgumentException("value contains invalid character");
            }

            int digitValue = DECODE_MAP[ch];
            if(result>(Long.MAX_VALUE - digitValue)/BASE) {
                throw new IllegalArgumentException("value too large");
            }
            result = result * BASE+digitValue;
        }
        return result;
    }
}