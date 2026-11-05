package com.dheeraj.url_shortener.generator;

import org.springframework.stereotype.Component;

@Component
public class IdObfuscator {
    
    private static final long MODULUS = 3_521_614_606_208L;

    private static final long MULTIPLIER = 2_147_483_659L;

    private static final long MOD_INVERSE = 3_007_295_802_787L;

    private static final long OFFSET = 1_000_000_007L;

    public long obfuscate(long id){
        if (id < 0 || id >= MODULUS) {
            throw new IllegalArgumentException("ID outside supported domain: " + id);
        }       

        long x = id;
        long productMod = modMultiply128(MULTIPLIER,x,MODULUS);
        return (productMod + OFFSET) % MODULUS;
    }

    public long reverse(long obfuscatedId){
        if(obfuscatedId <0 || obfuscatedId >= MODULUS){
            throw new IllegalArgumentException("Obfuscated ID out of range [ 0 ,"+MODULUS+"): "+ obfuscatedId);
        }

        long diff = (obfuscatedId - (OFFSET % MODULUS)) % MODULUS;
        if(diff < 0){
            diff += MODULUS;
        }
        return modMultiply128(MOD_INVERSE, diff, MODULUS);
    }

    public static long modMultiply128(long a, long b , long m){
        long high = Math.multiplyHigh(a,b);
        long low = a*b;
        long remainder = 0;
        for(int i=63;i>=0;i--){
            remainder = (remainder << 1) | ((high >>> i) & 1L);
            if(remainder >= m){
                remainder -= m;
            }
        }

        for(int i= 63;i>=0;i--){
            remainder = (remainder << 1) | ((low >>> i) & 1L);
            if(remainder >= m){
                remainder -=m;
            }
        }
        return remainder;
    }

    public static long computeModularInverse(long a, long m){
        long m0=m;
        long y = 0,x =1;

        if(m == 1){
            return 0;
        }

        while(a>1){
            long q = a/m;
            long t = m;

            m=a%m;
            a=t;
            t=y;

            y=x-q*y;
            x=t;
        }
        if(x<0){
            x+=m0;
        }
        return x;
    }
}
