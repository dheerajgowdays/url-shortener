package com.dheeraj.url_shortener.generator;

import org.springframework.stereotype.Component;

@Component 
public class ShortCodeGenerator {
    private final Base62Encoder base62Encoder;
    private final IdObfuscator idObfuscator;

    public ShortCodeGenerator(Base62Encoder base62Encoder,IdObfuscator idObfuscator ){
        this.base62Encoder = base62Encoder;
        this.idObfuscator = idObfuscator;
    }

    public String generate(long id){
        long obfuscatedId = idObfuscator.obfuscate(id);
        return base62Encoder.encode(obfuscatedId);
    }
}