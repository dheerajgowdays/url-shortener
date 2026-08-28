package com.dheeraj.urlshortener.util;

public final class Base62Encoder{
	private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BASE = ALPHABET.length();

	private Base62Encoder(){}
	public static String encode(long number){
	
		if(number < 0){
			throw new IllegalArgumentException("Number must be non-negative");
		}
		if(number == 0){
			return String.valueOf(ALPHABET.charAt(0));
		}
		StringBuilder result = new StringBuilder();

		while(number >0){
			int remainder = (int) (number % BASE);
			result.append(ALPHABET.charAt(remainder));
			number /= BASE;
		}
	return result.reverse().toString();
	}
}
